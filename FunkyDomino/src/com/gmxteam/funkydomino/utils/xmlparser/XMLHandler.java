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

import com.gmxteam.funkydomino.activities.AndEngineActivity;
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
 *
 * @author Guillaume Poirier-Morency
 */
public final class XMLHandler extends DefaultHandler {

    private long startedTime;
    // booleans that check whether it's in a specific tag or not 
    private boolean inComponent;
    private boolean inWidget, inLevel;
    /* Ces booléens ne sont pas utilisés, car le contenu de leurs balises n'est
     * pas encore bien défini. 
     */
    private boolean inDomino, inCog, inWater, inBall, inGround, inAddBall, inAddDomino;
    // this holds the data 
    /**
     * @see GameInformation
     */
    private GameInformation gameInformationData;
    /**
     * @see AndEngineActivity
     */
    private AndEngineActivity mAndEngineActivity;

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
    public XMLHandler(AndEngineActivity aea) {
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
     * This gets called at the start of an element. Here we're also setting the booleans to true if it's at that specific tag. (so we 
     * know where we are) 
     * 
     * @param namespaceURI 
     * @param localName 
     * @param qName 
     * @param atts 
     * @throws SAXException 
     */
    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        Log.v("funky-domino", "On entre dans la balise " + localName + ".");
        if (inComponent) {
            if (mAndEngineActivity == null) {
                return;
            }
            if (localName.equals("domino")) {
                mAndEngineActivity.mScene.attachChild(new Domino(mAndEngineActivity, atts));
                Log.v("funky-domino", "Adding a domino to the scene.");
                inDomino = true;

            } else if (localName.equals("cog")) {
                mAndEngineActivity.mScene.attachChild(new Cog(mAndEngineActivity, atts));
                Log.v("funky-domino", "Adding a cog to the scene.");
                inCog = true;
            } else if (localName.equals("ball")) {
                mAndEngineActivity.mScene.attachChild(new Ball(mAndEngineActivity, atts));
                Log.v("funky-domino", "Adding a ball to the scene.");
                inBall = true;
            } else if (localName.equals("ground")) {
                mAndEngineActivity.mScene.attachChild(new Ground(mAndEngineActivity, atts));
                Log.v("funky-domino", "Adding a ground to the scene.");
                inGround = true;
            } else if (localName.equals("water")) {
                mAndEngineActivity.mScene.attachChild(new Water(mAndEngineActivity, atts));
                Log.v("funky-domino", "Adding water to the scene.");
                inWater = true;
            }
        } else if (inWidget) {
            if (mAndEngineActivity.mScene == null) {
                return;
            }
            if (localName.equals("addball")) {
                mAndEngineActivity.mScene.attachChild(new AddBall(mAndEngineActivity, atts));
                Log.v("funky-domino", "Adding a addball widget to the scene.");
                inAddBall = true;

            } else if (localName.equals("adddomino")) {
                mAndEngineActivity.mScene.attachChild(new AddDomino(mAndEngineActivity, atts));
                Log.v("funky-domino", "Adding a adddomino widget to the scene.");
                inAddDomino = true;
            }
        }


        if (inLevel) {
            if (localName.equals("component")) {
                Component.loadResource(mAndEngineActivity, atts);
                gameInformationData.componentTheme = atts.getValue("theme");
                inComponent = true;
            } else if (localName.equals("widget")) {
                Widget.loadResource(mAndEngineActivity, atts);
                gameInformationData.widgetTheme = atts.getValue("theme");
                inWidget = true;
            }
        }

        if (localName.equals("level")) {
            inLevel = true;
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
     * Called at the end of the element. Setting the booleans to false, so we know that we've just left that tag. 
     * 
     * @param namespaceURI 
     * @param localName 
     * @param qName 
     * @throws SAXException 
     */
    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        Log.v("funky-domino", "On sort de la balise " + localName + ".");



        if (localName.equals("level")) {
            inLevel = false;

        }
        if (inLevel) {
            if (localName.equals("component")) {

                inComponent = false;
            } else if (localName.equals("widget")) {

                inWidget = false;
            }
        }

        if (inComponent) {
            if (localName.equals("domino")) {

                inDomino = false;
            } else if (localName.equals("cog")) {
                inCog = false;
            } else if (localName.equals("ball")) {
                inBall = false;
            } else if (localName.equals("ground")) {
                inGround = false;
            } else if (localName.equals("water")) {
                inWater = false;
            }
        } else if (inWidget) {
            if (localName.equals("adddomino")) {
                inAddDomino = false;
            } else if (localName.equals("addcog")) {
                inAddBall = false;
            }
        }
    }

    /** 
     * Calling when we're within an element. Here we're checking to see if there is any content in the tags that we're interested in 
     * and populating it in the Config object. 
     * <p>
     * On ne devrait pas avoir à utiliser cette section.
     * @param ch 
     * @param start 
     * @param length 
     */
    @Override
    public void characters(char ch[], int start, int length) {
        String chars = new String(ch, start, length);
        chars = chars.trim();

    }
}
