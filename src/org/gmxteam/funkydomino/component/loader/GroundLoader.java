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

import org.andengine.entity.IEntity;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
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

    /**
     *
     * @param pba
     */
    public GroundLoader(IBaseGameActivity pba) {
        super(pba);
        mBitmapTextureAtlas = new BitmapTextureAtlas(pba.getTextureManager(), Ground.GROUND_WIDTH, Ground.GROUND_HEIGHT, FunkyDominoActivity.TEXTURE_OPTION);
        mTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pba.getContext(), "ground/ground1.png", 0, 0);
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
            e = new Ground(pAttributes, mBitmapTextureAtlas, 16, pEntityLoaderData.getBaseGameActivity().getVertexBufferObjectManager());

        } else {
            // Must be a parcel
            e = new GroundParcel(pAttributes, mTextureRegion, pEntityLoaderData.getVertexBufferObjectManager());

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
