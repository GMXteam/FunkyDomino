/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.activity;

import android.hardware.SensorManager;
import android.os.Bundle;
import com.badlogic.gdx.math.Vector2;
import com.gmxteam.funkydomino.core.component.Component;
import com.gmxteam.funkydomino.core.model.GameModel;
import com.gmxteam.funkydomino.xml.XMLParser;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.andengine.engine.camera.SmoothCamera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.BaseGameActivity;
import org.xml.sax.SAXException;

/**
 *
 * @author guillaume
 */
public class GameActivity extends BaseGameActivity implements GameActivityConstants {

	/**
	 * Structure orm contenant les paramètres de la partie.
	 */
	private GameModel mGameData;
	/**
	 *
	 */
	private PhysicsWorld mPhysicsWorld;
	/**
	 *
	 */
	private Scene mScene;
	/**
	 *
	 */
	SmoothCamera mCamera;
	/**
	 *
	 */
	TiledSprite mBackground;
	/**
	 *
	 */
	TiledTextureRegion mBackgroundTextureRegion;
	/**
	 *
	 */
	BitmapTextureAtlas mBackgroundTexture;
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
				BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		Component.mGameActivity = this;

	}

	/**
	 *
	 * @return
	 */
	@Override
	public final EngineOptions onCreateEngineOptions() {
		this.mCamera = new SmoothCamera(CAMERA_LEFT, CAMERA_TOP, CAMERA_WIDTH, CAMERA_HEIGHT, 500.0f, 0.0f, 1.0f);
		this.mCamera.setBounds(WORLD_LEFT, WORLD_TOP, WORLD_WIDTH - CAMERA_WIDTH, WORLD_HEIGHT);

		mEngineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera);

		//engineOptions.getAudioOptions().setNeedsSound(true);
		return mEngineOptions;
	}

	/**
	 *
	 * @param pOnCreateSceneCallback
	 * @throws Exception
	 */
	public final void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws Exception {
		mScene = new Scene();

		mPhysicsWorld = new FixedStepPhysicsWorld(30, new Vector2(0, SensorManager.GRAVITY_EARTH), false, 8, 1);
		mScene.registerUpdateHandler(mPhysicsWorld);

		pOnCreateSceneCallback.onCreateSceneFinished(mScene);
	}

	/**
	 * Chargement des ressources du programme (images, textes, etc...).
	 */
	public void onLoadResources() {
		
		
		
		
		
	}

	/**
	 *
	 * @param pScene
	 * @param pOnPopulateSceneCallback
	 */
	public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) {

		try {
			XMLParser xp = new XMLParser();

			xp.inflate(pScene, mPhysicsWorld, this.getResources().openRawResource(mGameData.stage));

		} catch (ParserConfigurationException ex) {
			Logger.getLogger(GameActivity.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SAXException ex) {
			Logger.getLogger(GameActivity.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(GameActivity.class.getName()).log(Level.SEVERE, null, ex);
		}

		pOnPopulateSceneCallback.onPopulateSceneFinished();


	}

	/**
	 *
	 * @param pOnCreateResourcesCallback
	 * @throws Exception
	 */
	public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception {
		pOnCreateResourcesCallback.onCreateResourcesFinished();
	}
}
