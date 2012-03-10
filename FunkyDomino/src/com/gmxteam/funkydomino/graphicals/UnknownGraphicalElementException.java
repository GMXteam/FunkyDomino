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
package com.gmxteam.funkydomino.graphicals;

import android.util.Log;
import com.gmxteam.funkydomino.activities.FunkyDominoActivityConstants;

/**
 * Exception lancée lorsqu'un objet que l'on présume de type Graphical, mais 
 * s'avère être autre chose.
 * @author guillaume
 * @deprecated Cette exception n'est plus très utile.
 */
@Deprecated
public final class UnknownGraphicalElementException extends Exception implements FunkyDominoActivityConstants {    
    
    /**
     * 
     * @param message 
     */
    public UnknownGraphicalElementException(String message) {
        super(message);
        Log.e(APP_LOG_NAME, message);
    }
    
    /**
     * 
     * @param message 
     * @param t 
     */
    public UnknownGraphicalElementException(String message, Throwable t) {
        super(message, t);
        Log.e(APP_LOG_NAME, message, t);
    }
}
