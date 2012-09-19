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
package org.gmxteam.funkydomino.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import com.badlogic.gdx.math.Vector2;
import com.gmxteam.funkydomino.activity.R;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.camera.SmoothCamera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.text.Text;
import org.andengine.entity.util.FPSLogger;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.ui.activity.SimpleAsyncGameActivity;
import org.andengine.util.debug.Debug;
import org.andengine.util.debug.Debug.DebugLevel;
import org.andengine.util.level.IEntityLoader;
import org.andengine.util.preferences.SimplePreferences;
import org.andengine.util.progress.IProgressListener;
import org.gmxteam.funkydomino.component.loader.Loaders;
import org.gmxteam.funkydomino.component.loader.util.FunkyDominoLevelLoader;
import org.gmxteam.funkydomino.level.Levels;
import org.gmxteam.funkydomino.physics.box2d.ContactManager;
import org.gmxteam.funkydomino.physics.box2d.GravityBasedOrientationListener;
import org.gmxteam.funkydomino.util.PinchZoomAndScrollOnSceneTouchListener;
import org.gmxteam.funkydomino.util.TimeCounterHandler;
import org.gmxteam.funkydomino.util.TimeCounterHandler.IOnTimeChangeListener;

/**
 *
 * @author guillaume
 */
public class FunkyDominoActivity extends SimpleAsyncGameActivity implements IFunkyDominoActivity {

    private Levels mGameToLoad;
    private TimeCounterHandler mTimeCounterHandler;
    private HUD mHUD;

    ////////////////////////////////////////////////////////////////////////////
    // Événements Android
    /**
     * <p>
     *
     * @param pBundle is an Bundle containing datas. Here are the possible
     * values : bundle.level Name of the game to load ;
     *
     */
    @Override
    public void onCreate(Bundle pBundle) {
        super.onCreate(pBundle);

        // Use the current intent bundle if the pBundle is null.
        pBundle = pBundle == null ? getIntent().getExtras() : pBundle;


        // On écrit les préférences par défaut si c'est le premier lancement.
        PreferenceManager.setDefaultValues(this,
                R.layout.preference_graphic, false);

        PreferenceManager.setDefaultValues(this,
                R.layout.preference_audio, false);

        PreferenceManager.setDefaultValues(this,
                R.layout.preference_about, false);



        mGameToLoad = Levels.valueOf(pBundle.getString("bundle.level", Levels.LEVEL_1.name()));

        // Configs de déboguage
        Debug.setDebugLevel(DebugLevel.ALL);
        Debug.setTag(LOG_TAG);

        Debug.v("Partie à charger : " + mGameToLoad);
    }

