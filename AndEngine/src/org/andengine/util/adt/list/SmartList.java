package org.andengine.util.adt.list;

import java.util.ArrayList;
import java.util.List;

import org.andengine.util.IMatcher;
import org.andengine.util.call.ParameterCallable;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @param <T> 
 * @author Nicolas Gramlich
 * @since 22:20:08 - 27.12.2010
 */
public class SmartList<T> extends ArrayList<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 8655669528273139819L;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         */
        public SmartList() {

	}

        /**
         * 
         * @param pCapacity
         */
        public SmartList(final int pCapacity) {
		super(pCapacity);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	/**
	 * @param pItem the item to remove.
         * @param pParameterCallable to be called with the removed item, if it was removed.
         * @return  
	 */
	public boolean remove(final T pItem, final ParameterCallable<T> pParameterCallable) {
		final boolean removed = this.remove(pItem);
		if(removed) {
			pParameterCallable.call(pItem);
		}
		return removed;
	}

        /**
         * 
         * @param pMatcher
         * @return
         */
        public T remove(final IMatcher<T> pMatcher) {
		for(int i = 0; i < this.size(); i++) {
			if(pMatcher.matches(this.get(i))) {
				return this.remove(i);
			}
		}
		return null;
	}

        /**
         * 
         * @param pMatcher
         * @param pParameterCallable
         * @return
         */
        public T remove(final IMatcher<T> pMatcher, final ParameterCallable<T> pParameterCallable) {
		for(int i = this.size() - 1; i >= 0; i--) {
			if(pMatcher.matches(this.get(i))) {
				final T removed = this.remove(i);
				pParameterCallable.call(removed);
				return removed;
			}
		}
		return null;
	}

        /**
         * 
         * @param pMatcher
         * @return
         */
        public boolean removeAll(final IMatcher<T> pMatcher) {
		boolean result = false;
		for(int i = this.size() - 1; i >= 0; i--) {
			if(pMatcher.matches(this.get(i))) {
				this.remove(i);
				result = true;
			}
		}
		return result;
	}

	/**
	 * @param pMatcher to find the items.
         * @param pParameterCallable to be called with each matched item after it was removed.
         * @return  
	 */
	public boolean removeAll(final IMatcher<T> pMatcher, final ParameterCallable<T> pParameterCallable) {
		boolean result = false;
		for(int i = this.size() - 1; i >= 0; i--) {
			if(pMatcher.matches(this.get(i))) {
				final T removed = this.remove(i);
				pParameterCallable.call(removed);
				result = true;
			}
		}
		return result;
	}

        /**
         * 
         * @param pParameterCallable
         */
        public void clear(final ParameterCallable<T> pParameterCallable) {
		for(int i = this.size() - 1; i >= 0; i--) {
			final T removed = this.remove(i);
			pParameterCallable.call(removed);
		}
	}

        /**
         * 
         * @param pMatcher
         * @return
         */
        public int indexOf(final IMatcher<T> pMatcher) {
		final int size = this.size();
		for(int i = 0; i < size; i++) {
			final T item = this.get(i);
			if(pMatcher.matches(item)) {
				return i;
			}
		}
		return -1;
	}

        /**
         * 
         * @param pMatcher
         * @return
         */
        public int lastIndexOf(final IMatcher<T> pMatcher) {
		for(int i = this.size() - 1; i >= 0; i--) {
			final T item = this.get(i);
			if(pMatcher.matches(item)) {
				return i;
			}
		}
		return -1;
	}

        /**
         * 
         * @param pMatcher
         * @return
         */
        public ArrayList<T> query(final IMatcher<T> pMatcher) {
		return this.query(pMatcher, new ArrayList<T>());
	}

        /**
         * 
         * @param <L>
         * @param pMatcher
         * @param pResult
         * @return
         */
        public <L extends List<T>> L query(final IMatcher<T> pMatcher, final L pResult) {
		final int size = this.size();
		for(int i = 0; i < size; i++) {
			final T item = this.get(i);
			if(pMatcher.matches(item)) {
				pResult.add(item);
			}
		}

		return pResult;
	}

        /**
         * 
         * @param <S>
         * @param pMatcher
         * @return
         */
        public <S extends T> ArrayList<S> queryForSubclass(final IMatcher<T> pMatcher) {
		return this.queryForSubclass(pMatcher, new ArrayList<S>());
	}

        /**
         * 
         * @param <L>
         * @param <S>
         * @param pMatcher
         * @param pResult
         * @return
         */
        @SuppressWarnings("unchecked")
	public <L extends List<S>, S extends T> L queryForSubclass(final IMatcher<T> pMatcher, final L pResult) {
		final int size = this.size();
		for(int i = 0; i < size; i++) {
			final T item = this.get(i);
			if(pMatcher.matches(item)) {
				pResult.add((S)item);
			}
		}

		return pResult;
	}

        /**
         * 
         * @param pParameterCallable
         */
        public void call(final ParameterCallable<T> pParameterCallable) {
		for(int i = this.size() - 1; i >= 0; i--) {
			final T item = this.get(i);
			pParameterCallable.call(item);
		}
	}

        /**
         * 
         * @param pMatcher
         * @param pParameterCallable
         */
        public void call(final IMatcher<T> pMatcher, final ParameterCallable<T> pParameterCallable) {
		for(int i = this.size() - 1; i >= 0; i--) {
			final T item = this.get(i);
			if(pMatcher.matches(item)) {
				pParameterCallable.call(item);
			}
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
