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
package com.gmxteam.funkydomino.activity;

import org.andengine.opengl.texture.TextureOptions;

/**
 * Constantes pour
 *
 * @author guillaume
 */
public interface GameActivityConstants {

    ////////////////////////////////////////////////////////////////////////////
    // Constantes publiques. Elles sont disponibles dans tout le projet.
    /**
     *
     */
    public static final String LOG_TAG = "FunkyDomino";
    public static final boolean DEBUG = true;
    ////////////////////////////////////////////////////////////////////////////
    // Constantes spécifiques aux activités
    /**
     *
     */
    public static final float CAMERA_LEFT = 0.0f;
    /**
     *
     */
    public static final float CAMERA_TOP = 0.0f;
    public static final float CAMERA_MAX_VELOCITY_X =  500.0f;
    public static final float CAMERA_MAX_VELOCITY_Y =  CAMERA_MAX_VELOCITY_X;
    public static final float CAMERA_MAX_ZOOM_FACTOR_CHANGE = 2.0f;
    public static final float CAMERA_Z_NEAR = 10.0f, CAMERA_Z_FAR = 20.0f;
    /**
     *
     */
    public static final float WORLD_HEIGHT = 800.0f;
    /**
     *
     */
    public static final float WORLD_WIDTH = 5000.0f;
    /**
     *
     */
    public static final float WORLD_LEFT = 0.0f;
    /**
     *
     */
    public static final float WORLD_TOP = 0.0f;
    
    public static TextureOptions TEXTURE_OPTION = TextureOptions.REPEATING_BILINEAR_PREMULTIPLYALPHA;
}
