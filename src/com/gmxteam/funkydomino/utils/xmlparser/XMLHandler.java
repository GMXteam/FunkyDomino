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
package com.gmxteam.funkydomino.utils.xmlparser;

import android.util.Log;

import com.gmxteam.funkydomino.activities.FunkyDominoActivity;
import com.gmxteam.funkydomino.graphicals.components.Ball;
import com.gmxteam.funkydomino.graphicals.components.Cog;
import com.gmxteam.funkydomino.graphicals.components.Component;
import com.gmxteam.funkydomino.graphicals.components.Domino;
import com.gmxteam.funkydomino.graphicals.components.Ground;
import com.gmxteam.funkydomino.graphicals.components.Water;
import com.gmxteam.funkydomino.graphicals.widgets.AddBall;
import com.gmxteam.funkydomino.graphicals.widgets.AddDomino;
import com.gmxteam.funkydomino.graphicals.widgets.Widget;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * TODO Réécrire ce parser, il est crasse.
 *
 * @author Guillaume Poirier-Morency
 */
public final class XMLHandler extends DefaultHandler {

    private class StateStructure {

        private boolean level;
        private boolean component;
        private boolean domino, cog, ball, ground, water;
        private boolean widget;
        private boolean addDomino, addBall;

        /**
         * Teste la structure pour s'assurer que le fichier est valide en cours
         * de parsing.
         *
         * @return true si tout est correcte, false si il y a une erreur.
         */
        private boolean verifyStructure() {
            if (!level && (component | widget)) {
                Log.e("funky-domino", "Balise component ou widget à l'extérieur de level.");
                return false;
            }


            // Tout est impeccable !
            return true;

        }
    }
    /**
     * Entier contenant le temps de départ du parsing afin de mesurer le temps
     * que l'opération va prendre.
     */
    private long startedTime;
    /**
     * Dictionnaire contenant les états des balises XML.
     */
    private StateStructure states = new StateStructure();
    // this holds the data 
    /**
     * @see GameInformation
     */
    private GameInformation gameInformationData;
    /**
     * @see FunkyDominoActivity
     */
    private FunkyDominoActivity mAndEngineActivity;

    /**
     *
     */
    public XMLHandler() {
        mAndEngineActivity = null;
    }

    /**
     *
     * @param aea
     */
    public XMLHandler(FunkyDominoActivity aea) {
        mAndEngineActivity = aea;
    }

    /**
     * Returns the data object
     *
     * @return
     */
    public GameInformation getGameInformation() {
        return gameInformationData;
    }

    /**
     * This gets called when the xml document is first opened
     *
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException {
        startedTime = System.currentTimeMillis();
        Log.v("funky-domino", "Le parsing du fichier de niveau commence");

        gameInformationData = new GameInformation();
    }

    /**
     * Called when it's finished handling the document
     *
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        Log.v("funky-domino", "Le parsing du fichier de niveau est terminé en " + (System.currentTimeMillis() - startedTime) + " ms.");
    }

    /**
     * This gets called at the start of an element. Here we're also setting the
     * booleans to true if it's at that specific tag. (so we know where we are)
     *
     * @param namespaceURI
     * @param localName
     * @param qName
     * @param atts
     * @throws SAXException
     */
    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        if (!states.verifyStructure()) {
            return;
        }
        Log.v("funky-domino", "On entre dans la balise " + localName + ".");
        if (states.component) {
            if (mAndEngineActivity == null) {
                return;
            }
            if (localName.equals("domino")) {
                states.domino = true;
                mAndEngineActivity.mScene.attachChild(new Domino(mAndEngineActivity, atts));
                Log.v("funky-domino", "Adding a domino to the scene.");
            } else if (localName.equals("cog")) {
                states.cog = true;
                mAndEngineActivity.mScene.attachChild(new Cog(mAndEngineActivity, atts));
                Log.v("funky-domino", "Adding a cog to the scene.");
            } else if (localName.equals("ball")) {
                states.ball = true;

                mAndEngineActivity.mScene.attachChild(new Ball(mAndEngineActivity, atts));
                Log.v("funky-domino", "Adding a ball to the scene.");
            } else if (localName.equals("ground")) {
                states.ground = true;
                mAndEngineActivity.mScene.attachChild(new Ground(mAndEngineActivity, atts));
                Log.v("funky-domino", "Adding a ground to the scene.");
            } else if (localName.equals("water")) {
                states.water = true;
                mAndEngineActivity.mScene.attachChild(new Water(mAndEngineActivity, atts));
                Log.v("funky-domino", "Adding water to the scene.");
            }

        } else if (states.widget) {
            if (mAndEngineActivity.mScene == null) {
                return;
            }
            if (localName.equals("addball")) {
                states.addBall = true;

                mAndEngineActivity.mScene.attachChild(new AddBall(mAndEngineActivity, atts));
                Log.v("funky-domino", "Adding a addball widget to the scene.");
            } else if (localName.equals("adddomino")) {
                states.addDomino = true;
                mAndEngineActivity.mScene.attachChild(new AddDomino(mAndEngineActivity, atts));
                Log.v("funky-domino", "Adding a adddomino widget to the scene.");
            }
        }


