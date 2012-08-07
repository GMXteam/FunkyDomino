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

import com.gmxteam.funkydomino.activity.GameActivity;
import com.gmxteam.funkydomino.core.factory.Factorable;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * Classe statique contenant les fonctions utiles pour convertir les niveaux
 * encrypté asymétriquement en Activity.
 *
 * @author Guillaume Poirier-morency
 */
public final class XMLParser {
	
	private XMLHandler xh;
	
	public synchronized LinkedList<Factorable> getCachedComponents() {
		return xh.cachedComponents;
	}

	public void inflate(GameActivity ga,  InputStream ressource) throws SAXException, ParserConfigurationException, IOException {
		final SAXParserFactory spf = SAXParserFactory.newInstance();
		final SAXParser sp = spf.newSAXParser();
		final XMLReader xr = sp.getXMLReader();
		xh = new XMLHandler(ga);
		xr.setContentHandler(xh);

		// On essai au moins une autre fois si il y a une IOException.
		try {
			// On parse le fichier de jeu directement dans l'activité
			xr.parse(new InputSource(ressource));
		} catch (IOException ex) {
			xr.parse(new InputSource(ressource));
			
		}

	}
}
