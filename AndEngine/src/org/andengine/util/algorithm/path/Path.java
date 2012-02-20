package org.andengine.util.algorithm.path;


/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 23:00:24 - 16.08.2010
 */
public class Path {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final int[] mXs;
	private final int[] mYs;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pLength
         */
        public Path(final int pLength) {
		this.mXs = new int[pLength];
		this.mYs = new int[pLength];
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public int getLength() {
		return this.mXs.length;
	}

        /**
         * 
         * @return
         */
        public int getFromX() {
		return this.getX(0);
	}

        /**
         * 
         * @return
         */
        public int getFromY() {
		return this.getY(0);
	}

        /**
         * 
         * @return
         */
        public int getToX() {
		return this.getX(this.getLength() - 1);
	}

        /**
         * 
         * @return
         */
        public int getToY() {
		return this.getY(this.getLength() - 1);
	}

        /**
         * 
         * @param pIndex
         * @return
         */
        public int getX(final int pIndex) {
		return this.mXs[pIndex];
	}

        /**
         * 
         * @param pIndex
         * @return
         */
        public int getY(final int pIndex) {
		return this.mYs[pIndex];
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

        /**
         * 
         * @param pIndex
         * @param pX
         * @param pY
         */
        public void set(final int pIndex, final int pX, final int pY) {
		this.mXs[pIndex] = pX;
		this.mYs[pIndex] = pY;
	}

        /**
         * 
         * @param pX
         * @param pY
         * @return
         */
        public boolean contains(final int pX, final int pY) {
		final int[] xs = this.mXs;
		final int[] ys = this.mYs;
		for(int i = this.getLength() - 1; i >= 0; i--) {
			if(xs[i] == pX && ys[i] == pY) {
				return true;
			}
		}
		return false;
	}

        /**
         * 
         * @param pIndex
         * @return
         */
        public Direction getDirectionToPreviousStep(final int pIndex) {
		if(pIndex == 0) {
			return null;
		} else {
			final int dX = this.getX(pIndex - 1) - this.getX(pIndex);
			final int dY = this.getY(pIndex - 1) - this.getY(pIndex);
			return Direction.fromDelta(dX, dY);
		}
	}

        /**
         * 
         * @param pIndex
         * @return
         */
        public Direction getDirectionToNextStep(final int pIndex) {
		if(pIndex == this.getLength() - 1) {
			return null;
		} else {
			final int dX = this.getX(pIndex + 1) - this.getX(pIndex);
			final int dY = this.getY(pIndex + 1) - this.getY(pIndex);
			return Direction.fromDelta(dX, dY);
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
