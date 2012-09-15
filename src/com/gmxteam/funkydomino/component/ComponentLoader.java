/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.component;

import com.gmxteam.funkydomino.activity.IBaseGameActivity;
import com.gmxteam.funkydomino.component.ComponentAttributes;
import com.gmxteam.funkydomino.component.Components;
import org.andengine.entity.IEntity;
import org.andengine.util.debug.Debug;
import org.andengine.util.level.IEntityLoader;
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
     * @param string
     * @param atts
     * @return
     */
    public IEntity onLoadEntity(String string, Attributes atts) {
        IEntity currentEntity = null;

        try {
            currentEntity = Components.valueOf(string).getComponent().factory(mGameActivity, new ComponentAttributes(atts)).getEntity();
        } catch (InstantiationException ex) {

            Debug.e(ex.getMessage(), ex);
        } catch (IllegalAccessException ex) {
            Debug.e(ex.getMessage(), ex);
        }

        return currentEntity;



    }
}
