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
package com.gmxteam.funkydomino.core.component.factory;

import com.badlogic.gdx.math.Vector2;
import com.gmxteam.funkydomino.activity.GameActivity;
import com.gmxteam.funkydomino.activity.IFunkyDominoBaseActivity;
import com.gmxteam.funkydomino.core.component.AddDominoButton;
import com.gmxteam.funkydomino.core.component.Ball;
import com.gmxteam.funkydomino.core.component.Cog;
import com.gmxteam.funkydomino.core.component.Domino;
import com.gmxteam.funkydomino.core.component.Ground;

/**
 * Utility class containing methods to create and extract components.
 *
 * @author guillaume
 */
public class ComponentFactory {

    private static IFunkyDominoBaseActivity mGameActivity;

    public static void setGameActivity(IFunkyDominoBaseActivity pGameActivity) {
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
        attributes.put("x", x);
        attributes.put("y", y);

        return (Domino) Components.domino.getComponent().factory(mGameActivity, attributes);
    }

    public static AddDominoButton createAddDominoButton(float x, float y) throws InstantiationException, IllegalAccessException {
        ComponentAttributes attributes = new ComponentAttributes();
        attributes.put("x", x);
        attributes.put("y", y);

        return (AddDominoButton) Components.add_domino_button.getComponent().factory(mGameActivity, attributes);

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
    public static Ball createBall(float x, float y, float radius) throws InstantiationException, IllegalAccessException {
        ComponentAttributes attributes = new ComponentAttributes();
        attributes.put("x", x);
        attributes.put("y", y);
        attributes.put("radius", radius);

        return (Ball) Components.ball.getComponent().factory(mGameActivity, attributes);
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
    public static Ground createGround(float x, float y, Vector2[] vertex) throws InstantiationException, IllegalAccessException {

        ComponentAttributes attributes = new ComponentAttributes();
        attributes.put("vector", vertex);

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
        attributes.put("x", x);
        attributes.put("y", y);

        return (Cog) Components.ground.getComponent().factory(mGameActivity, attributes);
    }
}
