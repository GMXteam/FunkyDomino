package org.andengine.extension.svg.util;


/**
 * @author Larva Labs, LLC
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 16:50:17 - 21.05.2011
 */
public class SVGNumberParser {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

    /**
     * 
     * @param pString
     * @return
     */
    public static SVGNumberParserFloatResult parseFloats(final String pString) {
		if(pString == null) {
			return null;
		}

		final String[] parts = pString.split("[\\s,]+");
		final float[] numbers = new float[parts.length];
		for(int i = parts.length - 1; i >= 0; i--) {
			numbers[i] = Float.parseFloat(parts[i]);
		}

		return new SVGNumberParserFloatResult(numbers);
	}

        /**
         * 
         * @param pString
         * @return
         */
        public static SVGNumberParserIntegerResult parseInts(final String pString) {
		if(pString == null) {
			return null;
		}

		final String[] parts = pString.split("[\\s,]+");
		final int[] numbers = new int[parts.length];
		for(int i = parts.length - 1; i >= 0; i--) {
			numbers[i] = Integer.parseInt(parts[i]);
		}

		return new SVGNumberParserIntegerResult(numbers);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

        /**
         * 
         */
        public static class SVGNumberParserIntegerResult {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Fields
		// ===========================================================

		private final int[] mNumbers;

		// ===========================================================
		// Constructors
		// ===========================================================

                /**
                 * 
                 * @param pNumbers
                 */
                public SVGNumberParserIntegerResult(final int[] pNumbers) {
			this.mNumbers = pNumbers;
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================


                /**
                 * 
                 * @return
                 */
                public int[] getNumbers() {
			return this.mNumbers;
		}

                /**
                 * 
                 * @return
                 */
                public int getNumberCount() {
			return this.mNumbers.length;
		}

                /**
                 * 
                 * @param pIndex
                 * @return
                 */
                public int getNumber(final int pIndex) {
			return this.mNumbers[pIndex];
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

        /**
         * 
         */
        public static class SVGNumberParserFloatResult {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Fields
		// ===========================================================

		private final float[] mNumbers;

		// ===========================================================
		// Constructors
		// ===========================================================

                /**
                 * 
                 * @param pNumbers
                 */
                public SVGNumberParserFloatResult(final float[] pNumbers) {
			this.mNumbers = pNumbers;
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================


                /**
                 * 
                 * @return
                 */
                public float[] getNumbers() {
			return this.mNumbers;
		}

                /**
                 * 
                 * @return
                 */
                public int getNumberCount() {
			return this.mNumbers.length;
		}

                /**
                 * 
                 * @param pIndex
                 * @return
                 */
                public float getNumber(final int pIndex) {
			return this.mNumbers[pIndex];
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