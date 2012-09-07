/*
 *   This file is part of Funky Domino.
 *
 *   Funky Domino is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Funky Domino is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Funky Domino.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gmxteam.funkydomino.activity;

import android.content.Intent;
import android.graphics.Point;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.badlogic.gdx.math.Vector2;
import com.gmxteam.funkydomino.core.ContactManager;
import com.gmxteam.funkydomino.core.SceneLoader;
import com.gmxteam.funkydomino.core.component.factory.ComponentFactory;
import com.gmxteam.funkydomino.core.component.factory.ComponentLoader;
import com.gmxteam.funkydomino.core.component.factory.Components;
import java.io.IOException;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.camera.SmoothCamera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.RepeatingSpriteBackground;
import org.andengine.entity.util.FPSLogger;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.PinchZoomDetector;
import org.andengine.input.touch.detector.PinchZoomDetector.IPinchZoomDetectorListener;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.source.AssetBitmapTextureAtlasSource;
import org.andengine.ui.IGameInterface.OnCreateResourcesCallback;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;
import org.andengine.ui.IGameInterface.OnPopulateSceneCallback;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.debug.Debug;
import org.andengine.util.debug.Debug.DebugLevel;
import org.andengine.util.level.LevelLoader;
import org.andengine.util.preferences.SimplePreferences;
import org.xmlpull.v1.XmlPullParserException;

/**
 *
 * @author guillaume
 */
