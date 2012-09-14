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

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.hardware.SensorManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.badlogic.gdx.math.Vector2;
import com.gmxteam.funkydomino.core.Levels;
import com.gmxteam.funkydomino.core.component.factory.ComponentFactory;
import com.gmxteam.funkydomino.core.component.factory.Components;
import com.gmxteam.funkydomino.core.loader.ComponentLoader;
import com.gmxteam.funkydomino.core.loader.SceneLoader;
import com.gmxteam.funkydomino.core.physics.box2d.ContactManager;
import java.io.IOException;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.camera.SmoothCamera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.sensor.orientation.IOrientationListener;
import org.andengine.input.sensor.orientation.OrientationData;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.PinchZoomDetector;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.ui.activity.SimpleLayoutGameActivity;
import org.andengine.util.debug.Debug;
import org.andengine.util.level.LevelLoader;
import org.andengine.util.preferences.SimplePreferences;

/**
 * Activité principale de FunkyDomino. Permet d'accéder à : FunkyDominoActivity
 * pour jouer la dernière partie sauvegardée LoadGameActivity pour charger une
 * partie NewGameActivity pour créer une nouvelle partie PreferencesActivity
 * pour changer les préférences de FunkyDomino
 *
 * @author Guillaume Poirier-Morency
 */
public final class MainActivity extends SimpleLayoutGameActivity implements IFunkyDominoBaseActivity {

    private Scene mScene;
    private FixedStepPhysicsWorld mPhysicsWorld;
    private ContactManager mContactManager;
    private LevelLoader mLevelLoader;
    private SmoothCamera mCamera;

    ////////////////////////////////////////////////////////////////////////////
    // Menus
    /**
     *
     * @param mi
     */
    public void onHighscoresMenuItemClick(MenuItem mi) {
        startActivity(new Intent(MainActivity.this, HighscoresActivity.class));
    }

    /**
     *
     * @param mi
     */
    public void onPreferencesMenuItemClick(MenuItem mi) {
        startActivity(new Intent(MainActivity.this, PreferencesActivity.class));
    }

    ////////////////////////////////////////////////////////////////////////////
    // Listeners
    /**
     *
     * @param v
     */
    public void onPlayClick(View v) {
        Intent i = new Intent(MainActivity.this, GameActivity.class);
        i.putExtra("bundle.level", Levels.LEVEL_1.name());
        startActivity(i);

    }

    /**
     *
     * @param v
     */
    public void onNewGameClick(View v) {
        Intent i = new Intent(MainActivity.this, GameActivity.class);
        i.putExtra("bundle.level", Levels.LEVEL_1.name());
        startActivity(i);
    }

    /**
     *
     * @param v
     */
    public void onLoadGameClick(View v) {
        startActivity(new Intent(MainActivity.this, LoadGameActivity.class));

    }

    /**
     *
     * @param m
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu, m);
        return true;
    }

    /**
     *
     */
    @Override
    protected void onCreateResources() {

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        SoundFactory.setAssetBasePath("mfx/");
        MusicFactory.setAssetBasePath("mfx/");

        ComponentFactory.setGameActivity(this);

        mLevelLoader = new LevelLoader("level/");

        // On bind le chargeur de la scène avec l'entité scène.
        mLevelLoader.registerEntityLoader("level", new SceneLoader(this));

        // On enregistre toutes les entités supportées.
        mLevelLoader.registerEntityLoader(Components.strings(), new ComponentLoader(this));




    }

    /**
     *
     * @return
     */
    @Override
    protected Scene onCreateScene() {

        mScene = new Scene();

        mPhysicsWorld = new FixedStepPhysicsWorld(30, new Vector2(0, SensorManager.GRAVITY_EARTH), true, 8, 1);

        mContactManager = new ContactManager();

        mScene.registerUpdateHandler(mPhysicsWorld);

        mScene.registerUpdateHandler(new FPSLogger());

        try {
            /* Le levelloader va charger les éléments dans la scène et la scène
             * elle même.
             */
            mLevelLoader.loadLevelFromAsset(getAssets(), Levels.MAIN.toString());
        } catch (IOException ex) {
            Debug.e(ex);
        }


        mEngine.enableOrientationSensor(this, new IOrientationListener() {
            private Vector2 mGravity = mPhysicsWorld.getGravity();

            public void onOrientationAccuracyChanged(OrientationData pOrientationData) {
            }

            public void onOrientationChanged(OrientationData pOrientationData) {
                this.mGravity.x = pOrientationData.getRoll();
                this.mGravity.y = pOrientationData.getYaw();
                mPhysicsWorld.setGravity(this.mGravity);
            }
        });

        return mScene;
    }

