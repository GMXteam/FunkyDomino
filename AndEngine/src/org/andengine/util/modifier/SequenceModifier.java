package org.andengine.util.modifier;

import org.andengine.util.modifier.IModifier.IModifierListener;
import org.andengine.util.modifier.util.ModifierUtils;


/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @param <T> 
 * @author Nicolas Gramlich
 * @since 19:39:25 - 19.03.2010
 */
public class SequenceModifier<T> extends BaseModifier<T> implements IModifierListener<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private ISubSequenceModifierListener<T> mSubSequenceModifierListener;

	private final IModifier<T>[] mSubSequenceModifiers;
	private int mCurrentSubSequenceModifierIndex;

	private float mSecondsElapsed;
	private final float mDuration;

	private boolean mFinishedCached;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pModifiers
         * @throws IllegalArgumentException
         */
        public SequenceModifier(final IModifier<T> ... pModifiers) throws IllegalArgumentException {
		this(null, null, pModifiers);
	}

        /**
         * 
         * @param pSubSequenceModifierListener
         * @param pModifiers
         * @throws IllegalArgumentException
         */
        public SequenceModifier(final ISubSequenceModifierListener<T> pSubSequenceModifierListener, final IModifier<T> ... pModifiers) throws IllegalArgumentException {
		this(pSubSequenceModifierListener, null, pModifiers);
	}

        /**
         * 
         * @param pModifierListener
         * @param pModifiers
         * @throws IllegalArgumentException
         */
        public SequenceModifier(final IModifierListener<T> pModifierListener, final IModifier<T> ... pModifiers) throws IllegalArgumentException {
		this(null, pModifierListener, pModifiers);
	}

        /**
         * 
         * @param pSubSequenceModifierListener
         * @param pModifierListener
         * @param pModifiers
         * @throws IllegalArgumentException
         */
        public SequenceModifier(final ISubSequenceModifierListener<T> pSubSequenceModifierListener, final IModifierListener<T> pModifierListener, final IModifier<T> ... pModifiers) throws IllegalArgumentException {
		super(pModifierListener);

		if (pModifiers.length == 0) {
			throw new IllegalArgumentException("pModifiers must not be empty!");
		}

		this.mSubSequenceModifierListener = pSubSequenceModifierListener;
		this.mSubSequenceModifiers = pModifiers;

		this.mDuration = ModifierUtils.getSequenceDurationOfModifier(pModifiers);

		pModifiers[0].addModifierListener(this);
	}

        /**
         * 
         * @param pSequenceModifier
         * @throws org.andengine.util.modifier.IModifier.DeepCopyNotSupportedException
         */
        @SuppressWarnings("unchecked")
	protected SequenceModifier(final SequenceModifier<T> pSequenceModifier) throws DeepCopyNotSupportedException {
		this.mDuration = pSequenceModifier.mDuration;

		final IModifier<T>[] otherModifiers = pSequenceModifier.mSubSequenceModifiers;
		this.mSubSequenceModifiers = new IModifier[otherModifiers.length];

		final IModifier<T>[] shapeModifiers = this.mSubSequenceModifiers;
		for(int i = shapeModifiers.length - 1; i >= 0; i--) {
			shapeModifiers[i] = otherModifiers[i].deepCopy();
		}

		shapeModifiers[0].addModifierListener(this);
	}

	@Override
	public SequenceModifier<T> deepCopy() throws DeepCopyNotSupportedException{
		return new SequenceModifier<T>(this);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public ISubSequenceModifierListener<T> getSubSequenceModifierListener() {
		return this.mSubSequenceModifierListener;
	}

        /**
         * 
         * @param pSubSequenceModifierListener
         */
        public void setSubSequenceModifierListener(final ISubSequenceModifierListener<T> pSubSequenceModifierListener) {
		this.mSubSequenceModifierListener = pSubSequenceModifierListener;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @return
         */
        @Override
	public float getSecondsElapsed() {
		return this.mSecondsElapsed;
	}

        /**
         * 
         * @return
         */
        @Override
	public float getDuration() {
		return this.mDuration;
	}

	@Override
	public float onUpdate(final float pSecondsElapsed, final T pItem) {
		if(this.mFinished){
			return 0;
		} else {
			float secondsElapsedRemaining = pSecondsElapsed;
			this.mFinishedCached = false;
			while(secondsElapsedRemaining > 0 && !this.mFinishedCached) {
				secondsElapsedRemaining -= this.mSubSequenceModifiers[this.mCurrentSubSequenceModifierIndex].onUpdate(secondsElapsedRemaining, pItem);
			}
			this.mFinishedCached = false;

			final float secondsElapsedUsed = pSecondsElapsed - secondsElapsedRemaining;
			this.mSecondsElapsed += secondsElapsedUsed;
			return secondsElapsedUsed;
		}
	}

        /**
         * 
         */
        @Override
	public void reset() {
		if(this.isFinished()) {
			this.mSubSequenceModifiers[this.mSubSequenceModifiers.length - 1].removeModifierListener(this);
		} else {
			this.mSubSequenceModifiers[this.mCurrentSubSequenceModifierIndex].removeModifierListener(this);
		}

		this.mCurrentSubSequenceModifierIndex = 0;
		this.mFinished = false;
		this.mSecondsElapsed = 0;

		this.mSubSequenceModifiers[0].addModifierListener(this);

		final IModifier<T>[] shapeModifiers = this.mSubSequenceModifiers;
		for(int i = shapeModifiers.length - 1; i >= 0; i--) {
			shapeModifiers[i].reset();
		}
	}

        /**
         * 
         * @param pModifier
         * @param pItem
         */
        @Override
	public void onModifierStarted(final IModifier<T> pModifier, final T pItem) {
		if(this.mCurrentSubSequenceModifierIndex == 0) {
			this.onModifierStarted(pItem);
		}

		if(this.mSubSequenceModifierListener != null) {
			this.mSubSequenceModifierListener.onSubSequenceStarted(pModifier, pItem, this.mCurrentSubSequenceModifierIndex);
		}
	}

        /**
         * 
         * @param pModifier
         * @param pItem
         */
        @Override
	public void onModifierFinished(final IModifier<T> pModifier, final T pItem) {
		if(this.mSubSequenceModifierListener != null) {
			this.mSubSequenceModifierListener.onSubSequenceFinished(pModifier, pItem, this.mCurrentSubSequenceModifierIndex);
		}
		pModifier.removeModifierListener(this);

		this.mCurrentSubSequenceModifierIndex++;

		if(this.mCurrentSubSequenceModifierIndex < this.mSubSequenceModifiers.length) {
			final IModifier<T> nextSubSequenceModifier = this.mSubSequenceModifiers[this.mCurrentSubSequenceModifierIndex];
			nextSubSequenceModifier.addModifierListener(this);
		} else {
			this.mFinished = true;
			this.mFinishedCached = true;

			this.onModifierFinished(pItem);
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

        /**
         * 
         * @param <T>
         */
        public interface ISubSequenceModifierListener<T> {
            /**
             * 
             * @param pModifier
             * @param pItem
             * @param pIndex
             */
            public void onSubSequenceStarted(final IModifier<T> pModifier, final T pItem, final int pIndex);
                /**
                 * 
                 * @param pModifier
                 * @param pItem
                 * @param pIndex
                 */
                public void onSubSequenceFinished(final IModifier<T> pModifier, final T pItem, final int pIndex);
	}
}
