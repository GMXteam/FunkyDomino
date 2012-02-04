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

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

/**
 * Classe abstraite permettant une implémentation efficace d'un interface JBox2D. 
 * @author Guillaume Poirier-Morency
 */
public abstract class JBox2DActivity extends Activity {
    public int targetFPS = 40;  
    public int timeStep = (1000 / targetFPS);  
    public int iterations = 5;  
  
    private Body[] bodies;  
    private int count = 0;  
  
    private AABB worldAABB;  
    protected World world;      
    private Handler mHandler;
    
    private Runnable update = new Runnable() {  
        public void run() {  
            update();  
            mHandler.postDelayed(update, (long) (timeStep*1000));  
        }  
    };
    
    protected void create() {
    // Step 1: Create Physics World Boundaries  
        worldAABB = new AABB();  
        worldAABB.lowerBound.set(new Vec2((float) -100.0, (float) -100.0));  
        worldAABB.upperBound.set(new Vec2((float) 100.0, (float) 100.0));  
        
        
        
         
        mHandler = new Handler();  
        mHandler.post(update);  
    
    
    
    }
    
     private void update() {  
        // Update Physics World  
        world.step(1.0f,timeStep, iterations);  
  
        // Print info of latest body  
        if (count > 0) {  
            Vec2 position = bodies[count].getPosition();  
            float angle = bodies[count].getAngle();  
            Log.v("Physics Test", "Pos: (" + position.x + ", " + position.y + "), Angle: " + angle);  
        } 
    
    }
     
}
