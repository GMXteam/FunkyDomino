/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gmxteam.funkydomino.component;

import org.gmxteam.funkydomino.activity.IBaseGameActivity;
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


    /**
     *
     * @return
     */
    public String[] getEntityNames() {
        return Components.strings();
    }

    public IEntity onLoadEntity(String pEntityName, IEntity pParent, Attributes pAttributes, IEntityLoaderData pEntityLoaderData) throws IOException {
         IEntity currentEntity = null;
         
        try {
            currentEntity = Components.valueOf(pEntityName).getComponent().factory(mGameActivity, new ComponentAttributes(pAttributes));
        } catch (InstantiationException ex) {

            Debug.e(ex.getMessage(), ex);
        } catch (IllegalAccessException ex) {
            Debug.e(ex.getMessage(), ex);
        }

        return currentEntity;
    }
}
