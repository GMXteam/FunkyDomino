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
package org.gmxteam.funkydomino.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Point;
import org.andengine.audio.music.MusicManager;
import org.andengine.audio.sound.SoundManager;
import org.andengine.engine.camera.SmoothCamera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.text.Text;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.level.LevelLoader;
import org.gmxteam.funkydomino.component.loader.util.FunkyDominoLevelLoader;
import org.gmxteam.funkydomino.physics.box2d.ContactManager;

/**
 * Définit les ressources accessibles depuis l'implémentation d'une activité de
 * AndEngine dans FunkyDomino.
 *
 * @author Guillaume Poirier-Morency
 */
public interface IBaseGameActivityResource {

    /**
     *
     * @return
     */
    public HUD getHUD();

    /**
     * Chargeur de niveau.
     * @return 
     */
    public LevelLoader getLevelLoader();

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
     * Monde physique.
     * @return 
     */
    public PhysicsWorld getPhysicsWorld();

    /**
     * Scène contenant les entités.
     * @return 
     */
    public Scene getScene();

    /**
     * Gestionnaire de contact.
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
