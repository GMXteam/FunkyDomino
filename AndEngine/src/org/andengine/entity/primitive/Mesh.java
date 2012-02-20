package org.andengine.entity.primitive;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.shape.RectangularShape;
import org.andengine.entity.shape.Shape;
import org.andengine.opengl.shader.PositionColorShaderProgram;
import org.andengine.opengl.shader.constants.ShaderProgramConstants;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.HighPerformanceVertexBufferObject;
import org.andengine.opengl.vbo.IVertexBufferObject;
import org.andengine.opengl.vbo.VertexBufferObject.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.opengl.vbo.attribute.VertexBufferObjectAttribute;
import org.andengine.opengl.vbo.attribute.VertexBufferObjectAttributes;
import org.andengine.opengl.vbo.attribute.VertexBufferObjectAttributesBuilder;
import org.andengine.util.exception.MethodNotSupportedException;

import android.opengl.GLES20;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 16:44:50 - 09.02.2012
 */
public class Mesh extends Shape {
	// ===========================================================
	// Constants
	// ===========================================================

    /**
     * 
     */
    public static final int VERTEX_INDEX_X = 0;
    /**
     * 
     */
    public static final int VERTEX_INDEX_Y = Mesh.VERTEX_INDEX_X + 1;
        /**
         * 
         */
        public static final int COLOR_INDEX = Mesh.VERTEX_INDEX_Y + 1;

        /**
         * 
         */
        public static final int VERTEX_SIZE = 2 + 1;

        /**
         * 
         */
        public static final VertexBufferObjectAttributes VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT = new VertexBufferObjectAttributesBuilder(2)
	.add(ShaderProgramConstants.ATTRIBUTE_POSITION_LOCATION, ShaderProgramConstants.ATTRIBUTE_POSITION, 2, GLES20.GL_FLOAT, false)
	.add(ShaderProgramConstants.ATTRIBUTE_COLOR_LOCATION, ShaderProgramConstants.ATTRIBUTE_COLOR, 4, GLES20.GL_UNSIGNED_BYTE, true)
	.build();

	// ===========================================================
	// Fields
	// ===========================================================

