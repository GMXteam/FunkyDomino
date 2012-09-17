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
package org.gmxteam.funkydomino.physics.box2d;

import android.hardware.SensorManager;
import com.badlogic.gdx.math.Vector2;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.sensor.orientation.IOrientationListener;
import org.andengine.input.sensor.orientation.OrientationData;

/**
 * Listener pour actualiser la gravité du monde physique par rapport aux données
 * du senseur d'orientation.
 *
 * @author Guillaume Poirier-Morency
 */
public class GravityBasedOrientationListener implements IOrientationListener {

    private final GravityUpdateMode mGravityUpdateMode;
    private final float MAX_GRAVITY;
    private static final float MAX_PITCH = 90.0f,
            MAX_ROLL = 90.0f,
            MAX_YAW = 90.0f;
    /**
     * Sensitivité minimale.
     */
    private final int mSensitivity;
    /**
     * Gravité initiale.
     */
    private Vector2 mGravity;
    /**
     *
     */
    private PhysicsWorld mPhysicsWorld;

    /**
     *
     * @param pPhysicsWorld
     */
    public GravityBasedOrientationListener(PhysicsWorld pPhysicsWorld) {
        this(pPhysicsWorld, SensorManager.GRAVITY_EARTH, SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM, GravityUpdateMode.SCREEN_IS_VERTICAL);
    }

    /**
     *
     * @param pPhysicsWorld
     * @param pGravityUpdateMode
     */
    public GravityBasedOrientationListener(PhysicsWorld pPhysicsWorld, GravityUpdateMode pGravityUpdateMode) {
        this(pPhysicsWorld, SensorManager.GRAVITY_EARTH, SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM, pGravityUpdateMode);
    }

    /**
     *
     * @param pPhysicsWorld
     * @param pSensitivity
     */
    public GravityBasedOrientationListener(PhysicsWorld pPhysicsWorld, int pSensitivity) {
        this(pPhysicsWorld, SensorManager.GRAVITY_EARTH, pSensitivity, GravityUpdateMode.SCREEN_IS_VERTICAL);
    }

    /**
     *
     * @param pPhysicsWorld
     * @param pMaxGravity
     */
    public GravityBasedOrientationListener(PhysicsWorld pPhysicsWorld, float pMaxGravity) {
        this(pPhysicsWorld, pMaxGravity, SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM, GravityUpdateMode.SCREEN_IS_VERTICAL);
    }

    /**
     *
     * @param pPhysicsWorld
     * @param pMaxGravity
     * @param pSensitivity
     * @param pGravityUpdateMode
     */
    public GravityBasedOrientationListener(PhysicsWorld pPhysicsWorld, float pMaxGravity, int pSensitivity, GravityUpdateMode pGravityUpdateMode) {

        MAX_GRAVITY = pMaxGravity;
        mSensitivity = pSensitivity;
        mGravityUpdateMode = pGravityUpdateMode;
        mPhysicsWorld = pPhysicsWorld;
        mGravity = pPhysicsWorld.getGravity();

    }

    /**
     *
     * @param pOrientationData
     */
    public void onOrientationAccuracyChanged(OrientationData pOrientationData) {
    }

    /**
     *
     * @param pOrientationData
     */
    public void onOrientationChanged(OrientationData pOrientationData) {

        if (pOrientationData.getAccelerationAccuracy() < mSensitivity) {
            // Data not reliable
            return;
        }

        switch (mGravityUpdateMode) {
            case SCREEN_IS_VERTICAL:
                this.mGravity.x = MAX_GRAVITY * pOrientationData.getRoll() / MAX_ROLL;
                this.mGravity.y = MAX_GRAVITY * pOrientationData.getPitch() / MAX_PITCH;
                break;
            case SCREEN_IS_HORIZONTAL:
                this.mGravity.x = MAX_GRAVITY * pOrientationData.getYaw() / MAX_YAW;
                this.mGravity.y = MAX_GRAVITY * pOrientationData.getPitch() / MAX_PITCH;
                break;
        }

        mPhysicsWorld.setGravity(this.mGravity);
    }

    /**
     *
     */
    public enum GravityUpdateMode {

        /**
         *
         */
        SCREEN_IS_VERTICAL,
        /**
         *
         */
        SCREEN_IS_HORIZONTAL;
    }
}
