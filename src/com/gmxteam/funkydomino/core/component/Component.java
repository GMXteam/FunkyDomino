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
import android.provider.MediaStore.Audio;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.gmxteam.funkydomino.activity.GameActivity;
import com.gmxteam.funkydomino.core.ContactManager;
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
public abstract class Component extends Entity implements ComponentsConstants {

    protected FixtureDef mFixtureDef;
    private GameActivity mGameActivity;
    protected Sound mCollisionSound;

    public final Component factory(GameActivity ga, ComponentAttributes att) {

        mGameActivity = ga;
        
        try {
            mCollisionSound = SoundFactory.createSoundFromAsset(mGameActivity.getSoundManager(), ga, "explosion.ogg");
        } catch (IOException ex) {
            Logger.getLogger(Component.class.getName()).log(Level.SEVERE, null, ex);
        }


        this.setX(att.getFloat("x", 0.0f));
        this.setY(att.getFloat("y", 0.0f));


        mFixtureDef = new FixtureDef();

        mFixtureDef.density = att.getFloat("density", 5.0f);
        
        mFixtureDef.friction = att.getFloat("friction", 1.0f);


        onLoadResource();

        onCreateFixtureDef(mFixtureDef, att);

        onPopulatePhysicsWorld(mGameActivity.mPhysicsWorld, att);

        onPopulateEntity(this);

        onRegisterTouchAreas(mGameActivity.mScene);
        
        onRegisterContactListener(mGameActivity.mContactManager);

        return this;
    }

    ////////////////////////////////////////////////////////////////////////
    // Events
    protected abstract void onLoadResource();

    /**
     * Altération du FixtureDef (propriétés physiques initiales et
     * fondamentales)
     *
     * @param fd
     */
    protected abstract void onCreateFixtureDef(FixtureDef pFixtureDef, ComponentAttributes pAttributes);

    /**
     * Binding de l'entité avec le monde physique.
     *
     * @param pw
     */
    protected abstract void onPopulatePhysicsWorld(PhysicsWorld pPhysicsWorld, ComponentAttributes pAttributes);

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
    
    protected abstract void onRegisterContactListener(ContactManager pContactManager);

    /////////////////////////
    // Accessible resources from the GameActivity
    protected final TextureManager getTextureManager() {
        return mGameActivity.getTextureManager();

    }

    protected final VertexBufferObjectManager getVertexBufferObjectManager() {
        return mGameActivity.getVertexBufferObjectManager();
    }

    protected final MusicManager getMusicManager() {
        return mGameActivity.getMusicManager();
    }

    protected final Context getContext() {
        return (Context) mGameActivity;
    }
}
