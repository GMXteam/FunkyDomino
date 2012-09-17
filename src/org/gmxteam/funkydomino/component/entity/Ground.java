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
package org.gmxteam.funkydomino.component.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import org.gmxteam.funkydomino.component.Component;
import org.gmxteam.funkydomino.physics.box2d.ContactManager;
import java.util.Arrays;
import org.andengine.entity.Entity;
import org.andengine.entity.primitive.DrawMode;
import org.andengine.entity.primitive.Mesh;
import org.andengine.entity.primitive.vbo.HighPerformanceMeshVertexBufferObject;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.opengl.vbo.attribute.VertexBufferObjectAttribute;
import org.andengine.util.adt.color.Color;
import org.andengine.util.debug.Debug;

/**
 * Objet définissant le sol. Méta-entité, soit une entité contenant plusieurs
 * entités. Chaque body est assemblé avec celui qui le suit (d'où la
 * LinkedList).
 *
 * @author Guillaume Poirier-Morency
 */
public class Ground extends Component implements ContactListener {

    private Body mGroundBody;
    private GroundMesh mGround;
    
    /**
     * Retourne le tableau de vecteurs qui forme le polygone du sol.
     *
     * @return
     */
    public Vector2[] getVertices() {
        return mComponentAttributes.getVector2Array("vector", DEFAULT_VECTORS);
    }

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
     * @return  
     */
    @Override
    protected Entity onCreateEntity(float pX, float pY, float angle) {
        mGround = new GroundMesh(pX, pY, getVertices(), getBaseGameActivity().getVertexBufferObjectManager());
        mGround.setRotation(angle);
        mGround.setColor(Color.GREEN);
        return mGround;
    }

    @Override
    protected void onPopulatePhysicsWorld(PhysicsWorld pPhysicsWorld) {

        Debug.v(Arrays.toString(getVertices()));

        mGroundBody = PhysicsFactory.createPolygonBody(pPhysicsWorld, mGround,getVertices(), BodyDef.BodyType.DynamicBody, mFixtureDef);

        pPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(mGround, mGroundBody, true, true));


    }   

    /**
     *
     * @param pContactManager
     */
    @Override
    protected void onRegisterContactListener(ContactManager pContactManager) {
        pContactManager.registerContactListener(mGroundBody, this);
    }

    @Override
    protected void onRegisterTouchAreas(Scene pScene) {
    }

    public void beginContact(Contact contact) {
    }

    public void endContact(Contact contact) {
    }

    /**
     *
     * @param contact
     * @param oldManifold
     */
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    /**
     *
     * @param contact
     * @param impulse
     */
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }   

    class GroundMesh extends Mesh {

        /**
         * Uses a default {@link HighPerformanceMeshVertexBufferObject} in
         * {@link DrawType#STATIC} with the
         * {@link VertexBufferObjectAttribute}s:
         * {@link Mesh#VERTEXBUFFEROBJECTATTRIBUTES_DEFAULT}.
         */
        public GroundMesh(final float pX, final float pY, final Vector2[] vertices, final VertexBufferObjectManager pVertexBufferObjectManager) {
            super(pX, pY, vector2ArrayToBufferData(vertices), vertices.length, DrawMode.TRIANGLES, pVertexBufferObjectManager);

        }

        
    }
}
