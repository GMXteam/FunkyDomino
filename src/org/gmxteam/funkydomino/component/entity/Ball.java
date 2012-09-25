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

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import org.andengine.audio.sound.Sound;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.gmxteam.funkydomino.component.ComponentAttributes;
import org.gmxteam.funkydomino.component.IComponent;

/**
 *
 * @see Component
 * @author Guillaume Poirier-Morency
 */
public final class Ball extends Sprite implements IComponent, ContactListener {


    /**
     * Rayon d'une balle.
     */
    public static final int BALL_RADIUS = 32;
    /**
     * Son lors de la collision.
     */
    private final Sound mCollisionSound;

    /**
     * 
     * Constructeur pour une balle.
     * @param pAttributes
     * @param mTextureRegion
     * @param vertexBufferObjectManager
     * @param mCollisionSound
     */
    public Ball(ComponentAttributes pAttributes, TextureRegion mTextureRegion, VertexBufferObjectManager vertexBufferObjectManager, Sound mCollisionSound) {
        this(pAttributes.getX(), pAttributes.getY(), mTextureRegion, vertexBufferObjectManager, mCollisionSound);
    }

    /**
     *
     * @param pX
     * @param pY
     * @param pTextureRegion
     * @param pVertexBufferObjectManager
     * @param pCollisionSound
     */
    public Ball(final float pX, final float pY, final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager, Sound pCollisionSound) {
        super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
        mCollisionSound = pCollisionSound;
    }

    @Override
    public Body onCreateBody(PhysicsWorld pPhysicsWorld, FixtureDef pFixtureDef) {
        final Body mBallBody = PhysicsFactory.createCircleBody(pPhysicsWorld, this, BodyDef.BodyType.DynamicBody, pFixtureDef);
        return mBallBody;
    }

    /**
     *
     * @param cntct
     */
    public void beginContact(Contact cntct) {
        mCollisionSound.play();
    }

    /**
     *
     * @param cntct
     */
    public void endContact(Contact cntct) {
    }

    /**
     *
     * @param cntct
     * @param mnfld
     */
    public void preSolve(Contact cntct, Manifold mnfld) {
    }

    /**
     *
     * @param cntct
     * @param ci
     */
    public void postSolve(Contact cntct, ContactImpulse ci) {
    }
}
