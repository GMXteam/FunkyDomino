package org.anddev.andengine.extension.multiplayer.protocol.util;

import org.anddev.andengine.util.SystemUtils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 21:44:07 - 04.03.2011
 */
public class Bluetooth {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private static Boolean SUPPORTED = null;

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
         * @param pContext
         * @return
         */
        public static boolean isSupported(final Context pContext) {
		if(SUPPORTED == null) {
			SUPPORTED = SystemUtils.isAndroidVersionOrHigher(Build.VERSION_CODES.ECLAIR_MR1) && SystemUtils.hasSystemFeature(pContext, PackageManager.FEATURE_BLUETOOTH);
		}

		return SUPPORTED;
	}

        /**
         * 
         * @return
         */
        public static boolean isSupportedByAndroidVersion() {
		return SystemUtils.isAndroidVersionOrHigher(Build.VERSION_CODES.ECLAIR_MR1);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
