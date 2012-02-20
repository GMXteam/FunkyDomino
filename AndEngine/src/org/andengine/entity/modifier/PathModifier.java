package org.andengine.entity.modifier;

import org.andengine.entity.IEntity;
import org.andengine.util.modifier.IModifier;
import org.andengine.util.modifier.SequenceModifier;
import org.andengine.util.modifier.SequenceModifier.ISubSequenceModifierListener;
import org.andengine.util.modifier.ease.EaseLinear;
import org.andengine.util.modifier.ease.IEaseFunction;

import android.util.FloatMath;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 16:50:02 - 16.06.2010
 */
public class PathModifier extends EntityModifier {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final SequenceModifier<IEntity> mSequenceModifier;

	private IPathModifierListener mPathModifierListener;

	private final Path mPath;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pDuration
         * @param pPath
         */
        public PathModifier(final float pDuration, final Path pPath) {
		this(pDuration, pPath, null, null, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pDuration
         * @param pPath
         * @param pEaseFunction
         */
        public PathModifier(final float pDuration, final Path pPath, final IEaseFunction pEaseFunction) {
		this(pDuration, pPath, null, null, pEaseFunction);
	}

        /**
         * 
         * @param pDuration
         * @param pPath
         * @param pEntityModiferListener
         */
        public PathModifier(final float pDuration, final Path pPath, final IEntityModifierListener pEntityModiferListener) {
		this(pDuration, pPath, pEntityModiferListener, null, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pDuration
         * @param pPath
         * @param pPathModifierListener
         */
        public PathModifier(final float pDuration, final Path pPath, final IPathModifierListener pPathModifierListener) {
		this(pDuration, pPath, null, pPathModifierListener, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pDuration
         * @param pPath
         * @param pPathModifierListener
         * @param pEaseFunction
         */
        public PathModifier(final float pDuration, final Path pPath, final IPathModifierListener pPathModifierListener, final IEaseFunction pEaseFunction) {
		this(pDuration, pPath, null, pPathModifierListener, pEaseFunction);
	}

        /**
         * 
         * @param pDuration
         * @param pPath
         * @param pEntityModiferListener
         * @param pEaseFunction
         */
        public PathModifier(final float pDuration, final Path pPath, final IEntityModifierListener pEntityModiferListener, final IEaseFunction pEaseFunction) {
		this(pDuration, pPath, pEntityModiferListener, null, pEaseFunction);
	}

        /**
         * 
         * @param pDuration
         * @param pPath
         * @param pEntityModiferListener
         * @param pPathModifierListener
         * @throws IllegalArgumentException
         */
        public PathModifier(final float pDuration, final Path pPath, final IEntityModifierListener pEntityModiferListener, final IPathModifierListener pPathModifierListener) throws IllegalArgumentException {
		this(pDuration, pPath, pEntityModiferListener, pPathModifierListener, EaseLinear.getInstance());
	}

        /**
         * 
         * @param pDuration
         * @param pPath
         * @param pEntityModiferListener
         * @param pPathModifierListener
         * @param pEaseFunction
         * @throws IllegalArgumentException
         */
        public PathModifier(final float pDuration, final Path pPath, final IEntityModifierListener pEntityModiferListener, final IPathModifierListener pPathModifierListener, final IEaseFunction pEaseFunction) throws IllegalArgumentException {
		super(pEntityModiferListener);
		final int pathSize = pPath.getSize();

		if (pathSize < 2) {
			throw new IllegalArgumentException("Path needs at least 2 waypoints!");
		}

		this.mPath = pPath;
		this.mPathModifierListener = pPathModifierListener;

		final MoveModifier[] moveModifiers = new MoveModifier[pathSize - 1];

		final float[] coordinatesX = pPath.getCoordinatesX();
		final float[] coordinatesY = pPath.getCoordinatesY();

		final float velocity = pPath.getLength() / pDuration;

		final int modifierCount = moveModifiers.length;
		for(int i = 0; i < modifierCount; i++) {
			final float duration = pPath.getSegmentLength(i) / velocity;
			moveModifiers[i] = new MoveModifier(duration, coordinatesX[i], coordinatesX[i + 1], coordinatesY[i], coordinatesY[i + 1], null, pEaseFunction);
		}

		/* Create a new SequenceModifier and register the listeners that
		 * call through to mEntityModifierListener and mPathModifierListener. */
		this.mSequenceModifier = new SequenceModifier<IEntity>(
				new ISubSequenceModifierListener<IEntity>() {
					@Override
					public void onSubSequenceStarted(final IModifier<IEntity> pModifier, final IEntity pEntity, final int pIndex) {
						if(PathModifier.this.mPathModifierListener != null) {
							PathModifier.this.mPathModifierListener.onPathWaypointStarted(PathModifier.this, pEntity, pIndex);
						}
					}

					@Override
					public void onSubSequenceFinished(final IModifier<IEntity> pEntityModifier, final IEntity pEntity, final int pIndex) {
						if(PathModifier.this.mPathModifierListener != null) {
							PathModifier.this.mPathModifierListener.onPathWaypointFinished(PathModifier.this, pEntity, pIndex);
						}
					}
				},
				new IEntityModifierListener() {
					@Override
					public void onModifierStarted(final IModifier<IEntity> pModifier, final IEntity pEntity) {
						PathModifier.this.onModifierStarted(pEntity);
						if(PathModifier.this.mPathModifierListener != null) {
							PathModifier.this.mPathModifierListener.onPathStarted(PathModifier.this, pEntity);
						}
					}

					@Override
					public void onModifierFinished(final IModifier<IEntity> pEntityModifier, final IEntity pEntity) {
						PathModifier.this.onModifierFinished(pEntity);
						if(PathModifier.this.mPathModifierListener != null) {
							PathModifier.this.mPathModifierListener.onPathFinished(PathModifier.this, pEntity);
						}
					}
				},
				moveModifiers
		);
	}

        /**
         * 
         * @param pPathModifier
         * @throws org.andengine.util.modifier.IModifier.DeepCopyNotSupportedException
         */
        protected PathModifier(final PathModifier pPathModifier) throws DeepCopyNotSupportedException {
		this.mPath = pPathModifier.mPath.deepCopy();
		this.mSequenceModifier = pPathModifier.mSequenceModifier.deepCopy();
	}

	@Override
	public PathModifier deepCopy() throws DeepCopyNotSupportedException {
		return new PathModifier(this);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public Path getPath() {
		return this.mPath;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public boolean isFinished() {
		return this.mSequenceModifier.isFinished();
	}

        /**
         * 
         * @return
         */
        @Override
	public float getSecondsElapsed() {
		return this.mSequenceModifier.getSecondsElapsed();
	}

        /**
         * 
         * @return
         */
        @Override
	public float getDuration() {
		return this.mSequenceModifier.getDuration();
	}

        /**
         * 
         * @return
         */
        public IPathModifierListener getPathModifierListener() {
		return this.mPathModifierListener;
	}

        /**
         * 
         * @param pPathModifierListener
         */
        public void setPathModifierListener(final IPathModifierListener pPathModifierListener) {
		this.mPathModifierListener = pPathModifierListener;
	}

        /**
         * 
         */
        @Override
	public void reset() {
		this.mSequenceModifier.reset();
	}

	@Override
	public float onUpdate(final float pSecondsElapsed, final IEntity pEntity) {
		return this.mSequenceModifier.onUpdate(pSecondsElapsed, pEntity);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

        /**
         * 
         */
        public static interface IPathModifierListener {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Fields
		// ===========================================================

            /**
             * 
             * @param pPathModifier
             * @param pEntity
             */
            public void onPathStarted(final PathModifier pPathModifier, final IEntity pEntity);
                /**
                 * 
                 * @param pPathModifier
                 * @param pEntity
                 * @param pWaypointIndex
                 */
                public void onPathWaypointStarted(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex);
                /**
                 * 
                 * @param pPathModifier
                 * @param pEntity
                 * @param pWaypointIndex
                 */
                public void onPathWaypointFinished(final PathModifier pPathModifier, final IEntity pEntity, final int pWaypointIndex);
                /**
                 * 
                 * @param pPathModifier
                 * @param pEntity
                 */
                public void onPathFinished(final PathModifier pPathModifier, final IEntity pEntity);
	}

        /**
         * 
         */
        public static class Path {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Fields
		// ===========================================================

		private final float[] mCoordinatesX;
		private final float[] mCoordinatesY;

		private int mIndex;
		private boolean mLengthChanged = false;
		private float mLength;

		// ===========================================================
		// Constructors
		// ===========================================================

                /**
                 * 
                 * @param pLength
                 */
                public Path(final int pLength) {
			this.mCoordinatesX = new float[pLength];
			this.mCoordinatesY = new float[pLength];

			this.mIndex = 0;
			this.mLengthChanged = false;
		}

                /**
                 * 
                 * @param pCoordinatesX
                 * @param pCoordinatesY
                 * @throws IllegalArgumentException
                 */
                public Path(final float[] pCoordinatesX, final float[] pCoordinatesY) throws IllegalArgumentException {
			if (pCoordinatesX.length != pCoordinatesY.length) {
				throw new IllegalArgumentException("Coordinate-Arrays must have the same length.");
			}

			this.mCoordinatesX = pCoordinatesX;
			this.mCoordinatesY = pCoordinatesY;

			this.mIndex = pCoordinatesX.length;
			this.mLengthChanged = true;
		}

                /**
                 * 
                 * @param pPath
                 */
                public Path(final Path pPath) {
			final int size = pPath.getSize();
			this.mCoordinatesX = new float[size];
			this.mCoordinatesY = new float[size];

			System.arraycopy(pPath.mCoordinatesX, 0, this.mCoordinatesX, 0, size);
			System.arraycopy(pPath.mCoordinatesY, 0, this.mCoordinatesY, 0, size);

			this.mIndex = pPath.mIndex;
			this.mLengthChanged = pPath.mLengthChanged;
			this.mLength = pPath.mLength;
		}

                /**
                 * 
                 * @return
                 */
                public Path deepCopy() {
			return new Path(this);
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================

                /**
                 * 
                 * @param pX
                 * @param pY
                 * @return
                 */
                public Path to(final float pX, final float pY) {
			this.mCoordinatesX[this.mIndex] = pX;
			this.mCoordinatesY[this.mIndex] = pY;

			this.mIndex++;

			this.mLengthChanged = true;

			return this;
		}

                /**
                 * 
                 * @return
                 */
                public float[] getCoordinatesX() {
			return this.mCoordinatesX;
		}

                /**
                 * 
                 * @return
                 */
                public float[] getCoordinatesY() {
			return this.mCoordinatesY;
		}

                /**
                 * 
                 * @return
                 */
                public int getSize() {
			return this.mCoordinatesX.length;
		}

                /**
                 * 
                 * @return
                 */
                public float getLength() {
			if(this.mLengthChanged) {
				this.updateLength();
			}
			return this.mLength;
		}

                /**
                 * 
                 * @param pSegmentIndex
                 * @return
                 */
                public float getSegmentLength(final int pSegmentIndex) {
			final float[] coordinatesX = this.mCoordinatesX;
			final float[] coordinatesY = this.mCoordinatesY;

			final int nextSegmentIndex = pSegmentIndex + 1;

			final float dx = coordinatesX[pSegmentIndex] - coordinatesX[nextSegmentIndex];
			final float dy = coordinatesY[pSegmentIndex] - coordinatesY[nextSegmentIndex];

			return FloatMath.sqrt(dx * dx + dy * dy);
		}

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

		private void updateLength() {
			float length = 0.0f;

			for(int i = this.mIndex - 2; i >= 0; i--) {
				length += this.getSegmentLength(i);
			}
			this.mLength = length;
		}

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}
}
