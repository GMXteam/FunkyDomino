package com.gmxteam.funkydomino.activities.examples;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.text.Text;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.StrokeFont;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;

import android.graphics.Color;
import android.graphics.Typeface;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga
 *
 * @author Nicolas Gramlich
 * @since 22:49:43 - 26.07.2010
 */
public class StrokeFontExample extends BaseExample {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final int CAMERA_WIDTH = 720;
	private static final int CAMERA_HEIGHT = 480;

	private static final int FONT_SIZE = 48;

	// ===========================================================
	// Fields
	// ===========================================================

	private Camera mCamera;

	private BitmapTextureAtlas mFontTexture;
	private BitmapTextureAtlas mStrokeFontTexture;
	private BitmapTextureAtlas mStrokeOnlyFontTexture;

	private Font mFont;
	private StrokeFont mStrokeFont;
	private StrokeFont mStrokeOnlyFont;

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
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera));
	}

        /**
         * 
         */
        @Override
	public void onLoadResources() {
		this.mFontTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mStrokeFontTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mStrokeOnlyFontTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		this.mFont = new Font(this.mFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), FONT_SIZE, true, Color.BLACK);
		this.mStrokeFont = new StrokeFont(this.mStrokeFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), FONT_SIZE, true, Color.BLACK, 2, Color.WHITE);
		this.mStrokeOnlyFont = new StrokeFont(this.mStrokeOnlyFontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), FONT_SIZE, true, Color.BLACK, 2, Color.WHITE, true);

		this.mEngine.getTextureManager().loadTextures(this.mFontTexture, this.mStrokeFontTexture, this.mStrokeOnlyFontTexture);
		this.getFontManager().loadFonts(this.mFont, this.mStrokeFont, this.mStrokeOnlyFont);
	}

        /**
         * 
         * @return
         */
        @Override
	public Scene onLoadScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		final Scene scene = new Scene();
		scene.setBackground(new ColorBackground(0.09804f, 0.6274f, 0.8784f));

		final Text textNormal = new Text(100, 100, this.mFont, "Just some normal Text.");
		final Text textStroke = new Text(100, 200, this.mStrokeFont, "Text with fill and stroke.");
		final Text textStrokeOnly = new Text(100, 300, this.mStrokeOnlyFont, "Text with stroke only.");

		scene.attachChild(textNormal);
		scene.attachChild(textStroke);
		scene.attachChild(textStrokeOnly);

		return scene;
	}

        /**
         * 
         */
        @Override
	public void onLoadComplete() {

	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
