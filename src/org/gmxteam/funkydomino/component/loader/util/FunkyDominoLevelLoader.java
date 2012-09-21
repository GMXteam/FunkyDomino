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
import org.gmxteam.funkydomino.component.loader.GroundLoader;
import org.gmxteam.funkydomino.component.loader.HUDLoader;
import org.gmxteam.funkydomino.component.loader.SceneLoader;

/**
 * Classe de loader pour un fichier de jeu de FunkyDomino.
 *
 * Le loader s'occupe de lire le fichier XML de jeu et de construire la
 * structure en arbre d'entités. Il va analyser chaque balises et appeler les
 * IEntityLoader qui sont en mesure de les charger.
 *
 * @author guillaume
 */
public class FunkyDominoLevelLoader extends LevelLoader<FunkyDominoEntityLoaderData, IEntityLoaderListener, FunkyDominoLoaderResult> {

    private final IBaseGameActivity mBaseGameActivity;

    /**
     * Constructeur du loader.
     *
     * Par défaut, ce constructeure charge tous les loaders de base de Funky
     * Domino.
     *
     * @param pBaseGameActivity est une activité qui sera transmise au
     * FunkyDominoEntityLoaderData
     * @throws IOException certains loader lancent des exceptions car ils
     * chargent des ressources provenant des Assets.
     */
    public FunkyDominoLevelLoader(final IBaseGameActivity pBaseGameActivity) throws IOException {

        mBaseGameActivity = pBaseGameActivity;

        // Chargement des loaders par défaut, ces loaders chargent aussi des
        // ressources de base pour les entités.

        registerEntityLoader(new DominoLoader(pBaseGameActivity));

        registerEntityLoader(new BallLoader(pBaseGameActivity));
        
        registerEntityLoader(new GroundLoader(pBaseGameActivity));

        registerEntityLoader(new SceneLoader());

        registerEntityLoader(new HUDLoader());

        registerEntityLoader(new CogLoader(pBaseGameActivity));

    }

    /**
     * Retourne une instance d'un FunkyDominoEntityLoaderData.
     *
     * Un EntityLoaderData permet de donner accès à des ressources spécifiques
     * aux entités qui seront chargées.
     *
     * @return un objet de ressources.
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
