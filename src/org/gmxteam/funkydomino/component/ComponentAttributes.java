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
import java.util.Arrays;
import java.util.HashMap;
import org.andengine.entity.primitive.Mesh;
import org.andengine.util.debug.Debug;
import org.xml.sax.Attributes;

/**
 *
 * @author guillaume
 */
public class ComponentAttributes extends HashMap<String, String> {

    public float getFriction() {
        return getFloat("friction", 1.0f);
    }

    public float getDensity() {
        return getFloat("friction", 1.0f);
    }

    public float getX() {
        return getFloat("x", 0.0f);
    }

    public float getY() {
        return getFloat("y", 0.0f);
    }

    public float getAngle() {
        return getFloat("angle", 0.0f);
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public static ComponentAttributes createBaseAttributes(float x, float y) {
        return createBaseAttributes(x, y, IComponent.DEFAULT_ANGLE);
    }

    /**
     *
     * @param x
     * @param y
     * @param angle
     * @return
     */
    public static ComponentAttributes createBaseAttributes(float x, float y, float angle) {
        ComponentAttributes attributes = new ComponentAttributes();
        attributes.put("x", String.valueOf(x));
        attributes.put("y", String.valueOf(y));
        attributes.put("angle", String.valueOf(angle));


        return attributes;
    }

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
     * @param s
     * @return
     */
    public static boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException ffe) {
            Debug.e(ffe);
            return false;
        }
    }

    /**
     *
     * @param s
     * @return
     */
    public static boolean isBoolean(String s) {
        try {
            Boolean.parseBoolean(s);
            return true;
        } catch (Exception ffe) {
            Debug.e(ffe);
            return false;
        }
    }

    /**
     *
     * @param s
     * @return
     */
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ffe) {
            Debug.e(ffe);
            return false;
        }
    }

    /**
     * Constructeur utilisé par ComponentFactory pour les tests.
     */
    public ComponentAttributes() {
    }

    /**
     * Constructeur avec copie des attributs.
     *
     * @param atts
     */
    public ComponentAttributes(Attributes atts) {
        for (int i = 0; i < atts.getLength(); i++) {


            put(atts.getLocalName(i), atts.getValue(i));



        }

        // Copie des attributs dans le dictionnaire
    }

    /**
     *
     * @param source
     * @return
     */
    public static String vector2ArrayToString(Vector2[] source) {

        return Arrays.toString(source);

    }

    /**
     * Convertit une chaîne de caractères en tableau de vecteurs. Le caractère ;
     * est utilisé pour séparer les vecteurs et le caractère , est utilisé pour
     * séparer les composants x et y.
     *
     * @param source
     * @return
     */
    public static Vector2[] parseVector2Array(String source) {

        if (source.equals("")) {
            // Avoid parsing empty attribute
            return new Vector2[0];
        }

        source = source.replaceAll("\\s", "");

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
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public float getFloat(String key, float defaultValue) {
        final String o = get(key);
        return o != null && isFloat(o) ? Float.parseFloat(o) : defaultValue;
    }

    /**
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public int getInteger(String key, int defaultValue) {
        final String o = get(key);
        return o != null && isInteger(o) ? Integer.parseInt(o) : defaultValue;
    }

    /**
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Vector2[] getVector2Array(String key, Vector2[] defaultValue) {
        Object o = get(key);
        return o != null ? parseVector2Array((String) o) : defaultValue;
    }

    /**
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public String getString(String key, String defaultValue) {
        String o = get(key);
        return o != null ? (String) o : defaultValue;
    }

    /**
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        final String o = get(key);
        return o != null && isBoolean(o) ? Boolean.parseBoolean(o) : defaultValue;
    }

    public void putFloat(String friction, float f) {
        put(friction, String.valueOf(f));
    }
}
