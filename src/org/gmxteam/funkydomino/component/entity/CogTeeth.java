/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gmxteam.funkydomino.component.entity;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.gmxteam.funkydomino.component.IComponent;

/**
 *
 * @author Usager
 */
public class CogTeeth extends Rectangle implements IComponent {

    /**
     *
     */
    public static final int /**
             *
             */
            COG_TEETH_HEIGHT = 10,
            /**
             *
             */
            /**
             *
             */
            COG_TEETH_WIDTH = 20;

    /**
     *
     * @param pX
     * @param pY
     * @param pVertexBufferObjectManager
     */
    public CogTeeth(final float pX, final float pY, final VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, COG_TEETH_WIDTH, COG_TEETH_HEIGHT, pVertexBufferObjectManager);
    }

    public Body onCreateBody(PhysicsWorld pPhysicsWorld, FixtureDef pFixtureDef) {
        return PhysicsFactory.createBoxBody(pPhysicsWorld, this, BodyDef.BodyType.DynamicBody, pFixtureDef);

    }

    /**
     *
     * @param contact
     */
    public void beginContact(Contact contact) {
    }

    /**
     *
     * @param contact
     */
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
}