package org.anddev.andengine.examples.game.snake.entity;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 23:39:05 - 11.07.2010
 */
public interface ICellEntity {
	// ===========================================================
	// Constants
	// ===========================================================
	
    /**
     * 
     * @return
     */
    public abstract int getCellX();
        /**
         * 
         * @return
         */
        public abstract int getCellY();

        /**
         * 
         * @param pCellEntity
         */
        public abstract void setCell(final ICellEntity pCellEntity);
        /**
         * 
         * @param pCellX
         * @param pCellY
         */
        public abstract void setCell(final int pCellX, final int pCellY);

        /**
         * 
         * @param pCellEntity
         * @return
         */
        public abstract boolean isInSameCell(final ICellEntity pCellEntity);
}