package org.andengine.util.modifier;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.util.adt.list.SmartList;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @param <T> 
 * @author Nicolas Gramlich
 * @since 14:34:57 - 03.09.2010
 */
public class ModifierList<T> extends SmartList<IModifier<T>> implements IUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 1610345592534873475L;

	// ===========================================================
	// Fields
	// ===========================================================

	private final T mTarget;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pTarget
         */
        public ModifierList(final T pTarget) {
		this.mTarget = pTarget;
	}

        /**
         * 
         * @param pTarget
         * @param pCapacity
         */
        public ModifierList(final T pTarget, final int pCapacity){
		super(pCapacity);
		this.mTarget = pTarget;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public T getTarget() {
		return this.mTarget;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pModifier
         * @return
         */
        @Override
	public boolean add(IModifier<T> pModifier) {
		if(pModifier == null) {
			throw new IllegalArgumentException("Supplied " + IModifier.class.getSimpleName() + " must not be null.");
		} else {
			return super.add(pModifier);
		}
	}

        /**
         * 
         * @param pSecondsElapsed
         */
        @Override
	public void onUpdate(final float pSecondsElapsed) {
		final int modifierCount = this.size();
		if(modifierCount > 0) {
			for(int i = modifierCount - 1; i >= 0; i--) {
				final IModifier<T> modifier = this.get(i);
				modifier.onUpdate(pSecondsElapsed, this.mTarget);
				if(modifier.isFinished() && modifier.isAutoUnregisterWhenFinished()) {
					this.remove(i);
				}
			}
		}
	}

        /**
         * 
         */
        @Override
	public void reset() {
		for(int i = this.size() - 1; i >= 0; i--) {
			this.get(i).reset();
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
