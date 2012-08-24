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
	public static final String APP_LOG_NAME = "funky-domino";
	////////////////////////////////////////////////////////////////////////////
	// Constantes spécifiques aux activités
	
	/**
	 * Largeur de la caméra (correspond à l'écran)
	 */
	public static final float CAMERA_WIDTH = 800;
	/**
	 * Hauteur de la caméra (correspond à la hauteur de l'écran)
	 */
	public static final float CAMERA_HEIGHT = 480;
	/**
	 *
	 */
	public static final float CAMERA_LEFT = 0.0f;
	/**
	 *
	 */
	public static final float CAMERA_TOP = 0.0f;
	/**
	 *
	 */
	public static final float WORLD_HEIGHT = CAMERA_HEIGHT;
	/**
	 *
	 */
	public static final float WORLD_WIDTH =  CAMERA_WIDTH;
	/**
	 *
	 */
	public static final float WORLD_LEFT = 0.0f;
	/**
	 *
	 */
	public static final float WORLD_TOP = 0.0f;
}
