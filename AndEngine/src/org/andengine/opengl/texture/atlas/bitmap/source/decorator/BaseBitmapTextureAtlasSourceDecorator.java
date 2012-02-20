package org.andengine.opengl.texture.atlas.bitmap.source.decorator;

import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.source.BaseTextureAtlasSource;
import org.andengine.util.debug.Debug;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 16:43:29 - 06.08.2010
 */
public abstract class BaseBitmapTextureAtlasSourceDecorator extends BaseTextureAtlasSource implements IBitmapTextureAtlasSource {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

    /**
     * 
     */
    protected final IBitmapTextureAtlasSource mBitmapTextureAtlasSource;
        /**
         * 
         */
        protected TextureAtlasSourceDecoratorOptions mTextureAtlasSourceDecoratorOptions;
        /**
         * 
         */
        protected Paint mPaint = new Paint();

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pBitmapTextureAtlasSource
         */
        public BaseBitmapTextureAtlasSourceDecorator(final IBitmapTextureAtlasSource pBitmapTextureAtlasSource) {
		this(pBitmapTextureAtlasSource, new TextureAtlasSourceDecoratorOptions());
	}

        /**
         * 
         * @param pBitmapTextureAtlasSource
         * @param pTextureAtlasSourceDecoratorOptions
         */
        public BaseBitmapTextureAtlasSourceDecorator(final IBitmapTextureAtlasSource pBitmapTextureAtlasSource, final TextureAtlasSourceDecoratorOptions pTextureAtlasSourceDecoratorOptions) {
		super(pBitmapTextureAtlasSource.getTextureX(), pBitmapTextureAtlasSource.getTextureY(), pBitmapTextureAtlasSource.getTextureWidth(), pBitmapTextureAtlasSource.getTextureHeight());

		this.mBitmapTextureAtlasSource = pBitmapTextureAtlasSource;
		this.mTextureAtlasSourceDecoratorOptions = (pTextureAtlasSourceDecoratorOptions == null) ? new TextureAtlasSourceDecoratorOptions() : pTextureAtlasSourceDecoratorOptions;
		this.mPaint.setAntiAlias(this.mTextureAtlasSourceDecoratorOptions.getAntiAliasing());
	}

	@Override
	public abstract BaseBitmapTextureAtlasSourceDecorator deepCopy();

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public Paint getPaint() {
		return this.mPaint;
	}

        /**
         * 
         * @param pPaint
         */
        public void setPaint(final Paint pPaint) {
		this.mPaint = pPaint;
	}

        /**
         * 
         * @return
         */
        public TextureAtlasSourceDecoratorOptions getTextureAtlasSourceDecoratorOptions() {
		return this.mTextureAtlasSourceDecoratorOptions;
	}

