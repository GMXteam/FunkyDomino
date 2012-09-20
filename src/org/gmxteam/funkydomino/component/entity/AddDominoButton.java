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

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import org.andengine.entity.IEntity;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.gmxteam.funkydomino.activity.FunkyDominoActivity;
import org.gmxteam.funkydomino.activity.IBaseGameActivity;
import org.gmxteam.funkydomino.component.IComponent;

/**
 *
 * @author Usager
 */
public class AddDominoButton extends ButtonSprite implements IComponent, OnClickListener {

    private static BitmapTextureAtlas mBitmapTextureAtlas;
    private static TextureRegion mTextureRegion;

    /**
     *
     * @param pGameActivity
     */
    public static void loadResources(IBaseGameActivity pGameActivity) {
        mBitmapTextureAtlas = new BitmapTextureAtlas(pGameActivity.getTextureManager(), AddDominoButton.DOMINO_WIDTH, AddDominoButton.DOMINO_HEIGHT, FunkyDominoActivity.TEXTURE_OPTION);
        mTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pGameActivity.getContext(), "domino.png", 0, 0);
        pGameActivity.getTextureManager().loadTexture(mBitmapTextureAtlas);
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public static IEntity createAddDominoButton(float x, float y) {
        throw new UnsupportedOperationException("Not supported yet.");
//
//        final AddDominoButton d = new AddDominoButton(x, y, mTextureRegion, mGameActivity.getVertexBufferObjectManager());
//        ComponentAttributes ca = ComponentAttributes.createBaseAttributes(x, y);
//        ca.putFloat("friction", 1.0f);
//        ca.putFloat("density", 1.0f);
//        return ComponentFactory.factory(d, ca);

    }

    ////////////////////////////////////////////////////////////////////////
    /**
     *
     * @param pX
     * @param pY
     * @param pTextureRegion
     * @param pVertexBufferObjectManager
     */
    public AddDominoButton(final float pX, final float pY, final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTextureRegion, pTextureRegion, pVertexBufferObjectManager);
    }
    /**
     *
     */
    public static final int DOMINO_WIDTH = 32,
            /**
             *
             */
            DOMINO_HEIGHT = 64;

    public Body onCreateBody(PhysicsWorld pPhysicsWorld, FixtureDef pFixtureDef) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param contact
     */
    public void beginContact(Contact contact) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param contact
     */
    public void endContact(Contact contact) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param contact
     * @param oldManifold
     */
    public void preSolve(Contact contact, Manifold oldManifold) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param contact
     * @param impulse
     */
    public void postSolve(Contact contact, ContactImpulse impulse) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param pButtonSprite
     * @param pTouchAreaLocalX
     * @param pTouchAreaLocalY
     */
    public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX, float pTouchAreaLocalY) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
