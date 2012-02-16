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
import com.gmxteam.funkydomino.activities.R;
import java.io.IOException;
import java.io.InputStream;
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
public final class GameActivityXMLParser {

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
     * @return une activité Android pour la partie à jouer !
     */
    public static GameActivity buildGameInstance(GameActivity ga, int resourceId) {
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();
            XMLHandler xh = new XMLHandler(ga);
            xr.setContentHandler(xh);
            xr.parse(new InputSource(decrypt(ga, resourceId)));
            return ga;
        } catch (ParserConfigurationException ex) {
            Log.v("funky-domino", "", ex);            
        } catch (SAXException ex) {
            Log.v("funky-domino", "", ex);
        } catch (IOException ioe) {
            Log.v("funky-domino", "", ioe);
        }
        return null;
    }

    /**
     * Récupère un dictionnaire contenant les informations générales du fichier
     * XML. Permet une récupération rapide sans génération de code. 
     * Des constantes sont définies afin de récupérer les bonnes informations.
     * @param ga 
     * @param resourceId 
     * @return 
     */
    public static GameInformation obtainGameInformations(Activity ga, int resourceId) {

        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            XMLReader xr = sp.getXMLReader();
            XMLHandler xh = new XMLHandler();
            xr.setContentHandler(xh);
            xr.parse(new InputSource(decrypt(ga, resourceId)));
            return xh.getGameInformation();
        } catch (ParserConfigurationException ex) {
            Log.v("funky-domino", "", ex);
        } catch (SAXException ex) {
            Log.v("funky-domino", "", ex);
        } catch (IOException ioe) {
            Log.v("funky-domino", "", ioe);
        }


        return null;
    }

    /**
     * 
     * @param ga
     * @param resourceId     
     * @return 
     */
    private static InputStream decrypt(Activity ga, int resourceId) {
        // On récupère la clé publique...
        String publickey = ga.getString(R.string.key_0)
                + ga.getString(R.string.key_1)
                + ga.getString(R.string.key_2)
                + ga.getString(R.string.key_3)
                + ga.getString(R.string.key_4)
                + ga.getString(R.string.key_5)
                + ga.getString(R.string.key_6)
                + ga.getString(R.string.key_7);
        // On décrypte le niveau avec cette clé...


        // On retourne l'inputstream du niveau décrypté

        return ga.getResources().openRawResource(resourceId);


    }
}
