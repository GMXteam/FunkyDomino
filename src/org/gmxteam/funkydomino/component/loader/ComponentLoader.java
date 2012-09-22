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
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.util.debug.Debug;
import org.andengine.util.level.IEntityLoader;
import org.gmxteam.funkydomino.component.ComponentAttributes;
import org.gmxteam.funkydomino.component.IComponent;
import org.gmxteam.funkydomino.component.loader.util.FunkyDominoEntityLoaderData;
import org.xml.sax.Attributes;

/**
 *
 * @author guillaume
 */
public abstract class ComponentLoader implements IEntityLoader<FunkyDominoEntityLoaderData> {

    /**
     *
     */
    protected BitmapTextureAtlas mBitmapTextureAtlas;
    /**
     *
     */
    protected TextureRegion mTextureRegion;
    /**
     *
     */
    protected FixtureDef mFixtureDef = new FixtureDef();

    /**
     *
     * @param pEntityName
     * @param pParent
     * @param pAttributes
     * @param pEntityLoaderData
     * @return
     */
    public abstract IComponent onLoadEntity(String pEntityName, IEntity pParent, ComponentAttributes pAttributes, FunkyDominoEntityLoaderData pEntityLoaderData);

    /**
     *
     * @param pEntityName
     * @param pParent
     * @param pAttributes
     * @param pEntityLoaderData
     * @return
     */
    public IEntity onLoadEntity(String pEntityName, IEntity pParent, Attributes pAttributes, FunkyDominoEntityLoaderData pEntityLoaderData) {
        final ComponentAttributes componentAttributes = new ComponentAttributes(pAttributes);
        // Base loading...
        mFixtureDef.density = componentAttributes.getDensity();
        mFixtureDef.friction = componentAttributes.getFriction();

        final IComponent entity = onLoadEntity(pEntityName, pParent, componentAttributes, pEntityLoaderData);

        final Body body = entity.onCreateBody(pEntityLoaderData.getPhysicsWorld(), mFixtureDef);

        if (body != null) {
            // Ajout du connecteur physique
            pEntityLoaderData.getPhysicsWorld().registerPhysicsConnector(new PhysicsConnector(entity, body));

            // Binding avec le gestionnaire de contacts
            pEntityLoaderData.getContactManager().registerContactListener(body, entity);
        } else {
            Debug.v("Le body " + body + " de l'entité " + entity + " est null.");

        }


        // Binding avec le touch de la scène
        pEntityLoaderData.getScene().registerTouchArea(entity);



        return entity;

    }
}
