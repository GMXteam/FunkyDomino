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
package com.gmxteam.funkydomino.graphicals.components;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.util.color.Color;
import org.xml.sax.Attributes;

/**
 * Objet définissant le sol.
 * @author Guillaume Poirier-Morency
 */
public final class Ground extends Component {

    /**
     * Constructeur interprétant les attributs XML. Il ne devrait que convertir
     * les attributs et les passer en paramètres.
     * @param atts 
     */
    public Ground(PhysicsWorld pw, Attributes atts) {
    }

    /**
     * Constructeur extensif.
     * @param x est un tableau contenant les valeurs x des points formant le sol.
     * @param y est un tableau contenant les valeurs y des points formant le sol.
     */
    public Ground(PhysicsWorld pw, float[] x, float y[]) {

        this.mPhysicsWorld = pw;
        this.mColor = Color.BLACK;


    }
    
    /**
     * Experimental constructor.
     */
    private Ground() {
    
    

    }
}
