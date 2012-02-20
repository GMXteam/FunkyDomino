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

import android.os.Bundle;
import org.andengine.engine.Engine;
import org.andengine.engine.options.EngineOptions;
import org.andengine.entity.scene.Scene;

/**
 * Activité principale de l'application Android.
 * Elle sera construite avec JBox2D et permettera d'accéder aux autres activités.
 * @author Guillaume Poirier-Morency
 */
public final class MainActivity extends AndEngineActivity {

    /** Called when the activity is first created.
     * @param savedInstanceState 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 
     * @return
     */
    public Engine onLoadEngine() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 
     */
    public void onLoadResources() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 
     * @return
     */
    public Scene onLoadScene() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 
     */
    public void onLoadComplete() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public EngineOptions onCreateEngineOptions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
