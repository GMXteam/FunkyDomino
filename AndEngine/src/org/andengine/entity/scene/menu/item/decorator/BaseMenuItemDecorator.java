package org.andengine.entity.scene.menu.item.decorator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierMatcher;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.shape.IShape;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.shader.ShaderProgram;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.IVertexBufferObject;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.transformation.Transformation;
import org.andengine.util.color.Color;

/**
 * I HATE THIS CLASS!
 * 
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 15:05:44 - 18.11.2010
 */
public abstract class BaseMenuItemDecorator implements IMenuItem {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

    /**
     * 
     */
    protected final IMenuItem mMenuItem;

	// ===========================================================
	// Constructors
	// ===========================================================

    /**
     * 
     * @param pMenuItem
     */
    public BaseMenuItemDecorator(final IMenuItem pMenuItem) {
		this.mMenuItem = pMenuItem;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pMenuItem
         */
        protected abstract void onMenuItemSelected(final IMenuItem pMenuItem);
        /**
         * 
         * @param pMenuItem
         */
        protected abstract void onMenuItemUnselected(final IMenuItem pMenuItem);
        /**
         * 
         * @param pMenuItem
         */
        protected abstract void onMenuItemReset(final IMenuItem pMenuItem);

        /**
         * 
         * @return
         */
        @Override
	public int getID() {
		return this.mMenuItem.getID();
	}

        /**
         * 
         * @return
         */
        @Override
	public VertexBufferObjectManager getVertexBufferObjectManager() {
		return this.mMenuItem.getVertexBufferObjectManager();
	}

        /**
         * 
         * @return
         */
        @Override
	public IVertexBufferObject getVertexBufferObject() {
		return this.mMenuItem.getVertexBufferObject();
	}

        /**
         * 
         */
        @Override
	public final void onSelected() {
		this.mMenuItem.onSelected();
		this.onMenuItemSelected(this.mMenuItem);
	}

        /**
         * 
         */
        @Override
	public final void onUnselected() {
		this.mMenuItem.onUnselected();
		this.onMenuItemUnselected(this.mMenuItem);
	}

        /**
         * 
         * @return
         */
        @Override
	public float getX() {
		return this.mMenuItem.getX();
	}

        /**
         * 
         * @return
         */
        @Override
	public float getY() {
		return this.mMenuItem.getY();
	}

        /**
         * 
         * @param pOtherEntity
         */
        @Override
	public void setPosition(final IEntity pOtherEntity) {
		this.mMenuItem.setPosition(pOtherEntity);
	}

        /**
         * 
         * @param pX
         * @param pY
         */
        @Override
	public void setPosition(final float pX, final float pY) {
		this.mMenuItem.setPosition(pX, pY);
	}

        /**
         * 
         * @return
         */
        @Override
	public float getWidth() {
		return this.mMenuItem.getWidth();
	}
	
	@Override
	public float getBaseWidth() {
		return 0;
	}

        /**
         * 
         * @return
         */
        @Override
	public float getWidthScaled() {
		return this.mMenuItem.getWidthScaled();
	}

	@Override
	public float getHeight() {
		return this.mMenuItem.getHeight();
	}
	
	@Override
	public float getBaseHeight() {
		return this.mMenuItem.getBaseHeight();
	}

        /**
         * 
         * @return
         */
        @Override
	public float getHeightScaled() {
		return this.mMenuItem.getHeightScaled();
	}

	@Override
	public void setWidth(final float pWidth) {
		this.mMenuItem.setWidth(pWidth);
	}

        /**
         * 
         * @param pHeight
         */
        @Override
	public void setHeight(final float pHeight) {
		this.mMenuItem.setHeight(pHeight);
	}

	@Override
	public void setSize(final float pWidth, final float pHeight) {
		this.mMenuItem.setSize(pWidth, pHeight);
	}

        /**
         * 
         * @return
         */
        @Override
	public float getInitialX() {
		return this.mMenuItem.getInitialX();
	}

        /**
         * 
         * @return
         */
        @Override
	public float getInitialY() {
		return this.mMenuItem.getInitialY();
	}

        /**
         * 
         * @return
         */
        @Override
	public float getRed() {
		return this.mMenuItem.getRed();
	}

        /**
         * 
         * @return
         */
        @Override
	public float getGreen() {
		return this.mMenuItem.getGreen();
	}

        /**
         * 
         * @return
         */
        @Override
	public float getBlue() {
		return this.mMenuItem.getBlue();
	}

        /**
         * 
         * @return
         */
        @Override
	public float getAlpha() {
		return this.mMenuItem.getAlpha();
	}

        /**
         * 
         * @param pAlpha
         */
        @Override
	public void setAlpha(final float pAlpha) {
		this.mMenuItem.setAlpha(pAlpha);
	}

        /**
         * 
         * @return
         */
        @Override
	public Color getColor() {
		return this.mMenuItem.getColor();
	}

        /**
         * 
         * @param pColor
         */
        @Override
	public void setColor(final Color pColor) {
		this.mMenuItem.setColor(pColor);
	}

        /**
         * 
         * @param pRed
         * @param pGreen
         * @param pBlue
         */
        @Override
	public void setColor(final float pRed, final float pGreen, final float pBlue) {
		this.mMenuItem.setColor(pRed, pGreen, pBlue);
	}

	@Override
	public void setColor(final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
		this.mMenuItem.setColor(pRed, pGreen, pBlue, pAlpha);
	}

	@Override
	public boolean isRotated() {
		return this.mMenuItem.isRotated();
	}

	@Override
	public float getRotation() {
		return this.mMenuItem.getRotation();
	}

	@Override
	public void setRotation(final float pRotation) {
		this.mMenuItem.setRotation(pRotation);
	}

        /**
         * 
         * @return
         */
        @Override
	public float getRotationCenterX() {
		return this.mMenuItem.getRotationCenterX();
	}

        /**
         * 
         * @return
         */
        @Override
	public float getRotationCenterY() {
		return this.mMenuItem.getRotationCenterY();
	}

        /**
         * 
         * @param pRotationCenterX
         */
        @Override
	public void setRotationCenterX(final float pRotationCenterX) {
		this.mMenuItem.setRotationCenterX(pRotationCenterX);
	}

        /**
         * 
         * @param pRotationCenterY
         */
        @Override
	public void setRotationCenterY(final float pRotationCenterY) {
		this.mMenuItem.setRotationCenterY(pRotationCenterY);
	}

        /**
         * 
         * @param pRotationCenterX
         * @param pRotationCenterY
         */
        @Override
	public void setRotationCenter(final float pRotationCenterX, final float pRotationCenterY) {
		this.mMenuItem.setRotationCenter(pRotationCenterX, pRotationCenterY);
	}

        /**
         * 
         * @return
         */
        @Override
	public boolean isScaled() {
		return this.mMenuItem.isScaled();
	}

        /**
         * 
         * @return
         */
        @Override
	public float getScaleX() {
		return this.mMenuItem.getScaleX();
	}

        /**
         * 
         * @return
         */
        @Override
	public float getScaleY() {
		return this.mMenuItem.getScaleY();
	}

        /**
         * 
         * @param pScale
         */
        @Override
	public void setScale(final float pScale) {
		this.mMenuItem.setScale(pScale);
	}

        /**
         * 
         * @param pScaleX
         * @param pScaleY
         */
        @Override
	public void setScale(final float pScaleX, final float pScaleY) {
		this.mMenuItem.setScale(pScaleX, pScaleY);
	}

        /**
         * 
         * @param pScaleX
         */
        @Override
	public void setScaleX(final float pScaleX) {
		this.mMenuItem.setScaleX(pScaleX);
	}

	@Override
	public void setScaleY(final float pScaleY) {
		this.mMenuItem.setScaleY(pScaleY);
	}

        /**
         * 
         * @return
         */
        @Override
	public float getScaleCenterX() {
		return this.mMenuItem.getScaleCenterX();
	}

        /**
         * 
         * @return
         */
        @Override
	public float getScaleCenterY() {
		return this.mMenuItem.getScaleCenterY();
	}

        /**
         * 
         * @param pScaleCenterX
         */
        @Override
	public void setScaleCenterX(final float pScaleCenterX) {
		this.mMenuItem.setScaleCenterX(pScaleCenterX);
	}

        /**
         * 
         * @param pScaleCenterY
         */
        @Override
	public void setScaleCenterY(final float pScaleCenterY) {
		this.mMenuItem.setScaleCenterY(pScaleCenterY);
	}

        /**
         * 
         * @param pScaleCenterX
         * @param pScaleCenterY
         */
        @Override
	public void setScaleCenter(final float pScaleCenterX, final float pScaleCenterY) {
		this.mMenuItem.setScaleCenter(pScaleCenterX, pScaleCenterY);
	}
	
        /**
         * 
         * @return
         */
        @Override
	public boolean isSkewed() {
		return this.mMenuItem.isSkewed();
	}
	
        /**
         * 
         * @return
         */
        @Override
	public float getSkewX() {
		return this.mMenuItem.getSkewX();
	}
	
        /**
         * 
         * @return
         */
        @Override
	public float getSkewY() {
		return this.mMenuItem.getSkewY();
	}
	
        /**
         * 
         * @param pSkew
         */
        @Override
	public void setSkew(final float pSkew) {
		this.mMenuItem.setSkew(pSkew);
	}
	
        /**
         * 
         * @param pSkewX
         * @param pSkewY
         */
        @Override
	public void setSkew(final float pSkewX, final float pSkewY) {
		this.mMenuItem.setSkew(pSkewX, pSkewY);
	}
	
        /**
         * 
         * @param pSkewX
         */
        @Override
	public void setSkewX(final float pSkewX) {
		this.mMenuItem.setSkewX(pSkewX);
	}
	
        /**
         * 
         * @param pSkewY
         */
        @Override
	public void setSkewY(final float pSkewY) {
		this.mMenuItem.setSkewY(pSkewY);
	}
	
        /**
         * 
         * @return
         */
        @Override
	public float getSkewCenterX() {
		return this.mMenuItem.getSkewCenterX();
	}
	
        /**
         * 
         * @return
         */
        @Override
	public float getSkewCenterY() {
		return this.mMenuItem.getSkewCenterY();
	}
	
        /**
         * 
         * @param pSkewCenterX
         */
        @Override
	public void setSkewCenterX(final float pSkewCenterX) {
		this.mMenuItem.setSkewCenterX(pSkewCenterX);
	}
	
	@Override
	public void setSkewCenterY(final float pSkewCenterY) {
		this.mMenuItem.setSkewCenterY(pSkewCenterY);
	}
	
        /**
         * 
         * @param pSkewCenterX
         * @param pSkewCenterY
         */
        @Override
	public void setSkewCenter(final float pSkewCenterX, final float pSkewCenterY) {
		this.mMenuItem.setSkewCenter(pSkewCenterX, pSkewCenterY);
	}

        /**
         * 
         * @return
         */
        @Override
	public boolean isRotatedOrScaledOrSkewed() {
		return this.mMenuItem.isRotatedOrScaledOrSkewed();
	}
	
        /**
         * 
         * @param pOtherShape
         * @return
         */
        @Override
	public boolean collidesWith(final IShape pOtherShape) {
		return this.mMenuItem.collidesWith(pOtherShape);
	}

	@Override
	public float[] getSceneCenterCoordinates() {
		return this.mMenuItem.getSceneCenterCoordinates();
	}

        /**
         * 
         * @return
         */
        @Override
	public boolean isCullingEnabled() {
		return this.mMenuItem.isCullingEnabled();
	}

        /**
         * 
         * @param pEntityModifier
         */
        @Override
	public void registerEntityModifier(final IEntityModifier pEntityModifier) {
		this.mMenuItem.registerEntityModifier(pEntityModifier);
	}

        /**
         * 
         * @param pEntityModifier
         * @return
         */
        @Override
	public boolean unregisterEntityModifier(final IEntityModifier pEntityModifier) {
		return this.mMenuItem.unregisterEntityModifier(pEntityModifier);
	}

        /**
         * 
         * @param pEntityModifierMatcher
         * @return
         */
        @Override
	public boolean unregisterEntityModifiers(final IEntityModifierMatcher pEntityModifierMatcher) {
		return this.mMenuItem.unregisterEntityModifiers(pEntityModifierMatcher);
	}

        /**
         * 
         */
        @Override
	public void clearEntityModifiers() {
		this.mMenuItem.clearEntityModifiers();
	}

        /**
         * 
         */
        @Override
	public void setInitialPosition() {
		this.mMenuItem.setInitialPosition();
	}

	@Override
	public boolean isBlendingEnabled() {
		return this.mMenuItem.isBlendingEnabled();
	}

        /**
         * 
         * @param pBlendingEnabled
         */
        @Override
	public void setBlendingEnabled(final boolean pBlendingEnabled) {
		this.mMenuItem.setBlendingEnabled(pBlendingEnabled);
	}

        /**
         * 
         * @return
         */
        @Override
	public int getSourceBlendFunction() {
		return this.mMenuItem.getSourceBlendFunction();
	}

        /**
         * 
         * @return
         */
        @Override
	public int getDestinationBlendFunction() {
		return this.mMenuItem.getDestinationBlendFunction();
	}

        /**
         * 
         * @param pSourceBlendFunction
         * @param pDestinationBlendFunction
         */
        @Override
	public void setBlendFunction(final int pSourceBlendFunction, final int pDestinationBlendFunction) {
		this.mMenuItem.setBlendFunction(pSourceBlendFunction, pDestinationBlendFunction);
	}

        /**
         * 
         * @param pCullingEnabled
         */
        @Override
	public void setCullingEnabled(final boolean pCullingEnabled) {
		this.mMenuItem.setCullingEnabled(pCullingEnabled);
	}

        /**
         * 
         * @return
         */
        @Override
	public int getZIndex() {
		return this.mMenuItem.getZIndex();
	}

        /**
         * 
         * @param pZIndex
         */
        @Override
	public void setZIndex(final int pZIndex) {
		this.mMenuItem.setZIndex(pZIndex);
	}

        /**
         * 
         * @return
         */
        @Override
	public ShaderProgram getShaderProgram() {
		return this.mMenuItem.getShaderProgram();
	}

        /**
         * 
         * @param pShaderProgram
         */
        @Override
	public void setShaderProgram(final ShaderProgram pShaderProgram) {
		this.mMenuItem.setShaderProgram(pShaderProgram);
	}

        /**
         * 
         * @param pGLState
         * @param pCamera
         */
        @Override
	public void onDraw(final GLState pGLState, final Camera pCamera) {
		this.mMenuItem.onDraw(pGLState, pCamera);
	}

        /**
         * 
         * @param pSecondsElapsed
         */
        @Override
	public void onUpdate(final float pSecondsElapsed) {
		this.mMenuItem.onUpdate(pSecondsElapsed);
	}

        /**
         * 
         */
        @Override
	public void reset() {
		this.mMenuItem.reset();
		this.onMenuItemReset(this.mMenuItem);
	}

	@Override
	public boolean isDisposed() {
		return this.mMenuItem.isDisposed();
	}

	@Override
	public void dispose() {
		this.mMenuItem.dispose();
	}

        /**
         * 
         * @param pX
         * @param pY
         * @return
         */
        @Override
	public boolean contains(final float pX, final float pY) {
		return this.mMenuItem.contains(pX, pY);
	}

	@Override
	public float[] convertLocalToSceneCoordinates(final float pX, final float pY) {
		return this.mMenuItem.convertLocalToSceneCoordinates(pX, pY);
	}

	@Override
	public float[] convertLocalToSceneCoordinates(final float pX, final float pY, final float[] pReuse) {
		return this.mMenuItem.convertLocalToSceneCoordinates(pX, pY, pReuse);
	}

	@Override
	public float[] convertLocalToSceneCoordinates(final float[] pCoordinates) {
		return this.mMenuItem.convertLocalToSceneCoordinates(pCoordinates);
	}

	@Override
	public float[] convertLocalToSceneCoordinates(final float[] pCoordinates, final float[] pReuse) {
		return this.mMenuItem.convertLocalToSceneCoordinates(pCoordinates, pReuse);
	}

	@Override
	public float[] convertSceneToLocalCoordinates(final float pX, final float pY) {
		return this.mMenuItem.convertSceneToLocalCoordinates(pX, pY);
	}

	@Override
	public float[] convertSceneToLocalCoordinates(final float pX, final float pY, final float[] pReuse) {
		return this.mMenuItem.convertSceneToLocalCoordinates(pX, pY, pReuse);
	}

	@Override
	public float[] convertSceneToLocalCoordinates(final float[] pCoordinates) {
		return this.mMenuItem.convertSceneToLocalCoordinates(pCoordinates);
	}

	@Override
	public float[] convertSceneToLocalCoordinates(final float[] pCoordinates, final float[] pReuse) {
		return this.mMenuItem.convertSceneToLocalCoordinates(pCoordinates, pReuse);
	}

	@Override
	public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
		return this.mMenuItem.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
	}