    /**
     *
     */
    @Override
    public void onResumeGame() {

        if (showResumeDialog) {
            new AlertDialog.Builder(this)
                    .setTitle("Continuer la partie ?")
                    .setMessage("Continuer la partie ?")
                    .setPositiveButton("Continuer", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    FunkyDominoActivity.super.onResumeGame();
                    mTimeCounterHandler.resume();
                    showResumeDialog = false;

                }
            }).show();
        } else {
            FunkyDominoActivity.super.onResumeGame();
            mTimeCounterHandler.resume();
        }



    }

    /**
     *
     */
    @Override
    public void onPauseGame() {
        showResumeDialog = true;
        mTimeCounterHandler.pause();
        super.onPauseGame();
    }

    ////////////////////////////////////////////////////////////////////////////
    // Action des boutons et menus.
    /**
     *
     * @param mi
     */
    public void onHighscoresMenuItemClick(MenuItem mi) {
        startActivity(new Intent(FunkyDominoActivity.this, HighscoresActivity.class));
    }

    /**
     *
     * @param mi
     */
    public void onPreferencesMenuItemClick(MenuItem mi) {
        startActivity(new Intent(FunkyDominoActivity.this, PreferencesActivity.class));
    }

    /**
     *
     * @param v
     */
    public void onPauseGameMenuItemClick(MenuItem v) {
        onPauseGame();
    }

    /**
     *
     * @param v
     */
    public void onQuitterMenuItemClick(MenuItem v) {
        onBackPressed();
    }

    ////////////////////////////////////////////////////////////////////////////
    // Action diverses.
    /**
     *
     * @param m
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu m) {

        getMenuInflater().inflate(R.menu.menu, m);
        getMenuInflater().inflate(R.menu.game_menu, m);
        return true;
    }

    @Override
    public void onBackPressed() {
        onPauseGame();

        showResumeDialog = false;

        new AlertDialog.Builder(this)
                .setTitle("Quitter la partie ?")
                .setMessage("Quitter la partie ?")
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                onResumeGame();
            }
        })
                .setPositiveButton("Quitter", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                FunkyDominoActivity.super.onBackPressed();
            }
        }).show();


    }

    @Override
    public boolean onKeyUp(int i, KeyEvent ke) {

        if (i == KeyEvent.KEYCODE_MENU) {
            onPauseGame();
        }

        return super.onKeyUp(i, ke);
    }

    /**
     *
     * @param m
     */
    @Override
    public void onOptionsMenuClosed(Menu m) {
        showResumeDialog = false;
        onResumeGame();
    }
    ////////////////////////////////////////////////////////////////////////////
    // Paramètres et variables.
    /**
     * Détermine si le resume dialog doit apparaître.
     */
    private boolean showResumeDialog = false;
    /**
     * Scène contenant les entités.
     */
    private Scene mScene;
    /**
     * Caméra.
     */
    private SmoothCamera mCamera;
    /**
     * Monde physique.
     */
    private PhysicsWorld mPhysicsWorld;
    /**
     * Chargeur de niveau.
     */
    private FunkyDominoLevelLoader mLevelLoader;
    /**
     * Gestionnaire de contact.
     */
    private ContactManager mContactManager;
    /**
     * Entité contenant le temps écoulé depuis le début de la partie.
     */
    private Text mElapsedTimeText;
    /**
     * Police d'écriture à utiliser.
     */
    private Font mFont;

    ////////////////////////////////////////////////////////////////////////////
    // Événements de AndEngine   
    /**
     *
     * @return
     */
    @Override
    public final EngineOptions onCreateEngineOptions() {

        mCamera = new SmoothCamera(CAMERA_LEFT, CAMERA_TOP, getDrawableSurfaceDimensions().x, getDrawableSurfaceDimensions().y, CAMERA_MAX_VELOCITY_X, CAMERA_MAX_VELOCITY_Y, CAMERA_MAX_ZOOM_FACTOR_CHANGE);

        mCamera.setBoundsEnabled(true);


        EngineOptions mEngineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(getDrawableSurfaceDimensions().x, getDrawableSurfaceDimensions().y), mCamera);

        // On récupère les settings depuis les préférences partagées.       


        mEngineOptions.getAudioOptions().setNeedsSound(SimplePreferences.getInstance(this).getBoolean("engine.audio.sound.enabled", true));
        Debug.v("Le son est " + (mEngineOptions.getAudioOptions().needsSound() ? "activé" : "désactivé"));


        mEngineOptions.getAudioOptions().setNeedsMusic(SimplePreferences.getInstance(this).getBoolean("engine.audio.music.enabled", true));
        Debug.v("La musique est " + (mEngineOptions.getAudioOptions().needsSound() ? "activé" : "désactivé"));

        mEngineOptions.getRenderOptions().setDithering(SimplePreferences.getInstance(this).getBoolean("engine.graphic.dithering.enabled", true));
        Debug.v("Le dithering est " + (mEngineOptions.getRenderOptions().isDithering() ? "activé" : "désactivé"));


        mEngineOptions.getRenderOptions().getConfigChooserOptions().setRequestedMultiSampling(SimplePreferences.getInstance(this).getBoolean("engine.audio.multisampling.enabled", true));
        Debug.v("L'échantillonage multiple est " + (mEngineOptions.getRenderOptions().getConfigChooserOptions().isRequestedMultiSampling() ? "activé" : "désactivé"));


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
     * @param pProgressListener
     * @throws Exception
     */
    @Override
    public void onCreateResourcesAsync(IProgressListener pProgressListener) throws Exception {
        pProgressListener.onProgressChanged(IProgressListener.PROGRESS_MIN);

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        SoundFactory.setAssetBasePath("mfx/");
        MusicFactory.setAssetBasePath("mfx/");

        pProgressListener.onProgressChanged(IProgressListener.PROGRESS_MAX / 4);

        mLevelLoader = new FunkyDominoLevelLoader(this);

        pProgressListener.onProgressChanged(IProgressListener.PROGRESS_MAX / 2);


        // On enregistre toutes les entités supportées
        for (Loaders c : Loaders.values()) {
            Debug.v("Création du loader " + c.name());

            mLevelLoader.registerEntityLoader((IEntityLoader) c.getLoaderClass().getConstructor(IBaseGameActivity.class).newInstance(this));

        }


        final BitmapTextureAtlas mFontTexture = new BitmapTextureAtlas(getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        mFont = FontFactory.create(getFontManager(), mFontTexture, WORLD_TOP);
        getTextureManager().loadTexture(mFontTexture);
        getFontManager().loadFont(mFont);



        pProgressListener.onProgressChanged(IProgressListener.PROGRESS_MAX);
    }

    /**
     *
     * @param pProgressListener
     * @return
     * @throws Exception
     */
    @Override
    public Scene onCreateSceneAsync(IProgressListener pProgressListener) throws Exception {
        pProgressListener.onProgressChanged(IProgressListener.PROGRESS_MIN);

        mScene = new Scene();



        mScene.setOnSceneTouchListener(new PinchZoomAndScrollOnSceneTouchListener(mCamera));


        mPhysicsWorld = new FixedStepPhysicsWorld(30, new Vector2(0, -SensorManager.GRAVITY_EARTH), true);

        mEngine.enableOrientationSensor(this, new GravityBasedOrientationListener(mPhysicsWorld));

        mContactManager = new ContactManager();

        mPhysicsWorld.setContactListener(mContactManager);

        mScene.registerUpdateHandler(mPhysicsWorld);

        mScene.registerUpdateHandler(new FPSLogger());

        pProgressListener.onProgressChanged(IProgressListener.PROGRESS_MAX);


        return mScene;
    }

    /**
     *
     * @param pScene
     * @param pProgressListener
     * @throws Exception
     */
    @Override
    public void onPopulateSceneAsync(Scene pScene, IProgressListener pProgressListener) throws Exception {
        pProgressListener.onProgressChanged(IProgressListener.PROGRESS_MIN);

        /* Le levelloader va charger les éléments dans la scène et la scène
         * elle même ainsi que le HUD.
         */
        mLevelLoader.loadLevelFromAsset(getAssets(), mGameToLoad.toString());


        // HUD should have been created, let's populate it
        mElapsedTimeText = new Text(30.0f, 10.0f, mFont, Float.toString(0.0f), getVertexBufferObjectManager());
//        mHUD.attachChild(mElapsedTimeText);

        mTimeCounterHandler = new TimeCounterHandler(new IOnTimeChangeListener() {
            public void onTimeChange(float newTime) {
                //mElapsedTimeText.setText(Float.toString(newTime));
            }
        });

        getEngine().registerUpdateHandler(mTimeCounterHandler);

        //MusicFactory.createMusicFromAsset(getMusicManager(), this, "winslow.ogg").play();
        pProgressListener.onProgressChanged(IProgressListener.PROGRESS_MAX);
    }

    ////////////////////////////////////////////////////////////////////////////
    // Getters et setters
    /**
     *
     * @param pHUD
     */
    public void setHUD(HUD pHUD) {
        mHUD = pHUD;
    }

    /**
     *
     * @return
     */
    public HUD getHUD() {
        return mHUD;
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
     * @return
     */
    public Point getDrawableSurfaceDimensions() {
        Point p = new Point();
        getWindowManager().getDefaultDisplay().getSize(p);
        return p;
    }
}
