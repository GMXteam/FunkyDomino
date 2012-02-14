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
import android.view.MotionEvent;

import org.jbox2d.dynamics.World;
import org.xml.sax.Attributes;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public class Cog extends Component
{

    /**
     * 
     * @param w
     * @param atts  
     */
    public Cog(World w, Attributes atts) {


         body = w.createBody(bodyDef);  
        body.setUserData(this);  
    }
    
    @Override
    public void drawGL() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onClick(MotionEvent me) {
       
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void drawCanvas(Canvas c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

  
    
}
