/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Point;
import com.gmxteam.funkydomino.physics.box2d.ContactManager;
import org.andengine.audio.music.MusicManager;
import org.andengine.audio.sound.SoundManager;
import org.andengine.engine.camera.SmoothCamera;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.detector.PinchZoomDetector;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.IGameInterface;

/**
 *
 * @author guillaume
 */
public interface IBaseGameActivity extends IGameInterface {

    
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
