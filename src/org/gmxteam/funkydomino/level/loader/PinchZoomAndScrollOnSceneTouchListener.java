/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gmxteam.funkydomino.level.loader;

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
