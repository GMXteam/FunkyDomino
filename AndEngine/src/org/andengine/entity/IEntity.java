package org.andengine.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IDrawHandler;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.runnable.RunnableHandler;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierMatcher;
import org.andengine.entity.scene.Scene;
import org.andengine.util.IDisposable;
import org.andengine.util.IMatcher;
import org.andengine.util.adt.transformation.Transformation;
import org.andengine.util.call.ParameterCallable;
import org.andengine.util.color.Color;


/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 11:20:25 - 08.03.2010
 */
public interface IEntity extends IDrawHandler, IUpdateHandler, IDisposable {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

    /**
     * 
     * @return
     */
    public boolean isVisible();
        /**
         * 
         * @param pVisible
         */
        public void setVisible(final boolean pVisible);

        /**
         * 
         * @return
         */
        public boolean isIgnoreUpdate();
        /**
         * 
         * @param pIgnoreUpdate
         */
        public void setIgnoreUpdate(boolean pIgnoreUpdate);

        /**
         * 
         * @return
         */
        public boolean isChildrenVisible();
        /**
         * 
         * @param pChildrenVisible
         */
        public void setChildrenVisible(final boolean pChildrenVisible);

        /**
         * 
         * @return
         */
        public boolean isChildrenIgnoreUpdate();
        /**
         * 
         * @param pChildrenIgnoreUpdate
         */
        public void setChildrenIgnoreUpdate(boolean pChildrenIgnoreUpdate);

        /**
         * 
         * @return
         */
        public int getZIndex();
        /**
         * 
         * @param pZIndex
         */
        public void setZIndex(final int pZIndex);

        /**
         * 
         * @return
         */
        public boolean hasParent();
        /**
         * 
         * @return
         */
        public IEntity getParent();
        /**
         * 
         * @param pEntity
         */
        public void setParent(final IEntity pEntity);

        /**
         * 
         * @return
         */
        public float getX();
        /**
         * 
         * @return
         */
        public float getY();

        /**
         * 
         * @return
         */
        public float getInitialX();
        /**
         * 
         * @return
         */
        public float getInitialY();

        /**
         * 
         */
        public void setInitialPosition();
        /**
         * 
         * @param pOtherEntity
         */
        public void setPosition(final IEntity pOtherEntity);
        /**
         * 
         * @param pX
         * @param pY
         */
        public void setPosition(final float pX, final float pY);

        /**
         * 
         * @return
         */
        public boolean isRotated();
        /**
         * 
         * @return
         */
        public float getRotation();
        /**
         * 
         * @param pRotation
         */
        public void setRotation(final float pRotation);

        /**
         * 
         * @return
         */
        public float getRotationCenterX();
        /**
         * 
         * @return
         */
        public float getRotationCenterY();
        /**
         * 
         * @param pRotationCenterX
         */
        public void setRotationCenterX(final float pRotationCenterX);
        /**
         * 
         * @param pRotationCenterY
         */
        public void setRotationCenterY(final float pRotationCenterY);
        /**
         * 
         * @param pRotationCenterX
         * @param pRotationCenterY
         */
        public void setRotationCenter(final float pRotationCenterX, final float pRotationCenterY);

        /**
         * 
         * @return
         */
        public boolean isScaled();
        /**
         * 
         * @return
         */
        public float getScaleX();
        /**
         * 
         * @return
         */
        public float getScaleY();
        /**
         * 
         * @param pScaleX
         */
        public void setScaleX(final float pScaleX);
        /**
         * 
         * @param pScaleY
         */
        public void setScaleY(final float pScaleY);
        /**
         * 
         * @param pScale
         */
        public void setScale(final float pScale);
        /**
         * 
         * @param pScaleX
         * @param pScaleY
         */
        public void setScale(final float pScaleX, final float pScaleY);

        /**
         * 
         * @return
         */
        public float getScaleCenterX();
        /**
         * 
         * @return
         */
        public float getScaleCenterY();
        /**
         * 
         * @param pScaleCenterX
         */
        public void setScaleCenterX(final float pScaleCenterX);
        /**
         * 
         * @param pScaleCenterY
         */
        public void setScaleCenterY(final float pScaleCenterY);
        /**
         * 
         * @param pScaleCenterX
         * @param pScaleCenterY
         */
        public void setScaleCenter(final float pScaleCenterX, final float pScaleCenterY);