        /**
         * 
         */
        protected final IMeshVertexBufferObject mMeshVertexBufferObject;
	private int mVertexCountToDraw;
	private int mDrawMode;

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * Uses a default {@link HighPerformanceMeshVertexBufferObject} in {@link DrawType#STATIC} with the {@link VertexBufferObjectAttribute}s: {@link Mesh#VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT}.
         * 
         * @param pX 
         * @param pY 
         * @param pVertexBufferObjectManager
         * @param pBufferData 
         * @param pDrawMode 
         * @param pVertexCount  
         */
	public Mesh(final float pX, final float pY, final float[] pBufferData, final int pVertexCount, final DrawMode pDrawMode, final VertexBufferObjectManager pVertexBufferObjectManager) {
		this(pX, pY, pBufferData, pVertexCount, pDrawMode, pVertexBufferObjectManager, DrawType.STATIC);
	}

	/**
	 * Uses a default {@link HighPerformanceMeshVertexBufferObject} with the {@link VertexBufferObjectAttribute}s: {@link Mesh#VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT}.
         * 
         * @param pX 
         * @param pY 
         * @param pBufferData
         * @param pVertexCount
         * @param pVertexBufferObjectManager
         * @param pDrawMode 
         * @param pDrawType  
         */
	public Mesh(final float pX, final float pY, final float[] pBufferData, final int pVertexCount, final DrawMode pDrawMode, final VertexBufferObjectManager pVertexBufferObjectManager, final DrawType pDrawType) {
		this(pX, pY, pVertexCount, pDrawMode, new HighPerformanceMeshVertexBufferObject(pVertexBufferObjectManager, pBufferData, pVertexCount, pDrawType, true, Mesh.VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT));
	}

        /**
         * 
         * @param pX
         * @param pY
         * @param pVertexCount
         * @param pDrawMode
         * @param pMeshVertexBufferObject
         */
        public Mesh(final float pX, final float pY, final int pVertexCount, final DrawMode pDrawMode, final IMeshVertexBufferObject pMeshVertexBufferObject) {
		super(pX, pY, PositionColorShaderProgram.getInstance());

		this.mDrawMode = pDrawMode.getDrawMode();
		this.mMeshVertexBufferObject = pMeshVertexBufferObject;
		this.mVertexCountToDraw = pVertexCount;

		this.mMeshVertexBufferObject.setDirtyOnHardware();

		this.setBlendingEnabled(true);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public float[] getBufferData() {
		return this.mMeshVertexBufferObject.getBufferData();
	}

        /**
         * 
         * @param pVertexCountToDraw
         */
        public void setVertexCountToDraw(final int pVertexCountToDraw) {
		this.mVertexCountToDraw = pVertexCountToDraw;
	}

        /**
         * 
         * @param pDrawMode
         */
        public void setDrawMode(final DrawMode pDrawMode) {
		this.mDrawMode = pDrawMode.mDrawMode;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @return
         */
        @Override
	public IMeshVertexBufferObject getVertexBufferObject() {
		return this.mMeshVertexBufferObject;
	}

	@Override
	protected void preDraw(final GLState pGLState, final Camera pCamera) {
		super.preDraw(pGLState, pCamera);

		this.mMeshVertexBufferObject.bind(pGLState, this.mShaderProgram);
	}

	@Override
	protected void draw(final GLState pGLState, final Camera pCamera) {
		this.mMeshVertexBufferObject.draw(this.mDrawMode, this.mVertexCountToDraw);
	}

	@Override
	protected void postDraw(final GLState pGLState, final Camera pCamera) {
		this.mMeshVertexBufferObject.unbind(pGLState, this.mShaderProgram);

		super.postDraw(pGLState, pCamera);
	}

	@Override
	protected void onUpdateColor() {
		this.mMeshVertexBufferObject.onUpdateColor(this);
	}

        /**
         * 
         */
        @Override
	protected void onUpdateVertices() {
		this.mMeshVertexBufferObject.onUpdateVertices(this);
	}

        /**
         * 
         * @param pX
         * @param pY
         * @return
         * @deprecated
         */
        @Override
	@Deprecated
	public boolean contains(final float pX, final float pY) {
		throw new MethodNotSupportedException();
	}

	@Override
	public boolean collidesWith(final IShape pOtherShape) {
		if(pOtherShape instanceof Line) {
			// TODO
			return false;
		} else if(pOtherShape instanceof RectangularShape) {
			// TODO
			return false;
		} else {
			return false;
		}
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
        public static interface IMeshVertexBufferObject extends IVertexBufferObject {
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
            public float[] getBufferData();
                /**
                 * 
                 * @param pMesh
                 */
                public void onUpdateColor(final Mesh pMesh);
                /**
                 * 
                 * @param pMesh
                 */
                public void onUpdateVertices(final Mesh pMesh);
	}

        /**
         * 
         */
        public static class HighPerformanceMeshVertexBufferObject extends HighPerformanceVertexBufferObject implements IMeshVertexBufferObject {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Fields
		// ===========================================================

		private final int mVertexCount;

		// ===========================================================
		// Constructors
		// ===========================================================

                /**
                 * 
                 * @param pVertexBufferObjectManager
                 * @param pBufferData
                 * @param pVertexCount
                 * @param pDrawType
                 * @param pAutoDispose
                 * @param pVertexBufferObjectAttributes
                 */
                public HighPerformanceMeshVertexBufferObject(final VertexBufferObjectManager pVertexBufferObjectManager, final float[] pBufferData, final int pVertexCount, final DrawType pDrawType, final boolean pAutoDispose, final VertexBufferObjectAttributes pVertexBufferObjectAttributes) {
			super(pVertexBufferObjectManager, pBufferData, pDrawType, pAutoDispose, pVertexBufferObjectAttributes);

			this.mVertexCount = pVertexCount;
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

                /**
                 * 
                 * @param pMesh
                 */
                @Override
		public void onUpdateColor(final Mesh pMesh) {
			final float[] bufferData = this.mBufferData;

			final float packedColor = pMesh.getColor().getABGRPackedFloat();

			for(int i = 0; i < this.mVertexCount; i++) {
				bufferData[(i * Mesh.VERTEX_SIZE) + Mesh.COLOR_INDEX] = packedColor;
			}

			this.setDirtyOnHardware();
		}

                /**
                 * 
                 * @param pMesh
                 */
                @Override
		public void onUpdateVertices(final Mesh pMesh) {
			/* Since the buffer data is managed from the caller, we just mark the buffer data as dirty. */

			this.setDirtyOnHardware();
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
        public static enum DrawMode {
		// ===========================================================
		// Elements
		// ===========================================================

            /**
             * 
             */
            POINTS(GLES20.GL_POINTS),
            /**
             * 
             */
            LINE_STRIP(GLES20.GL_LINE_STRIP),
                /**
                 * 
                 */
                LINE_LOOP(GLES20.GL_LINE_LOOP),
                /**
                 * 
                 */
                LINES(GLES20.GL_LINES),
                /**
                 * 
                 */
                TRIANGLE_STRIP(GLES20.GL_TRIANGLE_STRIP),
                /**
                 * 
                 */
                TRIANGLE_FAN(GLES20.GL_TRIANGLE_FAN),
                /**
                 * 
                 */
                TRIANGLES(GLES20.GL_TRIANGLES);

		// ===========================================================
		// Constants
		// ===========================================================

                /**
                 * 
                 */
                public final int mDrawMode;

		// ===========================================================
		// Fields
		// ===========================================================

		// ===========================================================
		// Constructors
		// ===========================================================

		private DrawMode(final int pDrawMode) {
			this.mDrawMode = pDrawMode;
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================

                /**
                 * 
                 * @return
                 */
                public int getDrawMode() {
			return this.mDrawMode;
		}

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}
}
