package org.andengine.engine.camera;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.UpdateHandlerList;
import org.andengine.entity.IEntity;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.shape.RectangularShape;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;
import org.andengine.util.Constants;
import org.andengine.util.adt.transformation.Transformation;
import org.andengine.util.algorithm.collision.RectangularShapeCollisionChecker;
import org.andengine.util.math.MathUtils;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 10:24:18 - 25.03.2010
 */
public class Camera implements IUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	static final float[] VERTICES_TMP = new float[2];

	private static final int UPDATEHANDLERS_CAPACITY_DEFAULT = 4;

	// ===========================================================
	// Fields
	// ===========================================================

	private float mXMin;
	private float mXMax;
	private float mYMin;
	private float mYMax;

	private float mZNear = -1.0f;
	private float mZFar = 1.0f;

	private HUD mHUD;

	private IEntity mChaseEntity;

        /**
         * 
         */
        protected float mRotation = 0;
        /**
         * 
         */
        protected float mCameraSceneRotation = 0;

        /**
         * 
         */
        protected int mSurfaceX;
        /**
         * 
         */
        protected int mSurfaceY;
        /**
         * 
         */
        protected int mSurfaceWidth;
        /**
         * 
         */
        protected int mSurfaceHeight;

        /**
         * 
         */
        protected boolean mResizeOnSurfaceSizeChanged;
        /**
         * 
         */
        protected UpdateHandlerList mUpdateHandlers;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pX
         * @param pY
         * @param pWidth
         * @param pHeight
         */
        public Camera(final float pX, final float pY, final float pWidth, final float pHeight) {
		this.set(pX, pY, pX + pWidth, pY + pHeight);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public float getXMin() {
		return this.mXMin;
	}

        /**
         * 
         * @param pXMin
         */
        public void setXMin(final float pXMin) {
		this.mXMin = pXMin;
	}

        /**
         * 
         * @return
         */
        public float getXMax() {
		return this.mXMax;
	}

        /**
         * 
         * @param pXMax
         */
        public void setXMax(final float pXMax) {
		this.mXMax = pXMax;
	}

        /**
         * 
         * @return
         */
        public float getYMin() {
		return this.mYMin;
	}

        /**
         * 
         * @param pYMin
         */
        public void setYMin(final float pYMin) {
		this.mYMin = pYMin;
	}

        /**
         * 
         * @return
         */
        public float getYMax() {
		return this.mYMax;
	}

        /**
         * 
         * @param pYMax
         */
        public void setYMax(final float pYMax) {
		this.mYMax = pYMax;
	}

        /**
         * 
         * @param pXMin
         * @param pYMin
         * @param pXMax
         * @param pYMax
         */
        public void set(final float pXMin, final float pYMin, final float pXMax, final float pYMax) {
		this.mXMin = pXMin;
		this.mXMax = pXMax;
		this.mYMin = pYMin;
		this.mYMax = pYMax;
	}

        /**
         * 
         * @return
         */
        public float getZNear() {
		return this.mZNear;
	}

        /**
         * 
         * @return
         */
        public float getZFar() {
		return this.mZFar;
	}

        /**
         * 
         * @param pZNear
         */
        public void setZNear(final float pZNear) {
		this.mZNear = pZNear;
	}

        /**
         * 
         * @param pZFar
         */
        public void setZFar(final float pZFar) {
		this.mZFar = pZFar;
	}

        /**
         * 
         * @param pNearZClippingPlane
         * @param pFarZClippingPlane
         */
        public void setZClippingPlanes(final float pNearZClippingPlane, final float pFarZClippingPlane) {
		this.mZNear = pNearZClippingPlane;
		this.mZFar = pFarZClippingPlane;
	}

        /**
         * 
         * @return
         */
        public float getWidth() {
		return this.mXMax - this.mXMin;
	}

        /**
         * 
         * @return
         */
        public float getHeight() {
		return this.mYMax - this.mYMin;
	}

        /**
         * 
         * @return
         */
        public float getWidthRaw() {
		return this.mXMax - this.mXMin;
	}

        /**
         * 
         * @return
         */
        public float getHeightRaw() {
		return this.mYMax - this.mYMin;
	}

        /**
         * 
         * @return
         */
        public float getCenterX() {
		return (this.mXMin + this.mXMax) * 0.5f;
	}

        /**
         * 
         * @return
         */
        public float getCenterY() {
		return (this.mYMin + this.mYMax) * 0.5f;
	}

        /**
         * 
         * @param pCenterX
         * @param pCenterY
         */
        public void setCenter(final float pCenterX, final float pCenterY) {
		final float dX = pCenterX - this.getCenterX();
		final float dY = pCenterY - this.getCenterY();

		this.mXMin += dX;
		this.mXMax += dX;
		this.mYMin += dY;
		this.mYMax += dY;
	}

        /**
         * 
         * @param pX
         * @param pY
         */
        public void offsetCenter(final float pX, final float pY) {
		this.setCenter(this.getCenterX() + pX, this.getCenterY() + pY);
	}

        /**
         * 
         * @return
         */
        public HUD getHUD() {
		return this.mHUD;
	}

        /**
         * 
         * @param pHUD
         */
        public void setHUD(final HUD pHUD) {
		this.mHUD = pHUD;
		if(pHUD != null) {
			pHUD.setCamera(this);
		}
	}

        /**
         * 
         * @return
         */
        public boolean hasHUD() {
		return this.mHUD != null;
	}

        /**
         * 
         * @param pChaseEntity
         */
        public void setChaseEntity(final IEntity pChaseEntity) {
		this.mChaseEntity = pChaseEntity;
	}

        /**
         * 
         * @return
         */
        public boolean isRotated() {
		return this.mRotation != 0;
	}

        /**
         * 
         * @return
         */
        public float getRotation() {
		return this.mRotation;
	}

        /**
         * 
         * @param pRotation
         */
        public void setRotation(final float pRotation) {
		this.mRotation = pRotation;
	}

        /**
         * 
         * @return
         */
        public float getCameraSceneRotation() {
		return this.mCameraSceneRotation;
	}

        /**
         * 
         * @param pCameraSceneRotation
         */
        public void setCameraSceneRotation(final float pCameraSceneRotation) {
		this.mCameraSceneRotation = pCameraSceneRotation;
	}

        /**
         * 
         * @return
         */
        public int getSurfaceX() {
		return this.mSurfaceX;
	}

        /**
         * 
         * @return
         */
        public int getSurfaceY() {
		return this.mSurfaceY;
	}

        /**
         * 
         * @return
         */
        public int getSurfaceWidth() {
		return this.mSurfaceWidth;
	}

        /**
         * 
         * @return
         */
        public int getSurfaceHeight() {
		return this.mSurfaceHeight;
	}

        /**
         * 
         * @param pSurfaceX
         * @param pSurfaceY
         * @param pSurfaceWidth
         * @param pSurfaceHeight
         */
        public void setSurfaceSize(final int pSurfaceX, final int pSurfaceY, final int pSurfaceWidth, final int pSurfaceHeight) {
		if(this.mSurfaceHeight == 0 && this.mSurfaceWidth == 0) {
			this.onSurfaceSizeInitialized(pSurfaceX, pSurfaceY, pSurfaceWidth, pSurfaceHeight);
		} else if(this.mSurfaceWidth != pSurfaceWidth || this.mSurfaceHeight != pSurfaceHeight) {
			this.onSurfaceSizeChanged(this.mSurfaceX, this.mSurfaceY, this.mSurfaceWidth, this.mSurfaceHeight, pSurfaceX, pSurfaceY, pSurfaceWidth, pSurfaceHeight);
		}
	}

        /**
         * 
         * @return
         */
        public boolean isResizeOnSurfaceSizeChanged() {
		return this.mResizeOnSurfaceSizeChanged;
	}

        /**
         * 
         * @param pResizeOnSurfaceSizeChanged
         */
        public void setResizeOnSurfaceSizeChanged(final boolean pResizeOnSurfaceSizeChanged) {
		this.mResizeOnSurfaceSizeChanged = pResizeOnSurfaceSizeChanged;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pSecondsElapsed
         */
        @Override
	public void onUpdate(final float pSecondsElapsed) {
		if(this.mUpdateHandlers != null) {
			this.mUpdateHandlers.onUpdate(pSecondsElapsed);
		}

		if(this.mHUD != null) {
			this.mHUD.onUpdate(pSecondsElapsed);
		}

		this.updateChaseEntity();
	}

        /**
         * 
         */
        @Override
	public void reset() {

	}

	// ===========================================================
	// Methods
	// ===========================================================

        /**
         * 
         * @param pGLState
         */
        public void onDrawHUD(final GLState pGLState) {
		if(this.mHUD != null) {
			this.mHUD.onDraw(pGLState, this);
		}
	}

        /**
         * 
         */
        public void updateChaseEntity() {
		if(this.mChaseEntity != null) {
			final float[] centerCoordinates = this.mChaseEntity.getSceneCenterCoordinates();
			this.setCenter(centerCoordinates[Constants.VERTEX_INDEX_X], centerCoordinates[Constants.VERTEX_INDEX_Y]);
		}
	}

        /**
         * 
         * @param pLine
         * @return
         */
        public boolean isLineVisible(final Line pLine) {
		return RectangularShapeCollisionChecker.isVisible(this, pLine);
	}

        /**
         * 
         * @param pRectangularShape
         * @return
         */
        public boolean isRectangularShapeVisible(final RectangularShape pRectangularShape) {
		return RectangularShapeCollisionChecker.isVisible(this, pRectangularShape);
	}

        /**
         * 
         * @param pX
         * @param pY
         * @param pWidth
         * @param pHeight
         * @param pLocalToSceneTransformation
         * @return
         */
        public boolean isRectangularShapeVisible(final float pX, final float pY, final float pWidth, final float pHeight, final Transformation pLocalToSceneTransformation) {
		return RectangularShapeCollisionChecker.isVisible(this, pX, pY, pWidth, pHeight, pLocalToSceneTransformation);
	}

        /**
         * 
         * @param pGLState
         */
        public void onApplySceneMatrix(final GLState pGLState) {
		pGLState.orthoProjectionGLMatrixf(this.getXMin(), this.getXMax(), this.getYMax(), this.getYMin(), this.mZNear, this.mZFar);

		final float rotation = this.mRotation;
		if(rotation != 0) {
			this.applyRotation(pGLState, this.getCenterX(), this.getCenterY(), rotation);
		}
	}

        /**
         * 
         * @param pGLState
         */
        public void onApplySceneBackgroundMatrix(final GLState pGLState) {
		final float widthRaw = this.getWidthRaw();
		final float heightRaw = this.getHeightRaw();

		pGLState.orthoProjectionGLMatrixf(0, widthRaw, heightRaw, 0, this.mZNear, this.mZFar);

		final float rotation = this.mRotation;
		if(rotation != 0) {
			this.applyRotation(pGLState, widthRaw * 0.5f, heightRaw * 0.5f, rotation);
		}
	}

        /**
         * 
         * @param pGLState
         */
        public void onApplyCameraSceneMatrix(final GLState pGLState) {
		final float widthRaw = this.getWidthRaw();
		final float heightRaw = this.getHeightRaw();
		pGLState.orthoProjectionGLMatrixf(0, widthRaw, heightRaw, 0, this.mZNear, this.mZFar);

		final float cameraSceneRotation = this.mCameraSceneRotation;
		if(cameraSceneRotation != 0) {
			this.applyRotation(pGLState, widthRaw * 0.5f, heightRaw * 0.5f, cameraSceneRotation);
		}
	}

	private void applyRotation(final GLState pGLState, final float pRotationCenterX, final float pRotationCenterY, final float pAngle) {
		pGLState.translateProjectionGLMatrixf(pRotationCenterX, pRotationCenterY, 0);
		pGLState.rotateProjectionGLMatrixf(pAngle, 0, 0, 1);
		pGLState.translateProjectionGLMatrixf(-pRotationCenterX, -pRotationCenterY, 0);
	}

        /**
         * 
         * @param pSceneTouchEvent
         */
        public void convertSceneToCameraSceneTouchEvent(final TouchEvent pSceneTouchEvent) {
		this.unapplySceneRotation(pSceneTouchEvent);

		this.applySceneToCameraSceneOffset(pSceneTouchEvent);

		this.applyCameraSceneRotation(pSceneTouchEvent);
	}

        /**
         * 
         * @param pSceneX
         * @param pSceneY
         * @return
         */
        public float[] getCameraSceneCoordinatesFromSceneCoordinates(final float pSceneX, final float pSceneY) {
		Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X] = pSceneX;
		Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y] = pSceneY;

		return this.getCameraSceneCoordinatesFromSceneCoordinates(Camera.VERTICES_TMP);
	}

        /**
         * 
         * @param pSceneCoordinates
         * @return
         */
        public float[] getCameraSceneCoordinatesFromSceneCoordinates(final float[] pSceneCoordinates) {
		this.unapplySceneRotation(pSceneCoordinates);

		this.applySceneToCameraSceneOffset(pSceneCoordinates);

		this.applyCameraSceneRotation(pSceneCoordinates);

		return pSceneCoordinates;
	}

        /**
         * 
         * @param pCameraSceneTouchEvent
         */
        public void convertCameraSceneToSceneTouchEvent(final TouchEvent pCameraSceneTouchEvent) {
		this.unapplyCameraSceneRotation(pCameraSceneTouchEvent);

		this.unapplySceneToCameraSceneOffset(pCameraSceneTouchEvent);

		this.applySceneRotation(pCameraSceneTouchEvent);
	}

        /**
         * 
         * @param pCameraSceneX
         * @param pCameraSceneY
         * @return
         */
        public float[] getSceneCoordinatesFromCameraSceneCoordinates(final float pCameraSceneX, final float pCameraSceneY) {
		Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X] = pCameraSceneX;
		Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y] = pCameraSceneY;

		return this.getSceneCoordinatesFromCameraSceneCoordinates(Camera.VERTICES_TMP);
	}

        /**
         * 
         * @param pCameraSceneCoordinates
         * @return
         */
        public float[] getSceneCoordinatesFromCameraSceneCoordinates(final float[] pCameraSceneCoordinates) {
		this.unapplyCameraSceneRotation(pCameraSceneCoordinates);

		this.unapplySceneToCameraSceneOffset(pCameraSceneCoordinates);

		this.applySceneRotation(pCameraSceneCoordinates);

		return pCameraSceneCoordinates;
	}

        /**
         * 
         * @param pSceneTouchEvent
         */
        protected void applySceneToCameraSceneOffset(final TouchEvent pSceneTouchEvent) {
		pSceneTouchEvent.offset(-this.mXMin, -this.mYMin);
	}

        /**
         * 
         * @param pSceneCoordinates
         */
        protected void applySceneToCameraSceneOffset(final float[] pSceneCoordinates) {
		pSceneCoordinates[Constants.VERTEX_INDEX_X] -= this.mXMin;
		pSceneCoordinates[Constants.VERTEX_INDEX_Y] -= this.mYMin;
	}

        /**
         * 
         * @param pCameraSceneTouchEvent
         */
        protected void unapplySceneToCameraSceneOffset(final TouchEvent pCameraSceneTouchEvent) {
		pCameraSceneTouchEvent.offset(this.mXMin, this.mYMin);
	}

        /**
         * 
         * @param pCameraSceneCoordinates
         */
        protected void unapplySceneToCameraSceneOffset(final float[] pCameraSceneCoordinates) {
		pCameraSceneCoordinates[Constants.VERTEX_INDEX_X] += this.mXMin;
		pCameraSceneCoordinates[Constants.VERTEX_INDEX_Y] += this.mYMin;
	}

	private void applySceneRotation(final float[] pCameraSceneCoordinates) {
		final float rotation = this.mRotation;
		if(rotation != 0) {
			MathUtils.rotateAroundCenter(pCameraSceneCoordinates, -rotation, this.getCenterX(), this.getCenterY());
		}
	}

	private void applySceneRotation(final TouchEvent pCameraSceneTouchEvent) {
		final float rotation = this.mRotation;
		if(rotation != 0) {
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X] = pCameraSceneTouchEvent.getX();
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y] = pCameraSceneTouchEvent.getY();

			MathUtils.rotateAroundCenter(Camera.VERTICES_TMP, -rotation, this.getCenterX(), this.getCenterY());

			pCameraSceneTouchEvent.set(Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X], Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y]);
		}
	}

	private void unapplySceneRotation(final float[] pSceneCoordinates) {
		final float rotation = this.mRotation;

		if(rotation != 0) {
			MathUtils.revertRotateAroundCenter(pSceneCoordinates, rotation, this.getCenterX(), this.getCenterY());
		}
	}

	private void unapplySceneRotation(final TouchEvent pSceneTouchEvent) {
		final float rotation = this.mRotation;

		if(rotation != 0) {
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X] = pSceneTouchEvent.getX();
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y] = pSceneTouchEvent.getY();

			MathUtils.revertRotateAroundCenter(Camera.VERTICES_TMP, rotation, this.getCenterX(), this.getCenterY());

			pSceneTouchEvent.set(Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X], Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y]);
		}
	}

	private void applyCameraSceneRotation(final float[] pSceneCoordinates) {
		final float cameraSceneRotation = -this.mCameraSceneRotation;

		if(cameraSceneRotation != 0) {
			MathUtils.rotateAroundCenter(pSceneCoordinates, cameraSceneRotation, (this.mXMax - this.mXMin) * 0.5f, (this.mYMax - this.mYMin) * 0.5f);
		}
	}

	private void applyCameraSceneRotation(final TouchEvent pSceneTouchEvent) {
		final float cameraSceneRotation = -this.mCameraSceneRotation;

		if(cameraSceneRotation != 0) {
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X] = pSceneTouchEvent.getX();
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y] = pSceneTouchEvent.getY();

			MathUtils.rotateAroundCenter(Camera.VERTICES_TMP, cameraSceneRotation, (this.mXMax - this.mXMin) * 0.5f, (this.mYMax - this.mYMin) * 0.5f);

			pSceneTouchEvent.set(Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X], Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y]);
		}
	}

	private void unapplyCameraSceneRotation(final float[] pCameraSceneCoordinates) {
		final float cameraSceneRotation = -this.mCameraSceneRotation;

		if(cameraSceneRotation != 0) {
			MathUtils.revertRotateAroundCenter(pCameraSceneCoordinates, cameraSceneRotation, (this.mXMax - this.mXMin) * 0.5f, (this.mYMax - this.mYMin) * 0.5f);
		}
	}

	private void unapplyCameraSceneRotation(final TouchEvent pCameraSceneTouchEvent) {
		final float cameraSceneRotation = -this.mCameraSceneRotation;

		if(cameraSceneRotation != 0) {
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X] = pCameraSceneTouchEvent.getX();
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y] = pCameraSceneTouchEvent.getY();

			MathUtils.revertRotateAroundCenter(Camera.VERTICES_TMP, cameraSceneRotation, (this.mXMax - this.mXMin) * 0.5f, (this.mYMax - this.mYMin) * 0.5f);

			pCameraSceneTouchEvent.set(Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X], Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y]);
		}
	}

	// TODO Camera already knows about its surfaceWidth, is there a need to pass it in here again?
        /**
         * 
         * @param pSurfaceTouchEvent
         * @param pSurfaceWidth
         * @param pSurfaceHeight
         */
        public void convertSurfaceToSceneTouchEvent(final TouchEvent pSurfaceTouchEvent, final int pSurfaceWidth, final int pSurfaceHeight) {
		final float relativeX;
		final float relativeY;

		final float surfaceTouchEventX = pSurfaceTouchEvent.getX();
		final float surfaceTouchEventY = pSurfaceTouchEvent.getY();

		final float rotation = this.mRotation;
		if(rotation == 0) {
			relativeX = surfaceTouchEventX / pSurfaceWidth;
			relativeY = surfaceTouchEventY / pSurfaceHeight;
		} else if(rotation == 180) {
			relativeX = 1 - (surfaceTouchEventX / pSurfaceWidth);
			relativeY = 1 - (surfaceTouchEventY / pSurfaceHeight);
		} else {
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X] = surfaceTouchEventX;
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y] = surfaceTouchEventY;

			MathUtils.rotateAroundCenter(Camera.VERTICES_TMP, rotation, pSurfaceWidth >> 1, pSurfaceHeight >> 1);

			relativeX = Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X] / pSurfaceWidth;
			relativeY = Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y] / pSurfaceHeight;
		}

		this.convertAxisAlignedSurfaceToSceneTouchEvent(pSurfaceTouchEvent, relativeX, relativeY);
	}

	private void convertAxisAlignedSurfaceToSceneTouchEvent(final TouchEvent pSurfaceTouchEvent, final float pRelativeX, final float pRelativeY) {
		final float xMin = this.getXMin();
		final float xMax = this.getXMax();
		final float yMin = this.getYMin();
		final float yMax = this.getYMax();

		final float x = xMin + pRelativeX * (xMax - xMin);
		final float y = yMin + pRelativeY * (yMax - yMin);

		pSurfaceTouchEvent.set(x, y);
	}

        /**
         * 
         * @param pSceneTouchEvent
         * @param pSurfaceWidth
         * @param pSurfaceHeight
         */
        public void convertSceneToSurfaceTouchEvent(final TouchEvent pSceneTouchEvent, final int pSurfaceWidth, final int pSurfaceHeight) {
		this.convertAxisAlignedSceneToSurfaceTouchEvent(pSceneTouchEvent, pSurfaceWidth, pSurfaceHeight);

		final float rotation = this.mRotation;
		if(rotation == 0) {
			/* Nothing to do. */
		} else if(rotation == 180) {
			pSceneTouchEvent.set(pSurfaceWidth - pSceneTouchEvent.getX(), pSurfaceHeight - pSceneTouchEvent.getY());
		} else {
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X] = pSceneTouchEvent.getX();
			Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y] = pSceneTouchEvent.getY();

			MathUtils.revertRotateAroundCenter(Camera.VERTICES_TMP, rotation, pSurfaceWidth >> 1, pSurfaceHeight >> 1);

			pSceneTouchEvent.set(Camera.VERTICES_TMP[Constants.VERTEX_INDEX_X], Camera.VERTICES_TMP[Constants.VERTEX_INDEX_Y]);
		}
	}

	private void convertAxisAlignedSceneToSurfaceTouchEvent(final TouchEvent pSceneTouchEvent, final int pSurfaceWidth, final int pSurfaceHeight) {
		final float xMin = this.getXMin();
		final float xMax = this.getXMax();
		final float yMin = this.getYMin();
		final float yMax = this.getYMax();

		final float relativeX = (pSceneTouchEvent.getX() - xMin) / (xMax - xMin);
		final float relativeY = (pSceneTouchEvent.getY() - yMin) / (yMax - yMin);

		pSceneTouchEvent.set(relativeX * pSurfaceWidth, relativeY * pSurfaceHeight);
	}

        /**
         * 
         * @param pUpdateHandler
         */
        public void registerUpdateHandler(final IUpdateHandler pUpdateHandler) {
		if(this.mUpdateHandlers == null) {
			this.allocateUpdateHandlers();
		}
		this.mUpdateHandlers.add(pUpdateHandler);
	}

        /**
         * 
         * @param pUpdateHandler
         * @return
         */
        public boolean unregisterUpdateHandler(final IUpdateHandler pUpdateHandler) {
		if(this.mUpdateHandlers == null) {
			return false;
		}
		return this.mUpdateHandlers.remove(pUpdateHandler);
	}

        /**
         * 
         * @param pUpdateHandlerMatcher
         * @return
         */
        public boolean unregisterUpdateHandlers(final IUpdateHandlerMatcher pUpdateHandlerMatcher) {
		if(this.mUpdateHandlers == null) {
			return false;
		}
		return this.mUpdateHandlers.removeAll(pUpdateHandlerMatcher);
	}

        /**
         * 
         */
        public void clearUpdateHandlers() {
		if(this.mUpdateHandlers == null) {
			return;
		}
		this.mUpdateHandlers.clear();
	}

	private void allocateUpdateHandlers() {
		this.mUpdateHandlers = new UpdateHandlerList(Camera.UPDATEHANDLERS_CAPACITY_DEFAULT);
	}

        /**
         * 
         * @param pSurfaceX
         * @param pSurfaceY
         * @param pSurfaceWidth
         * @param pSurfaceHeight
         */
        protected void onSurfaceSizeInitialized(final int pSurfaceX, final int pSurfaceY, final int pSurfaceWidth, final int pSurfaceHeight) {
		this.mSurfaceX = pSurfaceX;
		this.mSurfaceY = pSurfaceY;
		this.mSurfaceWidth = pSurfaceWidth;
		this.mSurfaceHeight = pSurfaceHeight;
	}

        /**
         * 
         * @param pOldSurfaceX
         * @param pOldSurfaceY
         * @param pOldSurfaceWidth
         * @param pOldSurfaceHeight
         * @param pNewSurfaceX
         * @param pNewSurfaceY
         * @param pNewSurfaceWidth
         * @param pNewSurfaceHeight
         */
        protected void onSurfaceSizeChanged(final int pOldSurfaceX, final int pOldSurfaceY, final int pOldSurfaceWidth, final int pOldSurfaceHeight, final int pNewSurfaceX, final int pNewSurfaceY, final int pNewSurfaceWidth, final int pNewSurfaceHeight) {
		if(this.mResizeOnSurfaceSizeChanged) {
			final float surfaceWidthRatio = (float)pNewSurfaceWidth / pOldSurfaceWidth;
			final float surfaceHeightRatio = (float)pNewSurfaceHeight / pOldSurfaceHeight;

			final float centerX = this.getCenterX();
			final float centerY = this.getCenterY();

			final float newWidthRaw = this.getWidthRaw() * surfaceWidthRatio;
			final float newHeightRaw = this.getHeightRaw() * surfaceHeightRatio;

			final float newWidthRawHalf = newWidthRaw * 0.5f;
			final float newHeightRawHalf = newHeightRaw * 0.5f;

			final float xMin = centerX - newWidthRawHalf;
			final float yMin = centerY - newHeightRawHalf;
			final float xMax = centerX + newWidthRawHalf;
			final float yMax = centerY + newHeightRawHalf;

			this.set(xMin, yMin, xMax, yMax);
		}

		this.mSurfaceX = pNewSurfaceX;
		this.mSurfaceY = pNewSurfaceY;
		this.mSurfaceWidth = pNewSurfaceWidth;
		this.mSurfaceHeight = pNewSurfaceHeight;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
