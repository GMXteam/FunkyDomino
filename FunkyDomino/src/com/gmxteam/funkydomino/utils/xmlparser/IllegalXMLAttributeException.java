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
package com.gmxteam.funkydomino.utils.xmlparser;

import android.util.Log;
import com.gmxteam.funkydomino.activities.AndEngineActivityConstants;

/**
 * Exception lancée lorsqu'un attribut ou une balise est invalide.
 * @author guillaume
 */
public final class IllegalXMLAttributeException extends RuntimeException implements AndEngineActivityConstants {

    /**
     * 
     * @param message
     */
    public IllegalXMLAttributeException(String message) {
        super(message);
        Log.e(APP_LOG_NAME, message);
    }
    
    /**
     * 
     * @param message
     * @param t  
     */
    public IllegalXMLAttributeException(String message, Throwable t) {
        super(message, t);
        Log.e(APP_LOG_NAME, message, t);
    }
}
