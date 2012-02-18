/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.activities;


import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.view.Menu;
import android.view.MenuItem;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 22:10:28 - 11.04.2010
 */
public abstract class BaseExample extends BaseGameActivity {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final int MENU_TRACE = Menu.FIRST;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

        /**
         * 
         * @param pMenu
         * @return
         */
        @Override
	public boolean onCreateOptionsMenu(final Menu pMenu) {
		pMenu.add(Menu.NONE, MENU_TRACE, Menu.NONE, "Start Method Tracing");
		return super.onCreateOptionsMenu(pMenu);
	}

        /**
         * 
         * @param pMenu
         * @return
         */
        @Override
	public boolean onPrepareOptionsMenu(final Menu pMenu) {
		pMenu.findItem(MENU_TRACE).setTitle(this.mEngine.isMethodTracing() ? "Stop Method Tracing" : "Start Method Tracing");
		return super.onPrepareOptionsMenu(pMenu);
	}

        /**
         * 
         * @param pFeatureId
         * @param pItem
         * @return
         */
        @Override
	public boolean onMenuItemSelected(final int pFeatureId, final MenuItem pItem) {
		switch(pItem.getItemId()) {
			case MENU_TRACE:
				if(this.mEngine.isMethodTracing()) {
					this.mEngine.stopMethodTracing();
				} else {
					this.mEngine.startMethodTracing("AndEngine_" + System.currentTimeMillis() + ".trace");
				}
				return true;
			default:
				return super.onMenuItemSelected(pFeatureId, pItem);
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
