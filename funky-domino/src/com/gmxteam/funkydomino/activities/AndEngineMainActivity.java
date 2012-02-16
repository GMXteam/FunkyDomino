/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.activities;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.ui.activity.BaseGameActivity;

/**
 *
 * @author guillaume
 */
public class AndEngineMainActivity extends BaseGameActivity{

    public Engine onLoadEngine() {
        return mEngine;
       
    }

    public void onLoadResources() {
      
    }

    public Scene onLoadScene() {
     return new Scene();
    }

    public void onLoadComplete() {
       
    }
    
}
