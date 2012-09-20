/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gmxteam.funkydomino.component.loader;

import java.io.IOException;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.IEntity;
import org.andengine.util.level.IEntityLoaderData;
import org.gmxteam.funkydomino.activity.IBaseGameActivity;
import org.gmxteam.funkydomino.component.ComponentAttributes;
import org.gmxteam.funkydomino.component.loader.util.FunkyDominoEntityLoaderData;

/**
 *
 * @author guillaume
 */
public class HUDLoader extends ComponentLoader {

    /**
     *
     * @param pGameActivity
     */
    public HUDLoader(IBaseGameActivity pGameActivity) {
        super(pGameActivity);
        
    }
    private final static String[] ENTITY_NAMES = {"hud"};

    /**
     *
     * @return
     */
    public String[] getEntityNames() {
        return ENTITY_NAMES;
    }
    
    /**
     *
     * @param pEntityName
     * @param pParent
     * @param pAttributes
     * @param pEntityLoaderData
     * @return
     */
    @Override
    public IEntity onLoadEntity(String pEntityName, IEntity pParent, ComponentAttributes pAttributes, FunkyDominoEntityLoaderData pEntityLoaderData) {
        final HUD mHUD = new HUD();
        mHUD.setCamera(pEntityLoaderData.getBaseGameActivity().getCamera());
        pEntityLoaderData.getBaseGameActivity().setHUD(mHUD);
        return mHUD;
    }
}
