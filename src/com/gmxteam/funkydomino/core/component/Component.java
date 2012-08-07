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

import android.content.Context;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.gmxteam.funkydomino.activity.GameActivity;
import com.gmxteam.funkydomino.core.factory.Factorable;
import com.gmxteam.funkydomino.xml.AttributesExtended;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.IAreaShape;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

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

	// public static GameActivity mGameActivity;
	protected IAreaShape mAreaShape;
	protected FixtureDef mFixtureDef = new FixtureDef();
	private GameActivity mGameActivity;

	public final Component init(GameActivity ga, AttributesExtended att) {
		mGameActivity = ga;
		this.mX = att.getFloatValue("left", 0.0f);
		this.mY = att.getFloatValue("top", 0.0f);

		mFixtureDef.density = att.getFloatValue("density", 5.0f);
		mFixtureDef.friction = att.getFloatValue("friction", 5.0f);
		
		return this;
	}

	/////////////////////////
	// Accessible resources from the GameActivity
	
	protected final TextureManager getTextureManager() {
		return mGameActivity.getTextureManager();
	}

	protected final VertexBufferObjectManager getVertexBufferObjectManager() {
		return mGameActivity.getVertexBufferObjectManager();
	}

	protected final Context getContext() {
		return (Context) mGameActivity;
	}

	
	

	/**
	 *
	 * @param ga
	 * @return
	 */
	public final Component inflateOnScene(Scene ga) {
		ga.attachChild(this);
		return this;

	}
}
