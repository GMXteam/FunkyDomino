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

import android.hardware.SensorManager;
import com.badlogic.gdx.math.Vector2;
import com.gmxteam.funkydomino.utils.xmlparser.GameActivityXMLParser;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.xml.sax.SAXException;

/**
 * Classe principale de l'application Android.
 * Elle sera construite avec AndEngine.
 * @author Guillaume Poirier-Morency
 */
public final class MainActivity extends AndEngineActivity {

    /**
     * 
     * @return
     */
    public Engine onLoadEngine() {
        mPhysicsWorld = new PhysicsWorld(new Vector2(0, SensorManager.GRAVITY_EARTH), false);
        mEngine = new Engine(mEngineOptions);
        return mEngine;
    }

    /**
     * 
     */
    public void onLoadResources() {
    }

    /**
     * 
     * @return
     */
    public Scene onLoadScene() {
        mScene = new Scene();
        return mScene;
    }

    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception {
    }

    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws Exception {
    }

    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) {
        try {
            GameActivityXMLParser.buildGameInstance(this, pScene, pOnPopulateSceneCallback, R.raw.stage1);
        } catch (ParserConfigurationException ex) {
        } catch (SAXException ex) {
        } catch (IOException ex) {
        }
    }
}
