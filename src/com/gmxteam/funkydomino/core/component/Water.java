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
package com.gmxteam.funkydomino.core.component;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.gmxteam.funkydomino.core.physics.box2d.ContactManager;
import com.gmxteam.funkydomino.core.physics.box2d.PhysicsFactory;
import java.util.Arrays;
import org.andengine.entity.Entity;
import org.andengine.entity.primitive.DrawMode;
import org.andengine.entity.primitive.Mesh;
import org.andengine.entity.primitive.vbo.HighPerformanceMeshVertexBufferObject;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.IAreaShape;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.opengl.vbo.attribute.VertexBufferObjectAttribute;
import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

/**
 * Objet pour générer de l'eau. Cela risque plus d'être un champ de force
 * qu'autre chose par contre.
 *
 * @see Component
 * @author Guillaume Poirier-Morency
 */
public class Water extends Component {

    private Body mWaterBody;
    private WaterMesh mWater;

    /**
     *
     */
    @Override
    protected void onLoadResource() {
    }

    /**
     *
     * @param pX
     * @param pY
     * @param angle
     */
    @Override
    protected void onCreateSprite(float pX, float pY, float angle) {
        mWater = new WaterMesh(pX, pY, getVertices(), getVertexBufferObjectManager());
        mWater.setRotation(angle);
        mWater.setColor(Color.BLUE);
    }

    @Override
    protected void onPopulatePhysicsWorld(PhysicsWorld pw) {


        Debug.v(Arrays.toString(getVertices()));

        mWaterBody = PhysicsFactory.createPolygonBody(pw, mWater, BodyDef.BodyType.DynamicBody, mFixtureDef);

        pw.registerPhysicsConnector(new PhysicsConnector(mWater, mWaterBody, true, true));

    }

    @Override
    protected void onPopulateEntity(Entity e) {
        e.attachChild(mWater);
    }

    @Override
    protected void onRegisterTouchAreas(Scene pScene) {
    }

    /**
     *
     * @param pContactManager
     */
    @Override
    protected void onRegisterContactListener(ContactManager pContactManager) {
    }

    class WaterMesh extends Mesh implements IAreaShape {

        /**
         * Uses a default {@link HighPerformanceMeshVertexBufferObject} in
         * {@link DrawType#STATIC} with the
         * {@link VertexBufferObjectAttribute}s:
         * {@link Mesh#VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT}.
         */
        public WaterMesh(final float pX, final float pY, final Vector2[] vertices, final VertexBufferObjectManager pVertexBufferObjectManager) {
            super(pX, pY, vector2ArrayToBufferData(vertices), vertices.length, DrawMode.TRIANGLES, pVertexBufferObjectManager);

        }

        public float getWidth() {
            return 0.0f;
        }

        public float getHeight() {
            return 0.0f;
        }

        public float getWidthScaled() {
            return 0.0f;
        }

        public float getHeightScaled() {
            return 0.0f;
        }

        public void setHeight(float f) {
        }

        public void setWidth(float f) {
        }

        public void setSize(float f, float f1) {
        }
    }
}
