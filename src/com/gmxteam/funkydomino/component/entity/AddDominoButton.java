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
package com.gmxteam.funkydomino.component.entity;

import com.gmxteam.funkydomino.component.Component;
import com.gmxteam.funkydomino.activity.FunkyDominoActivity;
import com.gmxteam.funkydomino.component.ComponentFactory;
import com.gmxteam.funkydomino.physics.box2d.ContactManager;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.util.debug.Debug;

/**
 *
 * @author Usager
 */
public class AddDominoButton extends Component implements OnClickListener {

    ButtonSprite mAddDominoButtonSprite;
    /**
     *
     */
    public final int DOMINO_WIDTH = 32,
            /**
             *
             */
            DOMINO_HEIGHT = 64;
    private TextureRegion mDominoTextureRegion;

    /**
     *
     */
    @Override
    protected void onLoadResource() {

        BitmapTextureAtlas mBitmapTextureAtlas = new BitmapTextureAtlas(getTextureManager(), DOMINO_WIDTH, DOMINO_HEIGHT, FunkyDominoActivity.TEXTURE_OPTION);


        getTextureManager().loadTexture(mBitmapTextureAtlas);


        mDominoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, getContext(), "domino.png", 0, 0);



    }

    /**
     *
     * @param pX
     * @param pY
     * @param angle
     */
    @Override
    protected Entity onCreateEntity(float pX, float pY, float angle) {
        mAddDominoButtonSprite = new ButtonSprite(pX, pY, mDominoTextureRegion, this.getVertexBufferObjectManager());
        mAddDominoButtonSprite.setRotation(angle);
        mAddDominoButtonSprite.setOnClickListener(this);

        return mAddDominoButtonSprite;
    }

    /**
     *
     * @param ocl
     */
    public void setOnClickListener(OnClickListener ocl) {
        mAddDominoButtonSprite.setOnClickListener(ocl);
    }

    @Override
    protected void onPopulatePhysicsWorld(PhysicsWorld pPhysicsWorld) {
    }

    @Override
    protected void onRegisterTouchAreas(Scene pScene) {
        pScene.registerTouchArea(mAddDominoButtonSprite);
    }

    /**
     *
     * @param pContactManager
     */
    @Override
    protected void onRegisterContactListener(ContactManager pContactManager) {
    }

    /**
     *
     * @param bs
     * @param f
     * @param f1
     */
    public void onClick(ButtonSprite bs, float f, float f1) {
        try {
            mFunkyDominoBaseActivity.getScene().attachChild(ComponentFactory.createDomino(0.0f, 0.0f).getEntity());
        } catch (InstantiationException ex) {
            Debug.e(ex);
        } catch (IllegalAccessException ex) {
            Debug.e(ex);
        }


    }
}
