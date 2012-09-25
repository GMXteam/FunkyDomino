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
package org.gmxteam.funkydomino.component.loader.util;

import java.util.HashMap;
import org.andengine.util.level.IEntityLoader;
import org.andengine.util.level.IEntityLoaderListener;
import org.andengine.util.level.LevelLoaderContentHandler;

/**
 *
 * @author guillaume
 */
public class FunkyDominoLoaderContentHandler extends LevelLoaderContentHandler<FunkyDominoEntityLoaderData, IEntityLoaderListener, FunkyDominoLoaderResult> {

    /**
     *
     * @param pDefaultEntityLoader
     * @param pEntityLoaders
     * @param pEntityLoaderData
     */
    public FunkyDominoLoaderContentHandler(final IEntityLoader<FunkyDominoEntityLoaderData> pDefaultEntityLoader, final HashMap<String, IEntityLoader<FunkyDominoEntityLoaderData>> pEntityLoaders, FunkyDominoEntityLoaderData pEntityLoaderData) {
        super(pDefaultEntityLoader, pEntityLoaders, pEntityLoaderData);
    }

    /**
     * FunkyDominoLoaderResult est vide, pour le moment.
     *
     * @return
     */
    @Override
    public FunkyDominoLoaderResult getLevelLoaderResult() {
        return new FunkyDominoLoaderResult();
    }
}
