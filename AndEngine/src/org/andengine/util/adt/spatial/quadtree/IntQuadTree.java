package org.andengine.util.adt.spatial.quadtree;

import java.util.ArrayList;
import java.util.List;

import org.andengine.util.IMatcher;
import org.andengine.util.adt.bounds.BoundsSplit;
import org.andengine.util.adt.bounds.IIntBounds;
import org.andengine.util.adt.bounds.IntBounds;
import org.andengine.util.adt.bounds.BoundsSplit.BoundsSplitException;
import org.andengine.util.adt.spatial.ISpatialItem;
import org.andengine.util.adt.spatial.bounds.util.IntBoundsUtils;


/**
 * (c) Zynga 2011
 *
 * @param <T> 
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 20:22:18 - 10.10.2011
 */
public class IntQuadTree<T extends ISpatialItem<IIntBounds>> extends QuadTree<IIntBounds, T> implements IIntBounds {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final IntBounds mQueryIntBounds = new IntBounds(0, 0, 0, 0);

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pIntBounds
         */
        public IntQuadTree(final IIntBounds pIntBounds) {
		super(pIntBounds);
	}

        /**
         * 
         * @param pIntBounds
         * @param pMaxLevel
         */
        public IntQuadTree(final IIntBounds pIntBounds, final int pMaxLevel) {
		super(pIntBounds, pMaxLevel);
	}

