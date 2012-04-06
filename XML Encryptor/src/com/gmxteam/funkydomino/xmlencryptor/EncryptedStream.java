/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmxteam.funkydomino.utils.xmlparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Utilisé pour encrypter les fichiers XMLs.
 *
 * @author guillaume
 */
public class EncryptedStream extends OutputStream {

    private final OutputStream stdout;
    private final String privatekey;

    /**
     *
     * @param resourceStream
     * @param publicKey
     */
    EncryptedStream(OutputStream resourceStream) {
        String tempKey = "";
        System.out.println("Please enter the private key in order to encrypt this stream");
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        try {
            tempKey = stdin.readLine();
        } catch (IOException ex) {
            System.exit(0);

        }
        privatekey = tempKey;
        stdout = resourceStream;

    }

    /**
     *
     * @param resourceStream
     * @param publicKey
     */
    EncryptedStream(OutputStream resourceStream, String privateKey) {
        this.privatekey = privateKey;
        stdout = resourceStream;
    }

    @Override
    public void write(int oneByte) throws IOException {
        stdout.write(oneByte);
    }
}
