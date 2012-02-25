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

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.gmxteam.funkydomino.activities.AndEngineActivity;
import com.gmxteam.funkydomino.utils.xmlparser.IllegalXMLAttributeValueException;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
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
     * @param aea est l'activité sur laquelle le Ground va se contruire.
     * @param atts est un attribut XML contenant tous les attributs spécifiés
     * dans le fichier XML pour cet objet.
     */
    public Ground(AndEngineActivity aea, Attributes atts) {
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
        init(aea, x, y);
    }

    /**
     * Constructeur extensif.
     * @param aea 
     * @param x est un tableau contenant les valeurs x des points formant le sol.
     * @param y est un tableau contenant les valeurs y des points formant le sol.
     */
    public Ground(AndEngineActivity aea, float[] x, float y[]) {
        init(aea, x, y);
    }

    /**
     * Méthode pour unifier les appels de constructeurs.
     */
    private void init(AndEngineActivity aea, float[] x, float y[]) {
        this.mAndEngineActivity = aea;
        this.mColor = Color.BLACK;
        final VertexBufferObjectManager vertexBufferObjectManager = mAndEngineActivity.getVertexBufferObjectManager();

        final Rectangle bottomOuter = new Rectangle(0, AndEngineActivity.CAMERA_HEIGHT - 2, AndEngineActivity.CAMERA_WIDTH, 2, vertexBufferObjectManager);
        final Rectangle topOuter = new Rectangle(0, 0, AndEngineActivity.CAMERA_WIDTH, 2, vertexBufferObjectManager);
        final Rectangle leftOuter = new Rectangle(0, 0, 2, AndEngineActivity.CAMERA_HEIGHT, vertexBufferObjectManager);
        final Rectangle rightOuter = new Rectangle(AndEngineActivity.CAMERA_WIDTH - 2, 0, 2, AndEngineActivity.CAMERA_HEIGHT, vertexBufferObjectManager);


        final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(0, 0.5f, 0.5f);
        PhysicsFactory.createBoxBody(mAndEngineActivity.mPhysicsWorld, bottomOuter, BodyType.StaticBody, wallFixtureDef);
        PhysicsFactory.createBoxBody(mAndEngineActivity.mPhysicsWorld, topOuter, BodyType.StaticBody, wallFixtureDef);
        PhysicsFactory.createBoxBody(mAndEngineActivity.mPhysicsWorld, leftOuter, BodyType.StaticBody, wallFixtureDef);
        PhysicsFactory.createBoxBody(mAndEngineActivity.mPhysicsWorld, rightOuter, BodyType.StaticBody, wallFixtureDef);


        mAndEngineActivity.mScene.attachChild(bottomOuter);
        mAndEngineActivity.mScene.attachChild(topOuter);
        mAndEngineActivity.mScene.attachChild(leftOuter);
        mAndEngineActivity.mScene.attachChild(rightOuter);



    }
}
