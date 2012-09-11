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

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.widget.SimpleExpandableListAdapter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Activité pour charger une partie. Affiche une liste de parties.
 *
 * @author Guillaume Poirier-Morency
 */
public final class LoadGameActivity extends ExpandableListActivity {

	/**
     *
     * @param b
     */
    @Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.load_game);

		List<Map<String, String>> groupData = new LinkedList<Map<String, String>>();
		String[] groupFrom = new String[0];
		int[] groupTo = new int[0];
		List<List<Map<String, String>>> childData = new LinkedList<List<Map<String, String>>>();
		
		List<Map<String, String>> rowData = new LinkedList<Map<String, String>>();
		Map<String, String> data = new HashMap<String, String>();
		data.put("saved_game_1", "Some shits");
		rowData.add(data);
		childData.add(rowData);
		
		
		String[] childFrom = new String[0];
		int[] childTo = new int[0];

		SimpleExpandableListAdapter sela = new SimpleExpandableListAdapter(
			this,
			groupData, R.id.list_group,
			groupFrom, groupTo,
			childData,
			R.id.list_child, childFrom, childTo);


		this.setListAdapter(sela);


	}
}
