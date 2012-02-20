package org.andengine.util.algorithm.path;

import org.andengine.util.algorithm.path.astar.IAStarHeuristic;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @param <T> 
 * @author Nicolas Gramlich
 * @since 22:57:13 - 16.08.2010
 */
public interface IPathFinder<T> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

    /**
     * 
     * @param pPathFinderMap
     * @param pXMin
     * @param pYMin
     * @param pXMax
     * @param pYMax
     * @param pEntity
     * @param pFromX
     * @param pFromY
     * @param pToX
     * @param pToY
     * @param pAllowDiagonal
     * @param pAStarHeuristic
     * @param pCostFunction
     * @return
     */
    public Path findPath(final IPathFinderMap<T> pPathFinderMap, final int pXMin, final int pYMin, final int pXMax, final int pYMax, final T pEntity, final int pFromX, final int pFromY, final int pToX, final int pToY, final boolean pAllowDiagonal, final IAStarHeuristic<T> pAStarHeuristic, final ICostFunction<T> pCostFunction);
    /**
     * 
     * @param pPathFinderMap
     * @param pXMin
     * @param pYMin
     * @param pXMax
     * @param pYMax
     * @param pEntity
     * @param pFromX
     * @param pFromY
     * @param pToX
     * @param pToY
     * @param pAllowDiagonal
     * @param pAStarHeuristic
     * @param pCostFunction
     * @param pMaxCost
     * @return
     */
    public Path findPath(final IPathFinderMap<T> pPathFinderMap, final int pXMin, final int pYMin, final int pXMax, final int pYMax, final T pEntity, final int pFromX, final int pFromY, final int pToX, final int pToY, final boolean pAllowDiagonal, final IAStarHeuristic<T> pAStarHeuristic, final ICostFunction<T> pCostFunction, final float pMaxCost);
        /**
         * 
         * @param pPathFinderMap
         * @param pXMin
         * @param pYMin
         * @param pXMax
         * @param pYMax
         * @param pEntity
         * @param pFromX
         * @param pFromY
         * @param pToX
         * @param pToY
         * @param pAllowDiagonal
         * @param pAStarHeuristic
         * @param pCostFunction
         * @param pMaxCost
         * @param pPathFinderListener
         * @return
         */
        public Path findPath(final IPathFinderMap<T> pPathFinderMap, final int pXMin, final int pYMin, final int pXMax, final int pYMax, final T pEntity, final int pFromX, final int pFromY, final int pToX, final int pToY, final boolean pAllowDiagonal, final IAStarHeuristic<T> pAStarHeuristic, final ICostFunction<T> pCostFunction, final float pMaxCost, final IPathFinderListener<T> pPathFinderListener);

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
	
        /**
         * 
         * @param <T>
         */
        public interface IPathFinderListener<T> {
		// ===========================================================
		// Constants
		// ===========================================================
	
		// ===========================================================
		// Fields
		// ===========================================================
	
            /**
             * 
             * @param pEntity
             * @param pX
             * @param pY
             */
            public void onVisited(final T pEntity, final int pX, final int pY);
	}
}
