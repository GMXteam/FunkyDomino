/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.utils.xmlparser;

import android.util.Log;

import com.gmxteam.funkydomino.activities.GameActivity;
import com.gmxteam.funkydomino.graphicals.components.Ball;
import com.gmxteam.funkydomino.graphicals.components.Cog;
import com.gmxteam.funkydomino.graphicals.components.Domino;
import com.gmxteam.funkydomino.graphicals.components.Ground;
import com.gmxteam.funkydomino.graphicals.components.Water;

import com.gmxteam.funkydomino.graphicals.widgets.AddBall;
import com.gmxteam.funkydomino.graphicals.widgets.AddDomino;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author guillaume
 */
public class XMLHandler extends DefaultHandler {

    // booleans that check whether it's in a specific tag or not 
    private boolean inComponent;
    private boolean inWidget, inLevel;
    private boolean inDomino, inCog, inWater, inBall, inGround, inAddBall, inAddDomino;
    // this holds the data 
    private GameInformation gameInformationData;
    private GameActivity gameActivity;

    /**
     * 
     */
    public XMLHandler() {

        gameActivity = null;


    }

    /**
     * 
     * @param ga
     */
    public XMLHandler(GameActivity ga) {

        gameActivity = ga;

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
        gameInformationData = new GameInformation();
    }

    /** 
     * Called when it's finished handling the document 
     * 
     * @throws SAXException 
     */
    @Override
    public void endDocument() throws SAXException {
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
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {


        if (localName.equals("level")) {
            inLevel = true;
            gameInformationData.id = atts.getValue("id");
            gameInformationData.previousLevel = atts.getValue("previousLevel=");
            gameInformationData.nextLevel = atts.getValue("nextLevel");
            gameInformationData.name = atts.getValue("name");
            gameInformationData.description = atts.getValue("description");
        }

        if (inLevel) {
            if (localName.equals("component")) {
                gameInformationData.componentTheme = atts.getValue("theme");
                inComponent = true;
            } else if (localName.equals("widget")) {
                gameInformationData.widgetTheme = atts.getValue("theme");
                inWidget = true;
            }
        }


        if (inComponent) {
            if (gameActivity == null) {
                return;
            }
            if (localName.equals("domino")) {
                new Domino(gameActivity.world, atts);
                inDomino = true;

            } else if (localName.equals("cog")) {
                new Cog(gameActivity.world, atts);
                inCog = true;
            } else if (localName.equals("ball")) {
                new Ball(gameActivity.world, atts);
                inBall = true;
            } else if (localName.equals("ground")) {
                new Ground(gameActivity.world, atts);
                inGround = true;
            } else if (localName.equals("water")) {
                new Water(gameActivity.world, atts);
                inWater = true;
            }


        } else if (inWidget) {
            if (gameActivity == null) {
                return;
            }
            if (localName.equals("addball")) {
                new AddBall(gameActivity.world, atts);
                inDomino = true;

            } else if (localName.equals("adddomino")) {
                new AddDomino(gameActivity.world, atts);
                inCog = true;
            }
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
        Log.v("endElement", localName);



        if (localName.equals("level")) {
            inLevel = false;

        }

        if (inLevel) {
            if (localName.equals("component")) {

                inComponent = false;
            } else if (localName.equals("widget")) {

                inWidget = false;
            } else {
                throw new IllegalXMLNameException("Balise inconnue dans level.");
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
            } else {
                throw new IllegalXMLNameException("Balise inconnue dans les composants.");
            }
        } else if (inWidget) {
            if (localName.equals("adddomino")) {
                inAddDomino = false;
            } else if (localName.equals("addcog")) {
                inAddBall = false;
            } else {
                throw new IllegalXMLNameException("Balise inconnue dans les widgets.");
            }
        }
    }

    /** 
     * Calling when we're within an element. Here we're checking to see if there is any content in the tags that we're interested in 
     * and populating it in the Config object. 
     * 
     * On ne devrait pas avoir à utiliser cette section...
     * @param ch 
     * @param start 
     * @param length 
     */
    @Override
    public void characters(char ch[], int start, int length) {
        String chars = new String(ch, start, length);
        chars = chars.trim();

        if (inLevel) {
        } else if (inComponent) {
        } else if (inWidget) {
        }
    }
}
