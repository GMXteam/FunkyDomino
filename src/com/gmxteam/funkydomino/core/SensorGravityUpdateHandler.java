/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.core;

import android.hardware.SensorManager;
import com.badlogic.gdx.math.Vector2;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.extension.physics.box2d.PhysicsWorld;

/**
 *
 * @author guillaume
 */
public class SensorGravityUpdateHandler implements IUpdateHandler {
    
    private PhysicsWorld mPhysicsWorld;
    
    private Vector2 mGravityVector;
    
    SensorManager mSensorManager;
    
    public SensorGravityUpdateHandler(PhysicsWorld pPhysicsWorld) {
        mPhysicsWorld = pPhysicsWorld;
    }

    public void onUpdate(float f) {
        
        
        
        mPhysicsWorld.setGravity(mGravityVector);
    }

    public void reset() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
