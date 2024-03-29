package com.elleined.filesystemcryptographyapi.encryptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.elleined.filesystemcryptographyapi.util.AESUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@ExtendWith(MockitoExtension.class)
class EncryptorServiceImplTest {

    @Test
    void stringEncrypt() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        // Expected Value
        EncryptorService encryptorService = new EncryptorServiceImpl();

        // Mock data
        SecretKey secretKey = AESUtil.generateKey(128);
        IvParameterSpec iv = AESUtil.generateIv();

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
        SecretKey secretKey = AESUtil.generateKey(128);
        IvParameterSpec iv = AESUtil.generateIv();

        // Set up method

        // Stubbing methods

        // Calling the method
        File input = new File("./src/test/resources/input.txt");
        File output = new File("./src/test/resources/output.txt");

        // Calling the method
        assertDoesNotThrow(() -> encryptorService.encrypt(secretKey, iv, input, output));

        // Behavior Verifications

        // Assertions
    }
}