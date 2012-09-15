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
package com.gmxteam.funkydomino.component;

import com.badlogic.gdx.math.Vector2;
import com.gmxteam.funkydomino.activity.FunkyDominoActivity;
import com.gmxteam.funkydomino.activity.IBaseGameActivity;
import com.gmxteam.funkydomino.component.entity.AddDominoButton;
import com.gmxteam.funkydomino.component.entity.Ball;
import com.gmxteam.funkydomino.component.entity.Cog;
import com.gmxteam.funkydomino.component.entity.Domino;
import com.gmxteam.funkydomino.component.entity.Ground;

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
     * Creates a domino.
     *
     * @param x
     * @param y
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static Domino createDomino(float x, float y) throws InstantiationException, IllegalAccessException {

        ComponentAttributes attributes = new ComponentAttributes();
        attributes.put("x", String.valueOf(x));
        attributes.put("y",String.valueOf( y));

        return (Domino) Components.domino.getComponent().factory(mGameActivity, attributes);
    }

    /**
     *
     * @param x
     * @param y
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static AddDominoButton createAddDominoButton(float x, float y) throws InstantiationException, IllegalAccessException {
        ComponentAttributes attributes = new ComponentAttributes();
        attributes.put("x", String.valueOf(x));
        attributes.put("y",String.valueOf( y));

        return (AddDominoButton) Components.add_domino_button.getComponent().factory(mGameActivity, attributes);

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
    public static Ball createBall(float x, float y, float radius) throws InstantiationException, IllegalAccessException {
        ComponentAttributes attributes = new ComponentAttributes();
        attributes.put("x", String.valueOf(x)); 
        attributes.put("y", String.valueOf(y));
        attributes.put("radius", String.valueOf(radius));

        return (Ball) Components.ball.getComponent().factory(mGameActivity, attributes);
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
    public static Ground createGround(float x, float y, Vector2[] vertex) throws InstantiationException, IllegalAccessException {

        ComponentAttributes attributes = new ComponentAttributes();
        attributes.put("vector", ComponentAttributes.vector2ArrayToString(vertex));

        return (Ground) Components.ground.getComponent().factory(mGameActivity, attributes);
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
    public static Cog createCog(float x, float y) throws InstantiationException, IllegalAccessException {

        ComponentAttributes attributes = new ComponentAttributes();
        attributes.put("x", String.valueOf(x));
        attributes.put("y", String.valueOf(y));

        return (Cog) Components.ground.getComponent().factory(mGameActivity, attributes);
    }
}