/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.core;

import com.gmxteam.funkydomino.activity.IFunkyDominoBaseActivity;
import com.gmxteam.funkydomino.core.component.factory.ComponentFactory;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.IEntity;
import org.andengine.util.debug.Debug;
import org.andengine.util.level.IEntityLoader;
import org.xml.sax.Attributes;

/**
 *
 * @author guillaume
 */
public class HUDLoader implements IEntityLoader {

    /**
     *
     * @param pGameActivity
     */
    public HUDLoader(IFunkyDominoBaseActivity pGameActivity) {
        mGameActivity = pGameActivity;

    }
    private final IFunkyDominoBaseActivity mGameActivity;

    /**
     *
     * @param pEntityName
     * @param pAttributes
     * @return
     */
    public IEntity onLoadEntity(String pEntityName, Attributes pAttributes) {

        final HUD mHUD = new HUD();
        mHUD.setCamera(mGameActivity.getCamera());
        try {
            mHUD.attachChild(ComponentFactory.createAddDominoButton(0.0f, 0.0f));
        } catch (InstantiationException ex) {
            Debug.e(ex);
        } catch (IllegalAccessException ex) {
            Debug.e(ex);
        }
        return mHUD;

    }
}
