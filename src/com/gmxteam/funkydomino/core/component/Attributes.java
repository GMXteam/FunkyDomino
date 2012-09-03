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

/**
 *
 * @author guillaume
 */
public class Attributes extends HashMap<String, Object> {

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
        return o != null && o instanceof Vector2[] ? (Vector2[]) o : defaultValue;
    }
}
