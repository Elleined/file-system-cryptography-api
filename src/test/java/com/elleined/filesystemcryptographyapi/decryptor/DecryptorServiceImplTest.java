package com.elleined.filesystemcryptographyapi.decryptor;

import com.elleined.filesystemcryptographyapi.util.AESUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class DecryptorServiceImplTest {

    @Test
    void decrypt() throws NoSuchAlgorithmException {
        // Expected Value
        DecryptorService decryptorService = new DecryptorServiceImpl();

        // Mock data
        SecretKey secretKey = AESUtil.generateKey(128);
        IvParameterSpec iv = AESUtil.generateIv();

        // Set up method

        // Stubbing methods

        // Calling the method
        // String originalValue = decryptorService.decrypt(decryptorService, iv, );

        // Behavior Verifications

        // Assertions
//        assertNotNull(originalValue);
    }

    @Test
    void testDecrypt() {
        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method

        // Behavior Verifications

        // Assertions
    }
}