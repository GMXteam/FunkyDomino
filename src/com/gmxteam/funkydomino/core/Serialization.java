/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import org.andengine.util.debug.Debug;

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
