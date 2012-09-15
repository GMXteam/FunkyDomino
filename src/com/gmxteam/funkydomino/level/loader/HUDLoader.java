/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.level.loader;

import com.gmxteam.funkydomino.activity.IBaseGameActivity;
import com.gmxteam.funkydomino.component.ComponentFactory;
import java.io.IOException;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.IEntity;
import org.andengine.util.debug.Debug;
import org.andengine.util.level.IEntityLoader;
import org.andengine.util.level.IEntityLoaderData;
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
    
    private final static String[] ENTITY_NAMES = {"hud"};

    public String[] getEntityNames() {
        return ENTITY_NAMES;
    }

    public IEntity onLoadEntity(String pEntityName, IEntity pParent, Attributes pAttributes, IEntityLoaderData pEntityLoaderData) throws IOException {
        final HUD mHUD = new HUD();
        mHUD.setCamera(mGameActivity.getCamera());


        return mHUD;
    }
}