        /**
         * 
         * @return
         */
        @Override
	public int getChildCount() {
		return this.mMenuItem.getChildCount();
	}

        /**
         * 
         * @param pEntity
         */
        @Override
	public void attachChild(final IEntity pEntity) {
		this.mMenuItem.attachChild(pEntity);
	}

        /**
         * 
         * @param pEntity
         * @param pIndex
         * @return
         */
        @Override
	public boolean attachChild(final IEntity pEntity, final int pIndex) {
		return this.mMenuItem.attachChild(pEntity, pIndex);
	}

        /**
         * 
         * @return
         */
        @Override
	public IEntity getFirstChild() {
		return this.mMenuItem.getFirstChild();
	}

	@Override
	public IEntity getLastChild() {
		return this.mMenuItem.getLastChild();
	}

        /**
         * 
         * @param pIndex
         * @return
         */
        @Override
	public IEntity getChild(final int pIndex) {
		return this.mMenuItem.getChild(pIndex);
	}

        /**
         * 
         * @param pEntityMatcher
         * @return
         */
        @Override
	public IEntity getChild(IEntityMatcher pEntityMatcher) {
		return this.mMenuItem.getChild(pEntityMatcher);
	}

	@Override
	public int getChildIndex(final IEntity pEntity) {
		return this.mMenuItem.getChildIndex(pEntity);
	}

