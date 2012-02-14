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

/**
 * Activité principale de l'application Android.
 * Elle sera construite avec JBox2D et permettera d'accéder aux autres activités.
 * @author Guillaume Poirier-Morency
 */
public class MainActivity extends JBox2DCanvasActivity {
private Domino d;
    /** Called when the activity is first created.
     * @param savedInstanceState 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        d = new Domino(world);
    }

    @Override
    void drawActivityDebug(Canvas c , float initP, Paint p) {
        c.drawText("INFO SUR LE DOMINO QUI NOUS INTÉRESSE", 15.0f, initP += 15.0f, p);
        c.drawText("Position du Domino : " + d.body.getPosition(), 15.0f, initP += 15.0f, p);
        c.drawText("Masse du Domino : " + d.body.m_mass + " kg", 15.0f, initP += 15.0f, p);
        //c.drawText(" : " + fps + " fps", 15.0f, initP += 15.0f, p);
    }
}
