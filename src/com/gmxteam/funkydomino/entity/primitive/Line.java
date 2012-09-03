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
    
    public Line(float pX1, float pY1, float pX2, float pY2, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX1, pY1, pX2, pY2, pVertexBufferObjectManager);
    }
    
    public Line(float pX1, float pY1, float pX2, float pY2, VertexBufferObjectManager pVertexBufferObjectManager, DrawType pDrawType) {
        super(pX1, pY1, pX2, pY2, pVertexBufferObjectManager, pDrawType);
    }
    
    public Line(float pX1, float pY1, float pX2, float pY2, float pLineWidth, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX1, pY1, pX2, pY2, pLineWidth, pVertexBufferObjectManager);
        
    }
    
    public Line(float pX1, float pY1, float pX2, float pY2, float pLineWidth, VertexBufferObjectManager pVertexBufferObjectManager, DrawType pDrawType) {
        super(pX1, pY1, pX2, pY2, pLineWidth, pVertexBufferObjectManager, pDrawType);
        
    }
    
    public Line(float pX1, float pY1, float pX2, float pY2, float pLineWidth, ILineVertexBufferObject pLineVertexBufferObject) {
        super(pX1, pY1, pX2, pY2, pLineWidth, pLineVertexBufferObject);
        
    }
    
    public float getWidth() {
        return super.getLineWidth();
    }
    
    public float getHeight() {
        return 0.0f;
    }
    
    public float getWidthScaled() {
        
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public float getHeightScaled() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void setHeight(float f) {
        // Does nothing..
    }
    
    public void setWidth(float f) {
        super.setLineWidth(mLineWidth);        
    }
    
    public void setSize(float f, float f1) {
        super.setLineWidth(f);
    }
}
