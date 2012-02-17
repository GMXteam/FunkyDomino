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
import android.widget.Toast;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.sensor.accelerometer.AccelerometerData;
import org.anddev.andengine.sensor.accelerometer.IAccelerometerListener;
import org.anddev.andengine.ui.activity.BaseGameActivity;


/**
 *
 * @author guillaume
 */
public class AndEngineMainActivity extends BaseGameActivity implements IAccelerometerListener, IOnSceneTouchListener
{

    private static final int CAMERA_WIDTH = 720;
    private static final int CAMERA_HEIGHT = 480;
           private PhysicsWorld mPhysicsWorld;

    
    private Scene mScene;

        //private PhysicsWorld mPhysicsWorld;
        private int mFaceCount = 0;

    public Engine onLoadEngine() {

        Toast.makeText(this, "Touch the screen to add objects.", Toast.LENGTH_LONG).show();
        final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        final EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
        engineOptions.getTouchOptions().setRunOnUpdateThread(true);
        return new Engine(engineOptions);


    }

    public void onLoadResources() {
    }

    public Scene onLoadScene() {
        this.mEngine.registerUpdateHandler(new FPSLogger());

                this.mScene = new Scene();
                this.mScene.setBackground(new ColorBackground(0, 0, 0));
                this.mScene.setOnSceneTouchListener(this);

                this.mPhysicsWorld = new PhysicsWorld(new Vector2(0, SensorManager.GRAVITY_EARTH), false);

                final Shape ground = new Rectangle(0, CAMERA_HEIGHT - 2, CAMERA_WIDTH, 2);
                final Shape roof = new Rectangle(0, 0, CAMERA_WIDTH, 2);
                final Shape left = new Rectangle(0, 0, 2, CAMERA_HEIGHT);
                final Shape right = new Rectangle(CAMERA_WIDTH - 2, 0, 2, CAMERA_HEIGHT);

                final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0, 0.5f, 0.5f);
                PhysicsFactory.createBoxBody(this.mPhysicsWorld, ground, BodyType.StaticBody, wallFixtureDef);
                PhysicsFactory.createBoxBody(this.mPhysicsWorld, roof, BodyType.StaticBody, wallFixtureDef);
                PhysicsFactory.createBoxBody(this.mPhysicsWorld, left, BodyType.StaticBody, wallFixtureDef);
                PhysicsFactory.createBoxBody(this.mPhysicsWorld, right, BodyType.StaticBody, wallFixtureDef);

                this.mScene.attachChild(ground);
                this.mScene.attachChild(roof);
                this.mScene.attachChild(left);
                this.mScene.attachChild(right);

                this.mScene.registerUpdateHandler(this.mPhysicsWorld);

                return this.mScene;
    }

    public void onLoadComplete() {
    }

    public void onAccelerometerChanged(AccelerometerData pAccelerometerData) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
        if(this.mPhysicsWorld != null) {
                        if(pSceneTouchEvent.isActionDown()) {
                                
                                return true;
                        }
                }
                return false;
        }

      

        @Override
        public void onResumeGame() {
                super.onResumeGame();

                this.enableAccelerometerSensor(this);
        }

        @Override
        public void onPauseGame() {
                super.onPauseGame();

                this.disableAccelerometerSensor();
    }
}
