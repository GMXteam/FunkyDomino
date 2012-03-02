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
package com.gmxteam.funkydomino.activities;

import android.util.Log;
import com.gmxteam.funkydomino.graphicals.components.Ball;
import com.gmxteam.funkydomino.graphicals.components.Cog;
import com.gmxteam.funkydomino.graphicals.components.Domino;
import com.gmxteam.funkydomino.graphicals.components.Ground;
import com.gmxteam.funkydomino.graphicals.components.Water;
import com.gmxteam.funkydomino.utils.xmlparser.AndEngineActivityXMLParser;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.xml.sax.SAXException;

/**
 * Classe principale de l'application Android.
 * Elle sera construite avec AndEngine.
 * @author Guillaume Poirier-Morency
 */
public final class MainActivity extends AndEngineActivity {

    /**
     * InputStream contenant le niveau qui sera joué. Il est dans MainActivity
     * pour des raisons de tests. 
     * TODO Il sera déplacé dans GameActivity lorsque le programme sera plus 
     * fonctionnel.
     */
    private InputStream levelStream;

    /**
     * 
     * @param pScene
     * @param pOnPopulateSceneCallback 
     */
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) {


        ////////////////////////////////////////////////////////////////////////
        // Code de test

        mBackground = new TiledSprite(WORLD_LEFT, WORLD_TOP, WORLD_WIDTH, WORLD_HEIGHT, mBackgroundTextureRegion, this.getVertexBufferObjectManager()) {

            @Override
            public boolean onAreaTouched(TouchEvent pSceneTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {

                mCamera.setCenter(mCamera.getCenterX() + 50.0f, mCamera.getCenterY());


                return super.onAreaTouched(pSceneTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
        };
        pScene.attachChild(mBackground);
        pScene.registerTouchArea(mBackground);

        float[] squareX = {WORLD_LEFT + 1, WORLD_WIDTH, WORLD_WIDTH, WORLD_LEFT, WORLD_LEFT + 1};
        float[] squareY = {WORLD_TOP + 1, WORLD_TOP + 1, WORLD_HEIGHT, WORLD_HEIGHT, WORLD_TOP + 1};
        //float[] groundX = {WORLD_LEFT, 5.0f, 22.5f, 100.0f, WORLD_WIDTH};
        //float[] groundY = {122.0f, 205.0f, 205.5f, 255.7f, 120.0f};

        pScene.attachChild(new Ground(this, squareX, squareY));
        //pScene.attachChild(new Ground(this, groundX, groundY));

        //pScene.attachChild(new Domino(this, 25.0f, 25.0f, true));
        //pScene.attachChild(new Domino(this, 50.0f, 10.0f, false));
        //pScene.attachChild(new Domino(this, 100.0f, 5.0f, false));


        ////////////////////////////////////////////////////////////////////////
        // Code utilisant le système XML
        String publickey = getString(R.string.key_0)
                + getString(R.string.key_1)
                + getString(R.string.key_2)
                + getString(R.string.key_3)
                + getString(R.string.key_4)
                + getString(R.string.key_5)
                + getString(R.string.key_6)
                + getString(R.string.key_7);

        try {
            AndEngineActivityXMLParser.buildGameInstance(this, levelStream, publickey);
            levelStream.close();
        } catch (ParserConfigurationException ex) {
            Log.e(APP_LOG_NAME, "Parser configuration has crashed !", ex);
        } catch (SAXException ex) {
            Log.e(APP_LOG_NAME, "Parser has crashed ! There's an error in the level file !", ex);
        } catch (IOException ex) {
            Log.e(APP_LOG_NAME, "May be due to closing the stream or accessing it !", ex);
        }


        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }

    /**
     * Création des ressources.
     * @param pOnCreateResourcesCallback est un callback à utiliser pour avertir
     * AndEngine que les ressources ont été créée.
     * @throws Exception 
     */
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception {
        ////////////////////////////////////////////////////////////////////////
        // Loading local resources
        levelStream = this.getResources().openRawResource(R.raw.stage1);
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        mBackgroundTexture = new BitmapTextureAtlas(this.getTextureManager(), 128, 16, TextureOptions.BILINEAR);
        mBackgroundTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mBackgroundTexture, this, "vehicles.png", 0, 0, 6, 1);
        mBackgroundTexture.load();

        ////////////////////////////////////////////////////////////////////////
        // Loading other resources
        Domino.loadResource(this);
        Ground.loadResource(this);
        Ball.loadResource(this);
        Water.loadResource(this);
        Cog.loadResource(this);
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }
}
