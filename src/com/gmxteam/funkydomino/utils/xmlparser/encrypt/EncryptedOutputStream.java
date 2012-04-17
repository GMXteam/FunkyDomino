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
package com.gmxteam.funkydomino.utils.xmlparser.encrypt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Utilisé pour encrypter les fichiers XMLs. Seulement la méthode write est
 * recodée.
 *
 * @author Guillaume Poirier-Morency
 */
public class EncryptedOutputStream extends OutputStream {

    private final OutputStream stdout;
    private final char[] privatekey;
    private RSA encryptor;

    private void initRSA() {
        
        // On doit convertir la clé privée en BigInteger et la générer RSA.
    }
    
    /**
     * Constructeur pour le flux encrypté. Il demande d'entrer la clé privée en
     * entrée standard.
     *
     * @param resourceStream
     * @param publicKey
     */
    public EncryptedOutputStream(OutputStream resourceStream) {
        String tempKey = "";
        System.out.println("Please enter the private key in order to encrypt this stream");
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        try {
            tempKey = stdin.readLine();
        } catch (IOException ex) {
            // On niaise pas..
            System.exit(0);
        }
        privatekey = tempKey.toCharArray();
        tempKey = null;
        stdout = resourceStream;
    }

    /**
     * Constructeur pour le flux encrypté. La clé privée est spécifier en input.
     *
     * @param resourceStream
     * @param publicKey
     */
    public EncryptedOutputStream(OutputStream resourceStream, char[] privateKey) {
        this.privatekey = privateKey;
        stdout = resourceStream;
    }

    @Override
    public void write(int oneByte) throws IOException {
        this.write(this.intToByteArray(oneByte));
    }

    @Override
    public void write(byte[] oneArrayByte) throws IOException {
        for (int i = 0; i < oneArrayByte.length; i++) {
            oneArrayByte[i] = encryptOneByte(oneArrayByte[i]);
        }
        stdout.write(oneArrayByte);
    }

    @Override
    public void write(byte[] oneArrayByte, int i, int j) throws IOException {
        // On convertit seulement les portions de l'array qui est spécifiée
        for (int iCounter = i; iCounter < j; iCounter++) {
            oneArrayByte[iCounter] = encryptOneByte(oneArrayByte[iCounter]);
        }
        stdout.write(oneArrayByte, i, j);
    }

    @Override
    public void close() throws IOException {
        // On vide la clé et on ferme les flux.
        for (char c : this.privatekey) {
            c = 0;
        }
        stdout.close();
        super.close();
    }

    ////////////////////////////////////////////////////////////////////////////
    // Méthodes pour l'encryptage.
    /**
     * L'encryption RSA se fait dans cette méthode.
     * @param b
     * @return
     */
    private byte encryptOneByte(byte b) {        
        return encryptor.encrypt(b);
    }

    /**
     * Convertit un entier en tableau de byte afin de centraliser l'encryption
     * sur les bytes seulement.     * 
     * Fonction trouvée sur : http://snippets.dzone.com/posts/show/93     *
     * @param value est un entier qui sera convertit en tableau de byte,
     * @return un tableau de byte.
     */
    private byte[] intToByteArray(int value) {
        return new byte[]{
                    (byte) (value >>> 24),
                    (byte) (value >>> 16),
                    (byte) (value >>> 8),
                    (byte) value};
    }

    /**
     * http://snippets.dzone.com/posts/show/93. Pas besoin d'utiliser cette
     * fonction, on a seulement besoin d'encrypter byte par byte.
     *
     * @param b
     * @return
     */
    private int ByteArrayToInt(byte[] b) {
        return (b[0] << 24)
                + ((b[1] & 0xFF) << 16)
                + ((b[2] & 0xFF) << 8)
                + (b[3] & 0xFF);
    }
}
