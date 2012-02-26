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
package com.gmxteam.funkydomino.activities;

import android.util.Log;
import com.gmxteam.funkydomino.graphicals.Graphical;
import com.gmxteam.funkydomino.graphicals.components.Domino;
import com.gmxteam.funkydomino.graphicals.components.Ground;
import com.gmxteam.funkydomino.utils.xmlparser.AndEngineActivityXMLParser;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import org.andengine.entity.scene.Scene;
import org.xml.sax.SAXException;

/**
 * Classe principale de l'application Android.
 * Elle sera construite avec AndEngine.
 * @author Guillaume Poirier-Morency
 */
public final class MainActivity extends AndEngineActivity {

    /**
     * InputStream contenant le niveau qui sera joué. Il est dans MainActivity
     * pour des raisons de tests. 
     * TODO Il sera déplacé dans GameActivity lorsque le programme sera plus 
     * fonctionnel.
     */
    private InputStream levelStream;

    /**
     * Chargement des ressources du programme (images, textes, etc...).
     */
    public void onLoadResources() {
        levelStream = this.getResources().openRawResource(R.raw.stage1);
    }

    /**
     * 
     * @param pScene
     * @param pOnPopulateSceneCallback 
     */
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) {
        ////////////////////////////////////////////////////////////////////////
        // Code utilisant le système XML
        String publickey = getString(R.string.key_0)
                + getString(R.string.key_1)
                + getString(R.string.key_2)
                + getString(R.string.key_3)
                + getString(R.string.key_4)
                + getString(R.string.key_5)
                + getString(R.string.key_6)
                + getString(R.string.key_7);

        try {
            AndEngineActivityXMLParser.buildGameInstance(this, levelStream, publickey);
            levelStream.close();
        } catch (ParserConfigurationException ex) {
            Log.e(APP_LOG_NAME, "Parser configuration has crashed !", ex);
        } catch (SAXException ex) {
            Log.e(APP_LOG_NAME, "Parser has crashed ! There's an error in the level file !", ex);
        } catch (IOException ex) {
            Log.e(APP_LOG_NAME, "May be due to closing the stream or accessing it !", ex);
        }

        ////////////////////////////////////////////////////////////////////////
        // Code de test
        float[] x = {};
        float[] y = {};
        pScene.attachChild(new Ground(this, x, y));

        pScene.attachChild(new Domino(this, 25.0f,25.0f));



        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }
    
    /**
     * Création des ressources.
     * @param pOnCreateResourcesCallback est un callback à utiliser pour avertir
     * AndEngine que les ressources ont été créée.
     * @throws Exception 
     */
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception {
        Domino.loadResource(this);
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }
}
