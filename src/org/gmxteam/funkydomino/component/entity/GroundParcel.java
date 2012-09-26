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
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import java.util.Arrays;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.gmxteam.funkydomino.component.ComponentAttributes;
import org.gmxteam.funkydomino.component.IComponent;

/**
 * Chaque GroundParcel est un Sprite backé avec un polygon de Box2D.
 *
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
    private final Vector2[] mVectors;

    public static float average(float... floats) {
        float sum = 0.0f;
        for (float f : floats) {
            sum += f;
        }
        return sum / (float) floats.length;


    }

    public static boolean isValidPolygon(Vector2[] array) {

        // Calcul des angle extérieurs v[k], v[k+1], v[k+2] doit toujours > 180 

        for (int i = 0; i < array.length - 3; i++) {
            final Vector2 v1 = array[i],
                    v2 = array[i + 1],
                    v3 = array[i + 2];

            // Convex is len(v2) > moy(len(v1), len(v3))

            if (v2.len() < average(v1.len(), v3.len())) {
                return false;
            }
        }

        // Tous les vecteurs sont corrects

        return true;
    }

    /**
     *
     * @param pComponentAttributes
     * @param pTextureRegion
     * @param pVertexBufferObjectManager
     */
    public GroundParcel(final ComponentAttributes pComponentAttributes, final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pComponentAttributes.getX(), pComponentAttributes.getY(), pTextureRegion, pVertexBufferObjectManager);
        mVectors = pComponentAttributes.getVector2Array("vector", DEFAULT_VECTORS);

    }

    public Body onCreateBody(PhysicsWorld pPhysicsWorld, FixtureDef pFixtureDef) {
        // TODO : Les grounds triangulaires
        if (isValidPolygon(mVectors)) {
            return PhysicsFactory.createPolygonBody(pPhysicsWorld, this, mVectors, BodyDef.BodyType.DynamicBody, pFixtureDef);

        } else {
            throw new IllegalArgumentException("Not a valid polygon array " + Arrays.deepToString(mVectors));
        }
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
