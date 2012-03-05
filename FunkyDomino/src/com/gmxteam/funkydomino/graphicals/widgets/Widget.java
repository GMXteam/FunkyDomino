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
package com.gmxteam.funkydomino.graphicals.widgets;

import com.gmxteam.funkydomino.activities.AndEngineActivity;
import com.gmxteam.funkydomino.graphicals.Graphical;
import org.xml.sax.Attributes;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public abstract class Widget extends Graphical implements WidgetsConstants {

    /**
     * Charge les ressources spécifique à tous les Widgets.
     * @param mAndEngineActivity
     * @param atts 
     */
    public static void loadResource(AndEngineActivity mAndEngineActivity, Attributes atts) {
    }
    /**
     * 
     */
    public boolean isEnabled;
}
