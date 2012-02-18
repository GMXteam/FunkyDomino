package org.anddev.andengine.examples.adt.cityradar;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 20:32:16 - 28.10.2010
 */
public class City {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final String mName;
	private final double mLatitude;
	private final double mLongitude;
	private double mDistanceToUser;
	private double mBearingToUser;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pName
         * @param pLatitude
         * @param pLongitude
         */
        public City(final String pName, final double pLatitude, final double pLongitude) {
		this.mName = pName;
		this.mLatitude = pLatitude;
		this.mLongitude = pLongitude;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public final String getName() {
		return this.mName;
	}

        /**
         * 
         * @return
         */
        public final double getLatitude() {
		return this.mLatitude;
	}

        /**
         * 
         * @return
         */
        public final double getLongitude() {
		return this.mLongitude;
	}
	
        /**
         * 
         * @return
         */
        public double getDistanceToUser() {
		return this.mDistanceToUser;
	}

        /**
         * 
         * @param pDistanceToUser
         */
        public void setDistanceToUser(final double pDistanceToUser) {
		this.mDistanceToUser = pDistanceToUser;
	}
	
        /**
         * 
         * @return
         */
        public double getBearingToUser() {
		return this.mBearingToUser;
	}

        /**
         * 
         * @param pBearingToUser
         */
        public void setBearingToUser(final double pBearingToUser) {
		this.mBearingToUser = pBearingToUser;
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