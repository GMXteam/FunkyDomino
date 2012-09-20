/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gmxteam.funkydomino.component.loader;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import org.gmxteam.funkydomino.activity.FunkyDominoActivity;
import org.gmxteam.funkydomino.component.ComponentAttributes;
import org.andengine.entity.IEntity;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.scene.background.RepeatingSpriteBackground;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.util.debug.Debug;
import org.gmxteam.funkydomino.activity.IBaseGameActivity;
import org.gmxteam.funkydomino.component.loader.util.FunkyDominoEntityLoaderData;

/**
 *
 * @author guillaume
 */
public class SceneLoader extends ComponentLoader {

    /**
     *
     * @param pBaseGameActivity
     */
    public SceneLoader(IBaseGameActivity pBaseGameActivity) {
        super(pBaseGameActivity);
    }
    private final static String[] ENTITY_NAMES = {"level"};

    /**
     *
     * @return
     */
    public String[] getEntityNames() {
        return ENTITY_NAMES;
    }

    /**
     *
     * @param pEntityName
     * @param pParent
     * @param pAttributes
     * @param pEntityLoaderData
     * @return
     */
    @Override
    public IEntity onLoadEntity(String pEntityName, IEntity pParent, ComponentAttributes pAttributes, FunkyDominoEntityLoaderData pEntityLoaderData) {
        Debug.v("La scène est de dimension "
                + pAttributes.getFloat("width", pEntityLoaderData.getBaseGameActivity()
                .getDrawableSurfaceDimensions().x)
                + " de largeur par "
                + pAttributes.getFloat("width", pEntityLoaderData.getBaseGameActivity()
                .getDrawableSurfaceDimensions().y)
                + " de hauteur.");


        // Background


        mBitmapTextureAtlas = new BitmapTextureAtlas(pEntityLoaderData.getBaseGameActivity()
                .getTextureManager(), pAttributes.getInteger("background_width", 32), pAttributes.getInteger("background_height", 32), FunkyDominoActivity.TEXTURE_OPTION);

        pEntityLoaderData.getBaseGameActivity()
                .getTextureManager().loadTexture(mBitmapTextureAtlas);

        mTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pEntityLoaderData.getBaseGameActivity()
                .getContext(), pAttributes.getString("background", "background.png"), 0, 0);



        final RepeatingSpriteBackground mBackground = new RepeatingSpriteBackground(pEntityLoaderData.getBaseGameActivity()
                .getDrawableSurfaceDimensions().x, pEntityLoaderData.getBaseGameActivity()
                .getDrawableSurfaceDimensions().y, mTextureRegion, pEntityLoaderData.getBaseGameActivity()
                .getVertexBufferObjectManager());



        pEntityLoaderData.getBaseGameActivity()
                .getScene().setBackground(mBackground);
        // Boxing the scene

        final FixtureDef limitsFixtureDef = PhysicsFactory.createFixtureDef(1.0f, 1.0f, 1.0f);


        final float HEIGHT = pAttributes.getFloat("height", pEntityLoaderData.getBaseGameActivity()
                .getDrawableSurfaceDimensions().y),
                WIDTH = pAttributes.getFloat("width", pEntityLoaderData.getBaseGameActivity()
                .getDrawableSurfaceDimensions().x);


        float[][] lines = {
            {0.0f, 0.0f, WIDTH, 0.0f},
            {WIDTH, 0.0f, WIDTH, HEIGHT},
            {WIDTH, HEIGHT, 0.0f, HEIGHT},
            {0.0f, HEIGHT, 0.0f, 0.0f}
        };

        for (float[] points : lines) {

            final Line lineShape = new Line(points[0], points[1], points[2], points[3], pEntityLoaderData.getBaseGameActivity()
                    .getVertexBufferObjectManager());

            final Body lineBody = PhysicsFactory.createLineBody(pEntityLoaderData.getBaseGameActivity()
                    .getPhysicsWorld(), lineShape, limitsFixtureDef);

            pEntityLoaderData.getBaseGameActivity()
                    .getPhysicsWorld().registerPhysicsConnector(new PhysicsConnector(lineShape, lineBody));
            pEntityLoaderData.getBaseGameActivity()
                    .getScene().attachChild(lineShape);

        }

        // Refresh camera bounds
        pEntityLoaderData.getBaseGameActivity()
                .getCamera().setBounds(0.0f, 0.0f, pAttributes.getFloat("width", pEntityLoaderData.getBaseGameActivity()
                .getDrawableSurfaceDimensions().x), pAttributes.getFloat("height", pEntityLoaderData.getBaseGameActivity()
                .getDrawableSurfaceDimensions().y));



        return pEntityLoaderData.getBaseGameActivity()
                .getScene();
    }
}
