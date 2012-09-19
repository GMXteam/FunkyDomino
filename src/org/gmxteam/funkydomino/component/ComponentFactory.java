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
package org.gmxteam.funkydomino.component;

import android.util.FloatMath;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import org.andengine.entity.IEntity;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.util.debug.Debug;
import org.andengine.util.math.MathUtils;
import org.gmxteam.funkydomino.activity.FunkyDominoActivity;
import org.gmxteam.funkydomino.activity.IBaseGameActivity;
import org.gmxteam.funkydomino.component.entity.AddDominoButton;
import org.gmxteam.funkydomino.component.entity.Ball;
import org.gmxteam.funkydomino.component.entity.Cog;
import org.gmxteam.funkydomino.component.entity.CogCore;
import org.gmxteam.funkydomino.component.entity.CogTeeth;
import org.gmxteam.funkydomino.component.entity.Domino;
import org.gmxteam.funkydomino.component.entity.Ground;

/**
 * Utility class containing methods to create and extract components.
 *
 * @author guillaume
 */
public class ComponentFactory {

    private static IBaseGameActivity mGameActivity;
    private static float DEFAULT_DENSITY;
    private static float DEFAULT_FRICTION;

    /**
     *
     * @param pGameActivity
     */
    public static void setGameActivity(IBaseGameActivity pGameActivity) {
        mGameActivity = pGameActivity;
    }

    /**
     *
     * @param ga
     * @param att
     * @return
     */
    public static IEntity factory(IComponent pComponent, ComponentAttributes att) {

        final FixtureDef mFixtureDef = new FixtureDef();

        mFixtureDef.density = att.getFloat("density", DEFAULT_DENSITY);

        mFixtureDef.friction = att.getFloat("friction", DEFAULT_FRICTION);

        final Body entityBody = pComponent.onCreateBody(mGameActivity.getPhysicsWorld(), mFixtureDef);
        mGameActivity.getPhysicsWorld().registerPhysicsConnector(new PhysicsConnector(pComponent, entityBody, true, true));

        if (att.getBoolean("touchable", true)) {
            mGameActivity.getScene().registerTouchArea(pComponent);
        }

        mGameActivity.getContactManager().registerContactListener(entityBody, pComponent);

        Debug.v("Un nouveau composant est créé [" + pComponent.getX() + "," + pComponent.getY() + "]");


        return pComponent;
    }


    

    public static class GroundFactory {

        private static BitmapTextureAtlas mBitmapTextureAtlas;
        private static TextureRegion mTextureRegion;

        public static void loadResources() {
            mBitmapTextureAtlas = new BitmapTextureAtlas(mGameActivity.getTextureManager(), AddDominoButton.DOMINO_WIDTH, AddDominoButton.DOMINO_HEIGHT, FunkyDominoActivity.TEXTURE_OPTION);
            mTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, mGameActivity.getContext(), "ball.png", 0, 0);
            mGameActivity.getTextureManager().loadTexture(mBitmapTextureAtlas);
        }

        /**
         * Creates a domino.
         *
         * @param x
         * @param y
         * @param vertex
         * @return
         * @throws InstantiationException
         * @throws IllegalAccessException
         */
        public static IEntity createGround(float x, float y, Vector2[] vertex) throws InstantiationException, IllegalAccessException {

            final Ground d = new Ground(x, y, mBitmapTextureAtlas, 2, mGameActivity.getVertexBufferObjectManager());
            ComponentAttributes ca = ComponentAttributes.createBaseAttributes(x, y);
            ca.putFloat("friction", 1.0f);
            ca.putFloat("density", 1.0f);
            return ComponentFactory.factory(d, ca);
        }
    }

    
}
