/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.core;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.gmxteam.funkydomino.activity.GameActivity;
import com.gmxteam.funkydomino.activity.IFunkyDominoBaseActivity;
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
    
    public SceneLoader(IFunkyDominoBaseActivity pGameActivity) {
        mGameActivity = pGameActivity;
        
    }
    private IFunkyDominoBaseActivity mGameActivity;
    
    public IEntity onLoadEntity(String string, Attributes atts) {

            ComponentAttributes ea = new ComponentAttributes(atts);

            // Boxing the scene

            FixtureDef limitsFixtureDef = PhysicsFactory.createFixtureDef(1.0f, 1.0f, 1.0f);

            float[][] lines = {
                {0.0f, 0.0f, ea.getFloat("width", mGameActivity.getCameraDimensions().x), 0.0f},
                {ea.getFloat("width",  mGameActivity.getCameraDimensions().x), 0.0f, ea.getFloat("width",  mGameActivity.getCameraDimensions().x), ea.getFloat("height", mGameActivity.getCameraDimensions().y)},
                {ea.getFloat("width",  mGameActivity.getCameraDimensions().x), ea.getFloat("height",  mGameActivity.getCameraDimensions().y), 0.0f, ea.getFloat("height", mGameActivity.getCameraDimensions().y)},
                {0.0f, ea.getFloat("height",  mGameActivity.getCameraDimensions().y), 0.0f, 0.0f}
            };

            for (float[] points : lines) {
 
                Body lineBody = PhysicsFactory.createLineBody(mGameActivity.getPhysicsWorld(), points[0], points[1], points[2], points[3], limitsFixtureDef);
                Line lineShape = new Line(points[0], points[1], points[2], points[3], mGameActivity.getVertexBufferObjectManager());
                mGameActivity.getPhysicsWorld().registerPhysicsConnector(new PhysicsConnector(lineShape, lineBody, false, false));
                mGameActivity.getScene().attachChild(lineShape);

            }

            // Refresh camera bounds
            mGameActivity.getCamera().setBounds(0.0f, 0.0f, ea.getFloat("width", mGameActivity.getCameraDimensions().x), ea.getFloat("height", mGameActivity.getCameraDimensions().y));



            return  mGameActivity.getScene();
        }
    
}
