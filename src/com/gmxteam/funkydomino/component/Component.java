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
package com.gmxteam.funkydomino.component;

import android.content.Context;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.gmxteam.funkydomino.activity.IBaseGameActivity;
import com.gmxteam.funkydomino.physics.box2d.ContactManager;
import java.io.IOException;
import org.andengine.audio.music.MusicManager;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Mesh;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
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
    public final static float DEFAULT_FRICTION = 1.0f,
            DEFAULT_DENSITY = 1.0f,
            DEFAULT_X = 0.0f,
            DEFAULT_Y = 0.0f,
            DEFAULT_ANGLE = 0.0f;
    public final static Vector2[] DEFAULT_VECTORS = {};

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
     * Retourne le tableau de vecteurs qui forme le polygone du sol.
     *
     * @return
     */
    public Vector2[] getVertices() {
        return mComponentAttributes.getVector2Array("vector", DEFAULT_VECTORS);
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
    public final Component factory(IBaseGameActivity ga, ComponentAttributes att) {

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


        return this;
    }

    ////////////////////////////////////////////////////////////////////////
    // Events
    /**
     *
     */
    protected abstract void onLoadResource();

    /**
     *
     * @param pX
     * @param pY
     * @param angle
     */
    protected abstract Entity onCreateEntity(float pX, float pY, float angle);

    /**
     * Binding de l'entité avec le monde physique.
     *
     * @param pPhysicsWorld
     */
    protected abstract void onPopulatePhysicsWorld(PhysicsWorld pPhysicsWorld);

    /**
     * L'entité enregistre ses surfaces qui peuvent recevoir des événements de
     * type touch ainsi que la gestion de ces événements.
     *
     * @param pScene
     */
    protected abstract void onRegisterTouchAreas(Scene pScene);

    /**
     *
     * @param pContactManager
     */
    protected abstract void onRegisterContactListener(ContactManager pContactManager);

    /**
     *
     * @return
     */
    public final Entity getEntity() {
        return mEntity;
    }

    /////////////////////////
    // Accessible resources from the GameActivity
    /**
     *
     * @return
     */
    protected final TextureManager getTextureManager() {
        return mFunkyDominoBaseActivity.getTextureManager();
    }

    /**
     *
     * @return
     */
    protected final VertexBufferObjectManager getVertexBufferObjectManager() {
        return mFunkyDominoBaseActivity.getVertexBufferObjectManager();
    }

    /**
     *
     * @return
     */
    protected final MusicManager getMusicManager() {
        return mFunkyDominoBaseActivity.getMusicManager();
    }

    /**
     *
     * @return
     */
    protected final Context getContext() {
        return (Context) mFunkyDominoBaseActivity;
    }
}
