package com.elleined.filesystemcryptographyapi.decryptor;

import com.elleined.filesystemcryptographyapi.encryptor.EncryptorService;
import com.elleined.filesystemcryptographyapi.encryptor.EncryptorServiceImpl;
import com.elleined.filesystemcryptographyapi.util.IVUtil;
import com.elleined.filesystemcryptographyapi.util.KeyUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DecryptorServiceImplTest {

    @Test
    void stringDecrypt() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        // Expected Value
        EncryptorService encryptorService = new EncryptorServiceImpl();
        DecryptorService decryptorService = new DecryptorServiceImpl();

        // Mock data
        String data = "Hello World! Hello World! Hello World!";
        String encodedKey = KeyUtil.generateKey();
        SecretKey secretKey = KeyUtil.recoverKey(encodedKey);
        String encodeIv = IVUtil.generateIvBytes();
        IvParameterSpec iv = IVUtil.recoverIv(encodeIv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // Set up method

        // Stubbing methods

        // Calling the method
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        String encryptedText = encryptorService.encrypt(cipher, secretKey, iv, data);

        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        String originalValue = decryptorService.decrypt(cipher, secretKey, iv, encryptedText);

        // Behavior Verifications

        // Assertions
        assertNotNull(encryptedText);

        assertNotNull(originalValue);
        assertEquals(data, originalValue);
    }

    @Test
    void fileDecrypt() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        // Expected Value
        EncryptorService encryptorService = new EncryptorServiceImpl();
        DecryptorService decryptorService = new DecryptorServiceImpl();

        // Mock data
        String encodedKey = KeyUtil.generateKey();
        SecretKey secretKey = KeyUtil.recoverKey(encodedKey);
        String encodeIv = IVUtil.generateIvBytes();
        IvParameterSpec iv = IVUtil.recoverIv(encodeIv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // Set up method

        // Stubbing methods

        // Calling the method
        File normalFile = new File("./src/test/resources/decryptor/normal-file.txt");
        File encrypted = new File("./src/test/resources/decryptor/encrypted-file.txt");
        File output = new File("./src/test/resources/decryptor/output.txt");

        // Calling the method
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        assertDoesNotThrow(() -> encryptorService.encrypt(cipher, secretKey, iv, normalFile, encrypted));

        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        assertDoesNotThrow(() -> decryptorService.decrypt(cipher, secretKey, iv, encrypted, output));
        // Behavior Verifications

        // Assertions
    }

    @Test
    void decrypt() throws NoSuchAlgorithmException, IOException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        // Expected Value
        EncryptorService encryptorService = new EncryptorServiceImpl();
        DecryptorService decryptorService = new DecryptorServiceImpl();

        // Mock data
        String encodedKey = KeyUtil.generateKey();
        SecretKey secretKey = KeyUtil.recoverKey(encodedKey);
        IvParameterSpec iv = IVUtil.recoverIv();
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        boolean isRecursive = true;

        // Set up method
        Path directory = Paths.get("./src/test/resources/parentFolder");

        // Stubbing methods

        // Calling the method
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        encryptorService.encrypt(cipher, secretKey, iv, directory, isRecursive);

        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        decryptorService.decrypt(cipher, secretKey, iv, directory, isRecursive);

        // Behavior Verifications

        // Assertions
    }
}