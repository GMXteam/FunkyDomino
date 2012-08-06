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
package com.gmxteam.funkydomino.xml.exception;

import android.util.Log;
import com.gmxteam.funkydomino.activity.GameActivityConstants;

/**
 * Exception utilisée lorsque la valeur d'un attribut est invalide.
 * @author guillaume
 */
public final class IllegalXMLAttributeValueException extends RuntimeException implements GameActivityConstants {

    /**
     * 
     * @param message
     */
    public IllegalXMLAttributeValueException(String message) {
        super(message);
        Log.e(APP_LOG_NAME, message);
    }

    /**
     * Constructeur acceptant les throwables lorsque l'on veut convertir une 
     * exception standard.
     * @param message
     * @param nfe 
     */
    public IllegalXMLAttributeValueException(String message, Throwable nfe) {
        super(message, nfe);
        Log.e(APP_LOG_NAME, message, nfe);
    }
}
