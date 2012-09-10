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

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import java.util.List;

/**
 * Activité utilisée pour configurer Funky Domino. Il s'agit d'une simple
 * activité de type liste.
 *
 * @author Guillaume Poirier-Morency
 */
public final class PreferencesActivity extends PreferenceActivity {

    /**
     * Populate the activity with the top-level headers.
     */
    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.preferences_headers, target);

    }

    public static class FunkyDominoGraphicsPreferenceFragment extends PreferenceFragment {

        /**
         *
         * @param b
         */
        @Override
        public void onCreate(Bundle b) {
            super.onCreate(b);

            

            addPreferencesFromResource(R.layout.preference_graphic);


        }
    }

    public static class FunkyDominoAudioPreferenceFragment extends PreferenceFragment {

        /**
         *
         * @param b
         */
        @Override
        public void onCreate(Bundle b) {
            super.onCreate(b);

            

            addPreferencesFromResource(R.layout.preference_audio);

        }
    }

    public static class FunkyDominoAboutPreferenceFragment extends PreferenceFragment {

        /**
         *
         * @param b
         */
        @Override
        public void onCreate(Bundle b) {
            super.onCreate(b);
            



            addPreferencesFromResource(R.layout.preference_about);

        }
    }
}