        /**
         * 
         * @return
         */
        public boolean isSkewed();
        /**
         * 
         * @return
         */
        public float getSkewX();
        /**
         * 
         * @return
         */
        public float getSkewY();
        /**
         * 
         * @param pSkewX
         */
        public void setSkewX(final float pSkewX);
        /**
         * 
         * @param pSkewY
         */
        public void setSkewY(final float pSkewY);
        /**
         * 
         * @param pSkew
         */
        public void setSkew(final float pSkew);
        /**
         * 
         * @param pSkewX
         * @param pSkewY
         */
        public void setSkew(final float pSkewX, final float pSkewY);

        /**
         * 
         * @return
         */
        public float getSkewCenterX();
        /**
         * 
         * @return
         */
        public float getSkewCenterY();
        /**
         * 
         * @param pSkewCenterX
         */
        public void setSkewCenterX(final float pSkewCenterX);
        /**
         * 
         * @param pSkewCenterY
         */
        public void setSkewCenterY(final float pSkewCenterY);
        /**
         * 
         * @param pSkewCenterX
         * @param pSkewCenterY
         */
        public void setSkewCenter(final float pSkewCenterX, final float pSkewCenterY);

        /**
         * 
         * @return
         */
        public boolean isRotatedOrScaledOrSkewed();

        /**
         * 
         * @return
         */
        public float getRed();
        /**
         * 
         * @return
         */
        public float getGreen();
        /**
         * 
         * @return
         */
        public float getBlue();
        /**
         * 
         * @return
         */
        public float getAlpha();
        /**
         * 
         * @return
         */
        public Color getColor();

        /**
         * 
         * @param pAlpha
         */
        public void setAlpha(final float pAlpha);
        /**
         * 
         * @param pColor
         */
        public void setColor(final Color pColor);
        /**
         * 
         * @param pRed
         * @param pGreen
         * @param pBlue
         */
        public void setColor(final float pRed, final float pGreen, final float pBlue);
        /**
         * 
         * @param pRed
         * @param pGreen
         * @param pBlue
         * @param pAlpha
         */
        public void setColor(final float pRed, final float pGreen, final float pBlue, final float pAlpha);

	/**
	 * @return a shared(!) float[] of length 2.
	 */
	public float[] getSceneCenterCoordinates();

	/**
	 * @param pX
	 * @param pY
	 * @return a shared(!) float[] of length 2.
	 */
	public float[] convertLocalToSceneCoordinates(final float pX, final float pY);
	/**
	 * @param pX
	 * @param pY
	 * @param pReuse must be of length 2.
	 * @return <code>pReuse</code> as a convenience.
	 */
	public float[] convertLocalToSceneCoordinates(final float pX, final float pY, final float[] pReuse);
	/**
	 * @param pCoordinates must be of length 2.
	 * @return a shared(!) float[] of length 2.
	 */
	public float[] convertLocalToSceneCoordinates(final float[] pCoordinates);
	/**
	 * @param pCoordinates must be of length 2.
	 * @param pReuse must be of length 2.
	 * @return <code>pReuse</code> as a convenience.
	 */
	public float[] convertLocalToSceneCoordinates(final float[] pCoordinates, final float[] pReuse);

	/**
	 * @param pX
	 * @param pY
	 * @return a shared(!) float[] of length 2.
	 */
	public float[] convertSceneToLocalCoordinates(final float pX, final float pY);
	/**
	 * @param pX
	 * @param pY
	 * @param pReuse must be of length 2.
	 * @return <code>pReuse</code> as a convenience.
	 */
	public float[] convertSceneToLocalCoordinates(final float pX, final float pY, final float[] pReuse);
	/**
	 * @param pCoordinates must be of length 2.
	 * @return a shared(!) float[] of length 2.
	 */
	public float[] convertSceneToLocalCoordinates(final float[] pCoordinates);
	/**
	 * @param pCoordinates must be of length 2.
	 * @param pReuse must be of length 2.
	 * @return <code>pReuse</code> as a convenience.
	 */
	public float[] convertSceneToLocalCoordinates(final float[] pCoordinates, final float[] pReuse);

