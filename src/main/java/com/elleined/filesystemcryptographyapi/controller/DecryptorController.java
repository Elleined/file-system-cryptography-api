package com.elleined.filesystemcryptographyapi.controller;

import com.elleined.filesystemcryptographyapi.decryptor.DecryptorService;
import com.elleined.filesystemcryptographyapi.util.IVUtil;
import com.elleined.filesystemcryptographyapi.util.KeyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/decrypt")
public class DecryptorController {
    private final DecryptorService decryptorService;

    @Value("${cipher.algorithm}")
    private String algorithm;

    @PostMapping("/string")
    public String decrypt(@RequestParam("encodedKey") String encodedKey,
                          @RequestParam("encodedIv") String encodedIv,
                          @RequestParam("encryptedData") String encryptedData) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        Cipher cipher = Cipher.getInstance(algorithm);
        SecretKey secretKey = KeyUtil.recoverKey(encodedKey);
        IvParameterSpec iv = IVUtil.recoverIv(encodedIv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        return decryptorService.decrypt(cipher, secretKey, iv, encryptedData);
    }

    @PostMapping("/file")
    public void decrypt(@RequestParam("encodedKey") String encodedKey,
                        @RequestParam("encodedIv") String encodedIv,
                        @RequestParam("encryptedFileDestination") String encryptedFileDestination,
                        @RequestParam("outputDestination") String outputDestination) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        Cipher cipher = Cipher.getInstance(algorithm);
        SecretKey secretKey = KeyUtil.recoverKey(encodedKey);
        IvParameterSpec iv = IVUtil.recoverIv(encodedIv);
        File encrypted = new File(encryptedFileDestination);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        File output = new File(outputDestination);
        decryptorService.decrypt(cipher, secretKey, iv, encrypted, output);
    }

    @PostMapping("/directory")
    public void encrypt(@RequestParam("encodedKey") String encodedKey,
                        @RequestParam("encodedIv") String encodedIv,
                        @RequestParam("directory") String directory,
                        @RequestParam("isRecursive") boolean isRecursive) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IOException {

        Cipher cipher = Cipher.getInstance(algorithm);
        SecretKey secretKey = KeyUtil.recoverKey(encodedKey);
        IvParameterSpec iv = IVUtil.recoverIv(encodedIv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        decryptorService.decrypt(cipher, secretKey, iv, Paths.get(directory), isRecursive);
    }
}
