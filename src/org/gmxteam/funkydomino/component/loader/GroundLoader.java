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

import java.util.HashMap;
import org.andengine.entity.IEntity;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.gmxteam.funkydomino.activity.FunkyDominoActivity;
import org.gmxteam.funkydomino.activity.IBaseGameActivity;
import org.gmxteam.funkydomino.component.ComponentAttributes;
import org.gmxteam.funkydomino.component.IComponent;
import org.gmxteam.funkydomino.component.entity.Ground;
import org.gmxteam.funkydomino.component.entity.GroundParcel;
import org.gmxteam.funkydomino.component.loader.util.FunkyDominoEntityLoaderData;

/**
 *
 * @author guillaume
 */
public class GroundLoader extends ComponentLoader {

    private HashMap<String, TextureRegion> mParcelTextureRegion = new HashMap<String, TextureRegion>();
    private final static String[] TEXTURE_REGION_NAMES = {"ground1", "ground2", "ground3"};

    /**
     * Chargeur du sol.
     *
     * Les sols sont tous chargés sur le même texture atlas.
     *
     * @param pBaseGameActivity
     */
    public GroundLoader(final IBaseGameActivity pBaseGameActivity) {

        mBitmapTextureAtlas = new BitmapTextureAtlas(pBaseGameActivity.getTextureManager(), GroundParcel.GROUND_WIDTH * TEXTURE_REGION_NAMES.length, GroundParcel.GROUND_HEIGHT, FunkyDominoActivity.TEXTURE_OPTION);
        int textureCounter = 0;
        for (String name : TEXTURE_REGION_NAMES) {
            final TextureRegion aTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pBaseGameActivity.getContext(), "ground/" + name + ".png", GroundParcel.GROUND_WIDTH * textureCounter, 0);
            mParcelTextureRegion.put(name, aTextureRegion);
            textureCounter++;
        }



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
    public IComponent onLoadEntity(String pEntityName, IEntity pParent, ComponentAttributes pAttributes, FunkyDominoEntityLoaderData pEntityLoaderData) {
        final IComponent e;

        if (pEntityName.equals("ground")) {
            e = new Ground(pAttributes);

        } else {
            // Must be a parcel
            // Parent must be a ground entity
            assert pParent instanceof Ground;
            e = new GroundParcel(pAttributes, mParcelTextureRegion.get(pAttributes.getString("type", "ground1")), pEntityLoaderData.getVertexBufferObjectManager());

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
