package com.elleined.filesystemcryptographyapi.encryptor;

import com.elleined.filesystemcryptographyapi.util.IVUtil;
import com.elleined.filesystemcryptographyapi.util.KeyUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class EncryptorServiceImplTest {

    private final static String algorithm = "AES/CBC/PKCS5Padding";

    @Test
    void stringEncrypt() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        // Expected Value
        EncryptorService encryptorService = new EncryptorServiceImpl();

        // Mock data
        String encodedKey = KeyUtil.generateKey();
        SecretKey secretKey = KeyUtil.recoverKey(encodedKey);
        String encodeIv = IVUtil.generateIvBytes();
        IvParameterSpec iv = IVUtil.recoverIv(encodeIv);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        // Set up method

        // Stubbing methods

        // Calling the method
        String cipheredText = encryptorService.encrypt(cipher, secretKey, iv, "Hello World");

        // Behavior Verifications

        // Assertions
        assertNotNull(cipheredText);
    }

    @Test
    void fileEncrypt() throws NoSuchAlgorithmException, IOException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        // Expected Value
        EncryptorService encryptorService = new EncryptorServiceImpl();

        // Mock data
        String encodedKey = KeyUtil.generateKey();
        SecretKey secretKey = KeyUtil.recoverKey(encodedKey);
        IvParameterSpec iv = IVUtil.recoverIv();
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        // Set up method

        // Stubbing methods

        // Calling the method
        File normalFile = new File("./src/test/resources/encryptor/normal.txt");
        File output = new File("./src/test/resources/encryptor/encrypted.txt");

        // Calling the method
        assertDoesNotThrow(() -> encryptorService.encrypt(cipher, secretKey, iv, normalFile, output));

        // Behavior Verifications

        // Assertions
    }

    @Test
    void encrypt() throws NoSuchAlgorithmException, IOException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        // Expected Value
        EncryptorService encryptorService = new EncryptorServiceImpl();

        // Mock data
        String encodedKey = KeyUtil.generateKey();
        SecretKey secretKey = KeyUtil.recoverKey(encodedKey);
        IvParameterSpec iv = IVUtil.recoverIv();
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        boolean isRecursive = true;

        // Set up method
        Path directory = Paths.get("./src/test/resources/encryptor/parentFolder");

        // Stubbing methods

        // Calling the method
        encryptorService.encrypt(cipher, secretKey, iv, directory, isRecursive);

        // Behavior Verifications

        // Assertions
    }
}