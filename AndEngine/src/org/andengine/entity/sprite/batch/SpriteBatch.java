package org.andengine.entity.sprite.batch;

import java.nio.FloatBuffer;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.shape.Shape;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.shader.PositionColorTextureCoordinatesShaderProgram;
import org.andengine.opengl.shader.ShaderProgram;
import org.andengine.opengl.shader.constants.ShaderProgramConstants;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.HighPerformanceVertexBufferObject;
import org.andengine.opengl.vbo.IVertexBufferObject;
import org.andengine.opengl.vbo.LowMemoryVertexBufferObject;
import org.andengine.opengl.vbo.VertexBufferObject.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.opengl.vbo.attribute.VertexBufferObjectAttributes;
import org.andengine.opengl.vbo.attribute.VertexBufferObjectAttributesBuilder;
import org.andengine.util.adt.transformation.Transformation;
import org.andengine.util.color.ColorUtils;

import android.opengl.GLES20;

/**
 * TODO TRY DEGENERATE TRIANGLES!
 * TODO Check if there is this multiple-of-X-byte(?) alignment optimization?
 * 
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 11:45:48 - 14.06.2011
 */
public class SpriteBatch extends Shape {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final float[] VERTICES_TMP = new float[8];

	private static final Transformation TRANSFORATION_TMP = new Transformation();

        /**
         * 
         */
        public static final int VERTEX_INDEX_X = 0;
        /**
         * 
         */
        public static final int VERTEX_INDEX_Y = SpriteBatch.VERTEX_INDEX_X + 1;
        /**
         * 
         */
        public static final int COLOR_INDEX = SpriteBatch.VERTEX_INDEX_Y + 1;
        /**
         * 
         */
        public static final int TEXTURECOORDINATES_INDEX_U = SpriteBatch.COLOR_INDEX + 1;
        /**
         * 
         */
        public static final int TEXTURECOORDINATES_INDEX_V = SpriteBatch.TEXTURECOORDINATES_INDEX_U + 1;

        /**
         * 
         */
        public static final int VERTEX_SIZE = 2 + 1 + 2;
        /**
         * 
         */
        public static final int VERTICES_PER_SPRITE = 6;
        /**
         * 
         */
        public static final int SPRITE_SIZE = SpriteBatch.VERTEX_SIZE * SpriteBatch.VERTICES_PER_SPRITE;

        /**
         * 
         */
        public static final VertexBufferObjectAttributes VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT = new VertexBufferObjectAttributesBuilder(3)
		.add(ShaderProgramConstants.ATTRIBUTE_POSITION_LOCATION, ShaderProgramConstants.ATTRIBUTE_POSITION, 2, GLES20.GL_FLOAT, false)
		.add(ShaderProgramConstants.ATTRIBUTE_COLOR_LOCATION, ShaderProgramConstants.ATTRIBUTE_COLOR, 4, GLES20.GL_UNSIGNED_BYTE, true)
		.add(ShaderProgramConstants.ATTRIBUTE_TEXTURECOORDINATES_LOCATION, ShaderProgramConstants.ATTRIBUTE_TEXTURECOORDINATES, 2, GLES20.GL_FLOAT, false)
		.build();

	// ===========================================================
	// Fields
	// ===========================================================

        /**
         * 
         */
        protected ITexture mTexture;
        /**
         * 
         */
        protected final int mCapacity;
        /**
         * 
         */
        protected final ISpriteBatchVertexBufferObject mSpriteBatchVertexBufferObject;

        /**
         * 
         */
        protected int mIndex;
        /**
         * 
         */
        protected int mVertices;

	// ===========================================================
	// Constructors
	// ===========================================================

        /**
         * 
         * @param pTexture
         * @param pCapacity
         * @param pVertexBufferObjectManager
         */
        public SpriteBatch(final ITexture pTexture, final int pCapacity, final VertexBufferObjectManager pVertexBufferObjectManager) {
		this(pTexture, pCapacity, pVertexBufferObjectManager, DrawType.STATIC);
	}

        /**
         * 
         * @param pX
         * @param pY
         * @param pTexture
         * @param pCapacity
         * @param pVertexBufferObjectManager
         */
        public SpriteBatch(final float pX, final float pY, final ITexture pTexture, final int pCapacity, final VertexBufferObjectManager pVertexBufferObjectManager) {
		this(pX, pY, pTexture, pCapacity, pVertexBufferObjectManager, DrawType.STATIC);
	}

        /**
         * 
         * @param pTexture
         * @param pCapacity
         * @param pVertexBufferObjectManager
         * @param pDrawType
         */
        public SpriteBatch(final ITexture pTexture, final int pCapacity, final VertexBufferObjectManager pVertexBufferObjectManager, final DrawType pDrawType) {
		this(pTexture, pCapacity, new HighPerformanceSpriteBatchVertexBufferObject(pVertexBufferObjectManager, pCapacity * SpriteBatch.SPRITE_SIZE, pDrawType, true, SpriteBatch.VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT));
	}

        /**
         * 
         * @param pX
         * @param pY
         * @param pTexture
         * @param pCapacity
         * @param pVertexBufferObjectManager
         * @param pDrawType
         */
        public SpriteBatch(final float pX, final float pY, final ITexture pTexture, final int pCapacity, final VertexBufferObjectManager pVertexBufferObjectManager, final DrawType pDrawType) {
		this(pX, pY, pTexture, pCapacity, new HighPerformanceSpriteBatchVertexBufferObject(pVertexBufferObjectManager, pCapacity * SpriteBatch.SPRITE_SIZE, pDrawType, true, SpriteBatch.VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT));
	}

        /**
         * 
         * @param pTexture
         * @param pCapacity
         * @param pVertexBufferObjectManager
         * @param pShaderProgram
         */
        public SpriteBatch(final ITexture pTexture, final int pCapacity, final VertexBufferObjectManager pVertexBufferObjectManager, final ShaderProgram pShaderProgram) {
		this(pTexture, pCapacity, pVertexBufferObjectManager, DrawType.STATIC, pShaderProgram);
	}

        /**
         * 
         * @param pX
         * @param pY
         * @param pTexture
         * @param pVertexBufferObjectManager
         * @param pCapacity
         * @param pShaderProgram
         */
        public SpriteBatch(final float pX, final float pY, final ITexture pTexture, final VertexBufferObjectManager pVertexBufferObjectManager, final int pCapacity, final ShaderProgram pShaderProgram) {
		this(pX, pY, pTexture, pCapacity, pVertexBufferObjectManager, DrawType.STATIC, pShaderProgram);
	}

        /**
         * 
         * @param pTexture
         * @param pCapacity
         * @param pVertexBufferObjectManager
         * @param pDrawType
         * @param pShaderProgram
         */
        public SpriteBatch(final ITexture pTexture, final int pCapacity, final VertexBufferObjectManager pVertexBufferObjectManager, final DrawType pDrawType, final ShaderProgram pShaderProgram) {
		this(pTexture, pCapacity, new HighPerformanceSpriteBatchVertexBufferObject(pVertexBufferObjectManager, pCapacity * SpriteBatch.SPRITE_SIZE, pDrawType, true, SpriteBatch.VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT), pShaderProgram);
	}

