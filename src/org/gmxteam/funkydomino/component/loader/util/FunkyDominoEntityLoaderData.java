/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gmxteam.funkydomino.component.loader.util;

import org.andengine.util.level.IEntityLoaderData;
import org.andengine.util.level.simple.SimpleLevelEntityLoaderData;
import org.gmxteam.funkydomino.activity.IBaseGameActivity;

/**
 *
 * @author guillaume
 */
public class FunkyDominoEntityLoaderData implements IEntityLoaderData {

    private final IBaseGameActivity mBaseGameActivity;

    // ===========================================================
    // Constructors
    // ===========================================================
    public FunkyDominoEntityLoaderData(final IBaseGameActivity pBaseGameActivity) {
        this.mBaseGameActivity = pBaseGameActivity;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================
    public IBaseGameActivity getBaseGameActivity() {
        return this.mBaseGameActivity;
    }
}
