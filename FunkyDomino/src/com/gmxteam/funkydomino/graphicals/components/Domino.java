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
    private static TiledTextureRegion mVehiclesTextureRegion;

    /**
     * @see Ground#loadResource(com.gmxteam.funkydomino.activities.AndEngineActivity) 
     * @param andEngineActivity
     */
    public static void loadResource(AndEngineActivity andEngineActivity) {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        mDominoTexture = new BitmapTextureAtlas(andEngineActivity.getTextureManager(), 128, 16, TextureOptions.BILINEAR);
        mVehiclesTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mDominoTexture, andEngineActivity, "vehicles.png", 0, 0, 6, 1);
        mDominoTexture.load();        
    }
    ////////////////////////////////////////////////////////////////////////////
    //
    private TiledSprite mCar;

    /**
     * Constructeur qui interprète des données XML. Il gère les exceptions et 
     * appelle ensuite la méthode init().
     * @param andEngineActivity 
     * @param atts
     */
    public Domino(AndEngineActivity andEngineActivity, Attributes atts) {
        float x = Float.parseFloat(atts.getValue("x"));
        float y = Float.parseFloat(atts.getValue("y"));
        init(andEngineActivity, x, y);
    }

    /** 
     * Constructeur extensif. Tous les constructeurs sont connectés avec une
     * méthode interne init().
     * @param andEngineActivity
     * @param x 
     * @param y  
     */
    public Domino(AndEngineActivity andEngineActivity, float x, float y) {
        init(andEngineActivity, x, y);
    }

    /**
     * @see Domino#Domino(com.gmxteam.funkydomino.activities.AndEngineActivity, float, float) 
     * @param andEngineActivity est l'activité sur laquelle le domino est ajouté.
     * @param x est la position initiale en x du domino.
     * @param y est la position initiale en y du domino.
     */
    private void init(AndEngineActivity andEngineActivity, float x, float y) {
        this.mAndEngineActivity = andEngineActivity;
        
        // Entité visible
        this.mCar = new TiledSprite(x, y, 20, 20, mVehiclesTextureRegion, mAndEngineActivity.getVertexBufferObjectManager());
        this.mCar.setCurrentTileIndex(0);

        // Entité physique
        final FixtureDef carFixtureDef = PhysicsFactory.createFixtureDef(1, 0.5f, 0.5f);
        final Body mCarBody = PhysicsFactory.createBoxBody(mAndEngineActivity.mPhysicsWorld, this.mCar, BodyType.DynamicBody, carFixtureDef);
        
        // Connexion entre la physique et le visible
        mAndEngineActivity.mPhysicsWorld.registerPhysicsConnector(new PhysicsConnector(this.mCar, mCarBody, true, false));
        
        // Connexion avec la scène visible
        mAndEngineActivity.mScene.attachChild(this.mCar);


    }
}