        /**
         * 
         * @param pIntBounds
         * @return
         */
        @Override
	protected IntQuadTreeNode initRoot(final IIntBounds pIntBounds) {
		return new IntQuadTreeNode(QuadTree.LEVEL_ROOT, pIntBounds);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        @Override
	public int getXMin() {
		return this.getRoot().getXMin();
	}

	@Override
	public int getYMin() {
		return this.getRoot().getYMin();
	}

	@Override
	public int getXMax() {
		return this.getRoot().getXMax();
	}

        /**
         * 
         * @return
         */
        @Override
	public int getYMax() {
		return this.getRoot().getYMax();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @return
         */
        @SuppressWarnings("unchecked")
	@Override
	protected IntQuadTreeNode getRoot() {
		return (IntQuadTreeNode) this.mRoot;
	}

	// ===========================================================
	// Methods
	// ===========================================================

        /**
         * 
         * @param pX
         * @param pY
         * @return
         */
        public synchronized ArrayList<T> query(final int pX, final int pY) {
		this.mQueryIntBounds.set(pX, pY);
		return this.query(this.mQueryIntBounds);
	}

        /**
         * 
         * @param <L>
         * @param pX
         * @param pY
         * @param pResult
         * @return
         */
        public synchronized <L extends List<T>> L query(final int pX, final int pY, final L pResult) {
		this.mQueryIntBounds.set(pX, pY);
		return this.query(this.mQueryIntBounds, pResult);
	}

        /**
         * 
         * @param pX
         * @param pY
         * @param pMatcher
         * @return
         */
        public synchronized ArrayList<T> query(final int pX, final int pY, final IMatcher<T> pMatcher) {
		this.mQueryIntBounds.set(pX, pY);
		return this.query(this.mQueryIntBounds, pMatcher);
	}

        /**
         * 
         * @param <L>
         * @param pX
         * @param pY
         * @param pMatcher
         * @param pResult
         * @return
         */
        public synchronized <L extends List<T>> L query(final int pX, final int pY, final IMatcher<T> pMatcher, final L pResult) {
		this.mQueryIntBounds.set(pX, pY);
		return this.query(this.mQueryIntBounds, pMatcher, pResult);
	}

        /**
         * 
         * @param pXMin
         * @param pYMin
         * @param pXMax
         * @param pYMax
         * @return
         */
        public synchronized ArrayList<T> query(final int pXMin, final int pYMin, final int pXMax, final int pYMax) {
		this.mQueryIntBounds.set(pXMin, pYMin, pXMax, pYMax);
		return this.query(this.mQueryIntBounds);
	}

        /**
         * 
         * @param <L>
         * @param pXMin
         * @param pYMin
         * @param pXMax
         * @param pYMax
         * @param pResult
         * @return
         */
        public synchronized <L extends List<T>> L query(final int pXMin, final int pYMin, final int pXMax, final int pYMax, final L pResult) {
		this.mQueryIntBounds.set(pXMin, pYMin, pXMax, pYMax);
		return this.query(this.mQueryIntBounds, pResult);
	}

        /**
         * 
         * @param pXMin
         * @param pYMin
         * @param pXMax
         * @param pYMax
         * @param pMatcher
         * @return
         */
        public synchronized ArrayList<T> query(final int pXMin, final int pYMin, final int pXMax, final int pYMax, final IMatcher<T> pMatcher) {
		this.mQueryIntBounds.set(pXMin, pYMin, pXMax, pYMax);
		return this.query(this.mQueryIntBounds, pMatcher);
	}

        /**
         * 
         * @param <L>
         * @param pXMin
         * @param pYMin
         * @param pXMax
         * @param pYMax
         * @param pMatcher
         * @param pResult
         * @return
         */
        public synchronized <L extends List<T>> L query(final int pXMin, final int pYMin, final int pXMax, final int pYMax, final IMatcher<T> pMatcher, final L pResult) {
		this.mQueryIntBounds.set(pXMin, pYMin, pXMax, pYMax);
		return this.query(this.mQueryIntBounds, pMatcher, pResult);
	}

        /**
         * 
         * @param <L>
         * @param <S>
         * @param pX
         * @param pY
         * @param pMatcher
         * @param pResult
         * @return
         * @throws ClassCastException
         */
        public synchronized <L extends List<S>, S extends T> L queryForSubclass(final int pX, final int pY, final IMatcher<T> pMatcher, final L pResult) throws ClassCastException {
		this.mQueryIntBounds.set(pX, pY);
		return this.queryForSubclass(this.mQueryIntBounds, pMatcher, pResult);
	}

        /**
         * 
         * @param <L>
         * @param <S>
         * @param pXMin
         * @param pYMin
         * @param pXMax
         * @param pYMax
         * @param pMatcher
         * @param pResult
         * @return
         * @throws ClassCastException
         */
        public synchronized <L extends List<S>, S extends T> L queryForSubclass(final int pXMin, final int pYMin, final int pXMax, final int pYMax, final IMatcher<T> pMatcher, final L pResult) throws ClassCastException {
		this.mQueryIntBounds.set(pXMin, pYMin, pXMax, pYMax);
		return this.queryForSubclass(this.mQueryIntBounds, pMatcher, pResult);
	}

        /**
         * 
         * @param pX
         * @param pY
         * @return
         */
        public synchronized boolean containsAny(final int pX, final int pY) {
		this.mQueryIntBounds.set(pX, pY);
		return this.containsAny(this.mQueryIntBounds);
	}

        /**
         * 
         * @param pXMin
         * @param pYMin
         * @param pXMax
         * @param pYMax
         * @return
         */
        public synchronized boolean containsAny(final int pXMin, final int pYMin, final int pXMax, final int pYMax) {
		this.mQueryIntBounds.set(pXMin, pYMin, pXMax, pYMax);
		return this.containsAny(this.mQueryIntBounds);
	}

        /**
         * 
         * @param pX
         * @param pY
         * @param pMatcher
         * @return
         */
        public synchronized boolean containsAny(final int pX, final int pY, final IMatcher<T> pMatcher) {
		this.mQueryIntBounds.set(pX, pY);
		return this.containsAny(this.mQueryIntBounds, pMatcher);
	}

        /**
         * 
         * @param pXMin
         * @param pYMin
         * @param pXMax
         * @param pYMax
         * @param pMatcher
         * @return
         */
        public synchronized boolean containsAny(final int pXMin, final int pYMin, final int pXMax, final int pYMax, final IMatcher<T> pMatcher) {
		this.mQueryIntBounds.set(pXMin, pYMin, pXMax, pYMax);
		return this.containsAny(this.mQueryIntBounds, pMatcher);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

        /**
         * 
         */
        public class IntQuadTreeNode extends QuadTreeNode implements IIntBounds {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Fields
		// ===========================================================

		private final int mXMin;
		private final int mYMin;
		private final int mXMax;
		private final int mYMax;

		// ===========================================================
		// Constructors
		// ===========================================================

                /**
                 * 
                 * @param pLevel
                 * @param pIntBounds
                 */
                public IntQuadTreeNode(final int pLevel, final IIntBounds pIntBounds) {
			this(pLevel, pIntBounds.getXMin(), pIntBounds.getYMin(), pIntBounds.getXMax(), pIntBounds.getYMax());
		}

                /**
                 * 
                 * @param pLevel
                 * @param pXMin
                 * @param pYMin
                 * @param pXMax
                 * @param pYMax
                 */
                public IntQuadTreeNode(final int pLevel, final int pXMin, final int pYMin, final int pXMax, final int pYMax) {
			super(pLevel);

			this.mXMin = pXMin;
			this.mYMin = pYMin;
			this.mXMax = pXMax;
			this.mYMax = pYMax;

			if(pXMin > pXMax) {
				throw new IllegalArgumentException("pXMin must be smaller or equal to pXMax.");
			}
			if(pYMin > pYMax) {
				throw new IllegalArgumentException("pYMin must be smaller or equal to pYMax.");
			}
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================

		@Override
		public int getXMin() {
			return this.mXMin;
		}
		
                /**
                 * 
                 * @return
                 */
                @Override
		public int getYMin() {
			return this.mYMin;
		}

                /**
                 * 
                 * @return
                 */
                @Override
		public int getXMax() {
			return this.mXMax;
		}

                /**
                 * 
                 * @return
                 */
                @Override
		public int getYMax() {
			return this.mYMax;
		}

                /**
                 * 
                 * @return
                 */
                public int getWidth() {
			return this.mXMax - this.mXMin + 1;
		}

                /**
                 * 
                 * @return
                 */
                public int getHeight() {
			return this.mYMax - this.mYMin + 1;
		}

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

                /**
                 * 
                 * @param pBoundsSplit
                 * @return
                 */
                @Override
		protected IntQuadTreeNode split(final BoundsSplit pBoundsSplit) {
			final int width = this.getWidth();
			final int height = this.getHeight();

			if(width <= 2 && height <= 2) {
				throw new BoundsSplitException();
			}

			final int xMin = this.getXMin(pBoundsSplit);
			final int yMin = this.getYMin(pBoundsSplit);
			final int xMax = this.getXMax(pBoundsSplit);
			final int yMax = this.getYMax(pBoundsSplit);

			return new IntQuadTreeNode(this.mLevel + 1, xMin, yMin, xMax, yMax);
		}

		@Override
		protected boolean contains(final IIntBounds pIntBounds) {
			return this.contains(pIntBounds.getXMin(), pIntBounds.getYMin(), pIntBounds.getXMax(), pIntBounds.getYMax());
		}

                /**
                 * 
                 * @param pBoundsSplit
                 * @param pIntBounds
                 * @return
                 */
                @Override
		protected boolean contains(final BoundsSplit pBoundsSplit, final IIntBounds pIntBounds) {
			return IntBoundsUtils.contains(this.getXMin(pBoundsSplit), this.getYMin(pBoundsSplit), this.getXMax(pBoundsSplit), this.getYMax(pBoundsSplit), pIntBounds.getXMin(), pIntBounds.getYMin(), pIntBounds.getXMax(), pIntBounds.getYMax());
		}

		@Override
		protected boolean intersects(final IIntBounds pIntBounds) {
			return IntBoundsUtils.intersects(this.mXMin, this.mYMin, this.mXMax, this.mYMax, pIntBounds.getXMin(), pIntBounds.getYMin(), pIntBounds.getXMax(), pIntBounds.getYMax());
		}

                /**
                 * 
                 * @param pIntBoundsA
                 * @param pIntBoundsB
                 * @return
                 */
                @Override
		protected boolean intersects(final IIntBounds pIntBoundsA, final IIntBounds pIntBoundsB) {
			return IntBoundsUtils.intersects(pIntBoundsA, pIntBoundsB);
		}

                /**
                 * 
                 * @param pBounds
                 * @return
                 */
                @Override
		protected boolean containedBy(final IIntBounds pBounds) {
			return IntBoundsUtils.contains(pBounds.getXMin(), pBounds.getYMin(), pBounds.getXMax(), pBounds.getYMax(), this.mXMin, this.mYMin, this.mXMax, this.mYMax);
		}

		@Override
		protected void appendBoundsToString(final StringBuilder pStringBuilder) {
			pStringBuilder
				.append("[XMin: ")
				.append(this.mXMin)
				.append(", YMin: ")
				.append(this.mYMin)
				.append(", XMax: ")
				.append(this.mXMax)
				.append(", YMax: ")
				.append(this.mYMax)
				.append("]");
		}

		// ===========================================================
		// Methods
		// ===========================================================

		private int getXMin(final BoundsSplit pBoundsSplit) {
			final int width = this.getWidth();
			final int halfWidth = width / 2;

			if(width <= 2) {
				switch(pBoundsSplit) {
					case TOP_LEFT:
					case BOTTOM_LEFT:
						return this.mXMin;
					case BOTTOM_RIGHT:
					case TOP_RIGHT:
						throw new BoundsSplitException();
					default:
						throw new IllegalArgumentException("Unexpected " + BoundsSplit.class.getSimpleName() + ": '" + pBoundsSplit + "'.");
				}
			} else {
				switch(pBoundsSplit) {
					case TOP_LEFT:
						return this.mXMin;
					case TOP_RIGHT:
						return this.mXMin + halfWidth;
					case BOTTOM_LEFT:
						return this.mXMin;
					case BOTTOM_RIGHT:
						return this.mXMin + halfWidth;
					default:
						throw new IllegalArgumentException("Unexpected " + BoundsSplit.class.getSimpleName() + ": '" + pBoundsSplit + "'.");
				}
			}
		}
		
		private int getYMin(final BoundsSplit pBoundsSplit) {
			final int height = this.getHeight();
			final int halfHeight = height / 2;
			
			if(height <= 2) {
				switch(pBoundsSplit) {
					case TOP_LEFT:
					case TOP_RIGHT:
						return  this.mYMin;
					case BOTTOM_LEFT:
					case BOTTOM_RIGHT:
						throw new BoundsSplitException();
					default:
						throw new IllegalArgumentException("Unexpected " + BoundsSplit.class.getSimpleName() + ": '" + pBoundsSplit + "'.");
				}
			} else {
				switch(pBoundsSplit) {
					case TOP_LEFT:
						return  this.mYMin;
					case TOP_RIGHT:
						return this.mYMin;
					case BOTTOM_LEFT:
						return this.mYMin + halfHeight;
					case BOTTOM_RIGHT:
						return this.mYMin + halfHeight;
					default:
						throw new IllegalArgumentException("Unexpected " + BoundsSplit.class.getSimpleName() + ": '" + pBoundsSplit + "'.");
				}
			}
		}

		private int getXMax(final BoundsSplit pBoundsSplit) {
			final int width = this.getWidth();
			final int halfWidth = width / 2;

			if(width <= 2) {
				switch(pBoundsSplit) {
					case TOP_LEFT:
					case BOTTOM_LEFT:
						return this.mXMax;
					case BOTTOM_RIGHT:
					case TOP_RIGHT:
						throw new BoundsSplitException();
					default:
						throw new IllegalArgumentException("Unexpected " + BoundsSplit.class.getSimpleName() + ": '" + pBoundsSplit + "'.");
				}
			} else {
				switch(pBoundsSplit) {
					case TOP_LEFT:
						return this.mXMin + halfWidth;
					case TOP_RIGHT:
						return this.mXMax;
					case BOTTOM_LEFT:
						return this.mXMin + halfWidth;
					case BOTTOM_RIGHT:
						return this.mXMax;
					default:
						throw new IllegalArgumentException("Unexpected " + BoundsSplit.class.getSimpleName() + ": '" + pBoundsSplit + "'.");
				}
			}
		}

		private int getYMax(final BoundsSplit pBoundsSplit) {
			final int height = this.getHeight();
			final int halfHeight = height / 2;

			if(height <= 2) {
				switch(pBoundsSplit) {
					case TOP_LEFT:
					case TOP_RIGHT:
						return this.mYMax;
					case BOTTOM_LEFT:
					case BOTTOM_RIGHT:
						throw new BoundsSplitException();
					default:
						throw new IllegalArgumentException("Unexpected " + BoundsSplit.class.getSimpleName() + ": '" + pBoundsSplit + "'.");
				}
			} else {
				switch(pBoundsSplit) {
					case TOP_LEFT:
						return this.mYMin + halfHeight;
					case TOP_RIGHT:
						return this.mYMin + halfHeight;
					case BOTTOM_LEFT:
						return this.mYMax;
					case BOTTOM_RIGHT:
						return this.mYMax;
					default:
						throw new IllegalArgumentException("Unexpected " + BoundsSplit.class.getSimpleName() + ": '" + pBoundsSplit + "'.");
				}
			}
		}

                /**
                 * 
                 * @param pXMin
                 * @param pYMin
                 * @param pXMax
                 * @param pYMax
                 * @return
                 */
                public boolean intersects(final int pXMin, final int pYMin, final int pXMax, final int pYMax) {
			return IntBoundsUtils.intersects(this.mXMin, this.mYMin, this.mXMax, this.mYMax, pXMin, pYMin, pXMax, pYMax);
		}

                /**
                 * 
                 * @param pXMin
                 * @param pYMin
                 * @param pXMax
                 * @param pYMax
                 * @return
                 */
                public boolean contains(final int pXMin, final int pYMin, final int pXMax, final int pYMax) {
			return IntBoundsUtils.contains(this.mXMin, this.mYMin, this.mXMax, this.mYMax, pXMin, pYMin, pXMax, pYMax);
		}

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}
}
