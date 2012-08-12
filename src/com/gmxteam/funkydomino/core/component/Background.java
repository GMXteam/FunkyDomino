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
package com.gmxteam.funkydomino.core.component;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import org.andengine.entity.Entity;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.TiledTextureRegion;

/**
 * Contiens les paramètres de la partie ainsi que le background.
 *     Niveau actuel
 *     Niveau suivant
 *     Nom du niveau
 *     Description du niveau
 * @author guillaume
 */
public class Background extends Component {
	
	/**
	 *
	 */
	TiledSprite mBackground;
	/**
	 *
	 */
	TiledTextureRegion mBackgroundTextureRegion;
	/**
	 *
	 */
	BitmapTextureAtlas mBackgroundTexture;

	

	@Override
	protected void onCreateFixtureDef(FixtureDef fd) {
	}

	@Override
	protected void onPopulatePhysicsWorld(PhysicsWorld pw) {
	}

	@Override
	protected void onPopulateEntity(Entity e) {
	}

	@Override
	protected void onLoadResource() {
	}
}
