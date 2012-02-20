package org.andengine.entity.scene;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.runnable.RunnableHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.scene.Scene.ITouchArea.ITouchAreaMatcher;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.IBackground;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.shape.Shape;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;
import org.andengine.util.Constants;
import org.andengine.util.IMatcher;
import org.andengine.util.adt.list.SmartList;
import org.andengine.util.color.Color;

import android.util.SparseArray;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 12:47:39 - 08.03.2010
 */
public class Scene extends Entity {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final int TOUCHAREAS_CAPACITY_DEFAULT = 4;

	// ===========================================================
	// Fields
	// ===========================================================

	private float mSecondsElapsedTotal;

        /**
         * 
         */
        protected Scene mParentScene;
        /**
         * 
         */
        protected Scene mChildScene;
	private boolean mChildSceneModalDraw;
	private boolean mChildSceneModalUpdate;
	private boolean mChildSceneModalTouch;

        /**
         * 
         */
        protected SmartList<ITouchArea> mTouchAreas = new SmartList<ITouchArea>(Scene.TOUCHAREAS_CAPACITY_DEFAULT);

	private final RunnableHandler mRunnableHandler = new RunnableHandler();

	private IOnSceneTouchListener mOnSceneTouchListener;

	private IOnAreaTouchListener mOnAreaTouchListener;

	private IBackground mBackground = new Background(Color.BLACK);
	private boolean mBackgroundEnabled = true;

	private boolean mOnAreaTouchTraversalBackToFront = true;

	private boolean mTouchAreaBindingOnActionDownEnabled = false;
	private boolean mTouchAreaBindingOnActionMoveEnabled = false;
	private final SparseArray<ITouchArea> mTouchAreaBindings = new SparseArray<ITouchArea>();
	private boolean mOnSceneTouchListenerBindingOnActionDownEnabled = false;
	private final SparseArray<IOnSceneTouchListener> mOnSceneTouchListenerBindings = new SparseArray<IOnSceneTouchListener>();

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         */
        public Scene() {

	}

