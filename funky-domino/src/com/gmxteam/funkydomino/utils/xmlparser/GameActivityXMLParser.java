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


import com.gmxteam.funkydomino.activities.GameActivity;
import java.util.HashMap;


/**
 * Classe statique contenant les fonctions utiles pour convertir les niveaux
 * encrypté asymétriquement en Activity.
 * @author Guillaume Poirier-morency
 */
public class GameActivityXMLParser {  
    
    // D'autres constantes seront définies en fonction de la structure XML choisie.
    public static Integer INFO_APP_NAME = 0,
            INFO_APP_DESCRIPTION = 1;
    
    /**
     * Décode la ressource XML en entrée et l'interprète afin de générer le code
     * d'un niveau. Le GameActivity ainsi retourné est prêt à être joué !
     * @param f est une ressource XML.
     * @return une activité Android pour la partie à jouer !
     */
    public static GameActivity obtainGameInstance(String file, String key) {
        
       
        return null;
    } 
    
    /**
     * Récupère un dictionnaire contenant les informations générales du fichier
     * XML. Permet une récupération rapide sans génération de code. 
     * Des constantes sont définies afin de récupérer les bonnes informations.
     * @param f
     * @return 
     */
    public static HashMap<Integer, String> obtainGameInformations(String file, String key) {
    
        return null;
    }
    
}
