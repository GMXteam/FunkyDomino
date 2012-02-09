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
package com.gmxteam.funkydomino.graphicals.components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import org.jbox2d.collision.MassData;
import org.jbox2d.collision.PolygonDef;

import org.jbox2d.dynamics.World;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public class Domino extends Component {

    public Domino(World w) {
  
        bodyDef.massData.mass = 95.5f; // 95.5 kg
        body = w.createBody(bodyDef);
        
        PolygonDef pd = new PolygonDef();
        pd.setAsBox(0.0508f, 0.009525f);        
        body.createShape(pd);
        body.setMassFromShapes();
        body.setUserData(this);
    }

    @Override
    public void drawGL() {
        
    }

    @Override
    public void onClick(MotionEvent me) {
 
    }

    @Override
    public void drawCanvas(Canvas c) {
        c.drawRect(this.body.getPosition().x, this.body.getPosition().y, this.body.getPosition().x + 50, this.body.getPosition().x + 50, new Paint());
       c.drawText("", 100, 100, new Paint());
        
       
    }
}
