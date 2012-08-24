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
import com.gmxteam.funkydomino.activity.R;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public final class Domino extends Component {

	private Body mDominoBody;
	private Sprite mDominoSprite;

	@Override
	protected void onLoadResource() {
		BitmapTextureAtlas mBitmapTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 128, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		TextureRegion mDominoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromResource(mBitmapTextureAtlas, getContext(), R.drawable.domino, 0, 0);

		getTextureManager().loadTexture(mBitmapTextureAtlas);

		mDominoSprite = new Sprite(0, 0, mDominoTextureRegion, getVertexBufferObjectManager());


	}

	@Override
	protected void onCreateFixtureDef(FixtureDef fd) {
	}

	@Override
	protected void onPopulatePhysicsWorld(PhysicsWorld pw) {
		mDominoBody = PhysicsFactory.createBoxBody(pw, mDominoSprite, BodyDef.BodyType.DynamicBody, mFixtureDef);

		pw.registerPhysicsConnector(new PhysicsConnector(mDominoSprite, mDominoBody, true, true));
	}

	@Override
	protected void onPopulateEntity(Entity e) {
		e.attachChild(mDominoSprite);
	}

	public boolean onAreaTouched(TouchEvent te, ITouchArea ita, float f, float f1) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void onRegisterTouchAreas(Scene pScene) {
		pScene.registerTouchArea(mDominoSprite);
		
	}
}
