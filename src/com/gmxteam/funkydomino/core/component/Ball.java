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

import com.gmxteam.funkydomino.core.component.factory.ComponentAttributes;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.gmxteam.funkydomino.activity.GameActivity;
import com.gmxteam.funkydomino.core.ContactManager;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;

/**
 *
 * @see Component
 * @author Guillaume Poirier-Morency
 */
public final class Ball extends Component implements ContactListener {

    private Body mBallBody;
    private Sprite mBallSprite;
    public static final int BALL_RADIUS = 32;    

    @Override
    protected void onLoadResource() {

        BitmapTextureAtlas mBitmapTextureAtlas = new BitmapTextureAtlas(getTextureManager(), BALL_RADIUS * 2, BALL_RADIUS * 2, GameActivity.TEXTURE_OPTION);
        getTextureManager().loadTexture(mBitmapTextureAtlas);

        TextureRegion mBallTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, getContext(), "ball.png", 0, 0);


        mBallSprite = new Sprite(0, 0, mBallTextureRegion, getVertexBufferObjectManager());
    }

    @Override
    protected void onCreateFixtureDef(FixtureDef fd, ComponentAttributes pAttributes) {
    }

    @Override
    protected void onPopulatePhysicsWorld(PhysicsWorld pPhysicsWorld, ComponentAttributes pAttributes) {
        mBallBody = PhysicsFactory.createCircleBody(pPhysicsWorld, mBallSprite, BodyDef.BodyType.DynamicBody, mFixtureDef);
        pPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(mBallSprite, mBallBody, true, true));
    }

    @Override
    protected void onPopulateEntity(Entity e) {

        e.attachChild(mBallSprite);
    }

    @Override
    protected void onRegisterTouchAreas(Scene pScene) {
        pScene.registerTouchArea(mBallSprite);
    }

    @Override
    protected void onRegisterContactListener(ContactManager pContactManager) {
        pContactManager.registerContactListener(mBallBody, this);
    }

    public void beginContact(Contact cntct) {
    }

    public void endContact(Contact cntct) {
    }

    public void preSolve(Contact cntct, Manifold mnfld) {
    }

    public void postSolve(Contact cntct, ContactImpulse ci) {
    
    }
}
