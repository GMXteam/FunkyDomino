package org.andengine.util.algorithm.path.astar;

import org.andengine.util.algorithm.path.IPathFinderMap;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @param <T> 
 * @author Nicolas Gramlich
 * @since 22:59:20 - 16.08.2010
 */
public interface IAStarHeuristic<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

    /**
     * 
     * @param pPathFinderMap
     * @param pEntity
     * @param pFromX
     * @param pFromY
     * @param pToX
     * @param pToY
     * @return
     */
    public float getExpectedRestCost(final IPathFinderMap<T> pPathFinderMap, final T pEntity, final int pFromX, final int pFromY, final int pToX, final int pToY);
}
