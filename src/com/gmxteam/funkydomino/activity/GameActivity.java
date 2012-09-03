/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.activity;

import android.graphics.Point;
import android.hardware.SensorManager;
import android.util.Log;
import com.badlogic.gdx.math.Vector2;
import com.gmxteam.funkydomino.core.component.Component;
import com.gmxteam.funkydomino.core.component.ComponentFactory;
import java.io.IOException;
import org.andengine.engine.camera.SmoothCamera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.RepeatingSpriteBackground;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.source.AssetBitmapTextureAtlasSource;
import org.andengine.ui.IGameInterface.OnCreateResourcesCallback;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;
import org.andengine.ui.IGameInterface.OnPopulateSceneCallback;
import org.andengine.ui.activity.BaseGameActivity;
import org.xmlpull.v1.XmlPullParserException;

/**
 *
 * @author guillaume
 */
public class GameActivity extends BaseGameActivity implements GameActivityConstants {

    /**
     * Structure orm contenant les paramètres sauvegardés de la partie.
     */
    private RepeatingSpriteBackground mBackground;
    /**
     *
     */
    public PhysicsWorld mPhysicsWorld;
    /**
     *
     */
    public Scene mScene;
    public HUD mHUD;
    /**
     *
     */
    SmoothCamera mCamera;
    /**
     *
     */
    EngineOptions mEngineOptions;

    /**
     *
     * @return
     */
    public final Point getCameraDimensions() {
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


        Log.v(LOG_TAG, "Largeur : " + getCameraDimensions().x + " Hauteur : " + getCameraDimensions().y);
        this.mCamera = new SmoothCamera(CAMERA_LEFT, CAMERA_TOP, getCameraDimensions().x, getCameraDimensions().y, 500.0f, 0.0f, 1.0f);

        this.mHUD = new HUD();
        this.mHUD.setCamera(mCamera);

        mEngineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(getCameraDimensions().x, getCameraDimensions().y), this.mCamera);

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

        mPhysicsWorld = new FixedStepPhysicsWorld(30, new Vector2(0, SensorManager.GRAVITY_EARTH), false, 8, 1);

        mScene.registerUpdateHandler(mPhysicsWorld);

        pOnCreateSceneCallback.onCreateSceneFinished(mScene);
    }

    /**
     *
     * @param pScene
     * @param pOnPopulateSceneCallback
     */
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws InstantiationException, IllegalAccessException, IOException, XmlPullParserException {


        pScene.setBackground(mBackground);

        if (DEBUG) {
            // Do some shits..
            final int TOP = getCameraDimensions().y / 2;
            Vector2[] vertex = {
                new Vector2(getCameraDimensions().x, TOP),
                new Vector2(0.0f, getCameraDimensions().y),
                new Vector2(-getCameraDimensions().x, TOP),
                new Vector2(0.0f, -getCameraDimensions().y)
            };
            pScene.attachChild(ComponentFactory.createGround(vertex));
            //pScene.attachChild(ComponentFactory.createDomino(5, 5));
            //pScene.attachChild(ComponentFactory.createBall(5, 25));


        } else {
            // Call the parser           


            for (Component c : ComponentFactory.extractComponentsFromAsset("stage1.xml")) {
                pScene.attachChild(c);
            }


        }







        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }

    /**
     *
     * @param pOnCreateResourcesCallback
     * @throws Exception
     */
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) {

        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        ComponentFactory.setGameActivity(this);


        AssetBitmapTextureAtlasSource mBackgroundBaseTextureAtlasSource = AssetBitmapTextureAtlasSource.create(this.getAssets(), "gfx/background.png");

        mBackground = new RepeatingSpriteBackground(getCameraDimensions().x, getCameraDimensions().y, this.getTextureManager(), mBackgroundBaseTextureAtlasSource, this.getVertexBufferObjectManager());


        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }
}