        if (states.level) {
            if (localName.equals("component")) {
                states.component = true;
                Component.loadResource(mAndEngineActivity, atts);
                gameInformationData.componentTheme = atts.getValue("theme");
            } else if (localName.equals("widget")) {
                states.widget = true;
                Widget.loadResource(mAndEngineActivity, atts);
                gameInformationData.widgetTheme = atts.getValue("theme");
            }
        }

        if (localName.equals("level")) {
            states.level = true;
            gameInformationData.id = atts.getValue("id");
            gameInformationData.previousLevel = atts.getValue("previousLevel");
            gameInformationData.nextLevel = atts.getValue("nextLevel");
            gameInformationData.name = atts.getValue("name");
            gameInformationData.description = atts.getValue("description");
            Log.v("funky-domino", "Game info :"
                    + "\n\t" + gameInformationData.id
                    + "\n\t" + gameInformationData.previousLevel
                    + "\n\t" + gameInformationData.nextLevel
                    + "\n\t" + gameInformationData.name
                    + "\n\t" + gameInformationData.description);
        }
    }

    /**
     * Called at the end of the element. Setting the booleans to false, so we
     * know that we've just left that tag.
     *
     * @param namespaceURI
     * @param localName
     * @param qName
     * @throws SAXException
     */
    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if (!states.verifyStructure()) {
            return;
        }
        Log.v("funky-domino", "On sort de la balise " + localName + ".");
        if (localName.equals("level")) {
            states.level = false;
        }
        if (states.level) {
            if (localName.equals("component")) {
                states.component = false;
            } else if (localName.equals("widget")) {
                states.widget = false;
            }
        }
        if (states.component) {
            if (localName.equals("domino")) {

                states.domino = false;
            } else if (localName.equals("cog")) {
                states.cog = false;
            } else if (localName.equals("ball")) {
                states.ball = false;
            } else if (localName.equals("ground")) {
                states.ground = false;
            } else if (localName.equals("water")) {
                states.water = false;
            }
        } else if (states.widget) {
            if (localName.equals("adddomino")) {
                states.addDomino = false;
            } else if (localName.equals("addball")) {
                states.addBall = false;
            }
        }
    }

    /**
     * Calling when we're within an element. Here we're checking to see if there
     * is any content in the tags that we're interested in and populating it in
     * the Config object. <p> On ne devrait pas avoir à utiliser cette section.
     *
     * @param ch
     * @param start
     * @param length
     */
    @Override
    public void characters(char ch[], int start, int length) {
        //String chars = new String(ch, start, length);
        //chars = chars.trim();
    }
}
