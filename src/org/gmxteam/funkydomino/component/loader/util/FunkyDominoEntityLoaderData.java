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
package org.gmxteam.funkydomino.component.loader.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Point;
import org.andengine.audio.music.MusicManager;
import org.andengine.audio.sound.SoundManager;
import org.andengine.engine.camera.SmoothCamera;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.level.IEntityLoaderData;
import org.gmxteam.funkydomino.activity.IBaseGameActivity;
import org.gmxteam.funkydomino.activity.IBaseGameActivityResource;
import org.gmxteam.funkydomino.physics.box2d.ContactManager;

/**
 * Données qui sont transmises à la méthode onLoadEntity quand une entité est
 * chargée.
 *
 * @author guillaume
 */
public class FunkyDominoEntityLoaderData implements IEntityLoaderData, IBaseGameActivityResource {

    private final IBaseGameActivity mBaseGameActivity;


    public FunkyDominoEntityLoaderData(final IBaseGameActivity pBaseGameActivity) {
        this.mBaseGameActivity = pBaseGameActivity;
    }


    public VertexBufferObjectManager getVertexBufferObjectManager() {
        return this.mBaseGameActivity.getVertexBufferObjectManager();
    }

    public Scene getScene() {
        return this.mBaseGameActivity.getScene();
    }

    public PhysicsWorld getPhysicsWorld() {
        return this.mBaseGameActivity.getPhysicsWorld();
    }

    public IBaseGameActivity getBaseGameActivity() {
        return this.mBaseGameActivity;
    }

    public AssetManager getAssets() {
        return this.mBaseGameActivity.getAssets();

    }

    public SoundManager getSoundManager() {
        return this.mBaseGameActivity.getSoundManager();

    }

    public SmoothCamera getCamera() {
        return this.mBaseGameActivity.getCamera();

    }

    public MusicManager getMusicManager() {
        return this.mBaseGameActivity.getMusicManager();
    }

    public Context getContext() {
        return this.mBaseGameActivity.getContext();
    }

    public ContactManager getContactManager() {
        return this.mBaseGameActivity.getContactManager();
    }

    public TextureManager getTextureManager() {
        return this.mBaseGameActivity.getTextureManager();
    }

    public Point getDrawableSurfaceDimensions() {
        return this.mBaseGameActivity.getDrawableSurfaceDimensions();
    }
}
