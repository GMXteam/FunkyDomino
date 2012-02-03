/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.utils.xmlparser;

import com.gmxteam.funkydomino.activities.GameActivity;
import java.io.File;
import java.util.HashMap;

/**
 *
 * @author Guillaume Poirier-morency
 */
public class XMLParser
{
    
    private static String publicKey = "";
    
    
    
    // D'autres constantes seront définies en fonction de la structure XML choisie.
    public static Integer INFO_APP_NAME = 0,
            INFO_APP_DESCRIPTION = 1;
    public static GameActivity obtainGameInstance(File f) {
    
    
        return null;
    } 
    
    /**
     * Récupère un dictionnaire contenant les informations générales du fichier
     * XML. Permet une récupération rapide sans génération de code. 
     * Des constantes sont définies afin de récupérer les bonnes informations.
     * @param f
     * @return 
     */
    public static HashMap<Integer, String> obtainInformations(File f) {
    
        return null;
    }
    
}
