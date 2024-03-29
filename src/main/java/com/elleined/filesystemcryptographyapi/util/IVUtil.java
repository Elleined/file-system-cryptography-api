package com.elleined.filesystemcryptographyapi.util;

import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

public interface IVUtil {

    static String generateIvBytes(int n) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] iv = new byte[n];
        secureRandom.nextBytes(iv);
        return Base64.getEncoder().withoutPadding().encodeToString(iv);
    }

    static String generateIvBytes() {
        return generateIvBytes(16);
    }

    static IvParameterSpec recoverIv(String encodedIv) {
        byte[] encodedIvBytes = Base64.getDecoder().decode(encodedIv);
        return new IvParameterSpec(encodedIvBytes);
    }

    static IvParameterSpec recoverIv() {
        return recoverIv(generateIvBytes());
    }
}
