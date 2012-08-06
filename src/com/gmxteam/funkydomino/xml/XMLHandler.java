package com.gmxteam.funkydomino.xml;


import android.util.Log;
import com.gmxteam.funkydomino.core.component.Components;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

	/**
 * TODO Réécrire ce parser, il est crasse.
 *
 * @author Guillaume Poirier-Morency
 */
final class XMLHandler extends DefaultHandler {

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
	private PhysicsWorld mPhysicsWorld;

	/**
	 *
	 * @param aea
	 */
	public XMLHandler(Scene aea, PhysicsWorld pw) {
		mScene = aea;
		mPhysicsWorld = pw;
	}

	/**
	 * This gets called when the xml document is first opened
	 *
	 * @throws SAXException
	 */
	@Override
	public void startDocument() throws SAXException {
		startedTime = System.currentTimeMillis();
		Log.v("FunkyDomino", "Le parsing du fichier de niveau commence");

	}

	/**
	 * Called when it's finished handling the document
	 *
	 * @throws SAXException
	 */
	@Override
	public void endDocument() throws SAXException {
		Log.v("FunkyDomino", "Le parsing du fichier de niveau est terminé en " + (System.currentTimeMillis() - startedTime) + " ms.");
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
			Components.valueOf(localName).getComponent()
					.factory(atts)
					.inflateOnScene(mScene)
					.inflateOnPhysicsWorld(mPhysicsWorld);
			Log.d("FunkyDomino", "On rajoute un élément de type " + localName + " dans la scène et le monde physique.");
		} catch (InstantiationException ex) {
			Log.e("FunkyDomino", "La classe " + localName + " n'a pas pu être instanciée.", ex);
		} catch (IllegalAccessException ex) {
			Log.e("FunkyDomino", "La classe " + localName + " n'est pas accessible.", ex);

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
	