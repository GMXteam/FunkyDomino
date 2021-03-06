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
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.ui.IGameInterface;

/**
 *
 * @author guillaume
 */
public interface IBaseGameActivity extends IGameInterface, IBaseGameActivityResource {
    
     /**
     *
     */
    public static TextureOptions TEXTURE_OPTION = TextureOptions.REPEATING_BILINEAR_PREMULTIPLYALPHA;

    
    
}