        /**
         * 
         * @param pX
         * @param pY
         * @param pTexture
         * @param pCapacity
         * @param pVertexBufferObjectManager
         * @param pDrawType
         * @param pShaderProgram
         */
        public SpriteBatch(final float pX, final float pY, final ITexture pTexture, final int pCapacity, final VertexBufferObjectManager pVertexBufferObjectManager, final DrawType pDrawType, final ShaderProgram pShaderProgram) {
		this(pX, pY, pTexture, pCapacity, new HighPerformanceSpriteBatchVertexBufferObject(pVertexBufferObjectManager, pCapacity * SpriteBatch.SPRITE_SIZE, pDrawType, true, SpriteBatch.VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT), pShaderProgram);
	}

        /**
         * 
         * @param pTexture
         * @param pCapacity
         * @param pSpriteBatchVertexBufferObject
         */
        public SpriteBatch(final ITexture pTexture, final int pCapacity, final ISpriteBatchVertexBufferObject pSpriteBatchVertexBufferObject) {
		this(pTexture, pCapacity, pSpriteBatchVertexBufferObject, PositionColorTextureCoordinatesShaderProgram.getInstance());
	}

        /**
         * 
         * @param pX
         * @param pY
         * @param pTexture
         * @param pCapacity
         * @param pSpriteBatchVertexBufferObject
         */
        public SpriteBatch(final float pX, final float pY, final ITexture pTexture, final int pCapacity, final ISpriteBatchVertexBufferObject pSpriteBatchVertexBufferObject) {
		this(pX, pY, pTexture, pCapacity, pSpriteBatchVertexBufferObject, PositionColorTextureCoordinatesShaderProgram.getInstance());
	}

        /**
         * 
         * @param pTexture
         * @param pCapacity
         * @param pSpriteBatchVertexBufferObject
         * @param pShaderProgram
         */
        public SpriteBatch(final ITexture pTexture, final int pCapacity, final ISpriteBatchVertexBufferObject pSpriteBatchVertexBufferObject, final ShaderProgram pShaderProgram) {
		this(0, 0, pTexture, pCapacity, pSpriteBatchVertexBufferObject, pShaderProgram);
	}

