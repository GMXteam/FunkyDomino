/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gmxteam.funkydomino.component.loader;

import com.badlogic.gdx.physics.box2d.Body;
import org.andengine.entity.IEntity;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.gmxteam.funkydomino.activity.FunkyDominoActivity;
import org.gmxteam.funkydomino.activity.IBaseGameActivity;
import org.gmxteam.funkydomino.component.ComponentAttributes;
import org.gmxteam.funkydomino.component.entity.Domino;
import org.gmxteam.funkydomino.component.loader.util.FunkyDominoEntityLoaderData;

/**
 *
 * @author Usager
 */
public class DominoLoader extends ComponentLoader {

    /**
     *
     * @param pGameActivity
     */
    public DominoLoader(IBaseGameActivity pGameActivity) {
        super(pGameActivity);
        mBitmapTextureAtlas = new BitmapTextureAtlas(pGameActivity.getTextureManager(), Domino.DOMINO_WIDTH, Domino.DOMINO_HEIGHT, FunkyDominoActivity.TEXTURE_OPTION);
        mTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pGameActivity.getContext(), "domino.png", 0, 0);

        pGameActivity.getTextureManager().loadTexture(mBitmapTextureAtlas);
    }

    /**
     *
     * @return
     */
    public String[] getEntityNames() {
        final String[] mNames = {"domino"};
        return mNames;
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
        final Domino domino = new Domino(pAttributes, mTextureRegion, pEntityLoaderData.getBaseGameActivity().getVertexBufferObjectManager());
        final Body body = domino.onCreateBody(pEntityLoaderData.getBaseGameActivity().getPhysicsWorld(), mFixtureDef);
        pEntityLoaderData.getBaseGameActivity().getPhysicsWorld().registerPhysicsConnector(new PhysicsConnector(domino, body));
        pEntityLoaderData.getBaseGameActivity().getScene().registerTouchArea(domino);
        return domino;
    }
}
