/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Point;
import com.gmxteam.funkydomino.core.ContactManager;
import org.andengine.audio.music.MusicManager;
import org.andengine.audio.sound.SoundManager;
import org.andengine.engine.camera.SmoothCamera;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.IGameInterface;

/**
 *
 * @author guillaume
 */
public interface IFunkyDominoBaseActivity extends IGameInterface {
    
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
    public static final float CAMERA_MAX_VELOCITY_X =  500.0f;
    /**
     *
     */
    public static final float CAMERA_MAX_VELOCITY_Y =  CAMERA_MAX_VELOCITY_X;
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
    
    
    
    
    /**
     *
     * @return
     */
    public AssetManager getAssets();
    
    /**
     *
     * @return
     */
    public SoundManager getSoundManager();
    
    /**
     *
     * @return
     */
    public SmoothCamera getCamera();
    
    /**
     *
     * @return
     */
    public MusicManager getMusicManager();
    
    /**
     *
     * @return
     */
    public Context getContext();
    
    /**
     *
     * @return
     */
    public PhysicsWorld getPhysicsWorld();
    
    /**
     *
     * @return
     */
    public Scene getScene();
    
    /**
     *
     * @return
     */
    public ContactManager getContactManager();
    
    /**
     *
     * @return
     */
    public TextureManager getTextureManager();
    
    /**
     *
     * @return
     */
    public Point getCameraDimensions();
    
    /**
     *
     * @return
     */
    public VertexBufferObjectManager getVertexBufferObjectManager();
    
}
