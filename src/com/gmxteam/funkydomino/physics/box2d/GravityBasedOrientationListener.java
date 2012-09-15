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
package com.gmxteam.funkydomino.physics.box2d;

import com.badlogic.gdx.math.Vector2;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.sensor.orientation.IOrientationListener;
import org.andengine.input.sensor.orientation.OrientationData;
import org.andengine.util.debug.Debug;

/**
 *
 * @author guillaume
 */
public class GravityBasedOrientationListener implements IOrientationListener {

    private final GravityUpdateMode mGravityUpdateMode;

    public enum GravityUpdateMode {

        SCREEN_IS_VERTICAL,
        SCREEN_IS_HORIZONTAL;
    }
    private PhysicsWorld mPhysicsWorld;

    public GravityBasedOrientationListener(PhysicsWorld pPhysicsWorld) {
        this(pPhysicsWorld, GravityUpdateMode.SCREEN_IS_VERTICAL);
    }

    public GravityBasedOrientationListener(PhysicsWorld pPhysicsWorld, GravityUpdateMode pGravityUpdateMode) {
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
