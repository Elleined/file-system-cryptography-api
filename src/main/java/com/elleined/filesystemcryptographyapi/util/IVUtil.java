package com.elleined.filesystemcryptographyapi.util;

import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;

public interface IVUtil {

    static byte[] generateIvBytes(int n) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] iv = new byte[n];
        secureRandom.nextBytes(iv);
        return iv;
    }

    static byte[] generateIvBytes() {
        return generateIvBytes(16);
    }

    static IvParameterSpec generateIv(byte[] encodedIv) {
        return new IvParameterSpec(encodedIv);
    }

    static IvParameterSpec generateIv() {
        return new IvParameterSpec(generateIvBytes());
    }
}
