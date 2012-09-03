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

import android.content.res.XmlResourceParser;
import com.badlogic.gdx.math.Vector2;
import com.gmxteam.funkydomino.activity.GameActivity;
import java.io.IOException;
import java.util.LinkedList;
import org.xmlpull.v1.XmlPullParserException;

/**
 * Utility class containing methods to create and extract components.
 *
 * @author guillaume
 */
public class ComponentFactory {

    private static GameActivity mGameActivity;

    public static void setGameActivity(GameActivity pGameActivity) {
        mGameActivity = pGameActivity;
    }

    /**
     * Extracts all the components found in the specified XML asset and return a
     * LinkedList of them.
     *
     * @param pGameActivity
     * @param assetPath
     * @return
     * @throws IOException
     * @throws XmlPullParserException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static LinkedList<Component> extractComponentsFromAsset(String assetPath) throws IOException, XmlPullParserException, InstantiationException, IllegalAccessException {
        XmlResourceParser xrp = mGameActivity.getAssets().openXmlResourceParser(assetPath);

        LinkedList<Component> components = new LinkedList<Component>();
        while (xrp.next() != XmlResourceParser.END_DOCUMENT) {

            Attributes att = new Attributes();

            for (int i = 0; i < xrp.getAttributeCount(); i++) {
                att.put(xrp.getAttributeName(i), xrp.getAttributeValue(i));
            }

            components.add(Components.valueOf(xrp.getName()).getComponent().factory(mGameActivity, att));


        }
        xrp.close();
        return components;
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

        Attributes attributes = new Attributes();
        attributes.put("x", x);
        attributes.put("y", y);

        return (Domino) Components.domino.getComponent().factory(mGameActivity, attributes);
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
    public static Ball createBall(float x, float y) throws InstantiationException, IllegalAccessException {

        Attributes attributes = new Attributes();
        attributes.put("x", x);
        attributes.put("y", y);

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

        Attributes attributes = new Attributes();
        attributes.put("vector", vertex);

        return (Ground) Components.ground.getComponent().factory(mGameActivity, attributes);
    }
}
