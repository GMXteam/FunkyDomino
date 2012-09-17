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
package org.gmxteam.funkydomino.level.loader;

import com.badlogic.gdx.math.Vector2;
import org.andengine.entity.Entity;
import org.gmxteam.funkydomino.activity.IBaseGameActivity;
import org.gmxteam.funkydomino.component.Component;
import org.gmxteam.funkydomino.component.ComponentAttributes;
import org.gmxteam.funkydomino.component.Components;

/**
 * Utility class containing methods to create and extract components.
 *
 * @author guillaume
 */
public class ComponentFactory {

    private static IBaseGameActivity mGameActivity;

    /**
     *
     * @param pGameActivity
     */
    public static void setGameActivity(IBaseGameActivity pGameActivity) {
        mGameActivity = pGameActivity;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    private static ComponentAttributes createBaseAttributes(float x, float y) {
        return createBaseAttributes(x, y, Component.DEFAULT_ANGLE);
    }

    /**
     *
     * @param x
     * @param y
     * @param angle
     * @return
     */
    private static ComponentAttributes createBaseAttributes(float x, float y, float angle) {
        ComponentAttributes attributes = new ComponentAttributes();
        attributes.put("x", String.valueOf(x));
        attributes.put("y", String.valueOf(y));
        attributes.put("angle", String.valueOf(angle));
        return attributes;
    }

    /**
     * Creates a domino.
     *
     * @param x
     * @param y
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static Entity createDomino(float x, float y) throws InstantiationException, IllegalAccessException {

        return Components.domino.getComponent().factory(mGameActivity, createBaseAttributes(x, y));
    }

    /**
     *
     * @param x
     * @param y
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static Entity createAddDominoButton(float x, float y) throws InstantiationException, IllegalAccessException {


        return Components.add_domino_button.getComponent().factory(mGameActivity, createBaseAttributes(x, y));

    }

    /**
     * Creates a domino.
     *
     * @param x
     * @param y
     * @param radius
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static Entity createBall(float x, float y, float radius) throws InstantiationException, IllegalAccessException {
        ComponentAttributes attributes = createBaseAttributes(x, y);
        attributes.put("radius", String.valueOf(radius));
        return Components.ball.getComponent().factory(mGameActivity, attributes);
    }

    /**
     * Creates a domino.
     *
     * @param x
     * @param y
     * @param vertex
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static Entity createGround(float x, float y, Vector2[] vertex) throws InstantiationException, IllegalAccessException {

        ComponentAttributes attributes = createBaseAttributes(x, y);
        attributes.put("vector", ComponentAttributes.vector2ArrayToString(vertex));

        return Components.ground.getComponent().factory(mGameActivity, attributes);
    }

    /**
     * Creates a cog.
     *
     * @param x
     * @param y
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static Entity createCog(float x, float y) throws InstantiationException, IllegalAccessException {



        return Components.ground.getComponent().factory(mGameActivity, createBaseAttributes(x, y));
    }
}
