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
package com.gmxteam.funkydomino.graphicals.components;

import com.gmxteam.funkydomino.activities.AndEngineActivity;
import com.gmxteam.funkydomino.graphicals.Graphical;
import org.xml.sax.Attributes;

/**
 * Classe abstraite définissant les composants. Les composants sont des éléments
 * affectés par la physique. On parle du sol, des dominos, des billes et autres
 * objets.
 * @author Guillaume Poirier-Morency
 */
public abstract class Component extends Graphical implements ComponentsConstants {
    /* Ici, on peut mettre toutes les variables définissant généralement les
     * composants, sans toutefois définir les widgets.
     */
    public static void loadResource(AndEngineActivity aea, Attributes a) {
    
    
    }
    
   
}
