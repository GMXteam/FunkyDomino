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
import com.gmxteam.funkydomino.core.Serialization;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import org.andengine.util.preferences.SimplePreferences;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public final class HighscoresActivity extends ListActivity {

    public final class Highscore implements Serializable {
        
        private static final long serialVersionUID = 148303765L;

        public Highscore(String pName, long pPoints) {
            NAME = pName;
            POINTS = pPoints;
        }
        public final String NAME;
        public final long POINTS;
        
        @Override
        public String toString() {
            return NAME + " " + POINTS;
        }
    }

    /**
     *
     * @param b
     */
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);

        ArrayList<Highscore> mDefault = new ArrayList<Highscore>();



        String serializedData = SimplePreferences.getInstance(this).getString("highscores.values", Serialization.serialize(mDefault));


        ArrayList<Highscore> data = (ArrayList<Highscore>) Serialization.unserialize(serializedData);

        

        setListAdapter(new ArrayAdapter<Highscore>(this, android.R.layout.simple_list_item_1, data));


    }
}
