/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.activities;

import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import com.badlogic.gdx.math.Vector2;
import com.gmxteam.funkydomino.utils.database.model.GameModel;
import com.gmxteam.funkydomino.utils.xmlparser.AndEngineActivityXMLParser;
import java.io.IOException;
import java.io.InputStream;
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
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.BaseGameActivity;
import org.xml.sax.SAXException;

/**
 *
 * @author guillaume
 */
public class GameActivity extends BaseGameActivity implements GameActivityConstants {

	private InputStream levelStream;
	private int levelID;
	private GameModel mGameData;
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
		switch (b.getInt(STARTUP_STATE)) {
			case STARTUP_STATE_NEW_GAME:
				break;

			case STARTUP_STATE_LOADGAME:
				mGameData = (GameModel) b.getParcelable(GAME_DATA);
				break;


		}
		
		// Tests de base
		assert mGameData != null;

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
		levelStream = this.getResources().openRawResource(levelID);
	}

	/**
	 *
	 * @param pScene
	 * @param pOnPopulateSceneCallback
	 */
	public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) {
		String publickey = getString(R.string.key_0)
				+ getString(R.string.key_1)
				+ getString(R.string.key_2)
				+ getString(R.string.key_3)
				+ getString(R.string.key_4)
				+ getString(R.string.key_5)
				+ getString(R.string.key_6)
				+ getString(R.string.key_7);
		try {
			AndEngineActivityXMLParser.buildGameInstance(this, levelStream, publickey);
			levelStream.close();
		} catch (ParserConfigurationException ex) {
			Log.e(APP_LOG_NAME, "Parser configuration has crashed !", ex);
		} catch (SAXException ex) {
			Log.e(APP_LOG_NAME, "Parser has crashed !", ex);
		} catch (IOException ex) {
			Log.e(APP_LOG_NAME, "May be due to closing the stream or accessing it !", ex);
		}

	}

	/**
	 *
	 * @param pOnCreateResourcesCallback
	 * @throws Exception
	 */
	public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
