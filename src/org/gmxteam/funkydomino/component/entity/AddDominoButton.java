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
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.gmxteam.funkydomino.component.ComponentAttributes;
import org.gmxteam.funkydomino.component.IComponent;

/**
 *
 * @author Usager
 */
public class AddDominoButton extends ButtonSprite implements IComponent, OnClickListener {

    /**
     *
     */
    public static final int DOMINO_WIDTH = 32,
            /**
             *
             */
            DOMINO_HEIGHT = 64;

    ////////////////////////////////////////////////////////////////////////
    /**
     *
     * @param pX
     * @param pY
     * @param pTextureRegion
     * @param pVertexBufferObjectManager
     */
    public AddDominoButton(final float pX, final float pY, final ITextureRegion pTextureRegion, final ITextureRegion pTextureRegionClicked, final VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTextureRegion, pTextureRegion, pTextureRegionClicked, pVertexBufferObjectManager);
    }

    /**
     *
     * @param pComponentAttributes
     * @param pTextureRegion
     * @param pVertexBufferObjectManager
     */
    public AddDominoButton(final ComponentAttributes pComponentAttributes, final ITextureRegion pTextureRegion, final ITextureRegion pTextureRegionClicked, final VertexBufferObjectManager pVertexBufferObjectManager) {
        this(pComponentAttributes.getX(), pComponentAttributes.getY(), pTextureRegion, pTextureRegionClicked, pVertexBufferObjectManager);
    }

    public Body onCreateBody(PhysicsWorld pPhysicsWorld, FixtureDef pFixtureDef) {
        return null;
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

    /**
     *
     * @param pButtonSprite
     * @param pTouchAreaLocalX
     * @param pTouchAreaLocalY
     */
    public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX, float pTouchAreaLocalY) {
    }
}
