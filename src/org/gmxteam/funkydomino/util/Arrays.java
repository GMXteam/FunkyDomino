/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gmxteam.funkydomino.util;

/**
 *
 * @author guillaume
 */
public class Arrays {

    /**
     *
     * @param A
     * @param B
     * @return
     */
    public static Object[] concat(Object[] A, Object[] B) {
        Object[] C = new Object[A.length + B.length];
        System.arraycopy(A, 0, C, 0, A.length);
        System.arraycopy(B, 0, C, A.length, B.length);

        return C;
    }

    /**
     *
     * @param A
     * @param B
     * @return
     */
    public static String[] concat(String[] A, String[] B) {
        String[] C = new String[A.length + B.length];
        System.arraycopy(A, 0, C, 0, A.length);
        System.arraycopy(B, 0, C, A.length, B.length);

        return C;
    }
}
