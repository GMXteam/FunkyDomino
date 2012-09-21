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
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.gmxteam.funkydomino.component.ComponentAttributes;
import org.gmxteam.funkydomino.component.IComponent;

/**
 *
 * @author guillaume
 */
public class GroundParcel extends Sprite implements IComponent {

    /**
     *
     */
    public static final int GROUND_WIDTH = 128,
            /**
             *
             */
            GROUND_HEIGHT = 128;

    /**
     *
     * @param pX
     * @param pY
     * @param pTextureRegion
     * @param pVertexBufferObjectManager
     */
    public GroundParcel(final float pX, final float pY, final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTextureRegion.getWidth(), pTextureRegion.getHeight(), pTextureRegion, pVertexBufferObjectManager, DrawType.STATIC);
    }

    /**
     *
     * @param pComponentAttributes
     * @param pTextureRegion
     * @param pVertexBufferObjectManager
     */
    public GroundParcel(final ComponentAttributes pComponentAttributes, final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pComponentAttributes.getX(), pComponentAttributes.getY(), pTextureRegion.getWidth(), pTextureRegion.getHeight(), pTextureRegion, pVertexBufferObjectManager, DrawType.STATIC);
    }

    public Body onCreateBody(PhysicsWorld pPhysicsWorld, FixtureDef pFixtureDef) {
        // TODO : Les grounds triangulaires
        return PhysicsFactory.createBoxBody(pPhysicsWorld, this, BodyDef.BodyType.DynamicBody, pFixtureDef);
    }

    /**
     *
     * @param contact
     */
    public void beginContact(Contact contact) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param contact
     */
    public void endContact(Contact contact) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param contact
     * @param oldManifold
     */
    public void preSolve(Contact contact, Manifold oldManifold) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param contact
     * @param impulse
     */
    public void postSolve(Contact contact, ContactImpulse impulse) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
