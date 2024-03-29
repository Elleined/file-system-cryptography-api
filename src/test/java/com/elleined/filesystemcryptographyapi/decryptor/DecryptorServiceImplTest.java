package com.elleined.filesystemcryptographyapi.decryptor;

import com.elleined.filesystemcryptographyapi.encryptor.EncryptorService;
import com.elleined.filesystemcryptographyapi.encryptor.EncryptorServiceImpl;
import com.elleined.filesystemcryptographyapi.util.AESUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class DecryptorServiceImplTest {

    @Test
    void decrypt() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        // Expected Value
        EncryptorService encryptorService = new EncryptorServiceImpl();
        DecryptorService decryptorService = new DecryptorServiceImpl();

        // Mock data
        String data = "Hello World!";
        SecretKey secretKey = AESUtil.generateKey(128);
        IvParameterSpec iv = AESUtil.generateIv();

        // Set up method

        // Stubbing methods

        // Calling the method
        String encryptedText = encryptorService.encrypt(secretKey, iv, data);
        String originalValue = decryptorService.decrypt(secretKey, iv, encryptedText);

        // Behavior Verifications

        // Assertions
        assertNotNull(encryptedText);

        assertNotNull(originalValue);
        assertEquals(data, originalValue);
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