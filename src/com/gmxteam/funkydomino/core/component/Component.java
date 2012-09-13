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
package com.gmxteam.funkydomino.core.component;

import com.gmxteam.funkydomino.core.component.factory.ComponentAttributes;
import android.content.Context;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.gmxteam.funkydomino.activity.IFunkyDominoBaseActivity;
import com.gmxteam.funkydomino.core.physics.box2d.ContactManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.andengine.audio.music.MusicManager;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.entity.Entity;
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
public abstract class Component extends Entity {

    final float DEFAULT_FRICTION = 1.0f,
            DEFAULT_DENSITY = 1.0f,
            DEFAULT_X = 0.0f,
            DEFAULT_Y = 0.0f,
            DEFAULT_ANGLE = 0.0f;
    /**
     *
     */
    protected FixtureDef mFixtureDef;
    /**
     *
     */
    protected IFunkyDominoBaseActivity mFunkyDominoBaseActivity;
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
    public final Component factory(IFunkyDominoBaseActivity ga, ComponentAttributes att) {

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

        onCreateSprite(att.getFloat("x", DEFAULT_X), att.getFloat("y", DEFAULT_Y), att.getFloat("angle", DEFAULT_ANGLE));

        onPopulatePhysicsWorld(mFunkyDominoBaseActivity.getPhysicsWorld());
        //.setTransform(att.getFloat("x", 0.0f), att.getFloat("y", 0.0f), att.getFloat("angle", 0.0f));

        onPopulateEntity(this);

        onRegisterTouchAreas(mFunkyDominoBaseActivity.getScene());

        onRegisterContactListener(mFunkyDominoBaseActivity.getContactManager());

        Debug.v("Un nouveau composant est créé ["
                + this.getX() + "," + this.getY() + "]");


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
    protected abstract void onCreateSprite(float pX, float pY, float angle);

    /**
     * Binding de l'entité avec le monde physique.
     *
     * @param pPhysicsWorld
     */
    protected abstract void onPopulatePhysicsWorld(PhysicsWorld pPhysicsWorld);

    /**
     * Binding des sous-entités avec l'entité qui reçoit l'événement.
     *
     * @param e Év
     */
    protected abstract void onPopulateEntity(Entity e);

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