        /**
         * 
         * @param pEntity
         * @param pIndex
         * @return
         */
        @Override
	public boolean setChildIndex(final IEntity pEntity, final int pIndex) {
		return this.mMenuItem.setChildIndex(pEntity, pIndex);
	}

	@Override
	public ArrayList<IEntity> query(final IEntityMatcher pEntityMatcher) {
		return this.mMenuItem.query(pEntityMatcher);
	}

	public <L extends List<IEntity>> L query(final IEntityMatcher pEntityMatcher, final L pResult) {
		return this.mMenuItem.query(pEntityMatcher, pResult);
	}

	@Override
	public <S extends IEntity> ArrayList<S> queryForSubclass(final IEntityMatcher pEntityMatcher) throws ClassCastException {
		return this.mMenuItem.queryForSubclass(pEntityMatcher);
	}

	public <L extends List<S>, S extends IEntity> L queryForSubclass(final IEntityMatcher pEntityMatcher, final L pResult) throws ClassCastException {
		return this.mMenuItem.queryForSubclass(pEntityMatcher, pResult);
	}

        /**
         * 
         * @param pEntityA
         * @param pEntityB
         * @return
         */
        @Override
	public boolean swapChildren(final IEntity pEntityA, final IEntity pEntityB) {
		return this.mMenuItem.swapChildren(pEntityA, pEntityB);
	}

