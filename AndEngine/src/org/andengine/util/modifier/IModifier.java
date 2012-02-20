package org.andengine.util.modifier;

import java.util.Comparator;

import org.andengine.util.exception.AndEngineRuntimeException;


/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @param <T> 
 * @author Nicolas Gramlich
 * @since 11:17:50 - 19.03.2010
 */
public interface IModifier<T> {
	// ===========================================================
	// Constants
	// ===========================================================

    /**
     * 
     */
    public static final Comparator<IModifier<?>> MODIFIER_COMPARATOR_DURATION_DESCENDING = new Comparator<IModifier<?>>() {
		@Override
		public int compare(final IModifier<?> pModifierA, final IModifier<?> pModifierB) {
			final float durationA = pModifierA.getDuration();
			final float durationB = pModifierB.getDuration();

			if (durationA < durationB) {
				return 1;
			} else if (durationA > durationB) {
				return -1;
			} else {
				return 0;
			}
		}
	};

	// ===========================================================
	// Methods
	// ===========================================================

    /**
     * 
     */
    public void reset();

    /**
     * 
     * @return
     */
    public boolean isFinished();
        /**
         * 
         * @return
         */
        public boolean isAutoUnregisterWhenFinished();
        /**
         * 
         * @param pRemoveWhenFinished
         */
        public void setAutoUnregisterWhenFinished(final boolean pRemoveWhenFinished);

        /**
         * 
         * @return
         * @throws org.andengine.util.modifier.IModifier.DeepCopyNotSupportedException
         */
        public IModifier<T> deepCopy() throws DeepCopyNotSupportedException;

        /**
         * 
         * @return
         */
        public float getSecondsElapsed();
        /**
         * 
         * @return
         */
        public float getDuration();

        /**
         * 
         * @param pSecondsElapsed
         * @param pItem
         * @return
         */
        public float onUpdate(final float pSecondsElapsed, final T pItem);

        /**
         * 
         * @param pModifierListener
         */
        public void addModifierListener(final IModifierListener<T> pModifierListener);
        /**
         * 
         * @param pModifierListener
         * @return
         */
        public boolean removeModifierListener(final IModifierListener<T> pModifierListener);

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

        /**
         * 
         * @param <T>
         */
        public static interface IModifierListener<T> {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

            /**
             * 
             * @param pModifier
             * @param pItem
             */
            public void onModifierStarted(final IModifier<T> pModifier, final T pItem);
            /**
             * 
             * @param pModifier
             * @param pItem
             */
            public void onModifierFinished(final IModifier<T> pModifier, final T pItem);
	}

        /**
         * 
         */
        public static class DeepCopyNotSupportedException extends AndEngineRuntimeException {
		// ===========================================================
		// Constants
		// ===========================================================

		private static final long serialVersionUID = -5838035434002587320L;

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

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}
}
