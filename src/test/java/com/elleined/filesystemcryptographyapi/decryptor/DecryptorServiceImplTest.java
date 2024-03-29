package com.elleined.filesystemcryptographyapi.decryptor;

import com.elleined.filesystemcryptographyapi.encryptor.EncryptorService;
import com.elleined.filesystemcryptographyapi.encryptor.EncryptorServiceImpl;
import com.elleined.filesystemcryptographyapi.util.KeyUtil;
import com.elleined.filesystemcryptographyapi.util.IVUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DecryptorServiceImplTest {

    @Test
    void decrypt() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        // Expected Value
        EncryptorService encryptorService = new EncryptorServiceImpl();
        DecryptorService decryptorService = new DecryptorServiceImpl();

        // Mock data
        String data = "Hello World!";
        SecretKey secretKey = KeyUtil.generateKey();
        IvParameterSpec iv = IVUtil.generateIv();

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
    void testDecrypt() throws NoSuchAlgorithmException {
        // Expected Value
        EncryptorService encryptorService = new EncryptorServiceImpl();
        DecryptorService decryptorService = new DecryptorServiceImpl();

        // Mock data
        SecretKey secretKey = KeyUtil.generateKey();
        IvParameterSpec iv = IVUtil.generateIv();

        // Set up method

        // Stubbing methods

        // Calling the method
        File normalFile = new File("./src/test/resources/decryptor/normal-file.txt");
        File encrypted = new File("./src/test/resources/decryptor/encrypted-file.txt");
        File output = new File("./src/test/resources/decryptor/output.txt");

        // Calling the method
        assertDoesNotThrow(() -> encryptorService.encrypt(secretKey, iv, normalFile, encrypted));
        assertDoesNotThrow(() -> decryptorService.decrypt(secretKey, iv, encrypted, output));

        // Behavior Verifications

        // Assertions
    }
}