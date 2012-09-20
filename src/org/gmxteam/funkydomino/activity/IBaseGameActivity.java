/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gmxteam.funkydomino.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Point;
import org.gmxteam.funkydomino.physics.box2d.ContactManager;
import org.andengine.audio.music.MusicManager;
import org.andengine.audio.sound.SoundManager;
import org.andengine.engine.camera.SmoothCamera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.physics.box2d.PhysicsWorld;
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
     * @param pHUD
     */
    public void setHUD(HUD pHUD);
    
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
    public Point getDrawableSurfaceDimensions();

    /**
     *
     * @return
     */
    public VertexBufferObjectManager getVertexBufferObjectManager();
}
