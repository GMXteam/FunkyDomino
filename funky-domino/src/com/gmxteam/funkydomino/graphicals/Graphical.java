/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.graphicals;

import android.graphics.Canvas;
import android.view.MotionEvent;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;

/**
 *
 * @author Usager
 */
public abstract class Graphical{
    
    public BodyDef bodyDef = new BodyDef();
    public Body body;

    
    
   

    public abstract void drawGL();
    
    public abstract void drawCanvas(Canvas c);

    public abstract void onClick(MotionEvent me);   

    
    
}
