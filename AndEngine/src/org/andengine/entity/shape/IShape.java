package org.andengine.entity.shape;

import org.andengine.entity.IEntity;
import org.andengine.entity.scene.Scene.ITouchArea;
import org.andengine.opengl.shader.ShaderProgram;
import org.andengine.opengl.vbo.IVertexBufferObject;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.opengl.GLES20;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 13:32:52 - 07.07.2010
 */
public interface IShape extends IEntity, ITouchArea {
	// ===========================================================
	// Constants
	// ===========================================================

    /**
     * 
     */
    public static final int BLENDFUNCTION_SOURCE_DEFAULT = GLES20.GL_SRC_ALPHA;
    /**
     * 
     */
    public static final int BLENDFUNCTION_DESTINATION_DEFAULT = GLES20.GL_ONE_MINUS_SRC_ALPHA;

    /**
     * 
     */
    public static final int BLENDFUNCTION_SOURCE_PREMULTIPLYALPHA_DEFAULT = GLES20.GL_ONE;
    /**
     * 
     */
    public static final int BLENDFUNCTION_DESTINATION_PREMULTIPLYALPHA_DEFAULT = GLES20.GL_ONE_MINUS_SRC_ALPHA;

	// ===========================================================
	// Methods
	// ===========================================================

        /**
         * 
         * @param pOtherShape
         * @return
         */
        public boolean collidesWith(final IShape pOtherShape);

        /**
         * 
         * @return
         */
        public boolean isBlendingEnabled();
        /**
         * 
         * @param pBlendingEnabled
         */
        public void setBlendingEnabled(final boolean pBlendingEnabled);
        /**
         * 
         * @return
         */
        public int getSourceBlendFunction();
        /**
         * 
         * @return
         */
        public int getDestinationBlendFunction();
        /**
         * 
         * @param pSourceBlendFunction
         * @param pDestinationBlendFunction
         */
        public void setBlendFunction(final int pSourceBlendFunction, final int pDestinationBlendFunction);

        /**
         * 
         * @return
         */
        public VertexBufferObjectManager getVertexBufferObjectManager();
        /**
         * 
         * @return
         */
        public IVertexBufferObject getVertexBufferObject();
        /**
         * 
         * @return
         */
        public ShaderProgram getShaderProgram();
        /**
         * 
         * @param pShaderProgram
         */
        public void setShaderProgram(final ShaderProgram pShaderProgram);
}