public class GameActivity extends BaseGameActivity implements GameActivityConstants {

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        Debug.setDebugLevel(DebugLevel.ALL);
        Debug.setTag(LOG_TAG);
    }

    ////////////////////////////////////////////////////////////////////////////
    // Menus
    public void onHighscoresMenuItemClick(MenuItem mi) {
        startActivity(new Intent(GameActivity.this, HighscoresActivity.class));

    }

    public void onPreferencesMenuItemClick(MenuItem mi) {
        startActivity(new Intent(GameActivity.this, PreferencesActivity.class));
    }
    public Scene mScene;
    public SmoothCamera mCamera;
    /**
     *
     */
    private RepeatingSpriteBackground mBackground;
    /**
     *
     */
    public PhysicsWorld mPhysicsWorld;
    /**
     *
     */
    /**
     *
     */
    private HUD mHUD;
    /**
     *
     */
    private LevelLoader mLevelLoader;
    private float oriX;
    private float oriY;
    public ContactManager mContactManager;
    /**
     *
     */
    private FPSLogger mFPSLogger;

    /**
     *
     * @return
     */
    public final Point getCameraDimensions() {
        Point p = new Point();
        this.getWindowManager().getDefaultDisplay().getSize(p);
        return p;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu, m);
        return true;
    }

    @Override
    public final EngineOptions onCreateEngineOptions() {


        Debug.v("Dimensions initiales de la caméra : " + getCameraDimensions());


        mCamera = new SmoothCamera(CAMERA_LEFT, CAMERA_TOP, getCameraDimensions().x, getCameraDimensions().y, CAMERA_MAX_VELOCITY_X, CAMERA_MAX_VELOCITY_Y, CAMERA_MAX_ZOOM_FACTOR_CHANGE);


        mCamera.setBounds(0.0f, 0.0f, WORLD_WIDTH, WORLD_HEIGHT);
        mCamera.setBoundsEnabled(true);

        //mCamera.setZNear(CAMERA_Z_NEAR);
        //mCamera.setZFar(CAMERA_Z_FAR);



        mHUD = new HUD();
        mHUD.setCamera(mCamera);


        EngineOptions mEngineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(getCameraDimensions().x, getCameraDimensions().y), this.mCamera);
        mEngineOptions.getAudioOptions().setNeedsSound(true);
        mEngineOptions.getAudioOptions().setNeedsMusic(true);

        // Write engine option to preferences
        SimplePreferences.getEditorInstance(this)
                .putBoolean("engine.audio.sound.enabled", mEngineOptions.getAudioOptions().needsSound())
                .putBoolean("engine.audio.music.enabled", mEngineOptions.getAudioOptions().needsMusic())
                .apply();

        return mEngineOptions;
    }

    /**
     *
     * @param pOnCreateSceneCallback
     * @throws Exception
     */
    public final void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) {

        mScene = new Scene();


        mLevelLoader = new LevelLoader("level/");

        // On bind le chargeur de la scène avec l'entité scène.
        mLevelLoader.registerEntityLoader("scene", new SceneLoader(this));

        // On enregistre toutes les entités supportées.
        mLevelLoader.registerEntityLoader(Components.strings(), new ComponentLoader(this));



        final ScrollDetector sd = new ScrollDetector(new IScrollDetectorListener() {
            public void onScrollStarted(ScrollDetector sd, int i, float f, float f1) {
            }

            public void onScroll(ScrollDetector sd, int i, float f, float f1) {

                mCamera.setCenterDirect(oriX - f, oriY - f1);

            }

            public void onScrollFinished(ScrollDetector sd, int i, float f, float f1) {
                Debug.v("Nouveau centre de la caméra : [" + mCamera.getCenterX() + ", " + mCamera.getCenterY() + "]");
            }
        });

        final PinchZoomDetector pzd = new PinchZoomDetector(new IPinchZoomDetectorListener() {
            public void onPinchZoomStarted(PinchZoomDetector pzd, TouchEvent te) {
            }

            public void onPinchZoom(PinchZoomDetector pzd, TouchEvent te, float pZoomFactor) {

                mCamera.setZoomFactor(pZoomFactor);
            }

            public void onPinchZoomFinished(PinchZoomDetector pzd, TouchEvent te, float f) {
                Log.v(LOG_TAG, "Nouveau facteur de zoom de la caméra : " + f + "x");

            }
        });


        mScene.setOnSceneTouchListener(new IOnSceneTouchListener() {
            public boolean onSceneTouchEvent(Scene scene, TouchEvent te) {
                oriX = mCamera.getCenterX(); //with float oriX; in fields
                oriY = mCamera.getCenterY(); //with float oriY; in fields
                sd.onTouchEvent(te);
                return true;
                //return pzd.onManagedTouchEvent(te) || sd.onManagedTouchEvent(te);
            }
        });


        mPhysicsWorld = new FixedStepPhysicsWorld(30, new Vector2(0, SensorManager.GRAVITY_EARTH), true, 8, 1);

        mContactManager = new ContactManager();

        mPhysicsWorld.setContactListener(mContactManager);

        mScene.registerUpdateHandler(mPhysicsWorld);

        mScene.registerUpdateHandler(mFPSLogger);

        pOnCreateSceneCallback.onCreateSceneFinished(mScene);
    }

    /**
     *
     * @param pScene
     * @param pOnPopulateSceneCallback
     */
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws InstantiationException, IllegalAccessException, IOException, XmlPullParserException {


        pScene.setBackground(mBackground);

        pScene.attachChild(mHUD);

        mHUD.attachChild(ComponentFactory.createAddDominoButton(0.0f, 0.0f));

        /* Le levelloader va charger les éléments dans la scène et la scène
         * elle même.
         */
        mLevelLoader.loadLevelFromAsset(getAssets(), "stage1.lvl");

        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }

    /**
     *
     * @param pOnCreateResourcesCallback
     * @throws Exception
     */
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) {

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        SoundFactory.setAssetBasePath("mfx/");
        ComponentFactory.setGameActivity(this);






        AssetBitmapTextureAtlasSource mBackgroundBaseTextureAtlasSource = AssetBitmapTextureAtlasSource.create(this.getAssets(), "gfx/background.png");

        mBackground = new RepeatingSpriteBackground(getCameraDimensions().x, getCameraDimensions().y, this.getTextureManager(), mBackgroundBaseTextureAtlasSource, this.getVertexBufferObjectManager());


        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }
}
