package org.andengine.extension.svg.adt;

import java.util.HashMap;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 09:21:33 - 25.05.2011
 */
public class SVGDirectColorMapper implements ISVGColorMapper {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final HashMap<Integer, Integer> mColorMappings = new HashMap<Integer, Integer>();

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         */
        public SVGDirectColorMapper() {

	}

        /**
         * 
         * @param pColorFrom
         * @param pColorTo
         */
        public SVGDirectColorMapper(final Integer pColorFrom, final Integer pColorTo) {
		this.addColorMapping(pColorFrom, pColorTo);
	}

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
         * @param pColorFrom
         * @param pColorTo
         */
        public void addColorMapping(final Integer pColorFrom, final Integer pColorTo) {
		this.mColorMappings.put(pColorFrom, pColorTo);
	}

        /**
         * 
         * @param pColor
         * @return
         */
        @Override
	public Integer mapColor(final Integer pColor) {
		final Integer mappedColor = this.mColorMappings.get(pColor);
		if(mappedColor == null) {
			return pColor;
		} else {
			return mappedColor;
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
