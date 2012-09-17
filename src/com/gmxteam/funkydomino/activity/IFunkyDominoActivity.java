/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.activity;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.input.touch.detector.PinchZoomDetector;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.opengl.texture.TextureOptions;

/**
 *
 * @author guillaume
 */
public interface IFunkyDominoActivity extends IBaseGameActivity, ScrollDetector.IScrollDetectorListener, PinchZoomDetector.IPinchZoomDetectorListener {
    
    
    
    ////////////////////////////////////////////////////////////////////////////
    // Constantes publiques. Elles sont disponibles dans tout le projet.
    /**
     *
     */
    public static final String LOG_TAG = "FunkyDomino";
    /**
     *
     */
    public static final boolean DEBUG = true;
    ////////////////////////////////////////////////////////////////////////////
    // Constantes spécifiques aux activités
        public static final int PHYSIC_STEPS_PER_SECONDS = 30;

    /**
     *
     */
    public static final float CAMERA_LEFT = 0.0f;
    /**
     *
     */
    public static final float CAMERA_TOP = 0.0f;
    /**
     *
     */
    public static final float CAMERA_MAX_VELOCITY_X = 500.0f;
    /**
     *
     */
    public static final float CAMERA_MAX_VELOCITY_Y = CAMERA_MAX_VELOCITY_X;
    /**
     *
     */
    public static final float CAMERA_MAX_ZOOM_FACTOR_CHANGE = 2.0f;
    /**
     *
     */
    public static final float CAMERA_Z_NEAR = 10.0f,
            /**
             *
             */
            CAMERA_Z_FAR = 20.0f;
    /**
     *
     */
    public static final float WORLD_HEIGHT = 800.0f;
    /**
     *
     */
    public static final float WORLD_WIDTH = 5000.0f;
    /**
     *
     */
    public static final float WORLD_LEFT = 0.0f;
    /**
     *
     */
    public static final float WORLD_TOP = 0.0f;
    /**
     *
     */
    public static TextureOptions TEXTURE_OPTION = TextureOptions.REPEATING_BILINEAR_PREMULTIPLYALPHA;
    
    
    public void setHUD(HUD pHUD);
    public HUD getHUD();

    
}
