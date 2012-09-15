/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.level.loader;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.gmxteam.funkydomino.activity.FunkyDominoActivity;
import com.gmxteam.funkydomino.activity.IBaseGameActivity;
import com.gmxteam.funkydomino.component.ComponentAttributes;
import java.io.IOException;
import org.andengine.entity.IEntity;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.scene.background.RepeatingSpriteBackground;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.source.AssetBitmapTextureAtlasSource;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.util.debug.Debug;
import org.andengine.util.level.IEntityLoader;
import org.andengine.util.level.IEntityLoaderData;
import org.xml.sax.Attributes;

/**
 *
 * @author guillaume
 */
public class SceneLoader implements IEntityLoader {

    /**
     *
     * @param pGameActivity
     */
    public SceneLoader(IBaseGameActivity pGameActivity) {
        mGameActivity = pGameActivity;

    }
    private final IBaseGameActivity mGameActivity;
    
    private final static String[] ENTITY_NAMES = {"level"};

    public String[] getEntityNames() {
        return ENTITY_NAMES;
    }

    public IEntity onLoadEntity(String pEntityName, IEntity pParent, Attributes pAttributes, IEntityLoaderData pEntityLoaderData) throws IOException {


        ComponentAttributes ea = new ComponentAttributes(pAttributes);

        Debug.v("La scène est de dimension "
                + ea.getFloat("width", mGameActivity.getCameraDimensions().x)
                + " de largeur par "
                + ea.getFloat("width", mGameActivity.getCameraDimensions().y)
                + " de hauteur.");


        // Background
        
        
        BitmapTextureAtlas mBitmapTextureAtlas = new BitmapTextureAtlas(mGameActivity.getTextureManager(), ea.getInteger("background_width", 32), ea.getInteger("background_height", 32), FunkyDominoActivity.TEXTURE_OPTION);

        mGameActivity.getTextureManager().loadTexture(mBitmapTextureAtlas);

        final TextureRegion mDominoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, mGameActivity.getContext(), ea.getString("background", "background.png"), 0, 0);


        
        final RepeatingSpriteBackground mBackground = new RepeatingSpriteBackground(mGameActivity.getCameraDimensions().x, mGameActivity.getCameraDimensions().y,  mDominoTextureRegion, mGameActivity.getVertexBufferObjectManager());

        
        
        mGameActivity.getScene().setBackground(mBackground);
        // Boxing the scene

        final FixtureDef limitsFixtureDef = PhysicsFactory.createFixtureDef(1.0f, 1.0f, 1.0f);


        final float HEIGHT = ea.getFloat("height", mGameActivity.getCameraDimensions().y),
                WIDTH = ea.getFloat("width", mGameActivity.getCameraDimensions().x);

        float[][] lines = {
            {0.0f, 0.0f, WIDTH, 0.0f},
            {WIDTH, 0.0f, WIDTH, HEIGHT},
            {WIDTH, HEIGHT, 0.0f, HEIGHT},
            {0.0f, HEIGHT, 0.0f, 0.0f}
        };

        for (float[] points : lines) {

            final Line lineShape = new Line(points[0], points[1], points[2], points[3], mGameActivity.getVertexBufferObjectManager());

            final Body lineBody = PhysicsFactory.createLineBody(mGameActivity.getPhysicsWorld(), lineShape, limitsFixtureDef);

            mGameActivity.getPhysicsWorld().registerPhysicsConnector(new PhysicsConnector(lineShape, lineBody, true, true));
            mGameActivity.getScene().attachChild(lineShape);

        }

        // Refresh camera bounds
        mGameActivity.getCamera().setBounds(0.0f, 0.0f, ea.getFloat("width", mGameActivity.getCameraDimensions().x), ea.getFloat("height", mGameActivity.getCameraDimensions().y));



        return mGameActivity.getScene();
    }
}
