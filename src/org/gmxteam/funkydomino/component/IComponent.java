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
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import org.andengine.entity.IEntity;
import org.andengine.extension.physics.box2d.PhysicsWorld;

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
public interface IComponent extends  IEntity, ContactListener {

    
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

    ////////////////////////////////////////////////////////////////////////
    // Events     

    /**
     * Binding de l'entité avec le monde physique.
     *
     * @param pPhysicsWorld est le monde physique qui doit être populé.
     * @param pFixtureDef
     * @return  
     */
    public Body onCreateBody(PhysicsWorld pPhysicsWorld, FixtureDef pFixtureDef);
    
    

    
    
}