        /**
         * 
         * @return
         */
        public Transformation getLocalToSceneTransformation();
        /**
         * 
         * @return
         */
        public Transformation getSceneToLocalTransformation();

        /**
         * 
         * @return
         */
        public Transformation getLocalToParentTransformation();
        /**
         * 
         * @return
         */
        public Transformation getParentToLocalTransformation();

        /**
         * 
         * @return
         */
        public int getChildCount();

        /**
         * 
         */
        public void onAttached();
        /**
         * 
         */
        public void onDetached();

        /**
         * 
         * @param pEntity
         */
        public void attachChild(final IEntity pEntity);
        /**
         * 
         * @param pEntity
         * @param pIndex
         * @return
         */
        public boolean attachChild(final IEntity pEntity, final int pIndex);

        /**
         * 
         * @param pIndex
         * @return
         */
        public IEntity getChild(final int pIndex);
        /**
         * 
         * @param pEntityMatcher
         * @return
         */
        public IEntity getChild(final IEntityMatcher pEntityMatcher);
        /**
         * 
         * @return
         */
        public IEntity getFirstChild();
        /**
         * 
         * @return
         */
        public IEntity getLastChild();
        /**
         * 
         * @param pEntity
         * @return
         */
        public int getChildIndex(final IEntity pEntity);
        /**
         * 
         * @param pEntity
         * @param pIndex
         * @return
         */
        public boolean setChildIndex(final IEntity pEntity, final int pIndex);

	/**
	 * @param pEntityMatcher
	 * @return all children (recursively!) that match the supplied {@link IEntityMatcher}.
	 */
	public ArrayList<IEntity> query(final IEntityMatcher pEntityMatcher);
	/**
         * @param <L> 
         * @param pEntityMatcher
	 * @param pResult the {@link List} to put the result into.
	 * @return all children (recursively!) that match the supplied {@link IEntityMatcher}.
	 */
	public <L extends List<IEntity>> L query(final IEntityMatcher pEntityMatcher, final L pResult);
	/**
         * @param <S> 
         * @param pEntityMatcher
	 * @return all children (recursively!) that match the supplied {@link IEntityMatcher}.
	 * @throws ClassCastException when the supplied {@link IEntityMatcher} matched a {@link IEntity} that was not of the requested subtype.
	 */
	public <S extends IEntity> ArrayList<S> queryForSubclass(IEntityMatcher pEntityMatcher) throws ClassCastException;
	/**
         * @param <L> 
         * @param <S> 
         * @param pEntityMatcher
	 * @param pResult the {@link List} to put the result into.
	 * @return all children (recursively!) that match the supplied {@link IEntityMatcher}.
	 * @throws ClassCastException when the supplied {@link IEntityMatcher} matched a {@link IEntity} that was not of the requested subtype.
	 */
	public <L extends List<S>, S extends IEntity> L queryForSubclass(final IEntityMatcher pEntityMatcher, final L pResult) throws ClassCastException;

        /**
         * 
         * @param pIndexA
         * @param pIndexB
         * @return
         */
        public boolean swapChildren(final int pIndexA, final int pIndexB);
        /**
         * 
         * @param pEntityA
         * @param pEntityB
         * @return
         */
        public boolean swapChildren(final IEntity pEntityA, final IEntity pEntityB);

	/**
	 * Immediately sorts the {@link IEntity}s based on their ZIndex. Sort is stable.
	 */
	public void sortChildren();
	/**
	 * Sorts the {@link IEntity}s based on their ZIndex. Sort is stable.
	 * In contrast to {@link IEntity#sortChildren()} this method is particularly useful to avoid multiple sorts per frame. 
	 * @param pImmediate if <code>true</code>, the sorting is executed immediately.
	 * If <code>false</code> the sorting is executed before the next (visible) drawing of the children of this {@link IEntity}. 
	 */
	public void sortChildren(final boolean pImmediate);
	/**
	 * Sorts the {@link IEntity}s based on the {@link Comparator} supplied. Sort is stable.
	 * @param pEntityComparator
	 */
	public void sortChildren(final Comparator<IEntity> pEntityComparator);

        /**
         * 
         * @return
         */
        public boolean detachSelf();

