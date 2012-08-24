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

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import com.gmxteam.funkydomino.activity.R;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;

/**
 * Objet définissant le sol. Méta-entité, soit une entité contenant plusieurs
 * entités. Chaque body est assemblé avec celui qui le suit (d'où la
 * LinkedList).
 *
 * @author Guillaume Poirier-Morency
 */
public class Ground extends Component {

	private Body mGroundBody;
	private TiledTextureRegion mGroundTextureRegion;
	private TiledSprite mGround;
	private Vector2 mVertex[];
	public static final int GROUND_TEXTURE_HEIGHT = 38,
		GROUND_TEXTURE_WIDTH = 50;

	@Override
	protected void onLoadResource() {

		BitmapTextureAtlas mBitmapTextureAtlas = new BitmapTextureAtlas(getTextureManager(), GROUND_TEXTURE_WIDTH, GROUND_TEXTURE_HEIGHT);
		mGroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromResource(mBitmapTextureAtlas, getContext(), R.drawable.background, 0, 0, 10, 10);
		mGround = new TiledSprite(0.0f, 0.0f, mGroundTextureRegion, getVertexBufferObjectManager());

	}

	@Override
	protected void onCreateFixtureDef(FixtureDef fd) {
		this.mVertex = mAttributes.getVector2Value("vector", null);
	}

	@Override
	protected void onPopulatePhysicsWorld(PhysicsWorld pw) {

		mGroundBody = PhysicsFactory.createPolygonBody(pw, mGround, mVertex, BodyDef.BodyType.StaticBody, mFixtureDef);
		pw.registerPhysicsConnector(new PhysicsConnector(mGround, mGroundBody, false, false));
	}

	@Override
	protected void onPopulateEntity(Entity e) {
		e.attachChild(mGround);
	}

	public boolean onAreaTouched(TouchEvent te, ITouchArea ita, float f, float f1) {
		return false;
	}

	@Override
	protected void onRegisterTouchAreas(Scene pScene) {
		pScene.registerTouchArea(mGround);
	}
}
