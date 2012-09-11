/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.core.component.factory;

import com.gmxteam.funkydomino.activity.GameActivity;
import com.gmxteam.funkydomino.activity.IFunkyDominoBaseActivity;
import org.andengine.entity.IEntity;
import org.andengine.util.debug.Debug;
import org.andengine.util.level.IEntityLoader;
import org.xml.sax.Attributes;

/**
 *
 * @author guillaume
 */
public class ComponentLoader implements IEntityLoader {

    public ComponentLoader(IFunkyDominoBaseActivity pGameActivity) {
        mGameActivity = pGameActivity;
    }
    private IFunkyDominoBaseActivity mGameActivity;

    public IEntity onLoadEntity(String string, Attributes atts) {
        IEntity currentEntity = null;

        try {
            currentEntity = Components.valueOf(string).getComponent().factory(mGameActivity, new ComponentAttributes(atts));
        } catch (InstantiationException ex) {

            Debug.e(ex.getMessage(), ex);
        } catch (IllegalAccessException ex) {
            Debug.e(ex.getMessage(), ex);
        }

        return currentEntity;



    }
}
