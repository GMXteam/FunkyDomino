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
import com.gmxteam.funkydomino.core.component.Domino;
import com.gmxteam.funkydomino.core.component.factory.ComponentFactory;
import com.gmxteam.funkydomino.core.component.factory.ComponentLoader;
import com.gmxteam.funkydomino.core.component.factory.Components;
import java.io.IOException;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.camera.SmoothCamera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.camera.hud.controls.DigitalOnScreenControl;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.RepeatingSpriteBackground;
import org.andengine.entity.util.FPSLogger;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.sensor.orientation.IOrientationListener;
import org.andengine.input.sensor.orientation.OrientationData;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.PinchZoomDetector;
import org.andengine.input.touch.detector.PinchZoomDetector.IPinchZoomDetectorListener;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.source.AssetBitmapTextureAtlasSource;
import org.andengine.opengl.texture.region.TextureRegion;
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
    private HUD mHUD;
    /**
     *
     */
    private DigitalOnScreenControl mAddDominoButton;
    /**
     *
     */
    private LevelLoader mLevelLoader;
    /**
     *
     */
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


        mCamera.setBoundsEnabled(true);

        //mCamera.setZNear(CAMERA_Z_NEAR);
        //mCamera.setZFar(CAMERA_Z_FAR);

        mHUD = new HUD();
        mHUD.setCamera(mCamera);


        EngineOptions mEngineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(getCameraDimensions().x, getCameraDimensions().y), mCamera);
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

            public synchronized void onScroll(ScrollDetector sd, int i, float f, float f1) {
                // Le scrolling s'ajuste en fonction du zoom.
                mEngine.getCamera().setCenter((mEngine.getCamera().getCenterX() - f) / ((SmoothCamera) mEngine.getCamera()).getZoomFactor(), (mEngine.getCamera().getCenterY() - f1) / ((SmoothCamera) mEngine.getCamera()).getZoomFactor());

            }

            public void onScrollFinished(ScrollDetector sd, int i, float f, float f1) {
                Log.v(LOG_TAG, "Nouveau centre de la caméra : [" + mEngine.getCamera().getCenterX() + ", " + mEngine.getCamera().getCenterY() + "]");
            }
        });

        final PinchZoomDetector pzd = new PinchZoomDetector(new IPinchZoomDetectorListener() {
            public void onPinchZoomStarted(PinchZoomDetector pzd, TouchEvent te) {
            }

            public void onPinchZoom(PinchZoomDetector pzd, TouchEvent te, float pZoomFactor) {

                ((SmoothCamera) mEngine.getCamera()).setZoomFactor(pZoomFactor);
            }

            public void onPinchZoomFinished(PinchZoomDetector pzd, TouchEvent te, float f) {
                Log.v(LOG_TAG, "Nouveau facteur de zoom de la caméra : " + f + "x");

            }
        });


        mScene.setOnSceneTouchListener(new IOnSceneTouchListener() {
            public boolean onSceneTouchEvent(Scene scene, TouchEvent te) {
                return pzd.onManagedTouchEvent(te) || sd.onManagedTouchEvent(te);
            }
        });

        mPhysicsWorld = new FixedStepPhysicsWorld(30, new Vector2(0, SensorManager.GRAVITY_EARTH), true, 8, 1);

        mContactManager = new ContactManager();

        mPhysicsWorld.setContactListener(mContactManager.getContactListener());

        mScene.registerUpdateHandler(mPhysicsWorld);

        mFPSLogger = new FPSLogger();


        mScene.registerUpdateHandler(mFPSLogger);

        // On bind le l'accéléromètre avec la gravité du monde physique
        mEngine.enableOrientationSensor(this, new IOrientationListener() {
            private Vector2 mGravity = mPhysicsWorld.getGravity();

            public void onOrientationAccuracyChanged(OrientationData od) {
            }

            public void onOrientationChanged(OrientationData od) {

                mGravity.x = mEngine.getOrientationData().getRoll();
                mPhysicsWorld.setGravity(mGravity);

            }
        });





        pOnCreateSceneCallback.onCreateSceneFinished(mScene);
    }

    /**
     *
     * @param pScene
     * @param pOnPopulateSceneCallback
     */
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws InstantiationException, IllegalAccessException, IOException, XmlPullParserException {


        pScene.setBackground(mBackground);


        mLevelLoader.loadLevelFromAsset(getAssets(), "stage1.lvl");

        pScene.attachChild(mHUD);
        pScene.registerTouchArea(mAddDominoButton.getControlBase());
        pScene.registerTouchArea(mAddDominoButton.getControlKnob());

        mHUD.attachChild(mAddDominoButton);

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

        BitmapTextureAtlas mBitmapTextureAtlas = new BitmapTextureAtlas(getTextureManager(), Domino.DOMINO_WIDTH, Domino.DOMINO_HEIGHT, GameActivity.TEXTURE_OPTION);


        getTextureManager().loadTexture(mBitmapTextureAtlas);


        TextureRegion mDominoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, this, "domino.png", 0, 0);


        mAddDominoButton = new DigitalOnScreenControl(0.0f, 0.0f, mEngine.getCamera(), mDominoTextureRegion, mDominoTextureRegion, 0.1f, this.getVertexBufferObjectManager(), new BaseOnScreenControl.IOnScreenControlListener() {
            public void onControlChange(BaseOnScreenControl bosc, float f, float f1) {
                // mScene.attachChild(ComponentFactory.createDomino(5.5f, 2.5f));
            }
        });


        AssetBitmapTextureAtlasSource mBackgroundBaseTextureAtlasSource = AssetBitmapTextureAtlasSource.create(this.getAssets(), "gfx/background.png");

        mBackground = new RepeatingSpriteBackground(getCameraDimensions().x, getCameraDimensions().y, this.getTextureManager(), mBackgroundBaseTextureAtlasSource, this.getVertexBufferObjectManager());


        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }
}
