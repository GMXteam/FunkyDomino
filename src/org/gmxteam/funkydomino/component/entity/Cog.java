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

import android.util.FloatMath;
import org.gmxteam.funkydomino.component.IComponent;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import java.util.ArrayList;
import java.util.List;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.gmxteam.funkydomino.physics.box2d.ContactManager;
import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.IEntityComparator;
import org.andengine.entity.IEntityMatcher;
import org.andengine.entity.IEntityParameterCallable;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierMatcher;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.batch.SpriteBatch;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.adt.color.Color;
import org.andengine.util.adt.transformation.Transformation;
import org.andengine.util.debug.Debug;
import org.andengine.util.math.MathUtils;
import org.gmxteam.funkydomino.component.ComponentAttributes;

/**
 * Objet générant une roue dentée.
 *
 * @see Component
 * @author Guillaume Poirier-Morency
 */
public final class Cog extends SpriteBatch implements IComponent {

    /**
     *
     */
    public static final int COG_RADIUS = 64,
            /**
             *
             */
            COG_TEETH_COUNT = 8;
    /**
     *
     */
    public static final float COG_MOTOR_SPEED = 300.0f,
            /**
             *
             */
            COG_MOTOR_MAX_TORQUE = 1000.0f;

    public Cog(final float pX, final float pY, final ITexture pTexture, final int pCapacity, final VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTexture, pCapacity, pVertexBufferObjectManager);



    }

    public Cog(ComponentAttributes pAttributes, BitmapTextureAtlas mBitmapTextureAtlas, int i, VertexBufferObjectManager vertexBufferObjectManager) {
        this(pAttributes.getFloat("x", 0.0f), pAttributes.getFloat("x", 0.0f), mBitmapTextureAtlas, i, vertexBufferObjectManager);

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
