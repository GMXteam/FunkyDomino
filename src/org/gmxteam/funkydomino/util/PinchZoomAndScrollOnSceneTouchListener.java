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
package org.gmxteam.funkydomino.util;

import org.andengine.engine.camera.SmoothCamera;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.PinchZoomDetector;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.util.debug.Debug;

/**
 *
 * @author guillaume
 */
public class PinchZoomAndScrollOnSceneTouchListener implements IOnSceneTouchListener, ScrollDetector.IScrollDetectorListener, PinchZoomDetector.IPinchZoomDetectorListener {

    private final SmoothCamera mCamera;
    private final PinchZoomDetector mPinchZoomDetector;
    private final ScrollDetector mScrollDetector;

    /**
     *
     * @param pCamera
     */
    public PinchZoomAndScrollOnSceneTouchListener(SmoothCamera pCamera) {
        mCamera = pCamera;
        mPinchZoomDetector = new PinchZoomDetector(this);
        mScrollDetector = new ScrollDetector(this);
    }

    /**
     *
     * @param pScene
     * @param pSceneTouchEvent
     * @return
     */
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
        return mScrollDetector.onManagedTouchEvent(pSceneTouchEvent) ^ mPinchZoomDetector.onManagedTouchEvent(pSceneTouchEvent);

    }

    /**
     *
     * @param pzd
     * @param te
     */
    public void onPinchZoomStarted(PinchZoomDetector pzd, TouchEvent te) {
    }

    /**
     *
     * @param pzd
     * @param te
     * @param pZoomFactor
     */
    public void onPinchZoom(PinchZoomDetector pzd, TouchEvent te, float pZoomFactor) {

        mCamera.setZoomFactor(pZoomFactor);
    }

    /**
     *
     * @param pzd
     * @param te
     * @param f
     */
    public void onPinchZoomFinished(PinchZoomDetector pzd, TouchEvent te, float f) {
        Debug.v("Nouveau facteur de zoom de la caméra : " + f + "x");

    }

    /**
     *
     * @param sd
     * @param i
     * @param f
     * @param f1
     */
    public void onScrollStarted(ScrollDetector sd, int i, float f, float f1) {
    }

    /**
     *
     * @param sd
     * @param i
     * @param f
     * @param f1
     */
    public void onScroll(ScrollDetector sd, int i, float f, float f1) {
        mCamera.setCenter(mCamera.getCenterX() - f, mCamera.getCenterY() - f1);
    }

    /**
     *
     * @param sd
     * @param i
     * @param f
     * @param f1
     */
    public void onScrollFinished(ScrollDetector sd, int i, float f, float f1) {
    }
}
