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
package org.gmxteam.funkydomino.component.loader;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import org.andengine.entity.IEntity;
import org.andengine.entity.primitive.Line;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.RepeatingSpriteBackground;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.util.debug.Debug;
import org.andengine.util.level.IEntityLoader;
import org.gmxteam.funkydomino.activity.FunkyDominoActivity;
import org.gmxteam.funkydomino.component.ComponentAttributes;
import org.gmxteam.funkydomino.component.loader.util.FunkyDominoEntityLoaderData;
import org.xml.sax.Attributes;

/**
 * Chargeur de la scène.
 *
 * Permet de charger la scène à partir des attributs définit dans la balise
 * level du fichier XML.
 *
 * @author Guillaume Poirier-Morency <guillaumepoiriermorency@gmail.com>
 */
public class SceneLoader implements IEntityLoader<FunkyDominoEntityLoaderData> {

    private final static String[] ENTITY_NAMES = {"level"};
    private BitmapTextureAtlas mBitmapTextureAtlas;
    private TextureRegion mTextureRegion;

    /**
     *
     * @return
     */
    public String[] getEntityNames() {

        return ENTITY_NAMES;
    }

    /**
     *
     * @param pEntityName
     * @param pParent
     * @param pAttributes
     * @param pEntityLoaderData
     * @return
     */
    @Override
    public IEntity onLoadEntity(String pEntityName, IEntity pParent, Attributes pAttributes, FunkyDominoEntityLoaderData pEntityLoaderData) {
        final ComponentAttributes pComponentAttributes = new ComponentAttributes(pAttributes);



        final float HEIGHT = pComponentAttributes.getFloat("height", pEntityLoaderData.getDrawableSurfaceDimensions().y),
                WIDTH = pComponentAttributes.getFloat("width", pEntityLoaderData.getDrawableSurfaceDimensions().x);
        
        final Scene pScene = pEntityLoaderData.getScene();

        Debug.v("La scène est de dimension " + WIDTH + " de largeur par " + HEIGHT + " de hauteur.");


        // Background


        mBitmapTextureAtlas = new BitmapTextureAtlas(pEntityLoaderData
                .getTextureManager(), pComponentAttributes.getInteger("background_width", 32), pComponentAttributes.getInteger("background_height", 32), FunkyDominoActivity.TEXTURE_OPTION);

        pEntityLoaderData.getBaseGameActivity()
                .getTextureManager().loadTexture(mBitmapTextureAtlas);

        mTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mBitmapTextureAtlas, pEntityLoaderData
                .getContext(), pComponentAttributes.getString("background", "background.png"), 0, 0);



        final RepeatingSpriteBackground mBackground = new RepeatingSpriteBackground(pEntityLoaderData
                .getDrawableSurfaceDimensions().x, pEntityLoaderData
                .getDrawableSurfaceDimensions().y, mTextureRegion, pEntityLoaderData.getBaseGameActivity()
                .getVertexBufferObjectManager());



        pScene.setBackground(mBackground);
        // Boxing the scene

        final FixtureDef limitsFixtureDef = PhysicsFactory.createFixtureDef(1.0f, 1.0f, 1.0f);



        float[][] lines = {
            {0.0f, 0.0f, WIDTH, 0.0f},
            {WIDTH, 0.0f, WIDTH, HEIGHT},
            {WIDTH, HEIGHT, 0.0f, HEIGHT},
            {0.0f, HEIGHT, 0.0f, 0.0f}
        };

        for (float[] points : lines) {

            final Line lineShape = new Line(points[0], points[1], points[2], points[3], pEntityLoaderData.getVertexBufferObjectManager());

            final Body lineBody = PhysicsFactory.createLineBody(pEntityLoaderData.getPhysicsWorld(), lineShape, limitsFixtureDef);

            pEntityLoaderData.getPhysicsWorld().registerPhysicsConnector(new PhysicsConnector(lineShape, lineBody));
            pScene.attachChild(lineShape);

        }

        // Refresh camera bounds
        pEntityLoaderData.getCamera().setBounds(0.0f, 0.0f, WIDTH, HEIGHT);



        return pScene;
    }
}
