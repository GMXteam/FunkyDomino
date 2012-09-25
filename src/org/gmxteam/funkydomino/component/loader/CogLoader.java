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

import android.util.FloatMath;
import org.andengine.entity.IEntity;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.util.debug.Debug;
import org.andengine.util.math.MathUtils;
import org.gmxteam.funkydomino.activity.FunkyDominoActivity;
import org.gmxteam.funkydomino.activity.IBaseGameActivity;
import org.gmxteam.funkydomino.component.ComponentAttributes;
import org.gmxteam.funkydomino.component.IComponent;
import org.gmxteam.funkydomino.component.entity.Cog;
import org.gmxteam.funkydomino.component.entity.CogTeeth;
import org.gmxteam.funkydomino.component.loader.util.FunkyDominoEntityLoaderData;

/**
 *
 * @author guillaume
 */
public class CogLoader extends ComponentLoader {

    /**
     *
     * @param ga
     */
    public CogLoader(IBaseGameActivity ga) {
        mBitmapTextureAtlas = new BitmapTextureAtlas(ga.getTextureManager(), Cog.COG_TOTAL_RADIUS * 2, Cog.COG_TOTAL_RADIUS * 2, FunkyDominoActivity.TEXTURE_OPTION);
        mTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, ga.getContext(), "cog.png", 0, 0);
        ga.getTextureManager().loadTexture(mBitmapTextureAtlas);
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
        final Cog d = new Cog(pAttributes, mTextureRegion, Cog.COG_TEETH_COUNT + 1, pEntityLoaderData.getBaseGameActivity().getVertexBufferObjectManager());





        for (int i = 0; i < Cog.COG_TEETH_COUNT; i++) {

            final float theta = (360.0f / Cog.COG_TEETH_COUNT) * i;
            final float hypothenuse = Cog.COG_TOTAL_RADIUS - (0.5f * CogTeeth.COG_TEETH_HEIGHT);

            final float toothX = (hypothenuse * FloatMath.cos(MathUtils.degToRad(theta))) + Cog.COG_TOTAL_RADIUS;
            final float toothY = (hypothenuse * FloatMath.sin(MathUtils.degToRad(theta))) + Cog.COG_TOTAL_RADIUS;

            Debug.v("Teeth position : [" + toothX + ",", +toothY + "] with initial rotation " + theta + " rad.");
            final CogTeeth cogTeeth = new CogTeeth(toothX, toothY, pEntityLoaderData.getVertexBufferObjectManager());


            cogTeeth.setRotation(theta + (i % 2 == 0 ? 90.0f : 0.0f));


            d.attachChild(cogTeeth);

        }
        return d;
    }

    /**
     *
     * @return
     */
    public String[] getEntityNames() {
        final String[] names = {"cog"};
        return names;
    }
}