        /**
         * 
         * @param pTextureAtlasSourceDecoratorOptions
         */
        public void setTextureAtlasSourceDecoratorOptions(final TextureAtlasSourceDecoratorOptions pTextureAtlasSourceDecoratorOptions) {
		this.mTextureAtlasSourceDecoratorOptions = pTextureAtlasSourceDecoratorOptions;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pCanvas
         * @throws Exception
         */
        protected abstract void onDecorateBitmap(final Canvas pCanvas) throws Exception;

        /**
         * 
         * @return
         */
        @Override
	public int getTextureWidth() {
		return this.mBitmapTextureAtlasSource.getTextureWidth();
	}

        /**
         * 
         * @return
         */
        @Override
	public int getTextureHeight() {
		return this.mBitmapTextureAtlasSource.getTextureHeight();
	}

        /**
         * 
         * @param pBitmapConfig
         * @return
         */
        @Override
	public Bitmap onLoadBitmap(final Config pBitmapConfig) {
		final Bitmap bitmap = this.ensureLoadedBitmapIsMutable(this.mBitmapTextureAtlasSource.onLoadBitmap(pBitmapConfig));

		final Canvas canvas = new Canvas(bitmap);
		try {
			this.onDecorateBitmap(canvas);
		} catch (final Exception e) {
			Debug.e(e);
		}
		return bitmap;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	private Bitmap ensureLoadedBitmapIsMutable(final Bitmap pBitmap) {
		if(pBitmap.isMutable()) {
			return pBitmap;
		} else {
			final Bitmap mutableBitmap = pBitmap.copy(pBitmap.getConfig(), true);
			pBitmap.recycle();
			return mutableBitmap;
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

        /**
         * 
         */
        public static class TextureAtlasSourceDecoratorOptions {
		// ===========================================================
		// Constants
		// ===========================================================

            /**
             * 
             */
            public static final TextureAtlasSourceDecoratorOptions DEFAULT = new TextureAtlasSourceDecoratorOptions();

		// ===========================================================
		// Fields
		// ===========================================================

		private float mInsetLeft = 0.25f;
		private float mInsetRight = 0.25f;
		private float mInsetTop = 0.25f;
		private float mInsetBottom = 0.25f;

		private boolean mAntiAliasing;

		// ===========================================================
		// Constructors
		// ===========================================================

                /**
                 * 
                 * @return
                 */
                protected TextureAtlasSourceDecoratorOptions deepCopy() {
			final TextureAtlasSourceDecoratorOptions textureSourceDecoratorOptions = new TextureAtlasSourceDecoratorOptions();
			textureSourceDecoratorOptions.setInsets(this.mInsetLeft, this.mInsetTop, this.mInsetRight, this.mInsetBottom);
			textureSourceDecoratorOptions.setAntiAliasing(this.mAntiAliasing);
			return textureSourceDecoratorOptions;
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================

                /**
                 * 
                 * @return
                 */
                public boolean getAntiAliasing() {
			return this.mAntiAliasing;
		}

                /**
                 * 
                 * @return
                 */
                public float getInsetLeft() {
			return this.mInsetLeft;
		}

                /**
                 * 
                 * @return
                 */
                public float getInsetRight() {
			return this.mInsetRight;
		}

                /**
                 * 
                 * @return
                 */
                public float getInsetTop() {
			return this.mInsetTop;
		}

                /**
                 * 
                 * @return
                 */
                public float getInsetBottom() {
			return this.mInsetBottom;
		}

                /**
                 * 
                 * @param pAntiAliasing
                 * @return
                 */
                public TextureAtlasSourceDecoratorOptions setAntiAliasing(final boolean pAntiAliasing) {
			this.mAntiAliasing = pAntiAliasing;
			return this;
		}

                /**
                 * 
                 * @param pInsetLeft
                 * @return
                 */
                public TextureAtlasSourceDecoratorOptions setInsetLeft(final float pInsetLeft) {
			this.mInsetLeft = pInsetLeft;
			return this;
		}

                /**
                 * 
                 * @param pInsetRight
                 * @return
                 */
                public TextureAtlasSourceDecoratorOptions setInsetRight(final float pInsetRight) {
			this.mInsetRight = pInsetRight;
			return this;
		}

                /**
                 * 
                 * @param pInsetTop
                 * @return
                 */
                public TextureAtlasSourceDecoratorOptions setInsetTop(final float pInsetTop) {
			this.mInsetTop = pInsetTop;
			return this;
		}

                /**
                 * 
                 * @param pInsetBottom
                 * @return
                 */
                public TextureAtlasSourceDecoratorOptions setInsetBottom(final float pInsetBottom) {
			this.mInsetBottom = pInsetBottom;
			return this;
		}

                /**
                 * 
                 * @param pInsets
                 * @return
                 */
                public TextureAtlasSourceDecoratorOptions setInsets(final float pInsets) {
			return this.setInsets(pInsets, pInsets, pInsets, pInsets);
		}

                /**
                 * 
                 * @param pInsetLeft
                 * @param pInsetTop
                 * @param pInsetRight
                 * @param pInsetBottom
                 * @return
                 */
                public TextureAtlasSourceDecoratorOptions setInsets(final float pInsetLeft, final float pInsetTop, final float pInsetRight, final float pInsetBottom) {
			this.mInsetLeft = pInsetLeft;
			this.mInsetTop = pInsetTop;
			this.mInsetRight = pInsetRight;
			this.mInsetBottom = pInsetBottom;
			return this;
		}

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}
}
