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

import android.graphics.Color;
import android.view.MotionEvent;
import com.gmxteam.funkydomino.activities.JBox2DCanvasActivity;
import org.jbox2d.collision.PolygonDef;

import org.jbox2d.collision.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.xml.sax.Attributes;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public class Domino extends Component {

    /**
     * 
     * @param w
     * @param att  
     */
    public Domino(World w, Attributes att) {

        bodyDef.massData.mass = 95.5f; // 95.5 kg
        bodyDef.position = new Vec2(50.0f, 50.0f);

        body = w.createBody(bodyDef);

        PolygonDef pd = new PolygonDef();
        pd.setAsBox(0.0508f, 0.009525f);
        Shape s = body.createShape(pd);

        //body.setMassFromShapes();
        body.setUserData(this);
    }

    /**
     * 
     * @param w
     */
    public Domino(World w, Vec2 position) {
        paint.setColor(Color.BLACK);
        bodyDef.massData.mass = 95.5f; // 95.5 kg
        bodyDef.position = position;

        body = w.createBody(bodyDef);

        PolygonDef pd = new PolygonDef();
        pd.setAsBox(0.0508f, 0.009525f);
        Shape s = body.createShape(pd);

        //body.setMassFromShapes();
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
        c.drawRect(JBox2DCanvasActivity.toPixelX(body.getPosition().x),
                JBox2DCanvasActivity.toPixelY(body.getPosition().y + 25.0f),
                JBox2DCanvasActivity.toPixelX(body.getPosition().x + 25.0f),
                JBox2DCanvasActivity.toPixelY(body.getPosition().y),
                paint);

    }

    /**
     * 
     * @param c
     */
    @Override
    public void drawDebug(Canvas c) {
        float initP = JBox2DCanvasActivity.toPixelY(body.getPosition().y + 25.0f) - 15.0f;
        float positionX = JBox2DCanvasActivity.toPixelX(body.getPosition().x + 25.0f) + 5.0f;
        //c.drawText("INFO SUR LE DOMINO QUI NOUS INTÉRESSE", positionX, initP += 15.0f, paint);
        c.drawText("Position du Domino : " + body.getPosition(), positionX, initP += 15.0f, paint);
        c.drawText("Masse du Domino : " + body.m_mass + " kg", positionX, initP += 15.0f, paint);
    }

    /**
     * 
     */
    @Override
    public void drawGLDebug() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
