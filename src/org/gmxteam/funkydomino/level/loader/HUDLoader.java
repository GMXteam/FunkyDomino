/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gmxteam.funkydomino.level.loader;

import org.gmxteam.funkydomino.activity.IFunkyDominoActivity;
import java.io.IOException;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.IEntity;
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
    public HUDLoader(IFunkyDominoActivity pGameActivity) {
        mGameActivity = pGameActivity;

    }
    private final IFunkyDominoActivity mGameActivity;
    
    private final static String[] ENTITY_NAMES = {"hud"};

    /**
     *
     * @return
     */
    public String[] getEntityNames() {
        return ENTITY_NAMES;
    }

    public IEntity onLoadEntity(String pEntityName, IEntity pParent, Attributes pAttributes, IEntityLoaderData pEntityLoaderData) throws IOException {
        final HUD mHUD = new HUD();
        mHUD.setCamera(mGameActivity.getCamera());  
        
        mGameActivity.setHUD(mHUD);
        

        return mHUD;
    }
}
