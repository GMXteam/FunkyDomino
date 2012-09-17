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
package org.gmxteam.funkydomino.component;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import org.gmxteam.funkydomino.activity.IBaseGameActivity;
import org.gmxteam.funkydomino.physics.box2d.ContactManager;
import java.io.IOException;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Mesh;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.util.debug.Debug;

/**
 * Classe abstraite définissant les composants. Les composants sont des éléments
 * affectés par la physique. On parle du sol, des dominos, des billes et autres
 * objets. Dans leurs méthodes Factory, ils peuvent altérer comme bon leur
 * semble les variables mAreaShape (état graphique) et mBody (état physique).
 *
 * Le système de génération des composants est basé sur des événements.
 *
 * @author Guillaume Poirier-Morency
 */
public abstract class Component implements IComponent {

    private Entity mEntity;
    /**
     * Friction par défaut.
     */
    public final static float DEFAULT_FRICTION = 1.0f,
            /**
             * Densité par défaut.
             */
            DEFAULT_DENSITY = 1.0f,
            /**
             * Position X par défaut.
             */
            DEFAULT_X = 0.0f,
            /**
             * Position Y par défaut.
             */
            DEFAULT_Y = 0.0f,
            /**
             * Angle par défaut.
             */
            DEFAULT_ANGLE = 0.0f;
    /**
     *
     */
    public final static Vector2[] DEFAULT_VECTORS = {};

    /**
     *
     * @param vectors
     * @return
     */
    public static float[] vector2ArrayToBufferData(Vector2[] vectors) {

        final float[] bufferData = new float[vectors.length * Mesh.VERTEX_SIZE];

        int bufferDataIndex = 0;

        for (Vector2 v : vectors) {
            bufferData[bufferDataIndex + Mesh.VERTEX_INDEX_X] = v.x;
            bufferData[bufferDataIndex + Mesh.VERTEX_INDEX_Y] = v.y;
            bufferDataIndex += Mesh.VERTEX_SIZE;
        }

        Debug.v("Nombre de données dans le buffer " + bufferData.length);
        Debug.v("Nombre de vecteurs dans le buffer " + vectors.length);

        return bufferData;
    }

    
    /**
     *
     */
    protected FixtureDef mFixtureDef;
    /**
     *
     */
    protected IBaseGameActivity mFunkyDominoBaseActivity;
    /**
     *
     */
    protected Sound mCollisionSound;
    /**
     *
     */
    protected ComponentAttributes mComponentAttributes;

    /**
     *
     * @param ga
     * @param att
     * @return
     */
    public final Entity factory(IBaseGameActivity ga, ComponentAttributes att) {

        mFunkyDominoBaseActivity = ga;

        mComponentAttributes = att;

        try {
            mCollisionSound = SoundFactory.createSoundFromAsset(mFunkyDominoBaseActivity.getSoundManager(), ga.getContext(), "explosion.ogg");
        } catch (IOException ex) {
            Debug.e(ex);
        }

        mFixtureDef = new FixtureDef();

        mFixtureDef.density = att.getFloat("density", DEFAULT_DENSITY);

        mFixtureDef.friction = att.getFloat("friction", DEFAULT_FRICTION);

        onLoadResource();

        mEntity = onCreateEntity(att.getFloat("x", DEFAULT_X), att.getFloat("y", DEFAULT_Y), att.getFloat("angle", DEFAULT_ANGLE));

        onPopulatePhysicsWorld(mFunkyDominoBaseActivity.getPhysicsWorld());

        if (att.getBoolean("touchable", true)) {
            onRegisterTouchAreas(mFunkyDominoBaseActivity.getScene());

        }

        onRegisterContactListener(mFunkyDominoBaseActivity.getContactManager());

        Debug.v("Un nouveau composant est créé ["
                + getEntity().getX() + "," + getEntity().getY() + "]");


        return getEntity();
    }

    ////////////////////////////////////////////////////////////////////////
    // Events
    /**
     * Chargement des ressources.
     */
    protected abstract void onLoadResource();

    /**
     * Création de l'entité.
     *
     * @param pX est la position initiale en x de l'entité.
     * @param pY est la position initiale en x de l'entité.
     * @param angle est l'angle initial de l'entité.
     * @return l'entité qui a été créé.
     */
    protected abstract Entity onCreateEntity(float pX, float pY, float angle);

    /**
     * Binding de l'entité avec le monde physique.
     *
     * @param pPhysicsWorld est le monde physique qui doit être populé.
     */
    protected abstract void onPopulatePhysicsWorld(PhysicsWorld pPhysicsWorld);

    /**
     * L'entité enregistre ses surfaces qui peuvent recevoir des événements de
     * type touch ainsi que la gestion de ces événements.
     *
     * @param pScene est la scène sur laquelle on enregistre les zones de touch.
     */
    protected abstract void onRegisterTouchAreas(Scene pScene);

    /**
     * Le component enregistre des listeners pour écouter les collisions de ses
     * bodies.
     *
     * @param pContactManager est le gestionnaire de contact.
     */
    protected abstract void onRegisterContactListener(ContactManager pContactManager);

    /**
     * Retourne l'entité la plus générale.
     *
     * @return l'entité la plus générale.
     */
    public final Entity getEntity() {
        return mEntity;
    }
   
    /**
     * 
     * @return
     */
    public final IBaseGameActivity getBaseGameActivity() {
        return mFunkyDominoBaseActivity;
    }
}
