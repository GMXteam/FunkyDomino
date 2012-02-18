package com.gmxteam.funkydomino.activities.examples;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.SingleSceneSplitScreenEngine;
import org.anddev.andengine.engine.camera.BoundCamera;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.camera.hud.HUD;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.shape.Shape;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.TiledSprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.extension.physics.box2d.PhysicsConnector;
import org.anddev.andengine.extension.physics.box2d.PhysicsFactory;
import org.anddev.andengine.extension.physics.box2d.PhysicsWorld;
import org.anddev.andengine.extension.physics.box2d.util.Vector2Pool;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.sensor.accelerometer.AccelerometerData;
import org.anddev.andengine.sensor.accelerometer.IAccelerometerListener;

import android.hardware.SensorManager;
import android.widget.Toast;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga
 *
 * @author Nicolas Gramlich
 * @since 18:08:29 - 27.07.2010
 */
public class BoundCameraExample extends BaseExample implements IAccelerometerListener, IOnSceneTouchListener {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final int CAMERA_WIDTH = 400;
	private static final int CAMERA_HEIGHT = 480;

	// ===========================================================
	// Fields
	// ===========================================================

	private Camera mCamera;
	private BoundCamera mBoundChaseCamera;

	private Scene mScene;

	private PhysicsWorld mPhysicsWorld;

	private BitmapTextureAtlas mBitmapTextureAtlas;
	private TiledTextureRegion mBoxFaceTextureRegion;

	private BitmapTextureAtlas mHUDTexture;
	private TiledTextureRegion mToggleButtonTextureRegion;

	private int mFaceCount;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @return
         */
        @Override
	public Engine onLoadEngine() {
		Toast.makeText(this, "Touch the screen to add boxes.", Toast.LENGTH_LONG).show();
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		this.mBoundChaseCamera = new BoundCamera(0, 0, CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2, 0, CAMERA_WIDTH, 0, CAMERA_HEIGHT);
		final EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(CAMERA_WIDTH * 2, CAMERA_HEIGHT), this.mCamera);
		engineOptions.getTouchOptions().setRunOnUpdateThread(true);
		return new SingleSceneSplitScreenEngine(engineOptions, this.mBoundChaseCamera);
	}

        /**
         * 
         */
        @Override
	public void onLoadResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		this.mBitmapTextureAtlas = new BitmapTextureAtlas(64, 32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mBoxFaceTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "face_box_tiled.png", 0, 0, 2, 1); // 64x32
		this.mEngine.getTextureManager().loadTexture(this.mBitmapTextureAtlas);

		this.mHUDTexture = new BitmapTextureAtlas(256, 128,TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mToggleButtonTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mHUDTexture, this, "toggle_button.png", 0, 0, 2, 1); // 256x128
		this.mEngine.getTextureManager().loadTexture(this.mHUDTexture);
	}

        /**
         * 
         * @return
         */
        @Override
	public Scene onLoadScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		this.mScene = new Scene();
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

		final HUD hud = new HUD();

		final TiledSprite toggleButton = new TiledSprite(CAMERA_WIDTH / 2 - this.mToggleButtonTextureRegion.getTileWidth(), CAMERA_HEIGHT / 2 - this.mToggleButtonTextureRegion.getTileHeight(), this.mToggleButtonTextureRegion){
			@Override
			public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
				if(pSceneTouchEvent.isActionDown()) {
					final boolean boundsEnabled = BoundCameraExample.this.mBoundChaseCamera.isBoundsEnabled();
					if(boundsEnabled) {
						BoundCameraExample.this.mBoundChaseCamera.setBoundsEnabled(false);
						this.setCurrentTileIndex(1);
						BoundCameraExample.this.runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(BoundCameraExample.this, "Bounds Disabled.", Toast.LENGTH_SHORT).show();
							}
						});
					} else {
						BoundCameraExample.this.mBoundChaseCamera.setBoundsEnabled(true);
						this.setCurrentTileIndex(0);
						BoundCameraExample.this.runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(BoundCameraExample.this, "Bounds Enabled.", Toast.LENGTH_SHORT).show();
							}
						});
					}
				}
				return true;
			}
		};
		hud.registerTouchArea(toggleButton);
		hud.attachChild(toggleButton);

		this.mBoundChaseCamera.setHUD(hud);

		return this.mScene;
	}

        /**
         * 
         */
        @Override
	public void onLoadComplete() {

	}

	@Override
	public boolean onSceneTouchEvent(final Scene pScene, final TouchEvent pSceneTouchEvent) {
		if(this.mPhysicsWorld != null) {
			if(pSceneTouchEvent.isActionDown()) {
				this.addFace(pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
				return true;
			}
		}
		return false;
	}

        /**
         * 
         * @param pAccelerometerData
         */
        @Override
	public void onAccelerometerChanged(final AccelerometerData pAccelerometerData) {
		final Vector2 gravity = Vector2Pool.obtain(pAccelerometerData.getX(), pAccelerometerData.getY());
		this.mPhysicsWorld.setGravity(gravity);
		Vector2Pool.recycle(gravity);
	}

        /**
         * 
         */
        @Override
	public void onResumeGame() {
		super.onResumeGame();

		this.enableAccelerometerSensor(this);
	}

        /**
         * 
         */
        @Override
	public void onPauseGame() {
		super.onPauseGame();

		this.disableAccelerometerSensor();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private void addFace(final float pX, final float pY) {
		final FixtureDef objectFixtureDef = PhysicsFactory.createFixtureDef(1, 0.5f, 0.5f);

		final AnimatedSprite face = new AnimatedSprite(pX, pY, this.mBoxFaceTextureRegion).animate(100);
		final Body body = PhysicsFactory.createBoxBody(this.mPhysicsWorld, face, BodyType.DynamicBody, objectFixtureDef);

		this.mScene.attachChild(face);
		this.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(face, body, true, true));

		if(this.mFaceCount == 0){
			this.mBoundChaseCamera.setChaseEntity(face);
		}

		this.mFaceCount++;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
