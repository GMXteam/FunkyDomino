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

import android.util.FloatMath;
import com.gmxteam.funkydomino.component.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.gmxteam.funkydomino.activity.FunkyDominoActivity;
import com.gmxteam.funkydomino.physics.box2d.ContactManager;
import org.andengine.entity.Entity;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.util.debug.Debug;
import org.andengine.util.math.MathUtils;

/**
 * Objet générant une roue dentée.
 *
 * @see Component
 * @author Guillaume Poirier-Morency
 */
public final class Cog extends Component {

    /**
     *
     */
    public static final int COG_RADIUS = 64,
            /**
             *
             */
            COG_TEETH_HEIGHT = 10,
            /**
             *
             */
            COG_TEETH_WIDTH = 20,
            /**
             *
             */
            COG_TEETH_COUNT = 8;
    /**
     *
     */
    public static final float COG_MOTOR_SPEED = 300.0f,
            /**
             *
             */
            COG_MOTOR_MAX_TORQUE = 1000.0f;
    private Body mCogBody;
    private Body[] mCogToothBodies = new Body[COG_TEETH_COUNT];
    private Rectangle[] mCogToothRectangles = new Rectangle[COG_TEETH_COUNT];
    private Sprite mCogSprite;
    private TextureRegion mCogTextureRegion;

    /**
     *
     */
    @Override
    protected void onLoadResource() {

        BitmapTextureAtlas mBitmapTextureAtlas = new BitmapTextureAtlas(getTextureManager(), COG_RADIUS * 2, COG_RADIUS * 2, FunkyDominoActivity.TEXTURE_OPTION);
        getTextureManager().loadTexture(mBitmapTextureAtlas);

        mCogTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, getContext(), "cog.png", 0, 0);




    }

    /**
     *
     * @param pX
     * @param pY
     * @param angle
     */
    @Override
    protected Entity onCreateEntity(float pX, float pY, float angle) {

        mCogSprite = new Sprite(pX, pY, mCogTextureRegion, getVertexBufferObjectManager());
        mCogSprite.setRotation(angle);

        for (int i = 0; i < mCogToothRectangles.length; i++) {

            final float theta = ((2 * (float) Math.PI) / COG_TEETH_COUNT) * (i);
            final float hypothenuse = COG_RADIUS;
            
            final float toothX = (hypothenuse *  FloatMath.cos(theta)) + COG_RADIUS;
            final float toothY = (hypothenuse *  FloatMath.sin(theta)) + COG_RADIUS;

            Debug.v("Teeth position : [" + toothX + ",", +toothY + "] with initial rotation " + theta + " rad.");

            mCogToothRectangles[i] = new Rectangle(toothX, toothY, COG_TEETH_WIDTH, COG_TEETH_HEIGHT, getVertexBufferObjectManager());

            // On définit le centre de rotation au centre du rectangle.
            mCogToothRectangles[i].setRotation(MathUtils.radToDeg(theta + ((float)Math.PI / 2.0f)));            

            mCogSprite.attachChild(mCogToothRectangles[i]);

        }

        return mCogSprite;

    }

    @Override
    protected void onPopulatePhysicsWorld(PhysicsWorld pPhysicsWorld) {


        mCogBody = PhysicsFactory.createCircleBody(pPhysicsWorld, mCogSprite, BodyDef.BodyType.DynamicBody, mFixtureDef);
        pPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(mCogSprite, mCogBody, true, true));


        for (int i = 0; i < mCogToothRectangles.length; i++) {

            mCogToothBodies[i] = PhysicsFactory.createBoxBody(pPhysicsWorld, mCogToothRectangles[i], BodyDef.BodyType.DynamicBody, mFixtureDef);
            //pPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(mCogToothRectangles[i], mCogToothBodies[i], true, true));



            /*
             * |-----|
             * |  +  |
             * *------
             * 
             */
            // On lie le corps avec la dent en deux points...
            DistanceJointDef jd1 = new DistanceJointDef();
            Vector2 cogAnchor1 = mCogBody.getLocalCenter(),
                    toothAnchor1 = new Vector2(mCogToothBodies[i].localVector);
            jd1.initialize(mCogBody, mCogToothBodies[i], cogAnchor1, toothAnchor1);
            pPhysicsWorld.createJoint(jd1);

            /*
             * |-----|
             * |  +  |
             * ------*
             * 
             */
            DistanceJointDef jd2 = new DistanceJointDef();
            Vector2 cogAnchor2 = mCogBody.getLocalCenter(),
                    toothAnchor2 = new Vector2(mCogToothBodies[i].localVector.add(mCogToothBodies[i].getLocalCenter().mul(2f)));
            jd2.initialize(mCogBody, mCogToothBodies[i], cogAnchor2, toothAnchor2);
            pPhysicsWorld.createJoint(jd2);

            // On créé un moteur pour faire tourner le cog.

            RevoluteJointDef rjd = new RevoluteJointDef();

            rjd.initialize(mCogToothBodies[i], mCogBody, mCogBody.getLocalCenter());
            rjd.maxMotorTorque = COG_MOTOR_MAX_TORQUE;
            rjd.motorSpeed = COG_MOTOR_SPEED;


            pPhysicsWorld.createJoint(rjd);

        }

    }

    @Override
    protected void onRegisterTouchAreas(Scene pScene) {

        pScene.registerTouchArea(mCogSprite);

        for (Rectangle rectangleIterator : mCogToothRectangles) {
            pScene.registerTouchArea(rectangleIterator);
        }



    }

    /**
     *
     * @param pContactManager
     */
    @Override
    protected void onRegisterContactListener(ContactManager pContactManager) {
    }
}