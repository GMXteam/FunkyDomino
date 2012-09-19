/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gmxteam.funkydomino.component.loader.util;

import java.util.HashMap;
import org.andengine.entity.scene.Scene;
import org.andengine.util.level.IEntityLoader;
import org.andengine.util.level.IEntityLoaderListener;
import org.andengine.util.level.LevelLoaderContentHandler;

/**
 *
 * @author guillaume
 */
public class FunkyDominoLoaderContentHandler extends LevelLoaderContentHandler<FunkyDominoEntityLoaderData, IEntityLoaderListener, FunkyDominoLoaderResult>{

    public FunkyDominoLoaderContentHandler(final IEntityLoader<FunkyDominoEntityLoaderData> pDefaultEntityLoader, final HashMap<String, IEntityLoader<FunkyDominoEntityLoaderData>> pEntityLoaders, FunkyDominoEntityLoaderData pEntityLoaderData) {
        super(pDefaultEntityLoader, pEntityLoaders, pEntityLoaderData);
    }

    @Override
    public  FunkyDominoLoaderResult getLevelLoaderResult() {
        return new FunkyDominoLoaderResult((Scene) this.mRootEntity);
    }
}
