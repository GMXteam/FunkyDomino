/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gmxteam.funkydomino.component.loader;

import org.andengine.entity.IEntity;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.gmxteam.funkydomino.activity.FunkyDominoActivity;
import org.gmxteam.funkydomino.activity.IBaseGameActivity;
import org.gmxteam.funkydomino.component.ComponentAttributes;
import org.gmxteam.funkydomino.component.ComponentFactory;
import org.gmxteam.funkydomino.component.entity.AddDominoButton;
import org.gmxteam.funkydomino.component.entity.Ball;
import org.gmxteam.funkydomino.component.loader.util.FunkyDominoEntityLoaderData;

/**
 *
 * @author guillaume
 */
public class BallLoader extends ComponentLoader {

    public BallLoader(IBaseGameActivity pBaseGameActivity) {
        super(pBaseGameActivity);
        mBitmapTextureAtlas = new BitmapTextureAtlas(pBaseGameActivity.getTextureManager(), Ball.BALL_RADIUS * 2, Ball.BALL_RADIUS * 2, FunkyDominoActivity.TEXTURE_OPTION);
        mTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pBaseGameActivity.getContext(), "ball.png", 0, 0);
        pBaseGameActivity.getTextureManager().loadTexture(mBitmapTextureAtlas);
    }

    @Override
    public IEntity onLoadEntity(String pEntityName, IEntity pParent, ComponentAttributes pAttributes, FunkyDominoEntityLoaderData pEntityLoaderData) {
        final Ball d = new Ball(pAttributes, mTextureRegion, pEntityLoaderData.getBaseGameActivity().getVertexBufferObjectManager());

        return d;
    }

    public String[] getEntityNames() {
        final String[] names = {"cog"};
        return names;
    }
}
