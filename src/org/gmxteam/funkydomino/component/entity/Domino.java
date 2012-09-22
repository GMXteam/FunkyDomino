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
import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.gmxteam.funkydomino.component.ComponentAttributes;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public final class Domino extends Sprite implements IComponent, IScrollDetectorListener {
    private Body mBody;

    /**
     *
     * @param pComponentAttributes
     * @param pTextureRegion
     * @param pVertexBufferObjectManager
     */
    public Domino(final ComponentAttributes pComponentAttributes, final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pComponentAttributes.getX(), pComponentAttributes.getY(), pTextureRegion, pVertexBufferObjectManager);

    }
    /**
     *
     */
    public static final int DOMINO_HEIGHT = 64,
            /**
             *
             */
            DOMINO_WIDTH = 32;

    @Override
    public Body onCreateBody(PhysicsWorld pw, FixtureDef pFixtureDef) {
        return mBody = PhysicsFactory.createBoxBody(pw, this, BodyDef.BodyType.DynamicBody, pFixtureDef);
    }

    ////////////////////////////////////
    // Collisions
    /**
     *
     * @param cntct
     */
    public void beginContact(Contact cntct) {
        //mCollisionSound.play();
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

    /**
     *
     * @param sd
     * @param i
     * @param f
     * @param f1
     */
    public void onScrollStarted(ScrollDetector sd, int i, float f, float f1) {
    }

    /**
     *
     * @param sd
     * @param i
     * @param f
     * @param f1
     */
    public void onScroll(ScrollDetector sd, int i, float f, float f1) {
        // Make the domino follows the finger.
        f = f / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;
        f1 = f1 / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;
        mBody.setTransform(mBody.getPosition().x + f, mBody.getPosition().y + f1, mBody.getAngle());
    }

    /**
     *
     * @param sd
     * @param i
     * @param f
     * @param f1
     */
    public void onScrollFinished(ScrollDetector sd, int i, float f, float f1) {
        f = f / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;
        f1 = f1 / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT;
        mBody.setTransform(mBody.getPosition().x + f, mBody.getPosition().y + f1, mBody.getAngle());
    }
}
