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
import com.gmxteam.funkydomino.core.factory.Factorable;
import com.gmxteam.funkydomino.xml.AttributesExtended;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public final class Domino extends Component {
	
	private Body mBody;
	
	@Override
	public Domino factory(AttributesExtended att) {
		
		
		BitmapTextureAtlas mBitmapTextureAtlas;
		mBitmapTextureAtlas = new BitmapTextureAtlas(getTextureManager(), 128, 128, TextureOptions.BILINEAR);
		TextureRegion mDominoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromResource(mBitmapTextureAtlas, getContext(), R.drawable.icon, 0, 0);
		
		getTextureManager().loadTexture(mBitmapTextureAtlas);
		mAreaShape = new Sprite(0, 0, mDominoTextureRegion, getVertexBufferObjectManager());
		
		this.attachChild(mAreaShape);
		
		return this;
	}
	
	public Domino inflateOnPhysicsWorld(PhysicsWorld pw) {
		
		
		
		
		
		
		mBody = PhysicsFactory.createBoxBody(pw, mAreaShape, BodyDef.BodyType.DynamicBody, mFixtureDef);
		
		pw.registerPhysicsConnector(new PhysicsConnector(mAreaShape, mBody, true, true));
		
		
		
		return this;
	}
}
