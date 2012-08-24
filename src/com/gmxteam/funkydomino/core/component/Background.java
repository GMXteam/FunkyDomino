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
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.gmxteam.funkydomino.activity.GameActivityConstants;
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
 * Contiens les paramètres de la partie ainsi que le background. Niveau actuel
 * Niveau suivant Nom du niveau Description du niveau
 *
 * @author guillaume
 */
public class Background extends Component {

	/**
	 *
	 */
	TiledSprite mBackground;
	Body mBackgroundBody[] = new Body[4];
	/**
	 *
	 */
	TiledTextureRegion mBackgroundTextureRegion;
	/**
	 *
	 */
	BitmapTextureAtlas mBackgroundTexture;
	
	public ITouchArea getTouchArea() {
		
		return mBackground;
	}

	@Override
	protected void onCreateFixtureDef(FixtureDef fd) {
	}

	@Override
	protected void onPopulatePhysicsWorld(PhysicsWorld pw) {

		float worldWidth = GameActivityConstants.WORLD_WIDTH;
		float worldHeight = GameActivityConstants.WORLD_HEIGHT;

		mBackgroundBody[0] = PhysicsFactory.createLineBody(pw, 0.0f, 0.0f, worldWidth, 0.0f, mFixtureDef);
		mBackgroundBody[1] = PhysicsFactory.createLineBody(pw, worldWidth, 0.0f, worldWidth, worldHeight, mFixtureDef);
		mBackgroundBody[2] = PhysicsFactory.createLineBody(pw, worldWidth, worldHeight, 0.0f, worldHeight, mFixtureDef);
		mBackgroundBody[3] = PhysicsFactory.createLineBody(pw, 0.0f, worldHeight,0.0f, 0.0f, mFixtureDef);
		
		for (Body b : mBackgroundBody) {
			pw.registerPhysicsConnector(new PhysicsConnector(mBackground, b, false, false));

		}
		// No need to register an handler
	}

	@Override
	protected void onPopulateEntity(Entity e) {
		e.attachChild(mBackground);
	}

	@Override
	protected void onLoadResource() {
		mBackgroundTexture = new BitmapTextureAtlas(getTextureManager(), 50, 38, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		mBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromResource(mBackgroundTexture, getContext(), R.drawable.background, 0, 0, 50, 38);
		getTextureManager().loadTexture(mBackgroundTexture);

		mBackground = new TiledSprite(0, 0, mBackgroundTextureRegion, getVertexBufferObjectManager());
	}

	public boolean onAreaTouched(TouchEvent te, ITouchArea ita, float f, float f1) {
		Ball b = new Ball();
		// this.addComponentToScene(b, new AttributesExtended());
		return true;
	}

	@Override
	protected void onRegisterTouchAreas(Scene pScene) {
		pScene.registerTouchArea(mBackground);
	}
}
