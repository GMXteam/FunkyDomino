package org.andengine.entity.text;

import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.HorizontalAlign;

import android.opengl.GLES20;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 10:02:04 - 05.05.2010
 */
public class TickerText extends Text {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final TickerTextOptions mTickerTextOptions;

	private int mCharactersVisible;
	private float mSecondsElapsed;

	private float mDuration;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pX
         * @param pY
         * @param pFont
         * @param pText
         * @param pTickerTextOptions
         * @param pVertexBufferObjectManager
         */
        public TickerText(final float pX, final float pY, final IFont pFont, final String pText, final TickerTextOptions pTickerTextOptions, final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pFont, pText, pTickerTextOptions, pVertexBufferObjectManager);

		this.mTickerTextOptions = pTickerTextOptions;

		this.mDuration = this.mCharactersMaximum * this.mTickerTextOptions.mCharactersPerSecond;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        @Override
	public TickerTextOptions getTextOptions() {
		return (TickerTextOptions) super.getTextOptions();
	}

        /**
         * 
         * @return
         */
        public boolean isReverse() {
		return this.getTextOptions().mReverse;
	}

        /**
         * 
         * @param pReverse
         */
        public void setReverse(final boolean pReverse) {
		this.mTickerTextOptions.mReverse = pReverse;
	}

        /**
         * 
         * @return
         */
        public float getCharactersPerSecond() {
		return this.mTickerTextOptions.mCharactersPerSecond;
	}

        /**
         * 
         * @param pCharactersPerSecond
         */
        public void setCharactersPerSecond(final float pCharactersPerSecond) {
		this.mTickerTextOptions.mCharactersPerSecond = pCharactersPerSecond;

		this.mDuration = this.mCharactersToDraw * pCharactersPerSecond;
	}

        /**
         * 
         * @return
         */
        public int getCharactersVisible() {
		return this.mCharactersVisible;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pSecondsElapsed
         */
        @Override
	protected void onManagedUpdate(final float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);
		if(this.mTickerTextOptions.mReverse){
			if(this.mCharactersVisible < this.mCharactersToDraw){
				this.mSecondsElapsed = Math.max(0, this.mSecondsElapsed - pSecondsElapsed);
				this.mCharactersVisible = (int)(this.mSecondsElapsed * this.mTickerTextOptions.mCharactersPerSecond);
			}
		} else {
			if(this.mCharactersVisible < this.mCharactersToDraw){
				this.mSecondsElapsed = Math.min(this.mDuration, this.mSecondsElapsed + pSecondsElapsed);
				this.mCharactersVisible = (int)(this.mSecondsElapsed * this.mTickerTextOptions.mCharactersPerSecond);
			}
		}
	}

	@Override
	protected void draw(final GLState pGLState, final Camera pCamera) {
		this.mTextVertexBufferObject.draw(GLES20.GL_TRIANGLES, this.mCharactersVisible * Text.VERTICES_PER_LETTER);
	}

        /**
         * 
         */
        @Override
	public void reset() {
		super.reset();

		this.mCharactersVisible = 0;
		this.mSecondsElapsed = 0;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

        /**
         * 
         */
        public static class TickerTextOptions extends TextOptions {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Fields
		// ===========================================================

		/* package */ float mCharactersPerSecond;
		/* package */ boolean mReverse;

		// ===========================================================
		// Constructors
		// ===========================================================

                /**
                 * 
                 */
                public TickerTextOptions() {

		}

                /**
                 * 
                 * @param pCharactersPerSecond
                 */
                public TickerTextOptions(final float pCharactersPerSecond) {
			this(pCharactersPerSecond, false);
		}

                /**
                 * 
                 * @param pCharactersPerSecond
                 * @param pReverse
                 */
                public TickerTextOptions(final float pCharactersPerSecond, final boolean pReverse) {
			this(HorizontalAlign.LEFT, pCharactersPerSecond, pReverse);
		}

                /**
                 * 
                 * @param pHorizontalAlign
                 * @param pCharactersPerSecond
                 */
                public TickerTextOptions(final HorizontalAlign pHorizontalAlign, final float pCharactersPerSecond) {
			this(false, 0, Text.LEADING_DEFAULT, pHorizontalAlign, pCharactersPerSecond, false);
		}

                /**
                 * 
                 * @param pHorizontalAlign
                 * @param pCharactersPerSecond
                 * @param pReverse
                 */
                public TickerTextOptions(final HorizontalAlign pHorizontalAlign, final float pCharactersPerSecond, final boolean pReverse) {
			this(false, 0, Text.LEADING_DEFAULT, pHorizontalAlign, pCharactersPerSecond, pReverse);
		}

                /**
                 * 
                 * @param pAutoWordWrap
                 * @param pAutoWordWrapWidth
                 * @param pLeading
                 * @param pHorizontalAlign
                 * @param pCharactersPerSecond
                 */
                public TickerTextOptions(final boolean pAutoWordWrap, final float pAutoWordWrapWidth, final float pLeading, final HorizontalAlign pHorizontalAlign, final float pCharactersPerSecond) {
			this(pAutoWordWrap, pAutoWordWrapWidth, pLeading, pHorizontalAlign, pCharactersPerSecond, false);
		}

                /**
                 * 
                 * @param pAutoWordWrap
                 * @param pAutoWordWrapWidth
                 * @param pLeading
                 * @param pHorizontalAlign
                 * @param pCharactersPerSecond
                 * @param pReverse
                 */
                public TickerTextOptions(final boolean pAutoWordWrap, final float pAutoWordWrapWidth, final float pLeading, final HorizontalAlign pHorizontalAlign, final float pCharactersPerSecond, final boolean pReverse) {
			super(pAutoWordWrap, pAutoWordWrapWidth, pLeading, pHorizontalAlign);

			this.mCharactersPerSecond = pCharactersPerSecond;
			this.mReverse = pReverse;
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================

                /**
                 * 
                 * @return
                 */
                public float getCharactersPerSecond() {
			return this.mCharactersPerSecond;
		}

                /**
                 * 
                 * @param pCharactersPerSecond
                 */
                public void setCharactersPerSecond(final float pCharactersPerSecond) {
			this.mCharactersPerSecond = pCharactersPerSecond;
		}

                /**
                 * 
                 * @return
                 */
                public boolean isReverse() {
			return this.mReverse;
		}

                /**
                 * 
                 * @param pReverse
                 */
                public void setReverse(final boolean pReverse) {
			this.mReverse = pReverse;
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