        /**
         * 
         * @param pIndexA
         * @param pIndexB
         * @return
         */
        @Override
	public boolean swapChildren(final int pIndexA, final int pIndexB) {
		return this.mMenuItem.swapChildren(pIndexA, pIndexB);
	}

	@Override
	public void sortChildren() {
		this.mMenuItem.sortChildren();
	}
	
	@Override
	public void sortChildren(final boolean pImmediate) {
		this.mMenuItem.sortChildren(pImmediate);
	}

	@Override
	public void sortChildren(final Comparator<IEntity> pEntityComparator) {
		this.mMenuItem.sortChildren(pEntityComparator);
	}

        /**
         * 
         * @return
         */
        @Override
	public boolean detachSelf() {
		return this.mMenuItem.detachSelf();
	}

	@Override
	public boolean detachChild(final IEntity pEntity) {
		return this.mMenuItem.detachChild(pEntity);
	}

	@Override
	public IEntity detachChild(final IEntityMatcher pEntityMatcher) {
		return this.mMenuItem.detachChild(pEntityMatcher);
	}

	@Override
	public boolean detachChildren(final IEntityMatcher pEntityMatcher) {
		return this.mMenuItem.detachChildren(pEntityMatcher);
	}

        /**
         * 
         */
        @Override
	public void detachChildren() {
		this.mMenuItem.detachChildren();
	}

