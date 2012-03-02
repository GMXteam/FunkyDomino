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
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
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
     * @see BitmapTextureAtlas
     */
    private static BitmapTextureAtlas mDominoTexture;
    /**
     * @see TiledTextureRegion
     */
    private static TiledTextureRegion mDominoTextureRegion;

    /**
     * @see Ground#loadResource(com.gmxteam.funkydomino.activities.AndEngineActivity) 
     * @param andEngineActivity 
     */
    public static void loadResource(AndEngineActivity andEngineActivity) {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        mDominoTexture = new BitmapTextureAtlas(andEngineActivity.getTextureManager(), 128, 16, TextureOptions.BILINEAR);
        mDominoTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mDominoTexture, andEngineActivity, "vehicles.png", 0, 0, 6, 1);
        mDominoTexture.load();
    }
    ////////////////////////////////////////////////////////////////////////////
    // Variables locales
    /**
     * 
     */
    private TiledSprite mSprite;
    /**
     * 
     */
    private Body mBody;
    private final float DOMINO_WEIGHT = 2.0f;

    ////////////////////////////////////////////////////////////////////////////
    // Constructeurs
    /**
     * Constructeur qui interprète des données XML. Il gère les exceptions et 
     * appelle ensuite la méthode init().
     * @param andEngineActivity 
     * @param atts
     */
    public Domino(AndEngineActivity andEngineActivity, Attributes atts) {
        float x = Float.parseFloat(atts.getValue("x"));
        float y = Float.parseFloat(atts.getValue("y"));
        boolean moveable = Boolean.parseBoolean(atts.getValue("moveable"));
        init(andEngineActivity, x, y, moveable);
    }

    /** 
     * Constructeur extensif. Tous les constructeurs sont connectés avec une
     * méthode interne init().
     * @param andEngineActivity
     * @param x 
     * @param y  
     */
    public Domino(AndEngineActivity andEngineActivity, float x, float y, boolean moveable) {
        init(andEngineActivity, x, y, moveable);
    }

    /**
     * @see Domino#Domino(com.gmxteam.funkydomino.activities.AndEngineActivity, float, float) 
     * @param andEngineActivity est l'activité sur laquelle le domino est ajouté.
     * @param x est la position initiale en x du domino.
     * @param y est la position initiale en y du domino.
     */
    private void init(AndEngineActivity andEngineActivity, float x, float y, boolean moveable) {
        this.mAndEngineActivity = andEngineActivity;

        // Entité visible
        this.mSprite = new TiledSprite(x, y, 20, 100, mDominoTextureRegion, mAndEngineActivity.getVertexBufferObjectManager()) {

            @Override
            public boolean onAreaTouched(TouchEvent te, float x, float y) {

                /* TODO Appliquer une impulsion qui poussera le domino vers le
                 * doigt de l'utilisateur.
                 */
                mBody.applyTorque(50.0f);



                return true;
            }
        };
        this.mSprite.setCurrentTileIndex(0);


        // Entité physique
        final FixtureDef carFixtureDef = PhysicsFactory.createFixtureDef(moveable ? DOMINO_WEIGHT : 0.0f, 0.5f, 0.5f);

        mBody = PhysicsFactory.createBoxBody(mAndEngineActivity.mPhysicsWorld, this.mSprite, BodyType.DynamicBody, carFixtureDef);

        // Connexion entre la physique et le visible
        mAndEngineActivity.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(this.mSprite, mBody, true, true));

        // Connexion avec la scène visible
        mAndEngineActivity.mScene.attachChild(this.mSprite);
        andEngineActivity.mScene.registerTouchArea(mSprite);


    }
    ////////////////////////////////////////////////////////////////////////////
    // Méthodes et fonctions
}
