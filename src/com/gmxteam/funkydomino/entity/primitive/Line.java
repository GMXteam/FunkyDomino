/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.entity.primitive;

import org.andengine.entity.primitive.vbo.ILineVertexBufferObject;
import org.andengine.entity.shape.IAreaShape;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 *
 * @author guillaume
 */
public class Line extends org.andengine.entity.primitive.Line implements IAreaShape {
    
    /**
     *
     * @param pX1
     * @param pY1
     * @param pX2
     * @param pY2
     * @param pVertexBufferObjectManager
     */
    public Line(float pX1, float pY1, float pX2, float pY2, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX1, pY1, pX2, pY2, pVertexBufferObjectManager);
    }
    
    /**
     *
     * @param pX1
     * @param pY1
     * @param pX2
     * @param pY2
     * @param pVertexBufferObjectManager
     * @param pDrawType
     */
    public Line(float pX1, float pY1, float pX2, float pY2, VertexBufferObjectManager pVertexBufferObjectManager, DrawType pDrawType) {
        super(pX1, pY1, pX2, pY2, pVertexBufferObjectManager, pDrawType);
    }
    
    /**
     *
     * @param pX1
     * @param pY1
     * @param pX2
     * @param pY2
     * @param pLineWidth
     * @param pVertexBufferObjectManager
     */
    public Line(float pX1, float pY1, float pX2, float pY2, float pLineWidth, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX1, pY1, pX2, pY2, pLineWidth, pVertexBufferObjectManager);
        
    }
    
    /**
     *
     * @param pX1
     * @param pY1
     * @param pX2
     * @param pY2
     * @param pLineWidth
     * @param pVertexBufferObjectManager
     * @param pDrawType
     */
    public Line(float pX1, float pY1, float pX2, float pY2, float pLineWidth, VertexBufferObjectManager pVertexBufferObjectManager, DrawType pDrawType) {
        super(pX1, pY1, pX2, pY2, pLineWidth, pVertexBufferObjectManager, pDrawType);
        
    }
    
    /**
     *
     * @param pX1
     * @param pY1
     * @param pX2
     * @param pY2
     * @param pLineWidth
     * @param pLineVertexBufferObject
     */
    public Line(float pX1, float pY1, float pX2, float pY2, float pLineWidth, ILineVertexBufferObject pLineVertexBufferObject) {
        super(pX1, pY1, pX2, pY2, pLineWidth, pLineVertexBufferObject);
        
    }
    
    /**
     *
     * @return
     */
    public float getWidth() {
        return super.getLineWidth();
    }
    
    /**
     *
     * @return
     */
    public float getHeight() {
        return 0.0f;
    }
    
    /**
     *
     * @return
     */
    public float getWidthScaled() {
        
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     *
     * @return
     */
    public float getHeightScaled() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     *
     * @param f
     */
    public void setHeight(float f) {
        // Does nothing..
    }
    
    /**
     *
     * @param f
     */
    public void setWidth(float f) {
        super.setLineWidth(mLineWidth);        
    }
    
    /**
     *
     * @param f
     * @param f1
     */
    public void setSize(float f, float f1) {
        super.setLineWidth(f);
    }
}
