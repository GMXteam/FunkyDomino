/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.graphicals.components;

import android.view.MotionEvent;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

/**
 *
 * @author Usager
 */
public abstract class Component extends Body {

    private ComponentData data;
    
    public Component(BodyDef bd, World w) {


        super(bd, w);
    }

    @Override
    public Object getUserData() {


        return data;

    }

    public abstract void draw();
    
    public abstract void onClick(MotionEvent me);
}
