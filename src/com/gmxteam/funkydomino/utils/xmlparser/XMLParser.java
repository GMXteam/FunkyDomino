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

import android.app.Activity;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.andengine.entity.scene.Scene;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Classe statique contenant les fonctions utiles pour convertir les niveaux
 * encrypté asymétriquement en Activity.
 *
 * @author Guillaume Poirier-morency
 */
public final class XMLParser {

	public static void inflate(Scene ga, InputStream ressource) throws ParserConfigurationException, SAXException, IOException {

		final SAXParserFactory spf = SAXParserFactory.newInstance();
		final SAXParser sp = spf.newSAXParser();
		final XMLReader xr = sp.getXMLReader();
		final XMLHandler xh = new XMLHandler(ga);
		xr.setContentHandler(xh);

		// On parse le fichier de jeu directement dans l'activité
		xr.parse(new InputSource(ressource));

	}
	

	
	
}
