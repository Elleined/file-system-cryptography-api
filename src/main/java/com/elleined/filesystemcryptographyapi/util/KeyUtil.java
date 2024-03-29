package com.elleined.filesystemcryptographyapi.util;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public interface KeyUtil {
    static String generateKey(int n, String algorithm) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(n);
        SecretKey secretKey = keyGenerator.generateKey();
        return Base64.getEncoder().withoutPadding().encodeToString(secretKey.getEncoded());
    }

    static String generateKey() throws NoSuchAlgorithmException {
        return generateKey(128, "AES");
    }

    static SecretKey recoverKey(String encodedKey) {
        return recoverKey(encodedKey, "AES");
    }

    static SecretKey recoverKey(String encodedKey, String algorithm) {
        byte[] encodedKeyBytes = Base64.getDecoder().decode(encodedKey);
        return new SecretKeySpec(encodedKeyBytes, algorithm);
    }
}
