/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.utils.xmlparser.encrypt;

import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author guillaume
 */
public class DecryptedStream extends InputStream {

    private final InputStream stdin;
    private final String publicKey;

    /**
     * 
     * @param resourceStream
     * @param publicKey 
     */
    public DecryptedStream(InputStream resourceStream, String publicKey) {
        this.publicKey = publicKey;
        stdin = resourceStream;

    }

    @Override
    public int read() throws IOException {
        return stdin.read();
       
    }
    
}
