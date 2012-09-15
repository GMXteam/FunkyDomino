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

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.gmxteam.funkydomino.core.Levels;
import java.util.HashSet;
import org.andengine.util.preferences.SimplePreferences;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public final class HighscoresActivity extends ListActivity {

    /**
     *
     * @param b
     */
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);

        HashSet<String> mDefaultData = new HashSet<String>();
        mDefaultData.add("Test");
        

        String[] listItems = (String[]) SimplePreferences.getInstance(this).getStringSet("highscores.values", mDefaultData).toArray();

        setListAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems));


    }
}
