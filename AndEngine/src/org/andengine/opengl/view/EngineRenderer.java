package org.andengine.opengl.view;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import org.andengine.engine.Engine;
import org.andengine.opengl.util.GLState;
import org.andengine.util.debug.Debug;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 11:57:29 - 08.03.2010
 */
public class EngineRenderer implements GLSurfaceView.Renderer {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	final Engine mEngine;
	final ConfigChooser mConfigChooser;
	final boolean mMultiSampling;
	final IRendererListener mRendererListener;
	final GLState mGLState;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pEngine
         * @param pConfigChooser
         * @param pRendererListener
         */
        public EngineRenderer(final Engine pEngine, final ConfigChooser pConfigChooser, final IRendererListener pRendererListener) {
		this.mEngine = pEngine;
		this.mConfigChooser = pConfigChooser;
		this.mRendererListener = pRendererListener;
		this.mGLState = new GLState();
		this.mMultiSampling = this.mEngine.getEngineOptions().getRenderOptions().isMultiSampling();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pGL
         * @param pEGLConfig
         */
        @Override
	public void onSurfaceCreated(final GL10 pGL, final EGLConfig pEGLConfig) {
		synchronized(GLState.class) {
			if(this.mRendererListener != null) {
				this.mRendererListener.onSurfaceCreated();
			}

			this.mGLState.reset(this.mEngine.getEngineOptions().getRenderOptions(), this.mConfigChooser, pEGLConfig);

			// TODO Check if available and make available through EngineOptions-RenderOptions
//			GLES20.glEnable(GLES20.GL_POLYGON_SMOOTH);
//			GLES20.glHint(GLES20.GL_POLYGON_SMOOTH_HINT, GLES20.GL_NICEST);
//			GLES20.glEnable(GLES20.GL_LINE_SMOOTH);
//			GLES20.glHint(GLES20.GL_LINE_SMOOTH_HINT, GLES20.GL_NICEST);
//			GLES20.glEnable(GLES20.GL_POINT_SMOOTH);
//			GLES20.glHint(GLES20.GL_POINT_SMOOTH_HINT, GLES20.GL_NICEST);

			this.mGLState.disableDither();
			this.mGLState.disableDepthTest();

			this.mGLState.enableBlend();

			/* Enabling culling doesn't really make sense, because triangles are never drawn 'backwards' on purpose. */
//			this.mGLState.enableCulling();
//			GLES20.glFrontFace(GLES20.GL_CCW);
//			GLES20.glCullFace(GLES20.GL_BACK);
		}
	}

        /**
         * 
         * @param pGL
         * @param pWidth
         * @param pHeight
         */
        @Override
	public void onSurfaceChanged(final GL10 pGL, final int pWidth, final int pHeight) {
		if(this.mRendererListener != null) {
			this.mRendererListener.onSurfaceChanged(pWidth, pHeight);
		}

		this.mEngine.setSurfaceSize(pWidth, pHeight);
		GLES20.glViewport(0, 0, pWidth, pHeight);
		this.mGLState.loadProjectionGLMatrixIdentity();
	}

        /**
         * 
         * @param pGL
         */
        @Override
	public void onDrawFrame(final GL10 pGL) {
		synchronized(GLState.class) {
			if (this.mMultiSampling && this.mConfigChooser.isCoverageMultiSampling()) {
				final int GL_COVERAGE_BUFFER_BIT_NV = 0x8000;
				GLES20.glClear(GL_COVERAGE_BUFFER_BIT_NV);
			}

			try {
				this.mEngine.onDrawFrame(this.mGLState);
			} catch (final InterruptedException e) {
				Debug.e("GLThread interrupted!", e);
			}
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}