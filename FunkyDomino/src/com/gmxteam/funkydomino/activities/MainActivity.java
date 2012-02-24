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
            AndEngineActivityXMLParser.buildGameInstance(pScene, mPhysicsWorld, levelStream, publickey);
            levelStream.close();
        } catch (ParserConfigurationException ex) {
            Log.e(APP_LOG_NAME, "Parser configuration has crashed !", ex);
        } catch (SAXException ex) {
            Log.e(APP_LOG_NAME, "Parser has crashed !", ex);
        } catch (IOException ex) {
            Log.e(APP_LOG_NAME, "May be due to closing the stream or accessing it !", ex);
        }

        ////////////////////////////////////////////////////////////////////////
        // Code de test
        float[] x = {};
        float[] y = {};
        pScene.attachChild(new Ground(mPhysicsWorld, x, y));




        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }
}
