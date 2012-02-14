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
package com.gmxteam.funkydomino.activities;

import android.os.Bundle;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public class LoadGameActivity extends JBox2DOpenGLActivity
{
     private String publicKey0 = getString(R.string.key_0),
            publicKey1 = getString(R.string.key_1),
            publicKey2 = getString(R.string.key_2),
            publicKey3 = getString(R.string.key_3),
            publicKey4 = getString(R.string.key_4),
            publicKey5 = getString(R.string.key_5),
            publicKey6 = getString(R.string.key_6),
            publicKey7 = getString(R.string.key_7);
     
     @Override
    public void onCreate(Bundle ressources) {
        super.onCreate(ressources);
               
    }
}
