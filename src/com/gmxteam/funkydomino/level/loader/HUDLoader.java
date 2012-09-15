/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.level.loader;

import com.gmxteam.funkydomino.activity.IBaseGameActivity;
import com.gmxteam.funkydomino.component.ComponentFactory;
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
    public HUDLoader(IBaseGameActivity pGameActivity) {
        mGameActivity = pGameActivity;

    }
    private final IBaseGameActivity mGameActivity;

    /**
     *
     * @param pEntityName
     * @param pAttributes
     * @return
     */
    public IEntity onLoadEntity(String pEntityName, Attributes pAttributes) {

        final HUD mHUD = new HUD();
        mHUD.setCamera(mGameActivity.getCamera());


        return mHUD;

    }
}
