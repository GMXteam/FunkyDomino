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
package com.gmxteam.funkydomino.activities;

// Importations locales
// Importations pour le moteur de collisions
//import org.jbox2d.collision.AABB;
//import org.jbox2d.collision.Shape;
//import org.jbox2d.common.Vec2;
//import org.jbox2d.dynamics.Body;
//import org.jbox2d.dynamics.World;
// Importations pour le moteur de rendu
// Librairies standard Android
// Librairie standard Java
import com.gmxteam.funkydomino.constants.AndEngineActivityConstants;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.ui.activity.BaseGameActivity;

/**
 * Classe abstraite permettant une implémentation efficace d'un interface JBox2D.
 * Funky Domino sera premièrement développé en canvas afin d'obtenir rapidement
 * des résultats. Il sera ensuite converti en OpenGL afin d'en améliorer
 * considérablement les performances.
 * 
 * Le fonctionnement est simple. On redéfinit une activité android en y intégrant
 * un moteur de physique et de rendu. On intègre aussi certaines interactions
 * avec l'utilisateur pour minimiser le code des activités.
 * 
 * Les éléments d'interfaces qui seront alors utilisés pourront être ceux de la
 * librairie standard, mais il est recommandé d'utiliser des élément de physique
 * afin de nous donner plus de liberté. Un menu animé par la physique, c'est pas
 * cool ça?
 * 
 * Le redessinage est géré dans le thread de l'utilisateur interface. Quand on
 * finit de redessiner une image, elle est automatiquement invalidée et quand le
 * thread UI sera prêt à la redessiner, le processus recommencera.
 * 
 * La physique est gérée dans un thread à part.
 * @author Guillaume Poirier-Morency
 */
public abstract class AndEngineActivity extends BaseGameActivity implements AndEngineActivityConstants {

    private Camera mCamera;
    private static final int CAMERA_WIDTH = CELLS_HORIZONTAL * CELL_WIDTH;
    private static final int CAMERA_HEIGHT = CELLS_VERTICAL * CELL_HEIGHT;

    /**
     * 
     * @return
     */
    @Override
    public EngineOptions onCreateEngineOptions() {
        this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

        final EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera);
        engineOptions.getAudioOptions().setNeedsSound(true);
        return engineOptions;
    }
}
