/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.activity;

import android.graphics.Point;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import com.badlogic.gdx.math.Vector2;
import com.gmxteam.funkydomino.core.component.Component;
import com.gmxteam.funkydomino.core.model.GameModel;
import com.gmxteam.funkydomino.xml.XMLParser;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import org.andengine.engine.camera.SmoothCamera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.andengine.ui.activity.BaseGameActivity;
import org.xml.sax.SAXException;

/**
 *
 * @author guillaume
 */
public class GameActivity extends BaseGameActivity implements GameActivityConstants {

    /**
     * Structure orm contenant les paramètres sauvegardés de la partie.
     */
    private GameModel mGameData;
    private XMLParser mParser = new XMLParser();
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
    SmoothCamera mCamera;
    /**
     *
     */
    EngineOptions mEngineOptions;

    /**
     * Gère les différents états de démarrage et charge les ressources de base.
     *
     * @param b
     */
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);

        mGameData = new GameModel();
        mGameData.stage = R.raw.stage1;

    }

    public final Point getSize() {
        Point p = new Point();
        this.getWindowManager().getDefaultDisplay().getSize(p);

        return p;
    }

    /**
     *
     * @return
     */
    @Override
    public final EngineOptions onCreateEngineOptions() {


        Log.v(APP_LOG_NAME, "Largeur : " + getSize().x + " Hauteur : " + getSize().y);
        this.mCamera = new SmoothCamera(CAMERA_LEFT, CAMERA_TOP, getSize().x, getSize().y, 500.0f, 0.0f, 1.0f);


        mEngineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(getSize().x, getSize().y), this.mCamera);

        //engineOptions.getAudioOptions().setNeedsSound(true);
        return mEngineOptions;
    }

    /**
     *
     * @param pOnCreateSceneCallback
     * @throws Exception
     */
    public final void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) {

        mScene = new Scene();

        ScrollDetector sd = new ScrollDetector(new IScrollDetectorListener() {
            public void onScrollStarted(ScrollDetector sd, int i, float f, float f1) {
            }

            public void onScroll(ScrollDetector sd, int i, float f, float f1) {
                mCamera.setCenter(mCamera.getCenterX() + f, mCamera.getCenterY() + f1);

            }

            public void onScrollFinished(ScrollDetector sd, int i, float f, float f1) {
            }
        });




        mScene.setOnSceneTouchListener(sd);



        //RepeatingSpriteBackground b = new RepeatingSpriteBackground(getSize().x, getSize().y, this.getTextureManager(), ResourceBitmapTextureAtlasSource.create(this.getResources(), R.drawable.background), this.getVertexBufferObjectManager());

        mScene.setBackground(new Background(1, 1, 1));



        mPhysicsWorld = new FixedStepPhysicsWorld(30, new Vector2(0, SensorManager.GRAVITY_EARTH), false, 8, 1);
        mScene.registerUpdateHandler(mPhysicsWorld);

        pOnCreateSceneCallback.onCreateSceneFinished(mScene);
    }

    public void addComponentToScene(Component e) {
        mScene.registerTouchArea(e.getTouchArea());
        mScene.attachChild(e);

    }

    /**
     *
     * @param pScene
     * @param pOnPopulateSceneCallback
     */
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws SAXException, ParserConfigurationException, IOException {
        

                
         InputStream gameResource = this.getResources().openRawResource(mGameData.stage);
         mParser.inflate(this, gameResource);

         // Components should have been loaded in the onLoadResources method.
         for (Component c : mParser.getCachedComponents()) {


         pScene.registerTouchArea(c.getTouchArea());
         pScene.attachChild(c);

         }
         gameResource.close();
         

        pOnPopulateSceneCallback.onPopulateSceneFinished();





    }

    /**
     *
     * @param pOnCreateResourcesCallback
     * @throws Exception
     */
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) {


        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }
}
