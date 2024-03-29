package com.elleined.filesystemcryptographyapi.controller;

import com.elleined.filesystemcryptographyapi.decryptor.DecryptorService;
import com.elleined.filesystemcryptographyapi.util.AESUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequiredArgsConstructor
@RequestMapping("/decrypt")
public class DecryptorController {
    private final DecryptorService decryptorService;

    @PostMapping("/string")
    public String decrypt(byte[] encodedKey,
                          IvParameterSpec iv,
                          @RequestParam("encryptedData") String encryptedData) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        SecretKey secretKey = AESUtil.recoverSecretKey(encodedKey);
        return decryptorService.decrypt(secretKey, iv, encryptedData);
    }

    @PostMapping("/file")
    public void decrypt(byte[] encodedKey,
                        IvParameterSpec iv,
                        @RequestParam("encryptedFileDestination") String encryptedFileDestination,
                        @RequestParam("outputDestination") String outputDestination) throws IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        SecretKey secretKey = AESUtil.recoverSecretKey(encodedKey);
        File encrypted = new File(encryptedFileDestination);
        File output = new File(outputDestination);
        decryptorService.decrypt(secretKey, iv, encrypted, output);
    }
}