        /**
         * 
         * @param pEntityParameterCallable
         */
        @Override
	public void callOnChildren(final IEntityParameterCallable pEntityParameterCallable) {
		this.mMenuItem.callOnChildren(pEntityParameterCallable);
	}

        /**
         * 
         * @param pEntityParameterCallable
         * @param pEntityMatcher
         */
        @Override
	public void callOnChildren(final IEntityParameterCallable pEntityParameterCallable, final IEntityMatcher pEntityMatcher) {
		this.mMenuItem.callOnChildren(pEntityParameterCallable, pEntityMatcher);
	}

        /**
         * 
         * @return
         */
        @Override
	public Transformation getLocalToSceneTransformation() {
		return this.mMenuItem.getLocalToSceneTransformation();
	}

        /**
         * 
         * @return
         */
        @Override
	public Transformation getSceneToLocalTransformation() {
		return this.mMenuItem.getSceneToLocalTransformation();
	}

	@Override
	public Transformation getLocalToParentTransformation() {
		return this.mMenuItem.getLocalToParentTransformation();
	}

	@Override
	public Transformation getParentToLocalTransformation() {
		return this.mMenuItem.getParentToLocalTransformation();
	}

        /**
         * 
         * @return
         */
        @Override
	public boolean hasParent() {
		return this.mMenuItem.hasParent();
	}

