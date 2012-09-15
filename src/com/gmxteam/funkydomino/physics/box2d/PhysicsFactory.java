/*
 *   This file is part of Funky Domino.
 *
 *   Funky Domino is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Funky Domino is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Funky Domino.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gmxteam.funkydomino.physics.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import org.andengine.entity.primitive.Mesh;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import static org.andengine.extension.physics.box2d.util.constants.PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;

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
