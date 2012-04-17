/**
 * ***********************************************************************
 * Compilation: javac RSA.java Execution: java RSA N
 *
 * Generate an N-bit public and private RSA key and use to encrypt and decrypt a
 * random message.
 *
 * % java RSA 50 public = 65537 private = 553699199426609 modulus =
 * 825641896390631 message = 48194775244950 encrpyted = 321340212160104
 * decrypted = 48194775244950
 *
 * Known bugs (not addressed for simplicity)
 * ----------------------------------------- - It could be the case that the
 * message >= modulus. To avoid, use a do-while loop to generate key until
 * modulus happen to be exactly N bits.
 *
 * - It's possible that gcd(phi, publicKey) != 1 in which case the key
 * generation fails. This will only happen if phi is a multiple of 65537. To
 * avoid, use a do-while loop to generate keys until the gcd is 1.
 *
 ************************************************************************
 */
package com.gmxteam.funkydomino.utils.xmlparser.encrypt;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {

    private final static BigInteger one = new BigInteger("1");
    private final static SecureRandom random = new SecureRandom();
    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger modulus;

    // generate an N-bit (roughly) public and private key
    RSA(int N) {
        BigInteger p = BigInteger.probablePrime(N / 2, random);
        BigInteger q = BigInteger.probablePrime(N / 2, random);
        BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

        modulus = p.multiply(q);
        publicKey = new BigInteger("65537");     // common value in practice = 2^16 + 1
        privateKey = publicKey.modInverse(phi);
    }

    /**
     * Constructeur pour encrypter seulement.
     *
     * @param N
     * @param privateKey
     */
    public RSA(int N, BigInteger privateKey) {
        this(N);
        this.privateKey = privateKey;
    }

    ////////////////////////////////////////////////////////////////////////////
    // Adaptation du code original.
    /**
     * Constructeur avec des valeur pré-existantes.
     *
     * @param modulus
     * @param publicKey
     * @param privateKey
     */
    RSA(BigInteger modulus, BigInteger publicKey, BigInteger privateKey) {
        this.modulus = modulus;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }
    ////////////////////////////////////////////////////////////////////////////

    BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, modulus);
    }

    BigInteger decrypt(BigInteger encrypted) {
        return encrypted.modPow(privateKey, modulus);
    }

    ////////////////////////////////////////////////////////////////////////////
    // Adaptation du code original.
    public byte encrypt(byte b) {
        return encrypt(BigInteger.valueOf(b)).byteValue();
    }

    public byte decrypt(byte b) {
        return decrypt(BigInteger.valueOf(b)).byteValue();
    }
    ////////////////////////////////////////////////////////////////////////////
}
// Copyright © 2000–2011, Robert Sedgewick and Kevin Wayne. 
// Last updated: Wed Feb 9 09:20:16 EST 2011.

