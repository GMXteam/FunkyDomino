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
package org.gmxteam.funkydomino.activity;

import org.andengine.engine.camera.hud.HUD;

/**
 *
 * @author guillaume
 */
public interface IFunkyDominoActivity extends IBaseGameActivity {

    ////////////////////////////////////////////////////////////////////////////
    // Constantes publiques. Elles sont disponibles dans tout le projet.
    /**
     * Tag pour la sortie de déboguage d'Android.
     */
    public static final String LOG_TAG = "FunkyDomino";
    ////////////////////////////////////////////////////////////////////////////
    // Constantes spécifiques aux activités
    /**
     * Vélocité maximale de la caméra en x.
     */
    public static final float CAMERA_MAX_VELOCITY_X = 500.0f;
    /**
     * Vélocité maximale de la caméra en y.
     */
    public static final float CAMERA_MAX_VELOCITY_Y = CAMERA_MAX_VELOCITY_X;
    /**
     * Facteur maximal de changement pour le zoom.
     */
    public static final float CAMERA_MAX_ZOOM_FACTOR_CHANGE = 2.0f;
    /**
     * Valeur en z de la caméra (distance entre la scène et la caméra) considéré
     * comme near, ou proche.
     */
    public static final float CAMERA_Z_NEAR = 10.0f,
            /**
             * Valeur en z de la caméra (distance entre la scène et la caméra)
             * considéré comme far, ou lointaine.
             */
            CAMERA_Z_FAR = 20.0f;

    /**
     *
     * @param pHUD
     */
    public void setHUD(HUD pHUD);

    /**
     *
     * @return
     */
    public HUD getHUD();
}
