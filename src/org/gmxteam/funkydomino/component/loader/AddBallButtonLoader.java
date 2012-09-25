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

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.IEntity;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.gmxteam.funkydomino.activity.FunkyDominoActivity;
import org.gmxteam.funkydomino.activity.IBaseGameActivity;
import org.gmxteam.funkydomino.component.ComponentAttributes;
import org.gmxteam.funkydomino.component.IComponent;
import org.gmxteam.funkydomino.component.entity.AddBallButton;
import org.gmxteam.funkydomino.component.entity.AddDominoButton;
import org.gmxteam.funkydomino.component.entity.Domino;
import org.gmxteam.funkydomino.component.loader.util.FunkyDominoEntityLoaderData;

/**
 * Chargeur pour le bouton d'ajout de domino.
 *
 * @author Guillaume Poirier-Morency <guillaumepoiriermorency@gmail.com>
 */
public class AddBallButtonLoader extends ComponentLoader {

    /**
     *
     * @param pGameActivity
     */
    public AddBallButtonLoader(IBaseGameActivity pGameActivity) {
        mBitmapTextureAtlas = new BitmapTextureAtlas(pGameActivity.getTextureManager(), 2 * 2 * AddBallButton.BALL_RADIUS, 2 * AddBallButton.BALL_RADIUS, FunkyDominoActivity.TEXTURE_OPTION);
        mTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pGameActivity.getContext(), "ball.png", 0, 0);
        // On met la deuxième région de texture sur la deuxième moitié de l'atlas
        mTextureRegionClicked = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pGameActivity.getContext(), "ball.png", 2 * AddBallButton.BALL_RADIUS, 0);
        pGameActivity.getTextureManager().loadTexture(mBitmapTextureAtlas);

    }
    private ITextureRegion mTextureRegionClicked;

    @Override
    public IComponent onLoadEntity(String pEntityName, IEntity pParent, ComponentAttributes pAttributes, FunkyDominoEntityLoaderData pEntityLoaderData) {
        assert pParent instanceof HUD;
        return new AddBallButton(pAttributes, mTextureRegion, mTextureRegionClicked, pEntityLoaderData.getVertexBufferObjectManager());
    }

    /**
     *
     * @return
     */
    public String[] getEntityNames() {
        final String[] names = {"add_ball_button"};
        return names;
    }
}
