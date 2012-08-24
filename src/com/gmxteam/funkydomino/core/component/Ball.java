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
 * @see Component
 * @author Guillaume Poirier-Morency
 */
public final class Ball extends Component {

	private Body mBallBody;
	private Sprite mBallSprite;
	
	public final int BALL_RADIUS = 32;

	public ITouchArea getTouchArea() {
		return mBallSprite;
	}

	@Override
	protected void onLoadResource() {

		BitmapTextureAtlas mBitmapTextureAtlas = new BitmapTextureAtlas(getTextureManager(), BALL_RADIUS * 2, BALL_RADIUS * 2, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		TextureRegion mBallTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromResource(mBitmapTextureAtlas, getContext(), R.drawable.ball, 0, 0);

		getTextureManager().loadTexture(mBitmapTextureAtlas);

		mBallSprite = new Sprite(0, 0, mBallTextureRegion, getVertexBufferObjectManager());
	}

	@Override
	protected void onCreateFixtureDef(FixtureDef fd) {
	}

	@Override
	protected void onPopulatePhysicsWorld(PhysicsWorld pw) {
		mBallBody = PhysicsFactory.createCircleBody(pw, mBallSprite, BodyDef.BodyType.StaticBody, mFixtureDef);
		pw.registerPhysicsConnector(new PhysicsConnector(mBallSprite, mBallBody, true, true));
	}

	@Override
	protected void onPopulateEntity(Entity e) {

		e.attachChild(mBallSprite);
	}

	public boolean onAreaTouched(TouchEvent te, ITouchArea ita, float f, float f1) {
		return false;
	}

	@Override
	protected void onRegisterTouchAreas(Scene pScene) {
		pScene.registerTouchArea(mBallSprite);
	}
}
