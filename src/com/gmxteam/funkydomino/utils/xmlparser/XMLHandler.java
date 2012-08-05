package com.gmxteam.funkydomino.utils.xmlparser;


import android.util.Log;
import java.util.logging.Logger;
import org.andengine.entity.scene.Scene;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

	/**
 * TODO Réécrire ce parser, il est crasse.
 *
 * @author Guillaume Poirier-Morency
 */
public final class XMLHandler extends DefaultHandler {

	/**
	 * Entier contenant le temps de départ du parsing afin de mesurer le temps
	 * que l'opération va prendre.
	 */
	private long startedTime;
	// this holds the data 
	/**
	 * @see FunkyDominoActivity
	 */
	private Scene mScene;

	/**
	 *
	 * @param aea
	 */
	public XMLHandler(Scene aea) {
		mScene = aea;
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
		try {
			FactorableXMLNodes.valueOf(localName).getFactorableNode().factory(atts).inflate(mScene);

		} catch (InstantiationException ex) {
			Logger.getLogger(XMLHandler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(XMLHandler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
	