        /**
         * 
         * @param pX
         * @param pY
         * @param pTexture
         * @param pCapacity
         * @param pSpriteBatchVertexBufferObject
         * @param pShaderProgram
         */
        public SpriteBatch(final float pX, final float pY, final ITexture pTexture, final int pCapacity, final ISpriteBatchVertexBufferObject pSpriteBatchVertexBufferObject, final ShaderProgram pShaderProgram) {
		super(pX, pY, pShaderProgram);

		this.mTexture = pTexture;
		this.mCapacity = pCapacity;
		this.mSpriteBatchVertexBufferObject = pSpriteBatchVertexBufferObject;

		this.setBlendingEnabled(true);
		this.initBlendFunction(this.mTexture);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

        /**
         * 
         * @return
         */
        public int getIndex() {
		return this.mIndex;
	}

        /**
         * 
         * @return
         */
        public ITexture getTexture() {
		return this.mTexture;
	}

        /**
         * 
         * @param pTexture
         */
        public void setTexture(final ITexture pTexture) {
		this.mTexture = pTexture;
	}

        /**
         * 
         * @param pIndex
         */
        public void setIndex(final int pIndex) {
		this.assertCapacity(pIndex);

		this.mIndex = pIndex;

		final int bufferDataOffset = pIndex * SpriteBatch.SPRITE_SIZE;

		this.mSpriteBatchVertexBufferObject.setBufferDataOffset(bufferDataOffset);
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @return
         */
        @Override
	public ISpriteBatchVertexBufferObject getVertexBufferObject() {
		return this.mSpriteBatchVertexBufferObject;
	}

	@Override
	public boolean collidesWith(final IShape pOtherShape) {
		return false;
	}

        /**
         * 
         * @param pX
         * @param pY
         * @return
         */
        @Override
	public boolean contains(final float pX, final float pY) {
		return false;
	}

        /**
         * 
         */
        @Override
	protected void onUpdateVertices() {
		/* Nothing. */
	}

	@Override
	protected void preDraw(final GLState pGLState, final Camera pCamera) {
		super.preDraw(pGLState, pCamera);

		if(this.mBlendingEnabled) {
			pGLState.enableBlend();
			pGLState.blendFunction(this.mSourceBlendFunction, this.mDestinationBlendFunction);
		}

		this.mTexture.bind(pGLState);

		this.mSpriteBatchVertexBufferObject.bind(pGLState, this.mShaderProgram);
	}

	@Override
	protected void draw(final GLState pGLState, final Camera pCamera) {
		this.begin();

		this.mSpriteBatchVertexBufferObject.draw(GLES20.GL_TRIANGLES, this.mVertices);

		this.end();
	}

	@Override
	protected void postDraw(final GLState pGLState, final Camera pCamera) {
		this.mSpriteBatchVertexBufferObject.unbind(pGLState, this.mShaderProgram);

		if(this.mBlendingEnabled) {
			pGLState.disableBlend();
		}

		super.postDraw(pGLState, pCamera);
	}

        /**
         * 
         */
        @Override
	public void reset() {
		super.reset();

		this.initBlendFunction(this.mTexture);
	}

	@Override
	public void dispose() {
		super.dispose();

		if(this.mSpriteBatchVertexBufferObject != null && this.mSpriteBatchVertexBufferObject.isAutoDispose() && !this.mSpriteBatchVertexBufferObject.isDisposed()) {
			this.mSpriteBatchVertexBufferObject.dispose();
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

        /**
         * 
         */
        protected void begin() {
//		GLState.disableDepthMask(pGL); // TODO Test effect of this
	}

        /**
         * 
         */
        protected void end() {
//		GLState.enableDepthMask(pGL);
	}

	/**
         * @param pTextureRegion 
         * @param pY 
         * @param pX 
         * @param pWidth 
         * @param pGreen 
         * @param pBlue 
         * @param pHeight 
         * @param pAlpha 
         * @param pRed 
         * @see {@link SpriteBatchVertexBufferObject#add(ITextureRegion, float, float, float, float, float, float, float)}.
	 */
	public void draw(final ITextureRegion pTextureRegion, final float pX, final float pY, final float pWidth, final float pHeight, final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
		this.assertCapacity();
		this.assertTexture(pTextureRegion);

		this.add(pTextureRegion, pX, pY, pWidth, pHeight, pRed, pGreen, pBlue, pAlpha);

		this.mIndex++;
	}

	/**
         * @param pTextureRegion 
         * @param pX 
         * @param pY 
         * @param pWidth 
         * @param pHeight 
         * @param pPackedColor 
         * @see {@link SpriteBatchVertexBufferObject#add(ITextureRegion, float, float, float, float, float)}.
	 */
	public void draw(final ITextureRegion pTextureRegion, final float pX, final float pY, final float pWidth, final float pHeight, final float pPackedColor) {
		this.assertCapacity();
		this.assertTexture(pTextureRegion);

		this.add(pTextureRegion, pX, pY, pWidth, pHeight, pPackedColor);

		this.mIndex++;
	}

	/**
         * @param pTextureRegion 
         * @param pX 
         * @param pPackedColor 
         * @param pY 
         * @param pHeight 
         * @param pWidth 
         * @see {@link SpriteBatchVertexBufferObject#add(ITextureRegion, float, float, float, float, float)}.
	 */
	public void drawWithoutChecks(final ITextureRegion pTextureRegion, final float pX, final float pY, final float pWidth, final float pHeight, final float pPackedColor) {
		this.add(pTextureRegion, pX, pY, pWidth, pHeight, pPackedColor);

		this.mIndex++;
	}

        /**
         * 
         * @param pTextureRegion
         * @param pX
         * @param pY
         * @param pWidth
         * @param pHeight
         * @param pRed
         * @param pGreen
         * @param pBlue
         * @param pAlpha
         */
        public void drawWithoutChecks(final ITextureRegion pTextureRegion, final float pX, final float pY, final float pWidth, final float pHeight, final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
		this.add(pTextureRegion, pX, pY, pWidth, pHeight, pRed, pGreen, pBlue, pAlpha);

		this.mIndex++;
	}

	/**
         * @param pTextureRegion 
         * @param pWidth 
         * @param pY 
         * @param pX 
         * @param pRotation 
         * @param pHeight 
         * @param pGreen 
         * @param pRed 
         * @param pBlue 
         * @param pAlpha 
         * @see {@link SpriteBatchVertexBufferObject#add(ITextureRegion, float, float, float, float, float, float, float, float)}.
	 */
	public void draw(final ITextureRegion pTextureRegion, final float pX, final float pY, final float pWidth, final float pHeight, final float pRotation, final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
		this.assertCapacity();
		this.assertTexture(pTextureRegion);

		this.add(pTextureRegion, pX, pY, pWidth, pHeight, pRotation, pRed, pGreen, pBlue, pAlpha);

		this.mIndex++;
	}

        /**
         * 
         * @param pTextureRegion
         * @param pX
         * @param pY
         * @param pWidth
         * @param pHeight
         * @param pRotation
         * @param pRed
         * @param pGreen
         * @param pBlue
         * @param pAlpha
         */
        public void drawWithoutChecks(final ITextureRegion pTextureRegion, final float pX, final float pY, final float pWidth, final float pHeight, final float pRotation, final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
		this.add(pTextureRegion, pX, pY, pWidth, pHeight, pRotation, pRed, pGreen, pBlue, pAlpha);

		this.mIndex++;
	}

	/**
         * @param pTextureRegion 
         * @param pScaleY 
         * @param pX 
         * @param pWidth 
         * @param pHeight 
         * @param pGreen 
         * @param pY 
         * @param pRed 
         * @param pScaleX 
         * @param pBlue 
         * @param pAlpha 
         * @see {@link SpriteBatchVertexBufferObject#add(ITextureRegion, float, float, float, float, float, float, float, float, float)}.
	 */
	public void draw(final ITextureRegion pTextureRegion, final float pX, final float pY, final float pWidth, final float pHeight, final float pScaleX, final float pScaleY, final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
		this.assertCapacity();
		this.assertTexture(pTextureRegion);

		this.add(pTextureRegion, pX, pY, pWidth, pHeight, pScaleX, pScaleY, pRed, pGreen, pBlue, pAlpha);

		this.mIndex++;
	}

        /**
         * 
         * @param pTextureRegion
         * @param pX
         * @param pY
         * @param pWidth
         * @param pHeight
         * @param pScaleX
         * @param pScaleY
         * @param pRed
         * @param pGreen
         * @param pBlue
         * @param pAlpha
         */
        public void drawWithoutChecks(final ITextureRegion pTextureRegion, final float pX, final float pY, final float pWidth, final float pHeight, final float pScaleX, final float pScaleY, final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
		this.add(pTextureRegion, pX, pY, pWidth, pHeight, pScaleX, pScaleY, pRed, pGreen, pBlue, pAlpha);

		this.mIndex++;
	}

	/**
         * @param pTextureRegion 
         * @param pAlpha 
         * @param pX 
         * @param pWidth 
         * @param pHeight 
         * @param pRotation 
         * @param pY 
         * @param pRed 
         * @param pScaleX 
         * @param pBlue 
         * @param pScaleY 
         * @param pGreen 
         * @see {@link SpriteBatchVertexBufferObject#add(ITextureRegion, float, float, float, float, float, float, float, float, float, float)}.
	 */
	public void draw(final ITextureRegion pTextureRegion, final float pX, final float pY, final float pWidth, final float pHeight, final float pRotation, final float pScaleX, final float pScaleY, final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
		this.assertCapacity();
		this.assertTexture(pTextureRegion);

		this.add(pTextureRegion, pX, pY, pWidth, pHeight, pRotation, pScaleX, pScaleY, pRed, pGreen, pBlue, pAlpha);

		this.mIndex++;
	}

        /**
         * 
         * @param pTextureRegion
         * @param pX
         * @param pY
         * @param pWidth
         * @param pHeight
         * @param pRotation
         * @param pScaleX
         * @param pScaleY
         * @param pRed
         * @param pGreen
         * @param pBlue
         * @param pAlpha
         */
        public void drawWithoutChecks(final ITextureRegion pTextureRegion, final float pX, final float pY, final float pWidth, final float pHeight, final float pRotation, final float pScaleX, final float pScaleY, final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
		this.add(pTextureRegion, pX, pY, pWidth, pHeight, pRotation, pScaleX, pScaleY, pRed, pGreen, pBlue, pAlpha);

		this.mIndex++;
	}

	/**
         * @param pTextureRegion 
         * @param pX1 
         * @param pX3 
         * @param pX2 
         * @param pY1 
         * @param pY3 
         * @param pBlue 
         * @param pY2 
         * @param pY4 
         * @param pAlpha 
         * @param pRed 
         * @param pX4 
         * @param pGreen 
         * @see {@link SpriteBatchVertexBufferObject#addInner(ITextureRegion, float, float, float, float, float, float, float, float, float, float, float)}.
	 */
	public void draw(final ITextureRegion pTextureRegion, final float pX1, final float pY1, final float pX2, final float pY2, final float pX3, final float pY3, final float pX4, final float pY4, final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
		this.assertCapacity();
		this.assertTexture(pTextureRegion);

		this.addInner(pTextureRegion, pX1, pY1, pX2, pY2, pX3, pY3, pX4, pY4, pRed, pGreen, pBlue, pAlpha);

		this.mIndex++;
	}

        /**
         * 
         * @param pTextureRegion
         * @param pX1
         * @param pY1
         * @param pX2
         * @param pY2
         * @param pX3
         * @param pY3
         * @param pX4
         * @param pY4
         * @param pRed
         * @param pGreen
         * @param pBlue
         * @param pAlpha
         */
        public void drawWithoutChecks(final ITextureRegion pTextureRegion, final float pX1, final float pY1, final float pX2, final float pY2, final float pX3, final float pY3, final float pX4, final float pY4, final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
		this.addInner(pTextureRegion, pX1, pY1, pX2, pY2, pX3, pY3, pX4, pY4, pRed, pGreen, pBlue, pAlpha);

		this.mIndex++;
	}

	/**
         * @param pSprite 
         * @see {@link SpriteBatchVertexBufferObject#add(ITextureRegion, float, float, float, float, float)} {@link SpriteBatchVertexBufferObject#add(ITextureRegion, float, float, Transformation, float)}.
	 */
	public void draw(final Sprite pSprite) {
		this.draw(pSprite, pSprite.getColor().getABGRPackedFloat());
	}

	/**
         * @param pSprite 
         * @param pRed 
         * @param pGreen 
         * @param pBlue 
         * @param pAlpha 
         * @see {@link SpriteBatchVertexBufferObject#add(ITextureRegion, float, float, float, float, float, float, float, float)} {@link SpriteBatchVertexBufferObject#add(ITextureRegion, float, float, Transformation, float, float, float, float)}.
	 */
	public void draw(final Sprite pSprite, final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
		if(pSprite.isVisible()) {
			this.assertCapacity();

			final ITextureRegion textureRegion = pSprite.getTextureRegion();
			this.assertTexture(textureRegion);

			if(pSprite.isRotatedOrScaledOrSkewed()) {
				this.add(textureRegion, pSprite.getWidth(), pSprite.getHeight(), pSprite.getLocalToParentTransformation(), pRed, pGreen, pBlue, pAlpha);
			} else {
				this.add(textureRegion, pSprite.getX(), pSprite.getY(), pSprite.getWidth(), pSprite.getHeight(), pRed, pGreen, pBlue, pAlpha);
			}

			this.mIndex++;
		}
	}

	/**
         * @param pSprite 
         * @param pPackedColor 
         * @see {@link SpriteBatchVertexBufferObject#add(ITextureRegion, float, float, float, float, float)} {@link SpriteBatchVertexBufferObject#add(ITextureRegion, float, float, Transformation, float, float, float, float)}.
	 */
	public void draw(final Sprite pSprite, final float pPackedColor) {
		if(pSprite.isVisible()) {
			this.assertCapacity();

			final ITextureRegion textureRegion = pSprite.getTextureRegion();
			this.assertTexture(textureRegion);

			if(pSprite.isRotatedOrScaledOrSkewed()) {
				this.addWithPackedColor(textureRegion, pSprite.getWidth(), pSprite.getHeight(), pSprite.getLocalToParentTransformation(), pPackedColor);
			} else {
				this.addWithPackedColor(textureRegion, pSprite.getX(), pSprite.getY(), pSprite.getWidth(), pSprite.getHeight(), pPackedColor);
			}

			this.mIndex++;
		}
	}

        /**
         * 
         * @param pSprite
         */
        public void drawWithoutChecks(final Sprite pSprite) {
		this.drawWithoutChecks(pSprite, pSprite.getColor().getABGRPackedFloat());
	}

        /**
         * 
         * @param pSprite
         * @param pRed
         * @param pGreen
         * @param pBlue
         * @param pAlpha
         */
        public void drawWithoutChecks(final Sprite pSprite, final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
		if(pSprite.isVisible()) {
			final ITextureRegion textureRegion = pSprite.getTextureRegion();

			if(pSprite.isRotatedOrScaledOrSkewed()) {
				this.add(textureRegion, pSprite.getWidth(), pSprite.getHeight(), pSprite.getLocalToParentTransformation(), pRed, pGreen, pBlue, pAlpha);
			} else {
				this.add(textureRegion, pSprite.getX(), pSprite.getY(), pSprite.getWidth(), pSprite.getHeight(), pRed, pGreen, pBlue, pAlpha);
			}

			this.mIndex++;
		}
	}

        /**
         * 
         * @param pSprite
         * @param pPackedColor
         */
        public void drawWithoutChecks(final Sprite pSprite, final float pPackedColor) {
		if(pSprite.isVisible()) {
			final ITextureRegion textureRegion = pSprite.getTextureRegion();

			if(pSprite.isRotatedOrScaledOrSkewed()) {
				this.addWithPackedColor(textureRegion, pSprite.getWidth(), pSprite.getHeight(), pSprite.getLocalToParentTransformation(), pPackedColor);
			} else {
				this.addWithPackedColor(textureRegion, pSprite.getX(), pSprite.getY(), pSprite.getWidth(), pSprite.getHeight(), pPackedColor);
			}

			this.mIndex++;
		}
	}

        /**
         * 
         */
        public void submit() {
		this.onSubmit();
	}

        /**
         * 
         */
        protected void onSubmit() {
		this.mVertices = this.mIndex * SpriteBatch.VERTICES_PER_SPRITE;

		this.mSpriteBatchVertexBufferObject.setDirtyOnHardware();

		this.mIndex = 0;
		this.mSpriteBatchVertexBufferObject.setBufferDataOffset(0);
	}

	private void assertCapacity(final int pIndex) {
		if(pIndex >= this.mCapacity) {
			throw new IllegalStateException("This supplied pIndex: '" + pIndex + "' is exceeding the capacity: '" + this.mCapacity + "' of this SpriteBatch!");
		}
	}

	private void assertCapacity() {
		if(this.mIndex == this.mCapacity) {
			throw new IllegalStateException("This SpriteBatch has already reached its capacity (" + this.mCapacity + ") !");
		}
	}

        /**
         * 
         * @param pTextureRegion
         */
        protected void assertTexture(final ITextureRegion pTextureRegion) {
		if(pTextureRegion.getTexture() != this.mTexture) {
			throw new IllegalArgumentException("The supplied Texture does match the Texture of this SpriteBatch!");
		}
	}

	/**
	 * @param pTextureRegion
	 * @param pX
	 * @param pY
	 * @param pWidth
	 * @param pHeight
	 * @param pRotation around the center (pWidth * 0.5f, pHeight * 0.5f)
	 * @param pRed
	 * @param pGreen
	 * @param pBlue
	 * @param pAlpha
	 */
	protected void add(final ITextureRegion pTextureRegion, final float pX, final float pY, final float pWidth, final float pHeight, final float pRotation, final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
		final float widthHalf = pWidth * 0.5f;
		final float heightHalf = pHeight * 0.5f;

		SpriteBatch.TRANSFORATION_TMP.setToIdentity();

		SpriteBatch.TRANSFORATION_TMP.postTranslate(-widthHalf, -heightHalf);
		SpriteBatch.TRANSFORATION_TMP.postRotate(pRotation);
		SpriteBatch.TRANSFORATION_TMP.postTranslate(widthHalf, heightHalf);
		SpriteBatch.TRANSFORATION_TMP.postTranslate(pX, pY);

		this.add(pTextureRegion, pWidth, pHeight, SpriteBatch.TRANSFORATION_TMP, pRed, pGreen, pBlue, pAlpha);
	}

	/**
	 * @param pTextureRegion
	 * @param pX
	 * @param pY
	 * @param pWidth
	 * @param pHeight
	 * @param pRotation around the center (pWidth * 0.5f, pHeight * 0.5f)
	 * @param pPackedColor
	 */
	protected void addWithPackedColor(final ITextureRegion pTextureRegion, final float pX, final float pY, final float pWidth, final float pHeight, final float pRotation, final float pPackedColor) {
		final float widthHalf = pWidth * 0.5f;
		final float heightHalf = pHeight * 0.5f;

		SpriteBatch.TRANSFORATION_TMP.setToIdentity();

		SpriteBatch.TRANSFORATION_TMP.postTranslate(-widthHalf, -heightHalf);
		SpriteBatch.TRANSFORATION_TMP.postRotate(pRotation);
		SpriteBatch.TRANSFORATION_TMP.postTranslate(widthHalf, heightHalf);
		SpriteBatch.TRANSFORATION_TMP.postTranslate(pX, pY);

		this.addWithPackedColor(pTextureRegion, pWidth, pHeight, SpriteBatch.TRANSFORATION_TMP, pPackedColor);
	}

	/**
	 * @param pTextureRegion
	 * @param pX
	 * @param pY
	 * @param pWidth
	 * @param pHeight
	 * @param pScaleX around the center (pWidth * 0.5f, pHeight * 0.5f)
	 * @param pScaleY around the center (pWidth * 0.5f, pHeight * 0.5f)
	 * @param pRed
	 * @param pGreen
	 * @param pBlue
	 * @param pAlpha
	 */
	protected void add(final ITextureRegion pTextureRegion, final float pX, final float pY, final float pWidth, final float pHeight, final float pScaleX, final float pScaleY, final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
		final float widthHalf = pWidth * 0.5f;
		final float heightHalf = pHeight * 0.5f;

		SpriteBatch.TRANSFORATION_TMP.setToIdentity();

		SpriteBatch.TRANSFORATION_TMP.postTranslate(-widthHalf, -heightHalf);
		SpriteBatch.TRANSFORATION_TMP.postScale(pScaleX, pScaleY);
		SpriteBatch.TRANSFORATION_TMP.postTranslate(widthHalf, heightHalf);
		SpriteBatch.TRANSFORATION_TMP.postTranslate(pX, pY);

		this.add(pTextureRegion, pWidth, pHeight, SpriteBatch.TRANSFORATION_TMP, pRed, pGreen, pBlue, pAlpha);
	}

	/**
	 * @param pTextureRegion
	 * @param pX
	 * @param pY
	 * @param pWidth
	 * @param pHeight
	 * @param pScaleX around the center (pWidth * 0.5f, pHeight * 0.5f)
	 * @param pScaleY around the center (pWidth * 0.5f, pHeight * 0.5f)
	 * @param pPackedColor
	 */
	protected void addWithPackedColor(final ITextureRegion pTextureRegion, final float pX, final float pY, final float pWidth, final float pHeight, final float pScaleX, final float pScaleY, final float pPackedColor) {
		final float widthHalf = pWidth * 0.5f;
		final float heightHalf = pHeight * 0.5f;

		SpriteBatch.TRANSFORATION_TMP.setToIdentity();

		SpriteBatch.TRANSFORATION_TMP.postTranslate(-widthHalf, -heightHalf);
		SpriteBatch.TRANSFORATION_TMP.postScale(pScaleX, pScaleY);
		SpriteBatch.TRANSFORATION_TMP.postTranslate(widthHalf, heightHalf);
		SpriteBatch.TRANSFORATION_TMP.postTranslate(pX, pY);

		this.addWithPackedColor(pTextureRegion, pWidth, pHeight, SpriteBatch.TRANSFORATION_TMP, pPackedColor);
	}

	/**
	 * @param pTextureRegion
	 * @param pX
	 * @param pY
	 * @param pWidth
	 * @param pHeight
	 * @param pRotation around the center (pWidth * 0.5f, pHeight * 0.5f)
	 * @param pScaleX around the center (pWidth * 0.5f, pHeight * 0.5f)
	 * @param pScaleY around the center (pWidth * 0.5f, pHeight * 0.5f)
	 * @param pRed
	 * @param pGreen
	 * @param pBlue
	 * @param pAlpha
	 */
	protected void add(final ITextureRegion pTextureRegion, final float pX, final float pY, final float pWidth, final float pHeight, final float pRotation, final float pScaleX, final float pScaleY, final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
		final float widthHalf = pWidth * 0.5f;
		final float heightHalf = pHeight * 0.5f;

		SpriteBatch.TRANSFORATION_TMP.setToIdentity();

		SpriteBatch.TRANSFORATION_TMP.postTranslate(-widthHalf, -heightHalf);
		SpriteBatch.TRANSFORATION_TMP.postScale(pScaleX, pScaleY);
		SpriteBatch.TRANSFORATION_TMP.postRotate(pRotation);
		SpriteBatch.TRANSFORATION_TMP.postTranslate(widthHalf, heightHalf);
		SpriteBatch.TRANSFORATION_TMP.postTranslate(pX, pY);

		this.add(pTextureRegion, pWidth, pHeight, SpriteBatch.TRANSFORATION_TMP, pRed, pGreen, pBlue, pAlpha);
	}

	/**
	 * @param pTextureRegion
	 * @param pX
	 * @param pY
	 * @param pWidth
	 * @param pHeight
	 * @param pRotation around the center (pWidth * 0.5f, pHeight * 0.5f)
	 * @param pScaleX around the center (pWidth * 0.5f, pHeight * 0.5f)
	 * @param pScaleY around the center (pWidth * 0.5f, pHeight * 0.5f)
	 * @param pPackedColor
	 */
	protected void addWithPackedColor(final ITextureRegion pTextureRegion, final float pX, final float pY, final float pWidth, final float pHeight, final float pRotation, final float pScaleX, final float pScaleY, final float pPackedColor) {
		final float widthHalf = pWidth * 0.5f;
		final float heightHalf = pHeight * 0.5f;

		SpriteBatch.TRANSFORATION_TMP.setToIdentity();

		SpriteBatch.TRANSFORATION_TMP.postTranslate(-widthHalf, -heightHalf);
		SpriteBatch.TRANSFORATION_TMP.postScale(pScaleX, pScaleY);
		SpriteBatch.TRANSFORATION_TMP.postRotate(pRotation);
		SpriteBatch.TRANSFORATION_TMP.postTranslate(widthHalf, heightHalf);
		SpriteBatch.TRANSFORATION_TMP.postTranslate(pX, pY);

		this.addWithPackedColor(pTextureRegion, pWidth, pHeight, SpriteBatch.TRANSFORATION_TMP, pPackedColor);
	}

	/**
	 * @param pTextureRegion
	 * @param pWidth
	 * @param pHeight
	 * @param pTransformation
	 * @param pRed
	 * @param pGreen
	 * @param pBlue
	 * @param pAlpha
	 */
	protected void add(final ITextureRegion pTextureRegion, final float pWidth, final float pHeight, final Transformation pTransformation, final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
		SpriteBatch.VERTICES_TMP[0] = 0;
		SpriteBatch.VERTICES_TMP[1] = 0;

		SpriteBatch.VERTICES_TMP[2] = 0;
		SpriteBatch.VERTICES_TMP[3] = pHeight;

		SpriteBatch.VERTICES_TMP[4] = pWidth;
		SpriteBatch.VERTICES_TMP[5] = 0;

		SpriteBatch.VERTICES_TMP[6] = pWidth;
		SpriteBatch.VERTICES_TMP[7] = pHeight;

		pTransformation.transform(SpriteBatch.VERTICES_TMP);

		this.addInner(pTextureRegion, SpriteBatch.VERTICES_TMP[0], SpriteBatch.VERTICES_TMP[1], SpriteBatch.VERTICES_TMP[2], SpriteBatch.VERTICES_TMP[3],  SpriteBatch.VERTICES_TMP[4], SpriteBatch.VERTICES_TMP[5], SpriteBatch.VERTICES_TMP[6], SpriteBatch.VERTICES_TMP[7], pRed, pGreen, pBlue, pAlpha);
	}

	/**
	 * @param pTextureRegion
	 * @param pWidth
	 * @param pHeight
	 * @param pTransformation
	 * @param pPackedColor
	 */
	protected void addWithPackedColor(final ITextureRegion pTextureRegion, final float pWidth, final float pHeight, final Transformation pTransformation, final float pPackedColor) {
		SpriteBatch.VERTICES_TMP[0] = 0;
		SpriteBatch.VERTICES_TMP[1] = 0;

		SpriteBatch.VERTICES_TMP[2] = 0;
		SpriteBatch.VERTICES_TMP[3] = pHeight;

		SpriteBatch.VERTICES_TMP[4] = pWidth;
		SpriteBatch.VERTICES_TMP[5] = 0;

		SpriteBatch.VERTICES_TMP[6] = pWidth;
		SpriteBatch.VERTICES_TMP[7] = pHeight;

		pTransformation.transform(SpriteBatch.VERTICES_TMP);

		this.mSpriteBatchVertexBufferObject.addWithPackedColor(pTextureRegion, SpriteBatch.VERTICES_TMP[0], SpriteBatch.VERTICES_TMP[1], SpriteBatch.VERTICES_TMP[2], SpriteBatch.VERTICES_TMP[3],  SpriteBatch.VERTICES_TMP[4], SpriteBatch.VERTICES_TMP[5], SpriteBatch.VERTICES_TMP[6], SpriteBatch.VERTICES_TMP[7], pPackedColor);
	}

	/**
	 * @param pTextureRegion
	 * @param pX
	 * @param pY
	 * @param pWidth
	 * @param pHeight
	 * @param pRed
	 * @param pGreen
	 * @param pBlue
	 * @param pAlpha
	 */
	protected void add(final ITextureRegion pTextureRegion, final float pX, final float pY, final float pWidth, final float pHeight, final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
		this.addInner(pTextureRegion, pX, pY, pX + pWidth, pY + pHeight, pRed, pGreen, pBlue, pAlpha);
	}

	/**
	 * @param pTextureRegion
	 * @param pX
	 * @param pY
	 * @param pWidth
	 * @param pHeight
	 * @param pPackedColor
	 */
	protected void add(final ITextureRegion pTextureRegion, final float pX, final float pY, final float pWidth, final float pHeight, final float pPackedColor) {
		this.mSpriteBatchVertexBufferObject.addWithPackedColor(pTextureRegion, pX, pY, pX + pWidth, pY + pHeight, pPackedColor);
	}

	/**
	 * @param pTextureRegion
	 * @param pX
	 * @param pY
	 * @param pWidth
	 * @param pHeight
	 * @param pPackedColor
	 */
	protected void addWithPackedColor(final ITextureRegion pTextureRegion, final float pX, final float pY, final float pWidth, final float pHeight, final float pPackedColor) {
		this.mSpriteBatchVertexBufferObject.addWithPackedColor(pTextureRegion, pX, pY, pX + pWidth, pY + pHeight, pPackedColor);
	}

	/**
	 * 1-+
	 * |X|
	 * +-2
	 */
	private void addInner(final ITextureRegion pTextureRegion, final float pX1, final float pY1, final float pX2, final float pY2, final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
		this.mSpriteBatchVertexBufferObject.addWithPackedColor(pTextureRegion, pX1, pY1, pX2, pY2, ColorUtils.convertRGBAToABGRPackedFloat(pRed, pGreen, pBlue, pAlpha));
	}

	/**
	 * 1-3
	 * |X|
	 * 2-4
	 */
	private void addInner(final ITextureRegion pTextureRegion, final float pX1, final float pY1, final float pX2, final float pY2, final float pX3, final float pY3, final float pX4, final float pY4, final float pRed, final float pGreen, final float pBlue, final float pAlpha) {
		this.mSpriteBatchVertexBufferObject.addWithPackedColor(pTextureRegion, pX1, pY1, pX2, pY2, pX3, pY3, pX4, pY4, ColorUtils.convertRGBAToABGRPackedFloat(pRed, pGreen, pBlue, pAlpha));
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

        /**
         * 
         */
        public static interface ISpriteBatchVertexBufferObject extends IVertexBufferObject {
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
            public int getBufferDataOffset();
                /**
                 * 
                 * @param pBufferDataOffset
                 */
                public void setBufferDataOffset(final int pBufferDataOffset);

                /**
                 * 
                 * @param pTextureRegion
                 * @param pX1
                 * @param pY1
                 * @param pX2
                 * @param pY2
                 * @param pPackedColor
                 */
                public void addWithPackedColor(final ITextureRegion pTextureRegion, final float pX1, final float pY1, final float pX2, final float pY2, final float pPackedColor);
                /**
                 * 
                 * @param pTextureRegion
                 * @param pX1
                 * @param pY1
                 * @param pX2
                 * @param pY2
                 * @param pX3
                 * @param pY3
                 * @param pX4
                 * @param pY4
                 * @param pPackedColor
                 */
                public void addWithPackedColor(final ITextureRegion pTextureRegion, final float pX1, final float pY1, final float pX2, final float pY2, final float pX3, final float pY3, final float pX4, final float pY4, final float pPackedColor);
	}

        /**
         * 
         */
        public static class HighPerformanceSpriteBatchVertexBufferObject extends HighPerformanceVertexBufferObject implements ISpriteBatchVertexBufferObject {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Fields
		// ===========================================================

            /**
             * 
             */
            protected int mBufferDataOffset;

		// ===========================================================
		// Constructors
		// ===========================================================

                /**
                 * 
                 * @param pVertexBufferObjectManager
                 * @param pCapacity
                 * @param pDrawType
                 * @param pAutoDispose
                 * @param pVertexBufferObjectAttributes
                 */
                public HighPerformanceSpriteBatchVertexBufferObject(final VertexBufferObjectManager pVertexBufferObjectManager, final int pCapacity, final DrawType pDrawType, final boolean pAutoDispose, final VertexBufferObjectAttributes pVertexBufferObjectAttributes) {
			super(pVertexBufferObjectManager, pCapacity, pDrawType, pAutoDispose, pVertexBufferObjectAttributes);
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================

		@Override
		public int getBufferDataOffset() {
			return this.mBufferDataOffset;
		}

		@Override
		public void setBufferDataOffset(final int pBufferDataOffset) {
			this.mBufferDataOffset = pBufferDataOffset;
		}

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

		/**
		 * 1-3
		 * |X|
		 * 2-4
                 * 
                 * @param pTextureRegion 
                 * @param pX1
                 * @param pY1
                 * @param pX2
                 * @param pY2 
                 * @param pX3 
                 * @param pPackedColor
                 * @param pY3
                 * @param pX4
                 * @param pY4  
                 */
		@Override
		public void addWithPackedColor(final ITextureRegion pTextureRegion, final float pX1, final float pY1, final float pX2, final float pY2, final float pX3, final float pY3, final float pX4, final float pY4, final float pPackedColor) {
			final float[] bufferData = this.getBufferData();
			final int bufferDataOffset = this.mBufferDataOffset;

			final float x1 = pX1;
			final float y1 = pY1;
			final float x2 = pX2;
			final float y2 = pY2;
			final float x3 = pX3;
			final float y3 = pY3;
			final float x4 = pX4;
			final float y4 = pY4;
			final float u = pTextureRegion.getU();
			final float v = pTextureRegion.getV();
			final float u2 = pTextureRegion.getU2();
			final float v2 = pTextureRegion.getV2();

			if(pTextureRegion.isRotated()) {
				bufferData[bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x1;
				bufferData[bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y1;
				bufferData[bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u2;
				bufferData[bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v;

				bufferData[bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x2;
				bufferData[bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y2;
				bufferData[bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u;
				bufferData[bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v;

				bufferData[bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x3;
				bufferData[bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y3;
				bufferData[bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u2;
				bufferData[bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v2;

				bufferData[bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x3;
				bufferData[bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y3;
				bufferData[bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u2;
				bufferData[bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v2;

				bufferData[bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x2;
				bufferData[bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y2;
				bufferData[bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u;
				bufferData[bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v;

				bufferData[bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x4;
				bufferData[bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y4;
				bufferData[bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u;
				bufferData[bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v2;
			} else {
				bufferData[bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x1;
				bufferData[bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y1;
				bufferData[bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u;
				bufferData[bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v;

				bufferData[bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x2;
				bufferData[bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y2;
				bufferData[bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u;
				bufferData[bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v2;

				bufferData[bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x3;
				bufferData[bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y3;
				bufferData[bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u2;
				bufferData[bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v;

				bufferData[bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x3;
				bufferData[bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y3;
				bufferData[bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u2;
				bufferData[bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v;

				bufferData[bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x2;
				bufferData[bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y2;
				bufferData[bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u;
				bufferData[bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v2;

				bufferData[bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x4;
				bufferData[bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y4;
				bufferData[bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u2;
				bufferData[bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v2;
			}

			this.mBufferDataOffset += SpriteBatch.SPRITE_SIZE;
		}


		/**
		 * 1-+
		 * |X|
		 * +-2
                 * 
                 * @param pTextureRegion
                 * @param pX1 
                 * @param pY1 
                 * @param pPackedColor
                 * @param pY2
                 * @param pX2  
                 */
		@Override
		public void addWithPackedColor(final ITextureRegion pTextureRegion, final float pX1, final float pY1, final float pX2, final float pY2, final float pPackedColor) {
			final float[] bufferData = this.getBufferData();
			final int bufferDataOffset = this.mBufferDataOffset;

			final float x1 = pX1;
			final float y1 = pY1;
			final float x2 = pX2;
			final float y2 = pY2;
			final float u = pTextureRegion.getU();
			final float v = pTextureRegion.getV();
			final float u2 = pTextureRegion.getU2();
			final float v2 = pTextureRegion.getV2();

			if(pTextureRegion.isRotated()) {
				bufferData[bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x1;
				bufferData[bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y1;
				bufferData[bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u2;
				bufferData[bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v;

				bufferData[bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x1;
				bufferData[bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y2;
				bufferData[bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u;
				bufferData[bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v;

				bufferData[bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x2;
				bufferData[bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y1;
				bufferData[bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u2;
				bufferData[bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v2;

				bufferData[bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x2;
				bufferData[bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y1;
				bufferData[bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u2;
				bufferData[bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v2;

				bufferData[bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x1;
				bufferData[bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y2;
				bufferData[bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u;
				bufferData[bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v;

				bufferData[bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x2;
				bufferData[bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y2;
				bufferData[bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u;
				bufferData[bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v2;
			} else {
				bufferData[bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x1;
				bufferData[bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y1;
				bufferData[bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u;
				bufferData[bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v;

				bufferData[bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x1;
				bufferData[bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y2;
				bufferData[bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u;
				bufferData[bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v2;

				bufferData[bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x2;
				bufferData[bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y1;
				bufferData[bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u2;
				bufferData[bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v;

				bufferData[bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x2;
				bufferData[bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y1;
				bufferData[bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u2;
				bufferData[bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v;

				bufferData[bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x1;
				bufferData[bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y2;
				bufferData[bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u;
				bufferData[bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v2;

				bufferData[bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X] = x2;
				bufferData[bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y] = y2;
				bufferData[bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX] = pPackedColor;
				bufferData[bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U] = u2;
				bufferData[bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V] = v2;
			}

			this.mBufferDataOffset += SpriteBatch.SPRITE_SIZE;
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
        public static class LowMemorySpriteBatchVertexBufferObject extends LowMemoryVertexBufferObject implements ISpriteBatchVertexBufferObject {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Fields
		// ===========================================================

            /**
             * 
             */
            protected int mBufferDataOffset;

		// ===========================================================
		// Constructors
		// ===========================================================

                /**
                 * 
                 * @param pVertexBufferObjectManager
                 * @param pCapacity
                 * @param pDrawType
                 * @param pAutoDispose
                 * @param pVertexBufferObjectAttributes
                 */
                public LowMemorySpriteBatchVertexBufferObject(final VertexBufferObjectManager pVertexBufferObjectManager, final int pCapacity, final DrawType pDrawType, final boolean pAutoDispose, final VertexBufferObjectAttributes pVertexBufferObjectAttributes) {
			super(pVertexBufferObjectManager, pCapacity, pDrawType, pAutoDispose, pVertexBufferObjectAttributes);
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================

		@Override
		public int getBufferDataOffset() {
			return this.mBufferDataOffset;
		}

		@Override
		public void setBufferDataOffset(final int pBufferDataOffset) {
			this.mBufferDataOffset = pBufferDataOffset;
		}

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

		/**
		 * 1-3
		 * |X|
		 * 2-4
                 * 
                 * @param pTextureRegion 
                 * @param pX4
                 * @param pY1 
                 * @param pX2 
                 * @param pX1
                 * @param pY2
                 * @param pX3
                 * @param pPackedColor
                 * @param pY3 
                 * @param pY4  
                 */
		@Override
		public void addWithPackedColor(final ITextureRegion pTextureRegion, final float pX1, final float pY1, final float pX2, final float pY2, final float pX3, final float pY3, final float pX4, final float pY4, final float pPackedColor) {
			final FloatBuffer bufferData = this.getFloatBuffer();
			final int bufferDataOffset = this.mBufferDataOffset;

			final float x1 = pX1;
			final float y1 = pY1;
			final float x2 = pX2;
			final float y2 = pY2;
			final float x3 = pX3;
			final float y3 = pY3;
			final float x4 = pX4;
			final float y4 = pY4;
			final float u = pTextureRegion.getU();
			final float v = pTextureRegion.getV();
			final float u2 = pTextureRegion.getU2();
			final float v2 = pTextureRegion.getV2();

			if(pTextureRegion.isRotated()) {
				bufferData.put(bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x1);
				bufferData.put(bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y1);
				bufferData.put(bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u2);
				bufferData.put(bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v);

				bufferData.put(bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x2);
				bufferData.put(bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y2);
				bufferData.put(bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u);
				bufferData.put(bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v);

				bufferData.put(bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x3);
				bufferData.put(bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y3);
				bufferData.put(bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u2);
				bufferData.put(bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v2);

				bufferData.put(bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x3);
				bufferData.put(bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y3);
				bufferData.put(bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u2);
				bufferData.put(bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v2);

				bufferData.put(bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x2);
				bufferData.put(bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y2);
				bufferData.put(bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u);
				bufferData.put(bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v);

				bufferData.put(bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x4);
				bufferData.put(bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y4);
				bufferData.put(bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u);
				bufferData.put(bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v2);
			} else {
				bufferData.put(bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x1);
				bufferData.put(bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y1);
				bufferData.put(bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u);
				bufferData.put(bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v);

				bufferData.put(bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x2);
				bufferData.put(bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y2);
				bufferData.put(bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u);
				bufferData.put(bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v2);

				bufferData.put(bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x3);
				bufferData.put(bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y3);
				bufferData.put(bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u2);
				bufferData.put(bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v);

				bufferData.put(bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x3);
				bufferData.put(bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y3);
				bufferData.put(bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u2);
				bufferData.put(bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v);

				bufferData.put(bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x2);
				bufferData.put(bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y2);
				bufferData.put(bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u);
				bufferData.put(bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v2);

				bufferData.put(bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x4);
				bufferData.put(bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y4);
				bufferData.put(bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u2);
				bufferData.put(bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v2);
			}

			this.mBufferDataOffset += SpriteBatch.SPRITE_SIZE;
		}


		/**
		 * 1-+
		 * |X|
		 * +-2
                 * 
                 * @param pTextureRegion 
                 * @param pX2
                 * @param pX1 
                 * @param pY1
                 * @param pPackedColor
                 * @param pY2  
                 */
		@Override
		public void addWithPackedColor(final ITextureRegion pTextureRegion, final float pX1, final float pY1, final float pX2, final float pY2, final float pPackedColor) {
			final FloatBuffer bufferData = this.getFloatBuffer();
			final int bufferDataOffset = this.mBufferDataOffset;

			final float x1 = pX1;
			final float y1 = pY1;
			final float x2 = pX2;
			final float y2 = pY2;
			final float u = pTextureRegion.getU();
			final float v = pTextureRegion.getV();
			final float u2 = pTextureRegion.getU2();
			final float v2 = pTextureRegion.getV2();

			if(pTextureRegion.isRotated()) {
				bufferData.put(bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x1);
				bufferData.put(bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y1);
				bufferData.put(bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u2);
				bufferData.put(bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v);

				bufferData.put(bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x1);
				bufferData.put(bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y2);
				bufferData.put(bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u);
				bufferData.put(bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v);

				bufferData.put(bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x2);
				bufferData.put(bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y1);
				bufferData.put(bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u2);
				bufferData.put(bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v2);

				bufferData.put(bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x2);
				bufferData.put(bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y1);
				bufferData.put(bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u2);
				bufferData.put(bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v2);

				bufferData.put(bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x1);
				bufferData.put(bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y2);
				bufferData.put(bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u);
				bufferData.put(bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v);

				bufferData.put(bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x2);
				bufferData.put(bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y2);
				bufferData.put(bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u);
				bufferData.put(bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v2);
			} else {
				bufferData.put(bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x1);
				bufferData.put(bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y1);
				bufferData.put(bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u);
				bufferData.put(bufferDataOffset + 0 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v);

				bufferData.put(bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x1);
				bufferData.put(bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y2);
				bufferData.put(bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u);
				bufferData.put(bufferDataOffset + 1 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v2);

				bufferData.put(bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x2);
				bufferData.put(bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y1);
				bufferData.put(bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u2);
				bufferData.put(bufferDataOffset + 2 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v);

				bufferData.put(bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x2);
				bufferData.put(bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y1);
				bufferData.put(bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u2);
				bufferData.put(bufferDataOffset + 3 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v);

				bufferData.put(bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x1);
				bufferData.put(bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y2);
				bufferData.put(bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u);
				bufferData.put(bufferDataOffset + 4 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v2);

				bufferData.put(bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_X, x2);
				bufferData.put(bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.VERTEX_INDEX_Y, y2);
				bufferData.put(bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.COLOR_INDEX, pPackedColor);
				bufferData.put(bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_U, u2);
				bufferData.put(bufferDataOffset + 5 * SpriteBatch.VERTEX_SIZE + SpriteBatch.TEXTURECOORDINATES_INDEX_V, v2);
			}

			this.mBufferDataOffset += SpriteBatch.SPRITE_SIZE;
		}

		// ===========================================================
		// Methods
		// ===========================================================

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}
}
