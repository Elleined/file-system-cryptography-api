package com.elleined.filesystemcryptographyapi.controller;

import com.elleined.filesystemcryptographyapi.encryptor.EncryptorService;
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
@RequestMapping("/encrypt")
public class EncryptorController {
    private final EncryptorService encryptorService;

    @Value("${cipher.algorithm}")
    private String algorithm;

    @PostMapping("/string")
    public String encrypt(@RequestParam("encodedKey") String encodedKey,
                          @RequestParam("encodedIv") String encodedIv,
                          @RequestParam("data") String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        Cipher cipher = Cipher.getInstance(algorithm);
        SecretKey secretKey = KeyUtil.recoverKey(encodedKey);
        IvParameterSpec iv = IVUtil.recoverIv(encodedIv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        return encryptorService.encrypt(cipher, secretKey, iv, data);
    }

    @PostMapping("/file")
    public void encrypt(@RequestParam("encodedKey") String encodedKey,
                        @RequestParam("encodedIv") String encodedIv,
                        @RequestParam("normalFileDestination") String normalFileDestination,
                        @RequestParam("outputDestination") String outputDestination) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        Cipher cipher = Cipher.getInstance(algorithm);
        SecretKey secretKey = KeyUtil.recoverKey(encodedKey);
        IvParameterSpec iv = IVUtil.recoverIv(encodedIv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        File normalFile = new File(normalFileDestination);
        File output = new File(outputDestination);

        encryptorService.encrypt(cipher, secretKey, iv, normalFile, output);
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

        encryptorService.encrypt(cipher, secretKey, iv, Paths.get(directory), isRecursive);
    }
}
