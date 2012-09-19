/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gmxteam.funkydomino.component.loader;

import android.util.FloatMath;
import com.badlogic.gdx.physics.box2d.Body;
import org.andengine.entity.IEntity;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.util.debug.Debug;
import org.andengine.util.math.MathUtils;
import org.gmxteam.funkydomino.activity.FunkyDominoActivity;
import org.gmxteam.funkydomino.activity.IBaseGameActivity;
import org.gmxteam.funkydomino.component.ComponentAttributes;
import org.gmxteam.funkydomino.component.entity.Cog;
import org.gmxteam.funkydomino.component.entity.CogTeeth;
import org.gmxteam.funkydomino.component.loader.util.FunkyDominoEntityLoaderData;

/**
 *
 * @author guillaume
 */
public class CogLoader extends ComponentLoader {

    public CogLoader(IBaseGameActivity ga) {
        super(ga);
        mBitmapTextureAtlas = new BitmapTextureAtlas(ga.getTextureManager(), Cog.COG_TOTAL_RADIUS * 2, Cog.COG_TOTAL_RADIUS * 2, FunkyDominoActivity.TEXTURE_OPTION);
        mTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, ga.getContext(), "cog.png", 0, 0);
        ga.getTextureManager().loadTexture(mBitmapTextureAtlas);
    }

    @Override
    public IEntity onLoadEntity(String pEntityName, IEntity pParent, ComponentAttributes pAttributes, FunkyDominoEntityLoaderData pEntityLoaderData) {
        final Cog d = new Cog(pAttributes, mTextureRegion, Cog.COG_TEETH_COUNT + 1, pEntityLoaderData.getBaseGameActivity().getVertexBufferObjectManager());

        final Body b = d.onCreateBody(pEntityLoaderData.getBaseGameActivity().getPhysicsWorld(), mFixtureDef);


        pEntityLoaderData.getBaseGameActivity().getPhysicsWorld().registerPhysicsConnector(new PhysicsConnector(d, b));


        for (int i = 0; i < Cog.COG_TEETH_COUNT; i++) {

            final float theta = (360.0f / Cog.COG_TEETH_COUNT) * i;
            final float hypothenuse = Cog.COG_TOTAL_RADIUS - (0.5f * CogTeeth.COG_TEETH_HEIGHT);

            final float toothX = (hypothenuse * FloatMath.cos(MathUtils.degToRad(theta))) + Cog.COG_TOTAL_RADIUS;
            final float toothY = (hypothenuse * FloatMath.sin(MathUtils.degToRad(theta))) + Cog.COG_TOTAL_RADIUS;

            Debug.v("Teeth position : [" + toothX + ",", +toothY + "] with initial rotation " + theta + " rad.");
            final CogTeeth cogTeeth = new CogTeeth(toothX, toothY, pEntityLoaderData.getBaseGameActivity().getVertexBufferObjectManager());


            cogTeeth.setRotation(theta + (i % 2 == 0 ? 90.0f : 0.0f));


            d.attachChild(cogTeeth);

        }
        return d;
    }

    public String[] getEntityNames() {
        final String[] names = {"cog"};
        return names;
    }
}
