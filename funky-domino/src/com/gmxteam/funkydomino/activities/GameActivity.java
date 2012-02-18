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

import com.gmxteam.funkydomino.utils.xmlparser.GameActivityXMLParser;

import android.os.Bundle;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public final class GameActivity extends AndEngineActivity {

    /**
     * 
     * @param ressources
     */
    @Override
    public void onCreate(Bundle ressources) {
        super.onCreate(ressources);
        GameActivityXMLParser.buildGameInstance(this, ressources.getInt("LEVEL"));
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
}
