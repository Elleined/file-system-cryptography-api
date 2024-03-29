package com.elleined.filesystemcryptographyapi.encryptor;

import com.elleined.filesystemcryptographyapi.util.IVUtil;
import com.elleined.filesystemcryptographyapi.util.KeyUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class EncryptorServiceImplTest {

    @Test
    void stringEncrypt() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        // Expected Value
        EncryptorService encryptorService = new EncryptorServiceImpl();

        // Mock data
        String encodedKey = KeyUtil.generateKey();
        SecretKey secretKey = KeyUtil.recoverKey(encodedKey);
        IvParameterSpec iv = IVUtil.generateIv();

        // Set up method

        // Stubbing methods

        // Calling the method
        String cipheredText = encryptorService.encrypt(secretKey, iv, "Hello World");

        // Behavior Verifications

        // Assertions
        assertNotNull(cipheredText);
    }

    @Test
    void fileEncrypt() throws NoSuchAlgorithmException, IOException {
        // Expected Value
        EncryptorService encryptorService = new EncryptorServiceImpl();

        // Mock data
        String encodedKey = KeyUtil.generateKey();
        SecretKey secretKey = KeyUtil.recoverKey(encodedKey);
        IvParameterSpec iv = IVUtil.generateIv();

        // Set up method

        // Stubbing methods

        // Calling the method
        File normalFile = new File("./src/test/resources/encryptor/normal.txt");
        File output = new File("./src/test/resources/encryptor/encrypted.txt");

        // Calling the method
        assertDoesNotThrow(() -> encryptorService.encrypt(secretKey, iv, normalFile, output));

        // Behavior Verifications

        // Assertions
    }
}