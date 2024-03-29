package com.elleined.filesystemcryptographyapi.util;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

public interface AESUtil {
    static SecretKey generateKey(int n, String algorithm) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(n);
        return keyGenerator.generateKey();
    }

    static SecretKey generateKey() throws NoSuchAlgorithmException {
        return generateKey(128, "AES");
    }

    static SecretKey recoverSecretKey(byte[] encodedKey) {
        return new SecretKeySpec(encodedKey, "AES");
    }
}
