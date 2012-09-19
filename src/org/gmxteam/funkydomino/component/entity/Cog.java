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

import org.gmxteam.funkydomino.component.IComponent;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.gmxteam.funkydomino.component.ComponentAttributes;

/**
 * Objet générant une roue dentée.
 *
 * @see Component
 * @author Guillaume Poirier-Morency
 */
public final class Cog extends Sprite implements IComponent {

    /**
     *
     */
    public static final int COG_TEETH_COUNT = 8,
            COG_TOTAL_RADIUS = 54 + CogTeeth.COG_TEETH_HEIGHT;
    /**
     *
     */
    public static final float COG_MOTOR_SPEED = 300.0f,
            /**
             *
             */
            COG_MOTOR_MAX_TORQUE = 1000.0f;

    public Cog(final float pX, final float pY, final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);



    }

    public Cog(ComponentAttributes pAttributes, ITextureRegion pTextureRegion, int i, VertexBufferObjectManager vertexBufferObjectManager) {
        this(pAttributes.getFloat("x", 0.0f), pAttributes.getFloat("x", 0.0f), pTextureRegion, vertexBufferObjectManager);

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
