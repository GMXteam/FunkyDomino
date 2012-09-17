/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.component;

import com.gmxteam.funkydomino.activity.IBaseGameActivity;
import com.gmxteam.funkydomino.component.ComponentAttributes;
import com.gmxteam.funkydomino.component.Components;
import java.io.IOException;
import org.andengine.entity.IEntity;
import org.andengine.util.debug.Debug;
import org.andengine.util.level.IEntityLoader;
import org.andengine.util.level.IEntityLoaderData;
import org.xml.sax.Attributes;

/**
 *
 * @author guillaume
 */
public class ComponentLoader implements IEntityLoader {

    /**
     *
     * @param pGameActivity
     */
    public ComponentLoader(IBaseGameActivity pGameActivity) {
        mGameActivity = pGameActivity;
    }
    private final IBaseGameActivity mGameActivity;


    public String[] getEntityNames() {
        return Components.strings();
    }

    public IEntity onLoadEntity(String pEntityName, IEntity pParent, Attributes pAttributes, IEntityLoaderData pEntityLoaderData) throws IOException {
         IEntity currentEntity = null;
         
        try {
            currentEntity = Components.valueOf(pEntityName).getComponent().factory(mGameActivity, new ComponentAttributes(pAttributes)).getEntity();
        } catch (InstantiationException ex) {

            Debug.e(ex.getMessage(), ex);
        } catch (IllegalAccessException ex) {
            Debug.e(ex.getMessage(), ex);
        }

        return currentEntity;
    }
}
