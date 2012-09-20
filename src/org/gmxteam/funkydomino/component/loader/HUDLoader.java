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
import org.gmxteam.funkydomino.activity.IBaseGameActivity;
import org.gmxteam.funkydomino.component.ComponentAttributes;
import org.gmxteam.funkydomino.component.loader.util.FunkyDominoEntityLoaderData;

/**
 *
 * @author guillaume
 */
public class HUDLoader extends ComponentLoader {

    /**
     *
     * @param pGameActivity
     */
    public HUDLoader(IBaseGameActivity pGameActivity) {
        super(pGameActivity);
        
    }
    private final static String[] ENTITY_NAMES = {"hud"};

    /**
     *
     * @return
     */
    public String[] getEntityNames() {
        return ENTITY_NAMES;
    }
    
    @Override
    public IEntity onLoadEntity(String pEntityName, IEntity pParent, ComponentAttributes pAttributes, FunkyDominoEntityLoaderData pEntityLoaderData) {
        final HUD mHUD = new HUD();
        mHUD.setCamera(pEntityLoaderData.getBaseGameActivity().getCamera());
        pEntityLoaderData.getBaseGameActivity().setHUD(mHUD);
        return mHUD;
    }
}
