package org.andengine.opengl.texture.atlas.bitmap.source.decorator.shape;

import org.andengine.opengl.texture.atlas.bitmap.source.decorator.BaseBitmapTextureAtlasSourceDecorator.TextureAtlasSourceDecoratorOptions;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 
 * @author guillaume
 */
public class RectangleBitmapTextureAtlasSourceDecoratorShape implements IBitmapTextureAtlasSourceDecoratorShape {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private static RectangleBitmapTextureAtlasSourceDecoratorShape sDefaultInstance;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         */
        public RectangleBitmapTextureAtlasSourceDecoratorShape() {

	}

        /**
         * 
         * @return
         */
        public static RectangleBitmapTextureAtlasSourceDecoratorShape getDefaultInstance() {
		if(sDefaultInstance == null) {
			sDefaultInstance = new RectangleBitmapTextureAtlasSourceDecoratorShape();
		}
		return sDefaultInstance;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pCanvas
         * @param pPaint
         * @param pDecoratorOptions
         */
        @Override
	public void onDecorateBitmap(final Canvas pCanvas, final Paint pPaint, final TextureAtlasSourceDecoratorOptions pDecoratorOptions) {
		final float left = pDecoratorOptions.getInsetLeft();
		final float top = pDecoratorOptions.getInsetTop();
		final float right = pCanvas.getWidth() - pDecoratorOptions.getInsetRight();
		final float bottom = pCanvas.getHeight() - pDecoratorOptions.getInsetBottom();
		
		pCanvas.drawRect(left, top, right, bottom, pPaint);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}