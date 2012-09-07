/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.core;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.gmxteam.funkydomino.activity.GameActivity;
import com.gmxteam.funkydomino.core.component.factory.ComponentAttributes;
import com.gmxteam.funkydomino.entity.primitive.Line;
import org.andengine.entity.IEntity;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.util.level.IEntityLoader;
import org.xml.sax.Attributes;

/**
 *
 * @author guillaume
 */
public class SceneLoader implements IEntityLoader {
    
    public SceneLoader(GameActivity pGameActivity) {
        mGameActivity = pGameActivity;
        
    }
    private GameActivity mGameActivity;
    
    public IEntity onLoadEntity(String string, Attributes atts) {

            ComponentAttributes ea = new ComponentAttributes(atts);

            // Boxing the scene

            FixtureDef limitsFixtureDef = PhysicsFactory.createFixtureDef(1.0f, 1.0f, 1.0f);

            float[][] lines = {
                {0.0f, 0.0f, ea.getFloat("width", GameActivity.WORLD_WIDTH), 0.0f},
                {ea.getFloat("width",  GameActivity.WORLD_WIDTH), 0.0f, ea.getFloat("width",  GameActivity.WORLD_WIDTH), ea.getFloat("height", GameActivity.WORLD_HEIGHT)},
                {ea.getFloat("width",  GameActivity.WORLD_WIDTH), ea.getFloat("height",  GameActivity.WORLD_HEIGHT), 0.0f, ea.getFloat("height", GameActivity.WORLD_HEIGHT)},
                {0.0f, ea.getFloat("height",  GameActivity.WORLD_HEIGHT), 0.0f, 0.0f}
            };

            for (float[] points : lines) {
 
                Body lineBody = PhysicsFactory.createLineBody(mGameActivity.mPhysicsWorld, points[0], points[1], points[2], points[3], limitsFixtureDef);
                Line lineShape = new Line(points[0], points[1], points[2], points[3], mGameActivity.getVertexBufferObjectManager());
                mGameActivity.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(lineShape, lineBody, false, false));
                mGameActivity.mScene.attachChild(lineShape);

            }

            // Refresh camera bounds
            mGameActivity.mCamera.setBounds(0.0f, 0.0f, ea.getFloat("width", GameActivity.WORLD_WIDTH), ea.getFloat("height", GameActivity.WORLD_HEIGHT));



            return  mGameActivity.mScene;
        }
    
}
