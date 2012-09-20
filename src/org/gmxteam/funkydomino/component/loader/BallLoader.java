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
package org.gmxteam.funkydomino.component.loader;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import org.andengine.entity.IEntity;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.gmxteam.funkydomino.activity.FunkyDominoActivity;
import org.gmxteam.funkydomino.activity.IBaseGameActivity;
import org.gmxteam.funkydomino.component.ComponentAttributes;
import org.gmxteam.funkydomino.component.entity.Ball;
import org.gmxteam.funkydomino.component.loader.util.FunkyDominoEntityLoaderData;

/**
 *
 * @author guillaume
 */
public class BallLoader extends ComponentLoader {

    /**
     *
     * @param pBaseGameActivity
     */
    public BallLoader(IBaseGameActivity pBaseGameActivity) {
        super(pBaseGameActivity);
        mBitmapTextureAtlas = new BitmapTextureAtlas(pBaseGameActivity.getTextureManager(), Ball.BALL_RADIUS * 2, Ball.BALL_RADIUS * 2, FunkyDominoActivity.TEXTURE_OPTION);
        mTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pBaseGameActivity.getContext(), "ball.png", 0, 0);
        pBaseGameActivity.getTextureManager().loadTexture(mBitmapTextureAtlas);
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
        final Ball d = new Ball(pAttributes, mTextureRegion, pEntityLoaderData.getBaseGameActivity().getVertexBufferObjectManager());
        final Body b = PhysicsFactory.createCircleBody(pEntityLoaderData.getBaseGameActivity().getPhysicsWorld(), d, BodyType.DynamicBody, mFixtureDef);
        pEntityLoaderData.getBaseGameActivity().getPhysicsWorld().registerPhysicsConnector(new PhysicsConnector(d,b));
        return d;
    }

    /**
     *
     * @return
     */
    public String[] getEntityNames() {
        final String[] names = {"ball"};
        return names;
    }
}
