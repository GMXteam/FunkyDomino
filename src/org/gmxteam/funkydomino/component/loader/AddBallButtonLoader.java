/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gmxteam.funkydomino.component.loader;

import org.andengine.entity.IEntity;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.gmxteam.funkydomino.component.ComponentAttributes;
import org.gmxteam.funkydomino.component.IComponent;
import org.gmxteam.funkydomino.component.entity.AddDominoButton;
import org.gmxteam.funkydomino.component.loader.util.FunkyDominoEntityLoaderData;

/**
 *
 * @author guillaume
 */
public class AddBallButtonLoader extends ComponentLoader {

    private ITextureRegion mTextureRegionClicked;

    @Override
    public IComponent onLoadEntity(String pEntityName, IEntity pParent, ComponentAttributes pAttributes, FunkyDominoEntityLoaderData pEntityLoaderData) {
        return new AddDominoButton(pAttributes, mTextureRegion, mTextureRegionClicked, pEntityLoaderData.getVertexBufferObjectManager());
    }

    public String[] getEntityNames() {
        final String[] names = {"add_ball_button"};
        return names;
    }
}
