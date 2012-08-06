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

import android.content.res.AssetManager;
import com.badlogic.gdx.physics.box2d.Body;
import com.gmxteam.funkydomino.core.factory.Factorable;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.IAreaShape;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.region.TextureRegion;

/**
 * Classe abstraite définissant les composants. Les composants sont des éléments
 * affectés par la physique. On parle du sol, des dominos, des billes et autres
 * objets. Dans leurs méthodes Factory, ils peuvent altérer comme bon leur
 * semble les variables mAreaShape (état graphique) et mBody (état physique).
 *
 * @author Guillaume Poirier-Morency
 */
public abstract class Component extends Entity implements Factorable, ComponentsConstants {
	/* Ici, on peut mettre toutes les variables définissant généralement les
	 * composants, sans toutefois définir les widgets.
	 */

	static TextureManager mTextureManager;
	static AssetManager mAssetManager;
	
	public static void loadTextureManager(TextureManager tm, AssetManager am) {
		mTextureManager = tm;
		mAssetManager = am;
	}
	
	protected IAreaShape mAreaShape;
	protected Body mBody;
	
	/**
	 * Accessed through get and set.
	 */
	private TextureRegion mTextureRegion;

	
	TextureRegion getTextureRegion() {
		if (mTextureRegion == null) {
			onLoadResources();
		}
		assert mTextureRegion != null;
		return mTextureRegion;

	}
	
	void setTextureRegion(TextureRegion tr) {
		mTextureRegion = tr;
	}

	abstract void onLoadResources();

	/**
	 *
	 * @param ga
	 * @return
	 */
	public Component inflateOnScene(Scene ga) {
		ga.attachChild(this);
		return this;

	}
}
