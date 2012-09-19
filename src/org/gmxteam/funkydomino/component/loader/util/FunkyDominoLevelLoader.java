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
import org.andengine.util.level.LevelLoader;
import org.gmxteam.funkydomino.activity.IBaseGameActivity;

/**
 *
 * @author guillaume
 */
public class FunkyDominoLevelLoader extends LevelLoader<FunkyDominoEntityLoaderData, IEntityLoaderListener, FunkyDominoLoaderResult> {

    private final IBaseGameActivity mBaseGameActivity;
    
    public FunkyDominoLevelLoader(IBaseGameActivity pBaseGameActivity) {
        mBaseGameActivity = pBaseGameActivity;
    }

    @Override
    protected FunkyDominoEntityLoaderData onCreateEntityLoaderData() {
        return new FunkyDominoEntityLoaderData(mBaseGameActivity);

    }

    @Override
    protected FunkyDominoLoaderContentHandler onCreateLevelLoaderContentHandler(HashMap<String, IEntityLoader<FunkyDominoEntityLoaderData>> pEntityLoaders, IEntityLoader<FunkyDominoEntityLoaderData> pDefaultEntityLoader, FunkyDominoEntityLoaderData pEntityLoaderData, IEntityLoaderListener pEntityLoaderListener) {
        return new FunkyDominoLoaderContentHandler(pDefaultEntityLoader, pEntityLoaders, pEntityLoaderData);
    }
}
