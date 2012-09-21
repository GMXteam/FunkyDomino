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
import java.util.HashMap;
import org.andengine.entity.IEntity;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.gmxteam.funkydomino.activity.FunkyDominoActivity;
import org.gmxteam.funkydomino.activity.IBaseGameActivity;
import org.gmxteam.funkydomino.component.ComponentAttributes;
import org.gmxteam.funkydomino.component.entity.Ground;
import org.gmxteam.funkydomino.component.entity.GroundParcel;
import org.gmxteam.funkydomino.component.loader.util.FunkyDominoEntityLoaderData;

/**
 *
 * @author guillaume
 */
public class GroundLoader extends ComponentLoader {

    private HashMap<String, TextureRegion> mParcelTextureRegion = new HashMap<String, TextureRegion>();
    private HashMap<String, BitmapTextureAtlas> mParcelBitmapTextureAtlas = new HashMap<String, BitmapTextureAtlas>();
    private final static String[] TEXTURE_REGION_NAMES = {"ground1", "ground2", "ground3"};

    /**
     *
     * @param pba
     */
    public GroundLoader(IBaseGameActivity pba) {

        mBitmapTextureAtlas = new BitmapTextureAtlas(pba.getTextureManager(), GroundParcel.GROUND_WIDTH, GroundParcel.GROUND_HEIGHT, FunkyDominoActivity.TEXTURE_OPTION);

        for (String name : TEXTURE_REGION_NAMES) {
            final BitmapTextureAtlas aBitmapTextureAtlas = new BitmapTextureAtlas(pba.getTextureManager(), GroundParcel.GROUND_WIDTH, GroundParcel.GROUND_HEIGHT, FunkyDominoActivity.TEXTURE_OPTION);
            final TextureRegion aTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(aBitmapTextureAtlas, pba.getContext(), "ground/" + name + ".png", 0, 0);
            mParcelBitmapTextureAtlas.put(name, aBitmapTextureAtlas);
            mParcelTextureRegion.put(name, aTextureRegion);
            pba.getTextureManager().loadTexture(aBitmapTextureAtlas);
        }

        pba.getTextureManager().loadTexture(mBitmapTextureAtlas);
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
        final IEntity e;

        if (pEntityName.equals("ground")) {
            e = new Ground(pAttributes);

        } else {
            // Must be a parcel
            // Parent must be a ground entity
            assert pParent instanceof Ground;
            e = new GroundParcel(pAttributes, mParcelTextureRegion.get(pAttributes.getString("type", "ground1")), pEntityLoaderData.getVertexBufferObjectManager());
            final Body body = ((GroundParcel) e).onCreateBody(pEntityLoaderData.getPhysicsWorld(), mFixtureDef);
            pEntityLoaderData.getPhysicsWorld().registerPhysicsConnector(new PhysicsConnector(e, body));
        }


        return e;

    }

    /**
     *
     * @return
     */
    public String[] getEntityNames() {
        final String[] names = {"ground", "parcel"};

        return names;
    }
}
