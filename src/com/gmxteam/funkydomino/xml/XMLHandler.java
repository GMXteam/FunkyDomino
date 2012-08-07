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

package com.gmxteam.funkydomino.xml;

import android.util.Log;
import com.gmxteam.funkydomino.activity.GameActivity;
import com.gmxteam.funkydomino.core.component.Components;
import com.gmxteam.funkydomino.core.factory.Factorable;
import java.util.LinkedList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 *
 * @author Guillaume Poirier-Morency
 */
final public class XMLHandler extends DefaultHandler {
	public LinkedList<Factorable> cachedComponents = new LinkedList<Factorable>();
	/**
	 * Entier contenant le temps de départ du parsing afin de mesurer le temps
	 * que l'opération va prendre.
	 */
	private long startedTime;
	// this holds the data 
	/**
	 * @see FunkyDominoActivity
	 */
	private GameActivity mGameActivity;

	/**
	 *
	 * @param aea
	 */
	public XMLHandler(GameActivity ga) {
		mGameActivity = ga;

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
			AttributesExtended atts2 = new AttributesExtended(atts);
			cachedComponents.add(Components.valueOf(localName).getComponent()
					.init(mGameActivity, atts2)
					.factory(atts2));
			
					

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
