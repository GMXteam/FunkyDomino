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
package org.gmxteam.funkydomino.component.entity;

import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.gmxteam.funkydomino.activity.FunkyDominoActivity;
import org.gmxteam.funkydomino.component.Component;
import org.gmxteam.funkydomino.physics.box2d.ContactManager;

/**
 * Objet définissant le sol. Il est composé de deux entités. Un sprite png pour
 * le rendu final et un Mesh pour voir la représentation vectorielle du polygone
 * de collision. Le sprite png est de 100 pixels par 100 pixels. Il peut prendre
 * la forme qu'il veut.
 *
 * @author Guillaume Poirier-Morency
 */
public class Ground extends Component {

    private Sprite mGround;
    private TextureRegion mGroundTextureRegion;
    public static final int GROUND_WIDTH = 128, GROUND_HEIGHT = 128;

    /**
     *
     */
    @Override
    protected void onLoadResource() {
        final BitmapTextureAtlas mBitmapTextureAtlas = new BitmapTextureAtlas(getBaseGameActivity().getTextureManager(), GROUND_WIDTH,GROUND_HEIGHT, FunkyDominoActivity.TEXTURE_OPTION);


        getBaseGameActivity().getTextureManager().loadTexture(mBitmapTextureAtlas);


        mGroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, getBaseGameActivity().getContext(), "ground/ground1.png", 0, 0);


    }

    /**
     *
     * @param pX
     * @param pY
     * @param angle
     * @return
     */
    @Override
    protected Entity onCreateEntity(float pX, float pY, float angle) {
        mGround = new Sprite(pX, pY, GROUND_WIDTH, GROUND_HEIGHT, mGroundTextureRegion, getBaseGameActivity().getVertexBufferObjectManager());




        return mGround;
    }

    @Override
    protected void onPopulatePhysicsWorld(PhysicsWorld pPhysicsWorld) {
    }

    /**
     *
     * @param pContactManager
     */
    @Override
    protected void onRegisterContactListener(ContactManager pContactManager) {
    }

    @Override
    protected void onRegisterTouchAreas(Scene pScene) {
    }
}
