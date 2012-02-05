/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.graphicals.components;

import android.view.MotionEvent;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

/**
 *
 * @author Usager
 */
public class Domino extends Component
{
public Domino(BodyDef bd, World w) {


        super(bd, w);
    }
    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onClick(MotionEvent me) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
