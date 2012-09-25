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
package org.gmxteam.funkydomino.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import org.andengine.util.debug.Debug;

/**
 *
 * @author guillaume
 */
public class Serialization {

    /**
     *
     * @param o
     * @return
     */
    public static String serialize(Object o) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();


        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            oos.close();
            return baos.toString();
        } catch (IOException ex) {
            Debug.e(ex);
            return null;
        }
    }

    /**
     *
     * @param o
     * @return
     */
    public static Object unserialize(String o) {

        ByteArrayInputStream bais = new ByteArrayInputStream(o.getBytes());

        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object output = ois.readObject();
            
            ois.close();

            return output;
        } catch (StreamCorruptedException ex) {
            Debug.e(ex);
        } catch (IOException ex) {
            Debug.e(ex);
        } catch (ClassNotFoundException ex) {
            Debug.e(ex);
        } 
        
            
        return null;

    }
}
