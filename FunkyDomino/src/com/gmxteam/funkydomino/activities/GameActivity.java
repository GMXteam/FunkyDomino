/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.activities;

import android.os.Bundle;
import android.util.Log;
import com.gmxteam.funkydomino.utils.xmlparser.AndEngineActivityXMLParser;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import org.andengine.entity.scene.Scene;
import org.xml.sax.SAXException;

/**
 *
 * @author guillaume
 */
public class GameActivity extends AndEngineActivity implements AndEngineActivityConstants {

    
    private InputStream levelStream;
    private int levelID;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        levelID = b.getInt(LEVEL_KEY_IN_BUNDLE);
    
    }
    
    /**
     * Chargement des ressources du programme (images, textes, etc...).
     */
    public void onLoadResources() {
        levelStream = this.getResources().openRawResource(levelID);
    }

    /**
     * 
     * @param pScene
     * @param pOnPopulateSceneCallback 
     */
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) {
        String publickey = getString(R.string.key_0)
                + getString(R.string.key_1)
                + getString(R.string.key_2)
                + getString(R.string.key_3)
                + getString(R.string.key_4)
                + getString(R.string.key_5)
                + getString(R.string.key_6)
                + getString(R.string.key_7);
        try {
            AndEngineActivityXMLParser.buildGameInstance(pScene, mPhysicsWorld, levelStream, publickey);
            levelStream.close();
        } catch (ParserConfigurationException ex) {
            Log.e(APP_LOG_NAME, "Parser configuration has crashed !", ex);
        } catch (SAXException ex) {
            Log.e(APP_LOG_NAME, "Parser has crashed !", ex);
        } catch (IOException ex) {
            Log.e(APP_LOG_NAME, "May be due to closing the stream or accessing it !", ex);
        }

    }
}
