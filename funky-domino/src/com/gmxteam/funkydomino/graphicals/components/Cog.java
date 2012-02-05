/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.graphicals.components;

import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

/**
 *
 * @author Usager
 */
public class Cog extends Component
{

    public Cog(BodyDef bd, World w) {


        super(bd, w);
    }
    
    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
