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
import org.andengine.entity.IEntity;
import org.andengine.entity.primitive.Mesh;
import org.andengine.util.debug.Debug;
import org.xml.sax.Attributes;

/**
 *
 * @author guillaume
 */
public class ComponentAttributes extends HashMap<String, String> {

    public static final float DEFAULT_FRICTON = 1.0f,
            DEFAULT_DENSITY = 5.0f,
            DEFAULT_ANGLE = 0.0f,
            DEFAULT_HEIGHT = 0.0f,
            DEFAULT_WIDTH = 0.0f,
            DEFAULT_X = 0.0f,
            DEFAULT_Y = 0.0f;

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
     * Constructeur utilisé par ComponentFactory pour les tests.
     */
    public ComponentAttributes() {
    }

    /**
     * Constructeur avec copie des attributs XML dans le dictionnaire interne.
     *
     * @param atts
     */
    public ComponentAttributes(Attributes atts) {
        // Copie des attributs dans le dictionnaire

        for (int i = 0; i < atts.getLength(); i++) {


            put(atts.getLocalName(i), atts.getValue(i));



        }

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

    public float getRelativeFloat(String key, float parentValue, float defaultValue) {
        final String o = get(key).replaceAll("%", "");

        return o != null ? parentValue * (Float.parseFloat(o) / 100.0f) : defaultValue;
    }

    public int getRelativeInteger(String key, int parentValue, int defaultValue) {
        final String o = get(key).replaceAll("%", "");
        return o != null ? (int) (parentValue * (Float.parseFloat(o) / 100.0f)) : defaultValue;
    }

    /**
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public float getFloat(String key, float defaultValue) {
        final String o = get(key);
        return o != null ? Float.parseFloat(o) : defaultValue;
    }

    /**
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public int getInteger(String key, int defaultValue) {
        final String o = get(key);
        return o != null ? Integer.parseInt(o) : defaultValue;
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
    public float[] getBufferData(String key, float[] defaultValue) {
        Object o = get(key);
        final Vector2[] defaultV2 = {};
        return o != null ? ComponentAttributes.vector2ArrayToBufferData(getVector2Array(key, defaultV2)) : defaultValue;
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
        return o != null ? Boolean.parseBoolean(o) : defaultValue;
    }

    /**
     *
     * @param friction
     * @param f
     */
    public void putFloat(String friction, float f) {
        put(friction, String.valueOf(f));
    }

    ////////////////////////////////////////////////////////////////////////////
    // Specific grabbers
    /**
     *
     * @return
     */
    public float getFriction() {
        return getFloat("friction", DEFAULT_FRICTON);
    }

    /**
     *
     * @return
     */
    public float getDensity() {
        return getFloat("density", DEFAULT_DENSITY);
    }

    /**
     *
     * @return
     */
    public float getX() {
        return getFloat("x", DEFAULT_X);
    }

    /**
     *
     * @return
     */
    public float getY() {
        return getFloat("y", DEFAULT_Y);
    }

    /**
     *
     * @return
     */
    public float getWidth() {
        return getFloat("width", DEFAULT_WIDTH);
    }

    /**
     *
     * @return
     */
    public float getHeight() {
        return getFloat("height", DEFAULT_HEIGHT);
    }

    /**
     *
     * @return
     */
    public float getAngle() {
        return getFloat("angle", DEFAULT_ANGLE);
    }

    /**
     *
     * @return
     */
    public float getFriction(float defaultValue) {
        return getFloat("friction", defaultValue);
    }

    /**
     *
     * @return
     */
    public float getDensity(float defaultValue) {
        return getFloat("density", defaultValue);
    }

    /**
     *
     * @return
     */
    public float getX(float defaultValue) {
        return getFloat("x", defaultValue);
    }

    /**
     *
     * @return
     */
    public float getY(float defaultValue) {
        return getFloat("y", defaultValue);
    }

    /**
     *
     * @return
     */
    public float getWidth(float defaultValue) {
        return getFloat("width", defaultValue);
    }

    /**
     *
     * @return
     */
    public float getHeight(float defaultValue) {
        return getFloat("height", defaultValue);
    }

    /**
     *
     * @return
     */
    public float getAngle(float defaultValue) {
        return getFloat("angle", defaultValue);
    }
}
