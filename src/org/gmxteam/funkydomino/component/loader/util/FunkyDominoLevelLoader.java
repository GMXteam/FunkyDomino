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

import java.io.IOException;
import java.util.HashMap;
import org.andengine.util.level.IEntityLoader;
import org.andengine.util.level.IEntityLoaderListener;
import org.andengine.util.level.LevelLoader;
import org.gmxteam.funkydomino.activity.IBaseGameActivity;
import org.gmxteam.funkydomino.component.loader.BallLoader;
import org.gmxteam.funkydomino.component.loader.CogLoader;
import org.gmxteam.funkydomino.component.loader.DominoLoader;
import org.gmxteam.funkydomino.component.loader.HUDLoader;
import org.gmxteam.funkydomino.component.loader.SceneLoader;

/**
 *
 * @author guillaume
 */
public class FunkyDominoLevelLoader extends LevelLoader<FunkyDominoEntityLoaderData, IEntityLoaderListener, FunkyDominoLoaderResult> {

    private final IBaseGameActivity mBaseGameActivity;

    /**
     *
     * @param pBaseGameActivity
     */
    public FunkyDominoLevelLoader(IBaseGameActivity pBaseGameActivity) throws Exception {
        mBaseGameActivity = pBaseGameActivity;

        // Chargement des loaders par défaut

        registerEntityLoader(new DominoLoader(pBaseGameActivity));

        registerEntityLoader(new BallLoader(pBaseGameActivity));

        registerEntityLoader(new SceneLoader(pBaseGameActivity));

        registerEntityLoader(new HUDLoader(pBaseGameActivity));

        registerEntityLoader(new CogLoader(pBaseGameActivity));

    }

    /**
     *
     * @return
     */
    @Override
    protected FunkyDominoEntityLoaderData onCreateEntityLoaderData() {
        return new FunkyDominoEntityLoaderData(mBaseGameActivity);

    }

    /**
     *
     * @param pEntityLoaders
     * @param pDefaultEntityLoader
     * @param pEntityLoaderData
     * @param pEntityLoaderListener
     * @return
     */
    @Override
    protected FunkyDominoLoaderContentHandler onCreateLevelLoaderContentHandler(HashMap<String, IEntityLoader<FunkyDominoEntityLoaderData>> pEntityLoaders, IEntityLoader<FunkyDominoEntityLoaderData> pDefaultEntityLoader, FunkyDominoEntityLoaderData pEntityLoaderData, IEntityLoaderListener pEntityLoaderListener) {
        return new FunkyDominoLoaderContentHandler(pDefaultEntityLoader, pEntityLoaders, pEntityLoaderData);
    }
}
