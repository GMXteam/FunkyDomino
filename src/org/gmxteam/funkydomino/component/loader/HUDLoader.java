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
import org.andengine.util.level.IEntityLoader;
import org.gmxteam.funkydomino.component.loader.util.FunkyDominoEntityLoaderData;
import org.xml.sax.Attributes;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public class HUDLoader implements IEntityLoader<FunkyDominoEntityLoaderData> {

    private final static String[] ENTITY_NAMES = {"hud"};

    /**
     *
     * @return
     */
    public String[] getEntityNames() {
        return ENTITY_NAMES;
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
    public IEntity onLoadEntity(String pEntityName, IEntity pParent, Attributes pAttributes, FunkyDominoEntityLoaderData pEntityLoaderData) {

        return new HUD();
    }
}