        /**
         * 
         * @return
         */
        @Override
	public IEntity getParent() {
		return this.mMenuItem.getParent();
	}

        /**
         * 
         * @param pEntity
         */
        @Override
	public void setParent(final IEntity pEntity) {
		this.mMenuItem.setParent(pEntity);
	}

	@Override
	public boolean isVisible() {
		return this.mMenuItem.isVisible();
	}

	@Override
	public void setVisible(final boolean pVisible) {
		this.mMenuItem.setVisible(pVisible);
	}

	@Override
	public boolean isCulled(final Camera pCamera) {
		return this.mMenuItem.isCulled(pCamera);
	}

        /**
         * 
         * @return
         */
        @Override
	public boolean isChildrenVisible() {
		return this.mMenuItem.isChildrenVisible();
	}

	@Override
	public void setChildrenVisible(final boolean pChildrenVisible) {
		this.mMenuItem.setChildrenVisible(pChildrenVisible);
	}

        /**
         * 
         * @return
         */
        @Override
	public boolean isIgnoreUpdate() {
		return this.mMenuItem.isIgnoreUpdate();
	}

        /**
         * 
         * @param pIgnoreUpdate
         */
        @Override
	public void setIgnoreUpdate(final boolean pIgnoreUpdate) {
		this.mMenuItem.setIgnoreUpdate(pIgnoreUpdate);
	}

        /**
         * 
         * @return
         */
        @Override
	public boolean isChildrenIgnoreUpdate() {
		return this.mMenuItem.isChildrenIgnoreUpdate();
	}

        /**
         * 
         * @param pChildrenIgnoreUpdate
         */
        @Override
	public void setChildrenIgnoreUpdate(final boolean pChildrenIgnoreUpdate) {
		this.mMenuItem.setChildrenIgnoreUpdate(pChildrenIgnoreUpdate);
	}

        /**
         * 
         * @param pUserData
         */
        @Override
	public void setUserData(final Object pUserData) {
		this.mMenuItem.setUserData(pUserData);
	}

	@Override
	public Object getUserData() {
		return this.mMenuItem.getUserData();
	}

	@Override
	public void onAttached() {
		this.mMenuItem.onAttached();
	}

        /**
         * 
         */
        @Override
	public void onDetached() {
		this.mMenuItem.onDetached();
	}

	@Override
	public void registerUpdateHandler(final IUpdateHandler pUpdateHandler) {
		this.mMenuItem.registerUpdateHandler(pUpdateHandler);
	}

	@Override
	public boolean unregisterUpdateHandler(final IUpdateHandler pUpdateHandler) {
		return this.mMenuItem.unregisterUpdateHandler(pUpdateHandler);
	}

        /**
         * 
         */
        @Override
	public void clearUpdateHandlers() {
		this.mMenuItem.clearUpdateHandlers();
	}

        /**
         * 
         * @param pUpdateHandlerMatcher
         * @return
         */
        @Override
	public boolean unregisterUpdateHandlers(final IUpdateHandlerMatcher pUpdateHandlerMatcher) {
		return this.mMenuItem.unregisterUpdateHandlers(pUpdateHandlerMatcher);
	}

        /**
         * 
         * @param pStringBuilder
         */
        @Override
	public void toString(final StringBuilder pStringBuilder) {
		this.mMenuItem.toString(pStringBuilder);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
