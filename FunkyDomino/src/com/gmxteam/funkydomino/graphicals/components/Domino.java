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
package com.gmxteam.funkydomino.graphicals.components;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.gmxteam.funkydomino.activities.AndEngineActivity;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.xml.sax.Attributes;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public final class Domino extends Component {

    ////////////////////////////////////////////////////////////////////////////
    // Les textures sont statiques et chargées lors
    /**
     * 
     */
    public static BitmapTextureAtlas mVehiclesTexture;
    /**
     * 
     */
    public static TiledTextureRegion mVehiclesTextureRegion;
    ////////////////////////////////////////////////////////////////////////////
    //
    private TiledSprite mCar;

    /**
     * 
     * @param aea 
     * @param atts
     */
    public Domino(AndEngineActivity aea, Attributes atts) {
        float f = Float.parseFloat(atts.getValue("x"));
        float f0 = Float.parseFloat(atts.getValue("y"));
        init(aea, f, f0);
    }

    /**
     * 
     * @param aea
     * @param f
     * @param f0
     */
    public Domino(AndEngineActivity aea, float f, float f0) {
        init(aea, f, f0);
    }

    /**
     * 
     * @param aea
     * @param f
     * @param f0 
     */
    private void init(AndEngineActivity aea, float f, float f0) {
        this.mCar = new TiledSprite(20, 20, 20, 20, mVehiclesTextureRegion, aea.getVertexBufferObjectManager());
        this.mCar.setCurrentTileIndex(0);

        final FixtureDef carFixtureDef = PhysicsFactory.createFixtureDef(1, 0.5f, 0.5f);
        final Body mCarBody = PhysicsFactory.createBoxBody(aea.mPhysicsWorld, this.mCar, BodyType.DynamicBody, carFixtureDef);

        aea.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(this.mCar, mCarBody, true, false));

        aea.mScene.attachChild(this.mCar);


    }
}
