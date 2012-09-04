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

import com.badlogic.gdx.math.Vector2;
import java.util.HashMap;
import org.xml.sax.Attributes;

/**
 *
 * @author guillaume
 */
public class EntityAttributes extends HashMap<String, Object> {

    /**
     * Constructeur utilisé par ComponentFactory pour les tests.
     */
    public EntityAttributes() {
    }

    /**
     * Convertit une chaîne de caractères en tableau de vecteurs.
     * Le caractère ; est utilisé pour séparer les vecteurs et le caractère ,
     * est utilisé pour séparer les composants x et y.
     * @param source
     * @return 
     */
    public static Vector2[] parseVector2Array(String source) {
        
        if(source.equals("")) {
            // Avoid parsing empty attribute
            return new Vector2[0];
        }
        
        String stringVector = source;


        String[] stringVectors = stringVector.split(";");


        Vector2[] vectors = new Vector2[stringVectors.length];



        for (int i = 0; i < stringVectors.length; i++) {
            String[] components = stringVectors[i].split(",");
            vectors[i] = new Vector2(Float.parseFloat(components[0]), Float.parseFloat(components[1]));

        }
        return vectors;
    }

    /**
     * Constructeur avec copie des attributs.
     *
     * @param atts
     */
    public EntityAttributes(Attributes atts) {
        for (int i = 0; i < atts.getLength(); i++) {


            put(atts.getLocalName(i), atts.getValue(i));



        }

        // Copie des attributs dans le dictionnaire
    }

    public float getFloat(String key, float defaultValue) {
        Object o = get(key);
        return o != null && o instanceof Float ? ((Float) o).floatValue() : defaultValue;
    }

    public int getInteger(String key, int defaultValue) {
        Object o = get(key);
        return o != null && o instanceof Integer ? ((Integer) o).intValue() : defaultValue;
    }

    public Vector2[] getVector2Array(String key, Vector2[] defaultValue) {
        Object o = get(key);
        return o != null ? parseVector2Array((String) o) : defaultValue;
    }
}
