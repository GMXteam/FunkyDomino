package org.andengine.util.adt.map;

import java.util.Arrays;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @param <K> 
 * @author Nicolas Gramlich
 * @since 16:54:24 - 07.11.2010
 */
public class MultiKey<K> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final K[] mKeys;
	private final int mCachedHashCode;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pKeys
         */
        public MultiKey(final K... pKeys) {
		this.mKeys = pKeys;

		this.mCachedHashCode = MultiKey.hash(pKeys);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public K[] getKeys() {
		return this.mKeys;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pOther
         * @return
         */
        @Override
	public boolean equals(final Object pOther) {
		if(pOther == this) {
			return true;
		}
		if(pOther instanceof MultiKey<?>) {
			final MultiKey<?> otherMultiKey = (MultiKey<?>) pOther;
			return Arrays.equals(this.mKeys, otherMultiKey.mKeys);
		}
		return false;
	}

        /**
         * 
         * @param pKeys
         * @return
         */
        public static int hash(final Object ... pKeys) {
		int hashCode = 0;
		for(final Object key : pKeys) {
			if(key != null) {
				hashCode ^= key.hashCode();
			}
		}
		return hashCode;
	}


        /**
         * 
         * @return
         */
        @Override
	public int hashCode() {
		return this.mCachedHashCode;
	}

        /**
         * 
         * @return
         */
        @Override
	public String toString() {
		return "MultiKey" + Arrays.asList(this.mKeys).toString();
	}

	// ===========================================================
	// Methods
	// ===========================================================

        /**
         * 
         * @param pIndex
         * @return
         */
        public K getKey(final int pIndex) {
		return this.mKeys[pIndex];
	}

        /**
         * 
         * @return
         */
        public int size() {
		return this.mKeys.length;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