        /**
         * 
         * @param pChildCount
         * @deprecated
         */
        @Deprecated
	public Scene(final int pChildCount) {
		for(int i = 0; i < pChildCount; i++) {
			this.attachChild(new Entity());
		}
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public float getSecondsElapsedTotal() {
		return this.mSecondsElapsedTotal;
	}

        /**
         * 
         * @return
         */
        public IBackground getBackground() {
		return this.mBackground;
	}

        /**
         * 
         * @param pBackground
         */
        public void setBackground(final IBackground pBackground) {
		this.mBackground = pBackground;
	}

        /**
         * 
         * @return
         */
        public boolean isBackgroundEnabled() {
		return this.mBackgroundEnabled;
	}

        /**
         * 
         * @param pEnabled
         */
        public void setBackgroundEnabled(final boolean pEnabled) {
		this.mBackgroundEnabled  = pEnabled;
	}

        /**
         * 
         * @param pOnSceneTouchListener
         */
        public void setOnSceneTouchListener(final IOnSceneTouchListener pOnSceneTouchListener) {
		this.mOnSceneTouchListener = pOnSceneTouchListener;
	}

        /**
         * 
         * @return
         */
        public IOnSceneTouchListener getOnSceneTouchListener() {
		return this.mOnSceneTouchListener;
	}

        /**
         * 
         * @return
         */
        public boolean hasOnSceneTouchListener() {
		return this.mOnSceneTouchListener != null;
	}

        /**
         * 
         * @param pOnAreaTouchListener
         */
        public void setOnAreaTouchListener(final IOnAreaTouchListener pOnAreaTouchListener) {
		this.mOnAreaTouchListener = pOnAreaTouchListener;
	}

        /**
         * 
         * @return
         */
        public IOnAreaTouchListener getOnAreaTouchListener() {
		return this.mOnAreaTouchListener;
	}

        /**
         * 
         * @return
         */
        public boolean hasOnAreaTouchListener() {
		return this.mOnAreaTouchListener != null;
	}

	private void setParentScene(final Scene pParentScene) {
		this.mParentScene = pParentScene;
	}

        /**
         * 
         * @return
         */
        public boolean hasChildScene() {
		return this.mChildScene != null;
	}

        /**
         * 
         * @return
         */
        public Scene getChildScene() {
		return this.mChildScene;
	}

        /**
         * 
         * @param pChildScene
         */
        public void setChildSceneModal(final Scene pChildScene) {
		this.setChildScene(pChildScene, true, true, true);
	}

        /**
         * 
         * @param pChildScene
         */
        public void setChildScene(final Scene pChildScene) {
		this.setChildScene(pChildScene, false, false, false);
	}

        /**
         * 
         * @param pChildScene
         * @param pModalDraw
         * @param pModalUpdate
         * @param pModalTouch
         */
        public void setChildScene(final Scene pChildScene, final boolean pModalDraw, final boolean pModalUpdate, final boolean pModalTouch) {
		pChildScene.setParentScene(this);
		this.mChildScene = pChildScene;
		this.mChildSceneModalDraw = pModalDraw;
		this.mChildSceneModalUpdate = pModalUpdate;
		this.mChildSceneModalTouch = pModalTouch;
	}

        /**
         * 
         */
        public void clearChildScene() {
		this.mChildScene = null;
	}

        /**
         * 
         */
        public void setOnAreaTouchTraversalBackToFront() {
		this.mOnAreaTouchTraversalBackToFront = true;
	}

        /**
         * 
         */
        public void setOnAreaTouchTraversalFrontToBack() {
		this.mOnAreaTouchTraversalBackToFront = false;
	}

        /**
         * 
         * @return
         */
        public boolean isTouchAreaBindingOnActionDownEnabled() {
		return this.mTouchAreaBindingOnActionDownEnabled;
	}

        /**
         * 
         * @return
         */
        public boolean isTouchAreaBindingOnActionMoveEnabled() {
		return this.mTouchAreaBindingOnActionMoveEnabled;
	}

	/**
	 * Enable or disable the binding of TouchAreas to PointerIDs (fingers).
	 * When enabled: TouchAreas get bound to a PointerID (finger) when returning true in
	 * {@link IShape#onAreaTouched(TouchEvent, float, float)} or
	 * {@link IOnAreaTouchListener#onAreaTouched(TouchEvent, ITouchArea, float, float)}
	 * with {@link TouchEvent#ACTION_DOWN}, they will receive all subsequent {@link TouchEvent}s
	 * that are made with the same PointerID (finger)
	 * <b>even if the {@link TouchEvent} is outside of the actual {@link ITouchArea}</b>!
	 * 
	 * @param pTouchAreaBindingOnActionDownEnabled
	 */
	public void setTouchAreaBindingOnActionDownEnabled(final boolean pTouchAreaBindingOnActionDownEnabled) {
		if(this.mTouchAreaBindingOnActionDownEnabled && !pTouchAreaBindingOnActionDownEnabled) {
			this.mTouchAreaBindings.clear();
		}
		this.mTouchAreaBindingOnActionDownEnabled = pTouchAreaBindingOnActionDownEnabled;
	}

	/**
	 * Enable or disable the binding of TouchAreas to PointerIDs (fingers).
	 * When enabled: TouchAreas get bound to a PointerID (finger) when returning true in
	 * {@link IShape#onAreaTouched(TouchEvent, float, float)} or
	 * {@link IOnAreaTouchListener#onAreaTouched(TouchEvent, ITouchArea, float, float)}
	 * with {@link TouchEvent#ACTION_MOVE}, they will receive all subsequent {@link TouchEvent}s
	 * that are made with the same PointerID (finger)
	 * <b>even if the {@link TouchEvent} is outside of the actual {@link ITouchArea}</b>!
	 * 
	 * @param pTouchAreaBindingOnActionMoveEnabled
	 */
	public void setTouchAreaBindingOnActionMoveEnabled(final boolean pTouchAreaBindingOnActionMoveEnabled) {
		if(this.mTouchAreaBindingOnActionMoveEnabled && !pTouchAreaBindingOnActionMoveEnabled) {
			this.mTouchAreaBindings.clear();
		}
		this.mTouchAreaBindingOnActionMoveEnabled = pTouchAreaBindingOnActionMoveEnabled;
	}

        /**
         * 
         * @return
         */
        public boolean isOnSceneTouchListenerBindingOnActionDownEnabled() {
		return this.mOnSceneTouchListenerBindingOnActionDownEnabled;
	}

	/**
	 * Enable or disable the binding of TouchAreas to PointerIDs (fingers).
	 * When enabled: The OnSceneTouchListener gets bound to a PointerID (finger) when returning true in
	 * {@link Shape#onAreaTouched(TouchEvent, float, float)} or
	 * {@link IOnAreaTouchListener#onAreaTouched(TouchEvent, ITouchArea, float, float)}
	 * with {@link TouchEvent#ACTION_DOWN}, it will receive all subsequent {@link TouchEvent}s
	 * that are made with the same PointerID (finger)
	 * <b>even if the {@link TouchEvent} is would belong to an overlaying {@link ITouchArea}</b>!
	 * 
	 * @param pOnSceneTouchListenerBindingOnActionDownEnabled
	 */
	public void setOnSceneTouchListenerBindingOnActionDownEnabled(final boolean pOnSceneTouchListenerBindingOnActionDownEnabled) {
		if(this.mOnSceneTouchListenerBindingOnActionDownEnabled && !pOnSceneTouchListenerBindingOnActionDownEnabled) {
			this.mOnSceneTouchListenerBindings.clear();
		}
		this.mOnSceneTouchListenerBindingOnActionDownEnabled = pOnSceneTouchListenerBindingOnActionDownEnabled;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pGLState
         * @param pCamera
         */
        @Override
	protected void onManagedDraw(final GLState pGLState, final Camera pCamera) {
		final Scene childScene = this.mChildScene;

		if(childScene == null || !this.mChildSceneModalDraw) {
			if(this.mBackgroundEnabled) {
				pGLState.pushProjectionGLMatrix();

				pCamera.onApplySceneBackgroundMatrix(pGLState);
				pGLState.loadModelViewGLMatrixIdentity();

				this.mBackground.onDraw(pGLState, pCamera);

				pGLState.popProjectionGLMatrix();
			}

			{
				pGLState.pushProjectionGLMatrix();

				this.onApplyMatrix(pGLState, pCamera);
				pGLState.loadModelViewGLMatrixIdentity();

				super.onManagedDraw(pGLState, pCamera);

				pGLState.popProjectionGLMatrix();
			}
		}

		if(childScene != null) {
			childScene.onDraw(pGLState, pCamera);
		}
	}

        /**
         * 
         * @param pGLState
         * @param pCamera
         */
        protected void onApplyMatrix(final GLState pGLState, final Camera pCamera) {
		pCamera.onApplySceneMatrix(pGLState);
	}

        /**
         * 
         * @param pSecondsElapsed
         */
        @Override
	protected void onManagedUpdate(final float pSecondsElapsed) {
		this.mSecondsElapsedTotal += pSecondsElapsed;

		this.mRunnableHandler.onUpdate(pSecondsElapsed);

		final Scene childScene = this.mChildScene;
		if(childScene == null || !this.mChildSceneModalUpdate) {
			this.mBackground.onUpdate(pSecondsElapsed);
			super.onManagedUpdate(pSecondsElapsed);
		}

		if(childScene != null) {
			childScene.onUpdate(pSecondsElapsed);
		}
	}

        /**
         * 
         * @param pSceneTouchEvent
         * @return
         */
        public boolean onSceneTouchEvent(final TouchEvent pSceneTouchEvent) {
		final int action = pSceneTouchEvent.getAction();
		final boolean isActionDown = pSceneTouchEvent.isActionDown();
		final boolean isActionMove = pSceneTouchEvent.isActionMove();

		if(!isActionDown) {
			if(this.mOnSceneTouchListenerBindingOnActionDownEnabled) {
				final IOnSceneTouchListener boundOnSceneTouchListener = this.mOnSceneTouchListenerBindings.get(pSceneTouchEvent.getPointerID());
				if (boundOnSceneTouchListener != null) {
					/* Check if boundTouchArea needs to be removed. */
					switch(action) {
						case TouchEvent.ACTION_UP:
						case TouchEvent.ACTION_CANCEL:
							this.mOnSceneTouchListenerBindings.remove(pSceneTouchEvent.getPointerID());
					}
					final Boolean handled = this.mOnSceneTouchListener.onSceneTouchEvent(this, pSceneTouchEvent);
					if(handled != null && handled) {
						return true;
					}
				}
			}
			if(this.mTouchAreaBindingOnActionDownEnabled) {
				final SparseArray<ITouchArea> touchAreaBindings = this.mTouchAreaBindings;
				final ITouchArea boundTouchArea = touchAreaBindings.get(pSceneTouchEvent.getPointerID());
				/* In the case a ITouchArea has been bound to this PointerID,
				 * we'll pass this this TouchEvent to the same ITouchArea. */
				if(boundTouchArea != null) {
					final float sceneTouchEventX = pSceneTouchEvent.getX();
					final float sceneTouchEventY = pSceneTouchEvent.getY();

					/* Check if boundTouchArea needs to be removed. */
					switch(action) {
						case TouchEvent.ACTION_UP:
						case TouchEvent.ACTION_CANCEL:
							touchAreaBindings.remove(pSceneTouchEvent.getPointerID());
					}
					final Boolean handled = this.onAreaTouchEvent(pSceneTouchEvent, sceneTouchEventX, sceneTouchEventY, boundTouchArea);
					if(handled != null && handled) {
						return true;
					}
				}
			}
		}

		final Scene childScene = this.mChildScene;
		if(childScene != null) {
			final boolean handledByChild = this.onChildSceneTouchEvent(pSceneTouchEvent);
			if(handledByChild) {
				return true;
			} else if(this.mChildSceneModalTouch) {
				return false;
			}
		}

		final float sceneTouchEventX = pSceneTouchEvent.getX();
		final float sceneTouchEventY = pSceneTouchEvent.getY();

		final SmartList<ITouchArea> touchAreas = this.mTouchAreas;
		if(touchAreas != null) {
			final int touchAreaCount = touchAreas.size();
			if(touchAreaCount > 0) {
				if(this.mOnAreaTouchTraversalBackToFront) { /* Back to Front. */
					for(int i = 0; i < touchAreaCount; i++) {
						final ITouchArea touchArea = touchAreas.get(i);
						if(touchArea.contains(sceneTouchEventX, sceneTouchEventY)) {
							final Boolean handled = this.onAreaTouchEvent(pSceneTouchEvent, sceneTouchEventX, sceneTouchEventY, touchArea);
							if(handled != null && handled) {
								/* If binding of ITouchAreas is enabled and this is an ACTION_DOWN event,
								 *  bind this ITouchArea to the PointerID. */
								if((this.mTouchAreaBindingOnActionDownEnabled && isActionDown) || (this.mTouchAreaBindingOnActionMoveEnabled && isActionMove)) {
									this.mTouchAreaBindings.put(pSceneTouchEvent.getPointerID(), touchArea);
								}
								return true;
							}
						}
					}
				} else { /* Front to back. */
					for(int i = touchAreaCount - 1; i >= 0; i--) {
						final ITouchArea touchArea = touchAreas.get(i);
						if(touchArea.contains(sceneTouchEventX, sceneTouchEventY)) {
							final Boolean handled = this.onAreaTouchEvent(pSceneTouchEvent, sceneTouchEventX, sceneTouchEventY, touchArea);
							if(handled != null && handled) {
								/* If binding of ITouchAreas is enabled and this is an ACTION_DOWN event,
								 *  bind this ITouchArea to the PointerID. */
								if((this.mTouchAreaBindingOnActionDownEnabled && isActionDown) || (this.mTouchAreaBindingOnActionMoveEnabled && isActionMove)) {
									this.mTouchAreaBindings.put(pSceneTouchEvent.getPointerID(), touchArea);
								}
								return true;
							}
						}
					}
				}
			}
		}
		/* If no area was touched, the Scene itself was touched as a fallback. */
		if(this.mOnSceneTouchListener != null){
			final Boolean handled = this.mOnSceneTouchListener.onSceneTouchEvent(this, pSceneTouchEvent);
			if(handled != null && handled) {
				/* If binding of ITouchAreas is enabled and this is an ACTION_DOWN event,
				 *  bind the active OnSceneTouchListener to the PointerID. */
				if(this.mOnSceneTouchListenerBindingOnActionDownEnabled && isActionDown) {
					this.mOnSceneTouchListenerBindings.put(pSceneTouchEvent.getPointerID(), this.mOnSceneTouchListener);
				}
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	private Boolean onAreaTouchEvent(final TouchEvent pSceneTouchEvent, final float sceneTouchEventX, final float sceneTouchEventY, final ITouchArea touchArea) {
		final float[] touchAreaLocalCoordinates = touchArea.convertSceneToLocalCoordinates(sceneTouchEventX, sceneTouchEventY);
		final float touchAreaLocalX = touchAreaLocalCoordinates[Constants.VERTEX_INDEX_X];
		final float touchAreaLocalY = touchAreaLocalCoordinates[Constants.VERTEX_INDEX_Y];

		final boolean handledSelf = touchArea.onAreaTouched(pSceneTouchEvent, touchAreaLocalX, touchAreaLocalY);
		if(handledSelf) {
			return Boolean.TRUE;
		} else if(this.mOnAreaTouchListener != null) {
			return this.mOnAreaTouchListener.onAreaTouched(pSceneTouchEvent, touchArea, touchAreaLocalX, touchAreaLocalY);
		} else {
			return null;
		}
	}

        /**
         * 
         * @param pSceneTouchEvent
         * @return
         */
        protected boolean onChildSceneTouchEvent(final TouchEvent pSceneTouchEvent) {
		return this.mChildScene.onSceneTouchEvent(pSceneTouchEvent);
	}

        /**
         * 
         */
        @Override
	public void reset() {
		super.reset();

		this.clearChildScene();
	}

        /**
         * 
         * @param pEntity
         */
        @Override
	public void setParent(final IEntity pEntity) {
//		super.setParent(pEntity);
	}

	// ===========================================================
	// Methods
	// ===========================================================

        /**
         * 
         * @param pRunnable
         */
        public void postRunnable(final Runnable pRunnable) {
		this.mRunnableHandler.postRunnable(pRunnable);
	}

        /**
         * 
         * @param pTouchArea
         */
        public void registerTouchArea(final ITouchArea pTouchArea) {
		this.mTouchAreas.add(pTouchArea);
	}

        /**
         * 
         * @param pTouchArea
         * @return
         */
        public boolean unregisterTouchArea(final ITouchArea pTouchArea) {
		return this.mTouchAreas.remove(pTouchArea);
	}

        /**
         * 
         * @param pTouchAreaMatcher
         * @return
         */
        public boolean unregisterTouchAreas(final ITouchAreaMatcher pTouchAreaMatcher) {
		return this.mTouchAreas.removeAll(pTouchAreaMatcher);
	}

        /**
         * 
         */
        public void clearTouchAreas() {
		this.mTouchAreas.clear();
	}

        /**
         * 
         * @return
         */
        public SmartList<ITouchArea> getTouchAreas() {
		return this.mTouchAreas;
	}

        /**
         * 
         */
        public void back() {
		this.clearChildScene();

		if(this.mParentScene != null) {
			this.mParentScene.clearChildScene();
			this.mParentScene = null;
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

        /**
         * 
         */
        public static interface ITouchArea {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

            /**
             * 
             * @param pX
             * @param pY
             * @return
             */
            public boolean contains(final float pX, final float pY);

                /**
                 * 
                 * @param pX
                 * @param pY
                 * @return
                 */
                public float[] convertSceneToLocalCoordinates(final float pX, final float pY);
                /**
                 * 
                 * @param pX
                 * @param pY
                 * @return
                 */
                public float[] convertLocalToSceneCoordinates(final float pX, final float pY);

		/**
		 * This method only fires if this {@link ITouchArea} is registered to the {@link Scene} via {@link Scene#registerTouchArea(ITouchArea)}.
		 * @param pSceneTouchEvent
                 * @param pTouchAreaLocalX 
                 * @param pTouchAreaLocalY 
                 * @return <code>true</code> if the event was handled (that means {@link IOnAreaTouchListener} of the {@link Scene} will not be fired!), otherwise <code>false</code>.
		 */
		public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY);

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================

                /**
                 * 
                 */
                public static interface ITouchAreaMatcher extends IMatcher<ITouchArea> {
			// ===========================================================
			// Constants
			// ===========================================================

			// ===========================================================
			// Methods
			// ===========================================================
		}
	}

	/**
	 * An interface for a callback to be invoked when a {@link TouchEvent} is
	 * dispatched to an {@link ITouchArea} area. The callback will be invoked
	 * before the {@link TouchEvent} is passed to the {@link ITouchArea}.
	 */
	public static interface IOnAreaTouchListener {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

		/**
		 * Called when a {@link TouchEvent} is dispatched to an {@link ITouchArea}. This allows
		 * listeners to get a chance to respond before the target {@link ITouchArea#onAreaTouched(TouchEvent, float, float)} is called.
		 * 
		 * @param pTouchArea The {@link ITouchArea} that the {@link TouchEvent} has been dispatched to.
		 * @param pSceneTouchEvent The {@link TouchEvent} object containing full information about the event.
		 * @param pTouchAreaLocalX the x coordinate within the area touched.
		 * @param pTouchAreaLocalY the y coordinate within the area touched.
		 * 
		 * @return <code>true</code> if this {@link IOnAreaTouchListener} has consumed the {@link TouchEvent}, <code>false</code> otherwise.
		 */
		public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final ITouchArea pTouchArea, final float pTouchAreaLocalX, final float pTouchAreaLocalY);
	}

	/**
	 * An interface for a callback to be invoked when a {@link TouchEvent} is
	 * dispatched to a {@link Scene}. The callback will be invoked
	 * after all {@link ITouchArea}s have been checked and none consumed the {@link TouchEvent}.
	 */
	public static interface IOnSceneTouchListener {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

		/**
		 * Called when a {@link TouchEvent} is dispatched to a {@link Scene}.
		 * 
		 * @param pScene The {@link Scene} that the {@link TouchEvent} has been dispatched to.
		 * @param pSceneTouchEvent The {@link TouchEvent} object containing full information about the event.
		 * 
		 * @return <code>true</code> if this {@link IOnSceneTouchListener} has consumed the {@link TouchEvent}, <code>false</code> otherwise.
		 */
		public boolean onSceneTouchEvent(final Scene pScene, final TouchEvent pSceneTouchEvent);
	}
}
