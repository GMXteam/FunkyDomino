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
package com.gmxteam.funkydomino.core.component;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.gmxteam.funkydomino.activity.GameActivity;
import com.gmxteam.funkydomino.core.ContactManager;
import com.gmxteam.funkydomino.core.component.factory.ComponentAttributes;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.util.debug.Debug;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public final class Domino extends Component implements ContactListener, IScrollDetectorListener {

    /**
     *
     */
    public static final int DOMINO_HEIGHT = 64,
            /**
             *
             */
            DOMINO_WIDTH = 32;
    private Body mDominoBody;
    private Sprite mDominoSprite;
    private ScrollDetector mScrollDetector;
    private TextureRegion mDominoTextureRegion;

    /**
     *
     */
    @Override
    protected void onLoadResource() {
        BitmapTextureAtlas mBitmapTextureAtlas = new BitmapTextureAtlas(getTextureManager(), DOMINO_WIDTH, DOMINO_HEIGHT, GameActivity.TEXTURE_OPTION);


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
    protected void onCreateSprite(float pX, float pY, float angle) {
        mScrollDetector = new ScrollDetector(this);



        mDominoSprite = new Sprite(pX, pY, mDominoTextureRegion, getVertexBufferObjectManager()) {
            /**
             * Gestion manuelle de l'événement.
             */
            @Override
            public boolean onAreaTouched(TouchEvent te, float f, float f1) {


                return mScrollDetector.onManagedTouchEvent(te);
            }
        };

        mDominoSprite.setRotation(angle);

    }

 

    @Override
    protected void onPopulatePhysicsWorld(PhysicsWorld pw) {
        mDominoBody = PhysicsFactory.createBoxBody(pw, mDominoSprite, BodyDef.BodyType.DynamicBody, mFixtureDef);
        pw.registerPhysicsConnector(new PhysicsConnector(mDominoSprite, mDominoBody, true, true));
    }

    @Override
    protected void onPopulateEntity(Entity e) {
        e.attachChild(mDominoSprite);
    }

    @Override
    protected void onRegisterTouchAreas(Scene pScene) {


        pScene.registerTouchArea(mDominoSprite);




    }

    ////////////////////////////////////
    // Collisions
    /**
     *
     * @param pContactManager
     */
    @Override
    protected void onRegisterContactListener(ContactManager pContactManager) {
        pContactManager.registerContactListener(mDominoBody, this);
    }

    /**
     *
     * @param cntct
     */
    public void beginContact(Contact cntct) {
        // mCollisionSound.play();
    }

    /**
     *
     * @param cntct
     */
    public void endContact(Contact cntct) {
    }

    /**
     *
     * @param cntct
     * @param mnfld
     */
    public void preSolve(Contact cntct, Manifold mnfld) {
    }

    /**
     *
     * @param cntct
     * @param ci
     */
    public void postSolve(Contact cntct, ContactImpulse ci) {
    }

    /**
     *
     * @param sd
     * @param i
     * @param f
     * @param f1
     */
    public void onScrollStarted(ScrollDetector sd, int i, float f, float f1) {
        mDominoBody.setActive(false);
    }

    /**
     *
     * @param sd
     * @param i
     * @param f
     * @param f1
     */
    public void onScroll(ScrollDetector sd, int i, float f, float f1) {
        // Make the domino follows the finger.
        mDominoBody.setTransform(f, f1, mDominoBody.getAngle());

        Debug.v("Domino position : " + mDominoBody.getPosition());
    }

    /**
     *
     * @param sd
     * @param i
     * @param f
     * @param f1
     */
    public void onScrollFinished(ScrollDetector sd, int i, float f, float f1) {
        mDominoBody.setActive(true);

    }
}
