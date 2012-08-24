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

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import java.util.LinkedList;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;

/**
 * Objet définissant le sol. Méta-entité, soit une entité contenant plusieurs
 * entités. Chaque body est assemblé avec celui qui le suit (d'où la
 * LinkedList).
 *
 * @author Guillaume Poirier-Morency
 */
public class Ground extends Component {

	private LinkedList<Component> mComponents;
	private TiledSprite mGround;

	public ITouchArea getTouchArea() {
		return mGround;
	}

	@Override
	protected void onLoadResource() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void onCreateFixtureDef(FixtureDef fd) {
	}

	@Override
	protected void onPopulatePhysicsWorld(PhysicsWorld pw) {


		Body pBody = PhysicsFactory.createPolygonBody(pw, mGround, pVertices, BodyDef.BodyType.StaticBody, mFixtureDef);
		pw.registerPhysicsConnector(new PhysicsConnector(mGround, pBody));


	}

	@Override
	protected void onPopulateEntity(Entity e) {
		for (Component c : mComponents) {
			e.attachChild(c);
		}
	}

	public boolean onAreaTouched(TouchEvent te, ITouchArea ita, float f, float f1) {
		// Le sol ne réagit pas au toucher.
		return false;
	}

	@Override
	protected void onRegisterTouchAreas(Scene pScene) {
		// Le sol ne réagit pas au toucher
	}
}
