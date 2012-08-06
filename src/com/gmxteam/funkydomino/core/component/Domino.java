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

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.gmxteam.funkydomino.core.factory.Factorable;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.xml.sax.Attributes;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public final class Domino extends Component {

	TextureRegion mDominoTextureRegion;

	public Factorable factory(Attributes att) {

		BitmapTextureAtlas mBitmapTextureAtlas;
		mBitmapTextureAtlas = new BitmapTextureAtlas(mGameActivity.getTextureManager(), 32, 32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		mDominoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, mGameActivity, "badge.png", 0, 0);

		mGameActivity.getTextureManager().loadTexture(mBitmapTextureAtlas);

		mAreaShape = new Sprite(mX, mY, mDominoTextureRegion, new VertexBufferObjectManager());

		return this;
	}

	public Domino inflateOnPhysicsWorld(PhysicsWorld pw) {
		mBody = PhysicsFactory.createBoxBody(pw, mSkewCenterX, mSkewCenterY, mX, mX, mRotation, BodyDef.BodyType.StaticBody, null, mRotation);
		pw.registerPhysicsConnector(new PhysicsConnector(mAreaShape, mBody, true, true));


		return this;
	}

	
}
