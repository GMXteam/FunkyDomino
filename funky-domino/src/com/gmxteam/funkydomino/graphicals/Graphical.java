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
package com.gmxteam.funkydomino.graphicals;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import org.anddev.andengine.entity.Entity;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;

/**
 * Objet de base pour construire un élément graphique (widgets et composants).
 * @author Guillaume Poirier-Morency
 */
public abstract class Graphical extends Entity {

    /**
     * 
     */
    protected Paint paint = new Paint();
    /**
     * Définition générale du corps physique.
     */
    protected BodyDef bodyDef = new BodyDef();
    /**
     * Donne une définition précise du corps physique.
     */
    protected Body body;

    /**
     * Dessine l'objet graphique avec OpenGL ES 2.0.
     */
    public abstract void drawGL();

    /**
     * Dessine l'objet graphique sur un canvas.
     * @param c est le canvas sur lequel l'objet graphique est dessiné.
     */
    public abstract void drawCanvas(Canvas c);

    /**
     * Méthode appelée lorsque l'utilisateur clique sur l'objet graphique.
     * @param me est la description de l'événement.
     */
    public abstract void onClick(MotionEvent me);
    
    /**
     * 
     * @param c
     */
    public abstract void drawDebug(Canvas c);
    
    /**
     * 
     */
    public abstract void drawGLDebug();
}
