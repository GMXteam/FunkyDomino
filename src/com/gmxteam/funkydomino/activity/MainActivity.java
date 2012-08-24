/*
 *   This file is part of Funky Domino.
 *
 *   Funky Domino is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Funky Domino is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Funky Domino.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gmxteam.funkydomino.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Activité principale de FunkyDomino. Permet d'accéder à : FunkyDominoActivity
 * pour jouer la dernière partie sauvegardée LoadGameActivity pour charger une
 * partie NewGameActivity pour créer une nouvelle partie PreferencesActivity
 * pour changer les préférences de FunkyDomino
 *
 * @author Guillaume Poirier-Morency
 */
public final class MainActivity extends Activity {

	////////////////////////////////////////////////////////////////////////////
	// Menus
	public void onHighscoresMenuItemClick(MenuItem mi) {
		startActivity(new Intent(MainActivity.this, HighscoresActivity.class));

	}

	public void onPreferencesMenuItemClick(MenuItem mi) {
		startActivity(new Intent(MainActivity.this, PreferencesActivity.class));

	}

	////////////////////////////////////////////////////////////////////////////
	// Listeners
	public void onPlayClick(View v) {

		
		
		Intent i = new Intent(MainActivity.this, GameActivity.class);
		startActivity(i);


	}

	public void onNewGameClick(View v) {
		Intent i = new Intent(MainActivity.this, GameActivity.class);
		startActivity(i);

	}

	public void onLoadGameClick(View v) {
		startActivity(new Intent(MainActivity.this, LoadGameActivity.class));


	}

	////////////////////////////////////////////////////////////////////////////
	/**
	 *
	 * @param b
	 */
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		this.setContentView(R.layout.main);
		
		





	}

	@Override
	public boolean onCreateOptionsMenu(Menu m) {
		getMenuInflater().inflate(R.menu.menu, m);
		return true;
	}
;
}