    /**
     *
     * @return
     */
    @Override
    protected int getLayoutID() {
        return R.layout.main;
    }

    /**
     *
     * @return
     */
    @Override
    protected int getRenderSurfaceViewID() {
        return R.id.render_surface_view;
    }

    /**
     *
     * @return
     */
    public final Point getCameraDimensions() {
        Point p = new Point();
        getWindowManager().getDefaultDisplay().getSize(p);
        return p;
    }

    /**
     *
     * @return
     */
    public EngineOptions onCreateEngineOptions() {


        mCamera = new SmoothCamera(0.0f, 0.0f, getCameraDimensions().x, getCameraDimensions().y, 0.0f, 0.0f, 0.0f);




        EngineOptions mEngineOptions = new EngineOptions(true, ScreenOrientation.PORTRAIT_SENSOR, new RatioResolutionPolicy(getCameraDimensions().x, getCameraDimensions().y), mCamera);



        // On récupère les settings depuis les préférences partagées.       


        mEngineOptions.getAudioOptions().setNeedsSound(SimplePreferences.getInstance(this).getBoolean("engine.audio.sound.enabled", true));
        Debug.v("Le son est " + (mEngineOptions.getAudioOptions().needsSound() ? "activé" : "désactivé"));


        mEngineOptions.getAudioOptions().setNeedsMusic(SimplePreferences.getInstance(this).getBoolean("engine.audio.music.enabled", true));
        Debug.v("La musique est " + (mEngineOptions.getAudioOptions().needsSound() ? "activé" : "désactivé"));

        mEngineOptions.getRenderOptions().setDithering(SimplePreferences.getInstance(this).getBoolean("engine.graphic.dithering.enabled", true));
        Debug.v("Le dithering est " + (mEngineOptions.getRenderOptions().isDithering() ? "activé" : "désactivé"));


        mEngineOptions.getRenderOptions().setMultiSampling(SimplePreferences.getInstance(this).getBoolean("engine.audio.multisampling.enabled", true));
        Debug.v("L'échantillonage multiple est " + (mEngineOptions.getRenderOptions().isMultiSampling() ? "activé" : "désactivé"));


        try {
            TextureOptions.class.getField(SimplePreferences.getInstance(this).getString("engine.graphic.antialiasing", "DEFAULT"));
            Debug.v("Les textures sont " + SimplePreferences.getInstance(this).getString("engine.graphic.antialiasing", "DEFAULT"));

        } catch (NoSuchFieldException ex) {
            Debug.e(ex);
        }






        return mEngineOptions;
    }

    /**
     *
     * @return
     */
    public Context getContext() {
        return this;
    }

    /**
     *
     * @return
     */
    public PhysicsWorld getPhysicsWorld() {
        return mPhysicsWorld;
    }

    /**
     *
     * @return
     */
    public Scene getScene() {
        return mScene;
    }

    /**
     *
     * @return
     */
    public ContactManager getContactManager() {
        return mContactManager;
    }

    /**
     *
     * @return
     */
    public SmoothCamera getCamera() {
        return mCamera;
    }

    /**
     *
     * @param sd
     * @param i
     * @param f
     * @param f1
     */
    public void onScrollStarted(ScrollDetector sd, int i, float f, float f1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param sd
     * @param i
     * @param f
     * @param f1
     */
    public void onScroll(ScrollDetector sd, int i, float f, float f1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param sd
     * @param i
     * @param f
     * @param f1
     */
    public void onScrollFinished(ScrollDetector sd, int i, float f, float f1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param pzd
     * @param te
     */
    public void onPinchZoomStarted(PinchZoomDetector pzd, TouchEvent te) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param pzd
     * @param te
     * @param f
     */
    public void onPinchZoom(PinchZoomDetector pzd, TouchEvent te, float f) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param pzd
     * @param te
     * @param f
     */
    public void onPinchZoomFinished(PinchZoomDetector pzd, TouchEvent te, float f) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
