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

import com.gmxteam.funkydomino.core.component.factory.ComponentAttributes;
import android.util.Log;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.gmxteam.funkydomino.activity.GameActivity;
import com.gmxteam.funkydomino.core.ContactManager;
import java.util.Arrays;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;

/**
 * Objet définissant le sol. Méta-entité, soit une entité contenant plusieurs
 * entités. Chaque body est assemblé avec celui qui le suit (d'où la
 * LinkedList).
 *
 * @author Guillaume Poirier-Morency
 */
public class Ground extends Component {

    private Body mGroundBody;
    private TextureRegion mGroundTextureRegion;
    private Sprite mGround;
    /**
     *
     */
    public static final int GROUND_TEXTURE_HEIGHT = 32,
            /**
             *
             */
            GROUND_TEXTURE_WIDTH = 32,
            /**
             *
             */
            GROUND_COLUMNS = 10,
    /**
     *
     */
    GROUND_ROWS = 10;

    /**
     *
     */
    @Override
    protected void onLoadResource() {
        BitmapTextureAtlas mBitmapTextureAtlas = new BitmapTextureAtlas(getTextureManager(), GROUND_TEXTURE_WIDTH, GROUND_TEXTURE_HEIGHT);
        mGroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, getContext(), "background_grass.png", 0, 0);
        this.getTextureManager().loadTexture(mBitmapTextureAtlas);


    }
    
    @Override
    protected void onCreateSprite(float pX, float pY, float angle) {
        mGround = new Sprite(pX, pY, mGroundTextureRegion, getVertexBufferObjectManager());
        mGround.setRotation(angle);
    }

    @Override
    protected void onCreateFixtureDef(FixtureDef fd, ComponentAttributes pAttributes) {
        //this.mVertex = mAttributes.getVector2Value("vector", null);
    }

    @Override
    protected void onPopulatePhysicsWorld(PhysicsWorld pPhysicsWorld) {
        Vector2[] defaultVectors = {};

        Log.v(GameActivity.LOG_TAG, Arrays.toString(mComponentAttributes.getVector2Array("vector", defaultVectors)));

        mGroundBody = PhysicsFactory.createPolygonBody(pPhysicsWorld, mGround,mComponentAttributes.getVector2Array("vector", defaultVectors), BodyDef.BodyType.StaticBody, mFixtureDef);

        pPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(mGround, mGroundBody, false, false));
        
        
    }

    @Override
    protected void onPopulateEntity(Entity e) {
        e.attachChild(mGround);
    }

    

    @Override
    protected void onRegisterTouchAreas(Scene pScene) {
        pScene.registerTouchArea(mGround);
    }

    /**
     *
     * @param pContactManager
     */
    @Override
    protected void onRegisterContactListener(ContactManager pContactManager) {
    }

    

   
}
