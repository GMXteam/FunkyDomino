/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.core;

import com.badlogic.gdx.math.Vector2;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.sensor.orientation.IOrientationListener;
import org.andengine.input.sensor.orientation.OrientationData;
import org.andengine.util.debug.Debug;

/**
 *
 * @author guillaume
 */
public class GravityUpdateHandler implements IOrientationListener {

    private final GravityUpdateMode mGravityUpdateMode;

    public enum GravityUpdateMode {

        SCREEN_IS_VERTICAL,
        SCREEN_IS_HORIZONTAL;
    }
    private PhysicsWorld mPhysicsWorld;

    public GravityUpdateHandler(PhysicsWorld pPhysicsWorld) {
        this(pPhysicsWorld, GravityUpdateMode.SCREEN_IS_VERTICAL);
    }

    public GravityUpdateHandler(PhysicsWorld pPhysicsWorld, GravityUpdateMode pGravityUpdateMode) {
        mGravityUpdateMode = pGravityUpdateMode;
        mPhysicsWorld = pPhysicsWorld;
        mGravity = pPhysicsWorld.getGravity();
        
    }
    private Vector2 mGravity;

    public void onOrientationAccuracyChanged(OrientationData pOrientationData) {
    }

    public void onOrientationChanged(OrientationData pOrientationData) {
        switch (mGravityUpdateMode) {
            case SCREEN_IS_VERTICAL:
                this.mGravity.x = pOrientationData.getRoll();
                this.mGravity.y = - pOrientationData.getPitch();
                break;
            case SCREEN_IS_HORIZONTAL:
                this.mGravity.x = pOrientationData.getYaw();
                this.mGravity.y = pOrientationData.getPitch();
                break;

        }
        
        Debug.v("New gravity : " + mGravity);

        mPhysicsWorld.setGravity(this.mGravity);
    }
}
