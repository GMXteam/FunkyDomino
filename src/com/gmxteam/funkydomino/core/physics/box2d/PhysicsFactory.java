/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.core.physics.box2d;

import static org.andengine.extension.physics.box2d.util.constants.PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import org.andengine.entity.primitive.Mesh;
import org.andengine.extension.physics.box2d.PhysicsWorld;

/**
 *
 * @author guillaume
 */
public class PhysicsFactory extends org.andengine.extension.physics.box2d.PhysicsFactory {
    
    
    /**
     * @param pPhysicsWorld     
     * @param pMesh is a mesh polygon.
     * @param pBodyType
     * @param pFixtureDef
     * @return
     */
    public static Body createPolygonBody(final PhysicsWorld pPhysicsWorld, final Mesh pMesh, final BodyDef.BodyType pBodyType, final FixtureDef pFixtureDef, final float pPixelToMeterRatio) {
        Vector2[] vertices = new Vector2[pMesh.getBufferData().length / Mesh.VERTEX_SIZE];
        
        int j = 0;
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vector2(
                    pMesh.getBufferData()[j + Mesh.VERTEX_INDEX_X] / pPixelToMeterRatio,
                    pMesh.getBufferData()[j + Mesh.VERTEX_INDEX_Y] / pPixelToMeterRatio
                    );         
            j += Mesh.VERTEX_SIZE;
        }
        return org.andengine.extension.physics.box2d.PhysicsFactory.createPolygonBody(pPhysicsWorld, pMesh, vertices, pBodyType, pFixtureDef, pPixelToMeterRatio);
    }

    /**
     * @param pPhysicsWorld
     * @param pShape
     * @param pMesh is a mesh polygon. {@link PhysicsConstants#PIXEL_TO_METER_RATIO_DEFAULT} is applied automatically.
     * @param pBodyType
     * @param pFixtureDef
     * @return
     */
    public static Body createPolygonBody(final PhysicsWorld pPhysicsWorld, final Mesh pMesh, final BodyDef.BodyType pBodyType, final FixtureDef pFixtureDef) {
        
        Vector2[] vertices = new Vector2[pMesh.getBufferData().length / Mesh.VERTEX_SIZE];
        
        int j = 0;
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = new Vector2(
                    pMesh.getBufferData()[j + Mesh.VERTEX_INDEX_X] / PIXEL_TO_METER_RATIO_DEFAULT,
                    pMesh.getBufferData()[j + Mesh.VERTEX_INDEX_Y] / PIXEL_TO_METER_RATIO_DEFAULT
                    );         
            j += Mesh.VERTEX_SIZE;
        }
        return org.andengine.extension.physics.box2d.PhysicsFactory.createPolygonBody(pPhysicsWorld, pMesh, vertices, pBodyType, pFixtureDef);
    }
    
}
