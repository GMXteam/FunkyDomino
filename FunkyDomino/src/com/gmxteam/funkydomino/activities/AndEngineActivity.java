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
package com.gmxteam.funkydomino.activities;

import android.hardware.SensorManager;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.util.FPSLogger;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.color.Color;
import com.badlogic.gdx.math.Vector2;


/**
 * Classe abstraite permettant une implémentation efficace d'un interface JBox2D.
 * Funky Domino sera premièrement développé en canvas afin d'obtenir rapidement
 * des résultats. Il sera ensuite converti en OpenGL afin d'en améliorer
 * considérablement les performances.
 * 
 * Le fonctionnement est simple. On redéfinit une activité android en y intégrant
 * un moteur de physique et de rendu. On intègre aussi certaines interactions
 * avec l'utilisateur pour minimiser le code des activités.
 * 
 * Les éléments d'interfaces qui seront alors utilisés pourront être ceux de la
 * librairie standard, mais il est recommandé d'utiliser des élément de physique
 * afin de nous donner plus de liberté. Un menu animé par la physique, c'est pas
 * cool ça?
 * 
 * Le redessinage est géré dans le thread de l'utilisateur interface. Quand on
 * finit de redessiner une image, elle est automatiquement invalidée et quand le
 * thread UI sera prêt à la redessiner, le processus recommencera.
 * 
 * La physique est gérée dans un thread à part.
 * @author Guillaume Poirier-Morency
 */
public abstract class AndEngineActivity extends BaseGameActivity implements AndEngineActivityConstants {

    /**
     * 
     */
    public PhysicsWorld mPhysicsWorld;
    /**
     * 
     */
    public Scene mScene;
    /**
     * 
     */
    private Camera mCamera;
    

    /**
     * 
     * @return
     */
    @Override
    public EngineOptions onCreateEngineOptions() {
        this.mCamera = new Camera(CAMERA_LEFT, CAMERA_TOP, CAMERA_WIDTH, CAMERA_HEIGHT);
        //engineOptions.getAudioOptions().setNeedsSound(true);
        return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera);

    }

    

    /**
     * Chargement du moteur de physique et du moteur de jeu.
     * @return
     */
    public Engine onLoadEngine() {
        mEngine = new Engine(mEngineOptions);
        return mEngine;
    }

    /**
     * 
     * @param pOnCreateSceneCallback
     * @throws Exception 
     */
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws Exception {
        this.mEngine.registerUpdateHandler(new FPSLogger());
        mScene = new Scene();
        mScene.setBackground(new Background(Color.RED));
        this.mPhysicsWorld = new FixedStepPhysicsWorld(30, new Vector2(0, SensorManager.GRAVITY_EARTH), false, 8, 1);
        this.mScene.registerUpdateHandler(this.mPhysicsWorld);
        pOnCreateSceneCallback.onCreateSceneFinished(mScene);        
        
        
    }

    /**
     * Chargement de la scène.
     * @return
     */
    public Scene onLoadScene() {
        return mScene;
    }
}
