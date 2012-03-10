/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.utils.xmlparser;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guillaume
 */
class DecryptedStream extends InputStream {

    private InputStream stdin;
    private String publicKey;

    /**
     * 
     * @param resourceStream
     * @param publicKey 
     */
    DecryptedStream(InputStream resourceStream, String publicKey) {
        this.publicKey = publicKey;
        stdin = resourceStream;

    }

    @Override
    public int read() throws IOException {
        return stdin.read();
       
    }
    
    /**
     * Détruit la référence vers la clé publique.
     */
    void destroyNow() {
        publicKey = null;
        stdin = null;
    }
    
}
