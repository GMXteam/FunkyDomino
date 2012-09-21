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
import org.andengine.entity.Entity;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.gmxteam.funkydomino.component.ComponentAttributes;
import org.gmxteam.funkydomino.component.IComponent;

/**
 * Objet définissant le sol. Il est composé de deux entités. Un sprite png pour
 * le rendu final et un Mesh pour voir la représentation vectorielle du polygone
 * de collision. Le sprite png est de 100 pixels par 100 pixels. Il peut prendre
 * la forme qu'il veut.
 *
 * @author Guillaume Poirier-Morency
 */
public class Ground extends Entity implements IComponent {

    /**
     *
     * @param pX
     * @param pY
     * @param pTexture
     * @param pCapacity
     * @param pVertexBufferObjectManager
     */
    public Ground(final float pX, final float pY) {
        super(pX, pY);
    }

    /**
     *
     * @param pAttributes
     */
    public Ground(ComponentAttributes pAttributes) {
        this(0.0f, 0.0f);
    }

    public Body onCreateBody(PhysicsWorld pPhysicsWorld, FixtureDef pFixtureDef) {
        throw new UnsupportedOperationException("Not supported yet.");

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
