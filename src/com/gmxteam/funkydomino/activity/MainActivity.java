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
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Point;
import android.hardware.SensorManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.badlogic.gdx.math.Vector2;
import com.gmxteam.funkydomino.core.ContactManager;
import com.gmxteam.funkydomino.core.Levels;
import com.gmxteam.funkydomino.core.SceneLoader;
import com.gmxteam.funkydomino.core.component.factory.ComponentFactory;
import com.gmxteam.funkydomino.core.component.factory.ComponentLoader;
import com.gmxteam.funkydomino.core.component.factory.Components;
import java.io.IOException;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.camera.SmoothCamera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.RepeatingSpriteBackground;
import org.andengine.entity.util.FPSLogger;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.source.AssetBitmapTextureAtlasSource;
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
    private RepeatingSpriteBackground mBackground;

    ////////////////////////////////////////////////////////////////////////////
    // Menus
    public void onHighscoresMenuItemClick(MenuItem mi) {
        startActivity(new Intent(MainActivity.this, HighscoresActivity.class));
    }

    public void onPreferencesMenuItemClick(MenuItem mi) {
        startActivity(new Intent(MainActivity.this, PreferencesActivity.class));
    }

    ////////////////////////////////////////////////////////////////////////////
    // Listeners
    public void onPlayClick(View v) {
        Intent i = new Intent(MainActivity.this, GameActivity.class);
        startActivity(i);

    }

    public void onNewGameClick(View v) {
        Intent i = new Intent(MainActivity.this, GameActivity.class);
        startActivity(i);
    }

    public void onLoadGameClick(View v) {
        startActivity(new Intent(MainActivity.this, LoadGameActivity.class));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu, m);
        return true;
    }

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

        AssetBitmapTextureAtlasSource mBackgroundBaseTextureAtlasSource = AssetBitmapTextureAtlasSource.create(this.getAssets(), "gfx/background.png");

        mBackground = new RepeatingSpriteBackground(getCameraDimensions().x, getCameraDimensions().y, this.getTextureManager(), mBackgroundBaseTextureAtlasSource, this.getVertexBufferObjectManager());


    }

    @Override
    protected Scene onCreateScene() {
        mScene = new Scene();
        mPhysicsWorld = new FixedStepPhysicsWorld(30, new Vector2(0, SensorManager.GRAVITY_EARTH), true, 8, 1);

        mContactManager = new ContactManager();

        mScene.registerUpdateHandler(mPhysicsWorld);

        mScene.registerUpdateHandler(new FPSLogger());

        mScene.setBackground(mBackground);

        try {
            /* Le levelloader va charger les éléments dans la scène et la scène
             * elle même.
             */
            mLevelLoader.loadLevelFromAsset(getAssets(), Levels.MAIN.toString());
        } catch (IOException ex) {
            Debug.e(ex);
        }

        return mScene;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.main;
    }

    @Override
    protected int getRenderSurfaceViewID() {
        return R.id.render_surface_view;
    }

    public void onEngineOptionsChanged(EngineOptions pEngineOptions) {
        // On récupère les settings depuis les préférences partagées.       


        pEngineOptions.getAudioOptions().setNeedsSound(SimplePreferences.getInstance(this).getBoolean("engine.audio.sound.enabled", true));
        Debug.v("Le son est " + (pEngineOptions.getAudioOptions().needsSound() ? "activé" : "désactivé"));


        pEngineOptions.getAudioOptions().setNeedsMusic(SimplePreferences.getInstance(this).getBoolean("engine.audio.music.enabled", true));
        Debug.v("La musique est " + (pEngineOptions.getAudioOptions().needsSound() ? "activé" : "désactivé"));

        pEngineOptions.getRenderOptions().setDithering(SimplePreferences.getInstance(this).getBoolean("engine.graphic.dithering.enabled", true));
        Debug.v("Le dithering est " + (pEngineOptions.getRenderOptions().isDithering() ? "activé" : "désactivé"));


        pEngineOptions.getRenderOptions().setMultiSampling(SimplePreferences.getInstance(this).getBoolean("engine.audio.multisampling.enabled", true));
        Debug.v("L'échantillonage multiple est " + (pEngineOptions.getRenderOptions().isMultiSampling() ? "activé" : "désactivé"));


        try {
            TextureOptions.class.getField(SimplePreferences.getInstance(this).getString("engine.graphic.antialiasing", "DEFAULT"));
            Debug.v("Les textures sont " + SimplePreferences.getInstance(this).getString("engine.graphic.antialiasing", "DEFAULT"));

        } catch (NoSuchFieldException ex) {
            Debug.e(ex);
        }

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

    public EngineOptions onCreateEngineOptions() {


        mCamera = new SmoothCamera(0.0f, 0.0f, getCameraDimensions().x, getCameraDimensions().y, 0.0f, 0.0f, 0.0f);




        EngineOptions mEngineOptions = new EngineOptions(true, ScreenOrientation.PORTRAIT_SENSOR, new RatioResolutionPolicy(getCameraDimensions().x, getCameraDimensions().y), mCamera);



        SimplePreferences.getInstance(this).registerOnSharedPreferenceChangeListener(this);


        onEngineOptionsChanged(mEngineOptions);





        return mEngineOptions;
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        this.onEngineOptionsChanged(mEngine.getEngineOptions());
    }

    public Context getContext() {
        return this;
    }

    public PhysicsWorld getPhysicsWorld() {
        return mPhysicsWorld;
    }

    public Scene getScene() {
        return mScene;
    }

    public ContactManager getContactManager() {
        return mContactManager;
    }

    public SmoothCamera getCamera() {
        return mCamera;
    }
}
