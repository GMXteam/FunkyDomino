package org.andengine.input.touch.detector;

import org.andengine.input.touch.TouchEvent;

import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

/**
 * @author rkpost
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 11:36:26 - 11.10.2010
 */
public abstract class SurfaceGestureDetector extends BaseDetector {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final float SWIPE_MIN_DISTANCE_DEFAULT = 120;

	// ===========================================================
	// Fields
	// ===========================================================

	private final GestureDetector mGestureDetector;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         */
        public SurfaceGestureDetector() {
		this(SurfaceGestureDetector.SWIPE_MIN_DISTANCE_DEFAULT);
	}

        /**
         * 
         * @param pSwipeMinDistance
         */
        public SurfaceGestureDetector(final float pSwipeMinDistance) {
		this.mGestureDetector = new GestureDetector(new InnerOnGestureDetectorListener(pSwipeMinDistance));
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @return
         */
        protected abstract boolean onSingleTap();
        /**
         * 
         * @return
         */
        protected abstract boolean onDoubleTap();
        /**
         * 
         * @return
         */
        protected abstract boolean onSwipeUp();
        /**
         * 
         * @return
         */
        protected abstract boolean onSwipeDown();
        /**
         * 
         * @return
         */
        protected abstract boolean onSwipeLeft();
        /**
         * 
         * @return
         */
        protected abstract boolean onSwipeRight();

	@Override
	public void reset() {

	}

        /**
         * 
         * @param pSceneTouchEvent
         * @return
         */
        @Override
	public boolean onManagedTouchEvent(final TouchEvent pSceneTouchEvent) {
		return this.mGestureDetector.onTouchEvent(pSceneTouchEvent.getMotionEvent());
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	private class InnerOnGestureDetectorListener extends SimpleOnGestureListener {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Fields
		// ===========================================================

		private final float mSwipeMinDistance;

		// ===========================================================
		// Constructors
		// ===========================================================

		public InnerOnGestureDetectorListener(final float pSwipeMinDistance) {
			this.mSwipeMinDistance = pSwipeMinDistance;
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

		@Override
		public boolean onSingleTapConfirmed(final MotionEvent pMotionEvent) {
			return SurfaceGestureDetector.this.onSingleTap();
		}

		@Override
		public boolean onDoubleTap(final MotionEvent pMotionEvent) {
			return SurfaceGestureDetector.this.onDoubleTap();
		}

		@Override
		public boolean onFling(final MotionEvent pMotionEventStart, final MotionEvent pMotionEventEnd, final float pVelocityX, final float pVelocityY) {
			final float swipeMinDistance = this.mSwipeMinDistance;

			final boolean isHorizontalFling = Math.abs(pVelocityX) > Math.abs(pVelocityY);

			if(isHorizontalFling) {
				if(pMotionEventStart.getX() - pMotionEventEnd.getX() > swipeMinDistance) {
					return SurfaceGestureDetector.this.onSwipeLeft();
				} else if(pMotionEventEnd.getX() - pMotionEventStart.getX() > swipeMinDistance) {
					return SurfaceGestureDetector.this.onSwipeRight();
				}
			} else {
				if(pMotionEventStart.getY() - pMotionEventEnd.getY() > swipeMinDistance) {
					return SurfaceGestureDetector.this.onSwipeUp();
				} else if(pMotionEventEnd.getY() - pMotionEventStart.getY() > swipeMinDistance) {
					return SurfaceGestureDetector.this.onSwipeDown();
				}
			}

			return false;
		}

		// ===========================================================
		// Methods
		// ===========================================================

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}

        /**
         * 
         */
        public static class SurfaceGestureDetectorAdapter extends SurfaceGestureDetector {
		// ===========================================================
		// Constants
		// ===========================================================

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

            /**
             * 
             * @return
             */
            @Override
		protected boolean onDoubleTap() {
			return false;
		}

		@Override
		protected boolean onSingleTap() {
			return false;
		}

                /**
                 * 
                 * @return
                 */
                @Override
		protected boolean onSwipeDown() {
			return false;
		}

                /**
                 * 
                 * @return
                 */
                @Override
		protected boolean onSwipeLeft() {
			return false;
		}

                /**
                 * 
                 * @return
                 */
                @Override
		protected boolean onSwipeRight() {
			return false;
		}

                /**
                 * 
                 * @return
                 */
                @Override
		protected boolean onSwipeUp() {
			return false;
		}

		// ===========================================================
		// Methods
		// ===========================================================

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}
}