	/**
	 * <b><i>WARNING:</i> This function should be called from within
	 * {@link RunnableHandler#postRunnable(Runnable)} which is registered
	 * to a {@link Scene} or the {@link Engine} itself, because otherwise
	 * it may throw an {@link IndexOutOfBoundsException} in the
	 * Update-Thread or the GL-Thread!</b>
         * 
         * @param pEntity 
         * @return 
         */
	public boolean detachChild(final IEntity pEntity);
	/**
	 * <b><i>WARNING:</i> This function should be called from within
	 * {@link RunnableHandler#postRunnable(Runnable)} which is registered
	 * to a {@link Scene} or the {@link Engine} itself, because otherwise
	 * it may throw an {@link IndexOutOfBoundsException} in the
	 * Update-Thread or the GL-Thread!</b>
         * 
         * @param pEntityMatcher 
         * @return 
         */
	public IEntity detachChild(final IEntityMatcher pEntityMatcher);
	/**
	 * <b><i>WARNING:</i> This function should be called from within
	 * {@link RunnableHandler#postRunnable(Runnable)} which is registered
	 * to a {@link Scene} or the {@link Engine} itself, because otherwise
	 * it may throw an {@link IndexOutOfBoundsException} in the
	 * Update-Thread or the GL-Thread!</b>
         * 
         * @param pEntityMatcher 
         * @return 
         */
	public boolean detachChildren(final IEntityMatcher pEntityMatcher);

        /**
         * 
         */
        public void detachChildren();

        /**
         * 
         * @param pEntityParameterCallable
         */
        public void callOnChildren(final IEntityParameterCallable pEntityParameterCallable);
        /**
         * 
         * @param pEntityParameterCallable
         * @param pEntityMatcher
         */
        public void callOnChildren(final IEntityParameterCallable pEntityParameterCallable, final IEntityMatcher pEntityMatcher);

        /**
         * 
         * @param pUpdateHandler
         */
        public void registerUpdateHandler(final IUpdateHandler pUpdateHandler);
        /**
         * 
         * @param pUpdateHandler
         * @return
         */
        public boolean unregisterUpdateHandler(final IUpdateHandler pUpdateHandler);
        /**
         * 
         * @param pUpdateHandlerMatcher
         * @return
         */
        public boolean unregisterUpdateHandlers(final IUpdateHandlerMatcher pUpdateHandlerMatcher);
        /**
         * 
         */
        public void clearUpdateHandlers();

        /**
         * 
         * @param pEntityModifier
         */
        public void registerEntityModifier(final IEntityModifier pEntityModifier);
        /**
         * 
         * @param pEntityModifier
         * @return
         */
        public boolean unregisterEntityModifier(final IEntityModifier pEntityModifier);
        /**
         * 
         * @param pEntityModifierMatcher
         * @return
         */
        public boolean unregisterEntityModifiers(final IEntityModifierMatcher pEntityModifierMatcher);
        /**
         * 
         */
        public void clearEntityModifiers();

        /**
         * 
         * @return
         */
        public boolean isCullingEnabled();
        /**
         * 
         * @param pCullingEnabled
         */
        public void setCullingEnabled(final boolean pCullingEnabled);
	/**
	 * Will only be performed if {@link IEntity#isCullingEnabled()} is true.
	 *
	 * @param pCamera the currently active camera to perform culling checks against.
	 * @return <code>true</code> when this object is visible by the {@link Camera}, <code>false</code> otherwise.
	 */
	public boolean isCulled(final Camera pCamera);

        /**
         * 
         * @param pUserData
         */
        public void setUserData(final Object pUserData);
        /**
         * 
         * @return
         */
        public Object getUserData();

        /**
         * 
         * @param pStringBuilder
         */
        public void toString(final StringBuilder pStringBuilder);

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

        /**
         * 
         */
        public interface IEntityMatcher extends IMatcher<IEntity> {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

            /**
             * 
             * @param pEntity
             * @return
             */
            @Override
		public boolean matches(final IEntity pEntity);
	}

        /**
         * 
         */
        public interface IEntityParameterCallable extends ParameterCallable<IEntity> {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

		@Override
		public void call(final IEntity pEntity);
	}
}
