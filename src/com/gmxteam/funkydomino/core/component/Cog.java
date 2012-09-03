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
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;

/**
 * Objet générant une roue dentée.
 *
 * @see Component
 * @author Guillaume Poirier-Morency
 */
public final class Cog extends Component {

	protected Sprite mCog;

	public ITouchArea getTouchArea() {
		return mCog;
	
	}

	@Override
	protected void onLoadResource() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void onCreateFixtureDef(FixtureDef fd, EntityAttributes pAttributes) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void onPopulatePhysicsWorld(PhysicsWorld pw, EntityAttributes pAttributes) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void onPopulateEntity(Entity e) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public boolean onAreaTouched(TouchEvent te, ITouchArea ita, float f, float f1) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void onRegisterTouchAreas(Scene pScene) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
