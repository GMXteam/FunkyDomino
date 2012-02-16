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

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import com.gmxteam.funkydomino.graphicals.components.Domino;
import org.jbox2d.common.Vec2;

/**
 * Activité principale de l'application Android.
 * Elle sera construite avec JBox2D et permettera d'accéder aux autres activités.
 * @author Guillaume Poirier-Morency
 */
public final class MainActivity extends JBox2DCanvasActivity {

    /** Called when the activity is first created.
     * @param savedInstanceState 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //new Domino(world, new Vec2(100.0f, 100.0f));
        new Domino(world, new Vec2(100.0f, 150.0f));
        new Domino(world, new Vec2(100.0f, 200.0f));
       
       
    }

    @Override
    void drawActivityDebug(Canvas c , float initP, Paint p) {
        
        //c.drawText(" : " + fps + " fps", 15.0f, initP += 15.0f, p);
    }
    
    
}
