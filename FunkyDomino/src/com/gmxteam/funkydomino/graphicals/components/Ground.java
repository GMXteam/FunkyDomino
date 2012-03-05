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

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.gmxteam.funkydomino.activities.AndEngineActivity;
import com.gmxteam.funkydomino.utils.xmlparser.IllegalXMLAttributeValueException;
import org.andengine.entity.primitive.Line;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;
import org.xml.sax.Attributes;

/**
 * Objet définissant le sol.
 * @author Guillaume Poirier-Morency
 */
public final class Ground extends Component {

    private Body[] mBodies;

    ////////////////////////////////////////////////////////////////////////////
    // Les textures sont statiques et chargées lors
    /**
     * Permet de charger les ressources de façon statique (disponible a tous les
     * objets de ce type). Cette méthode doit nécéssairement être lancée avant
     * toute forme de création d'objets de type Graphical.
     * @param aea 
     */
    public static void loadResource(AndEngineActivity aea) {
    }
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Constructeur interprétant les attributs XML. Il ne devrait que convertir
     * les attributs et les passer en paramètres.
     * @param aea est l'activité sur laquelle le Ground va se contruire.
     * @param atts est un attribut XML contenant tous les attributs spécifiés
     * dans le fichier XML pour cet objet.
     */
    public Ground(AndEngineActivity aea, Attributes atts) {
        if (atts.getValue("type").equals("linear")) {
            String[] xStringList = atts.getValue("x").split(" ");
            String[] yStringList = atts.getValue("y").split(" ");
            if (xStringList.length != yStringList.length) {
                throw new IllegalXMLAttributeValueException("La liste de floats pour construire le sol ne possède pas autant d'éléments en x qu'en y"
                        + "\n" + atts.getValue("x")
                        + "\n" + atts.getValue("y"));
            }
            float[] x = new float[xStringList.length];
            float[] y = new float[yStringList.length];
            for (int i = 0; i < xStringList.length; i++) {
                try {
                    x[i] = Float.parseFloat(xStringList[i]);
                } catch (NumberFormatException nfe) {
                    throw new IllegalXMLAttributeValueException("La valeur x " + xStringList[i] + " n'a pas pu être convertie en float.", nfe);

                }
                try {
                    y[i] = Float.parseFloat(yStringList[i]);
                } catch (NumberFormatException nfe) {
                    throw new IllegalXMLAttributeValueException("La valeur y " + yStringList[i] + " n'a pas pu être convertie en float.", nfe);

                }
            }
            initLinear(aea, x, y);
        } else if (atts.getValue("type").equals("bezier")) {
            String[] xStringList = atts.getValue("x").split(" ");
            String[] yStringList = atts.getValue("y").split(" ");
            if (xStringList.length != 4 | yStringList.length != 4) {
                throw new IllegalXMLAttributeValueException("La liste de floats pour construire le sol avec une courbe de bézier ne possède pas exactement 4 éléments !"
                        + "\n" + atts.getValue("x")
                        + "\n" + atts.getValue("y"));
            }
            float[] x = new float[xStringList.length];
            float[] y = new float[yStringList.length];
            for (int i = 0; i < xStringList.length; i++) {
                try {
                    x[i] = Float.parseFloat(xStringList[i]);
                } catch (NumberFormatException nfe) {
                    throw new IllegalXMLAttributeValueException("La valeur x " + xStringList[i] + " n'a pas pu être convertie en float.", nfe);

                }
                try {
                    y[i] = Float.parseFloat(yStringList[i]);
                } catch (NumberFormatException nfe) {
                    throw new IllegalXMLAttributeValueException("La valeur y " + yStringList[i] + " n'a pas pu être convertie en float.", nfe);

                }
            }
            initBezier(aea, x, y);
        }
    }

    /**
     * Constructeur extensif.
     * @param aea 
     * @param x est un tableau contenant les valeurs x des points formant le sol.
     * @param y est un tableau contenant les valeurs y des points formant le sol.
     */
    public Ground(AndEngineActivity aea, float[] x, float y[]) {
        if (x.length != y.length) {
            throw new IllegalXMLAttributeValueException("La liste de floats pour construire le sol ne possède pas autant d'éléments en x qu'en y"
                    + "\n" + x.length
                    + "\n" + y.length);
        }
        initLinear(aea, x, y);
    }

    /**
     * Méthode pour unifier les appels de constructeurs.
     */
    private void initLinear(AndEngineActivity aea, float[] x, float y[]) {
        this.mAndEngineActivity = aea;
        this.mColor = Color.BLACK;
        final VertexBufferObjectManager vertexBufferObjectManager = mAndEngineActivity.getVertexBufferObjectManager();


        final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0, 0.5f, 0.0f);

        this.mBodies = new Body[x.length];

        for (int i = 0; i < x.length - 1; i++) {
            Line l = new Line(x[i], y[i], x[i + 1], y[i + 1], 1.0f, vertexBufferObjectManager);

            this.attachChild(l);
            mBodies[i] = PhysicsFactory.createLineBody(aea.mPhysicsWorld, x[i], y[i], x[i + 1], y[i + 1], wallFixtureDef);
        }
    }

    /**
     * Génère une courbe de bézier avec quantre points de contrôle.
     * @param aea
     * @param x
     * @param y 
     */
    private void initBezier(AndEngineActivity aea, float[] x, float y[]) {
        this.mAndEngineActivity = aea;
        this.mColor = Color.BLACK;
        final VertexBufferObjectManager vertexBufferObjectManager = mAndEngineActivity.getVertexBufferObjectManager();


        final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0, 0.5f, 0.0f);

        this.mBodies = new Body[x.length];

        for (int i = 0; i < x.length - 1; i++) {
            Line l = new Line(x[i], y[i], x[i + 1], y[i + 1], 1.0f, vertexBufferObjectManager);

            this.attachChild(l);

            mBodies[i] = PhysicsFactory.createLineBody(aea.mPhysicsWorld, x[i], y[i], x[i + 1], y[i + 1], wallFixtureDef);
        }
    }
}
