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
import com.gmxteam.funkydomino.xml.AttributesExtended;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.IOnAreaTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Classe abstraite définissant les composants. Les composants sont des éléments
 * affectés par la physique. On parle du sol, des dominos, des billes et autres
 * objets. Dans leurs méthodes Factory, ils peuvent altérer comme bon leur
 * semble les variables mAreaShape (état graphique) et mBody (état physique).
 *
 * Le système de génération des composants est basé sur des événements.
 *
 * @author Guillaume Poirier-Morency
 */
public abstract class Component extends Entity implements ComponentsConstants, IOnAreaTouchListener {

	protected FixtureDef mFixtureDef;
	protected AttributesExtended mAttributes;
	private GameActivity mGameActivity;
	

	public final Component init(GameActivity ga, AttributesExtended att) {
		mGameActivity = ga;
		mAttributes = att;

		this.mX = mAttributes.getFloatValue("left", 0.0f);
		this.mY = mAttributes.getFloatValue("top", 0.0f);

		mFixtureDef = new FixtureDef();

		mFixtureDef.density = att.getFloatValue("density", 5.0f);
		mFixtureDef.friction = att.getFloatValue("friction", 5.0f);

		onLoadResource();

		onCreateFixtureDef(mFixtureDef);

		onPopulatePhysicsWorld(mGameActivity.mPhysicsWorld);

		onPopulateEntity(this);
		
		onRegisterTouchAreas(mGameActivity.mScene);
		
		return this;
	}


	////////////////////////////////////////////////////////////////////////
	// Events
	protected abstract void onLoadResource();

	/**
	 * Altération du FixtureDef (propriétés physiques initiales et fondamentales)
	 * @param fd 
	 */
	protected abstract void onCreateFixtureDef(FixtureDef fd);

	/**
	 * Binding de l'entité avec le monde physique.
	 *
	 * @param pw
	 */
	protected abstract void onPopulatePhysicsWorld(PhysicsWorld pw);

	/**
	 * Binding des sous-entités avec l'entité qui reçoit l'événement.
	 * @param e Év
	 */
	protected abstract void onPopulateEntity(Entity e);

	
	protected abstract void onRegisterTouchAreas(Scene pScene);
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
}
