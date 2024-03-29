package com.elleined.filesystemcryptographyapi.util;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

public interface KeyUtil {
    static SecretKey generateKey(int n, String algorithm) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(n);
        return keyGenerator.generateKey();
    }

    static SecretKey generateKey() throws NoSuchAlgorithmException {
        return generateKey(128, "AES");
    }

    static SecretKey recoverKey(byte[] encodedKey) {
        return new SecretKeySpec(encodedKey, "AES");
    }
    static SecretKey recoverKey(byte[] encodedKey, String algorithm) {
        return new SecretKeySpec(encodedKey, algorithm);
    }
}