/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.utils.xmlparser;

import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author guillaume
 */
class DecryptedStream extends InputStream {

    private final InputStream stdin;
    private final String publicKey;

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
    
}
