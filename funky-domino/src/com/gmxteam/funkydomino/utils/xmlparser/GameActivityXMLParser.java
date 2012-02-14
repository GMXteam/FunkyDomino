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
import com.gmxteam.funkydomino.activities.GameActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * Classe statique contenant les fonctions utiles pour convertir les niveaux
 * encrypté asymétriquement en Activity.
 * @author Guillaume Poirier-morency
 */
public class GameActivityXMLParser {

    // D'autres constantes seront définies en fonction de la structure XML choisie.
    /**
     * 
     */
    /**
     * 
     */
    public static Integer INFO_APP_NAME = 0,
            INFO_APP_DESCRIPTION = 1;

    /**
     * Décode la ressource XML en entrée et l'interprète afin de générer le code
     * d'un niveau. Le GameActivity ainsi retourné est prêt à être joué !
     * @param ga 
     * @param resourceId 
     * @param key 
     * @return une activité Android pour la partie à jouer !
     */
    public static GameActivity buildGameInstance(GameActivity ga, int resourceId, String key) {
         try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();
            XMLHandler xh = new XMLHandler(ga);
            xr.setContentHandler(xh);
            xr.parse(new InputSource(ga.getResources().openRawResource(resourceId)));
            return ga;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GameActivityXMLParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(GameActivityXMLParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ioe) {
            Log.v("", key, ioe);
        }
        return null;
    }

    /**
     * Récupère un dictionnaire contenant les informations générales du fichier
     * XML. Permet une récupération rapide sans génération de code. 
     * Des constantes sont définies afin de récupérer les bonnes informations.
     * @param ga 
     * @param resourceId 
     * @param key 
     * @return 
     */
    public static GameInformation obtainGameInformations(Activity ga, int resourceId, String key) {
        
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();
            XMLHandler xh = new XMLHandler();
            xr.setContentHandler(xh);
            xr.parse(new InputSource(ga.getResources().openRawResource(resourceId)));
            return xh.getGameInformation();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(GameActivityXMLParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(GameActivityXMLParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ioe) {
            Log.v("", key, ioe);
        }


        return null;
    }
}
