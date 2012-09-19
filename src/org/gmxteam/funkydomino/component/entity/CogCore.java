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
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.gmxteam.funkydomino.component.ComponentAttributes;
import org.gmxteam.funkydomino.component.IComponent;

public class CogCore extends Sprite implements IComponent {

    public static final int /**
             *
             */
            COG_CORE_RADIUS = 10;

    public CogCore(final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
        super(0.0f, 0.0f, COG_CORE_RADIUS * 2, COG_CORE_RADIUS * 2, pTextureRegion, pVertexBufferObjectManager);
    }

  

    public Body onCreateBody(PhysicsWorld pPhysicsWorld, FixtureDef pFixtureDef) {
        return PhysicsFactory.createCircleBody(pPhysicsWorld, this, BodyDef.BodyType.DynamicBody, pFixtureDef);

    }

    public void beginContact(Contact contact) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void endContact(Contact contact) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void preSolve(Contact contact, Manifold oldManifold) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void postSolve(Contact contact, ContactImpulse impulse) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}