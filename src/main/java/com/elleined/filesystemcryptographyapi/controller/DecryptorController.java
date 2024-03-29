package com.elleined.filesystemcryptographyapi.controller;

import com.elleined.filesystemcryptographyapi.decryptor.DecryptorService;
import com.elleined.filesystemcryptographyapi.util.IVUtil;
import com.elleined.filesystemcryptographyapi.util.KeyUtil;
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
    public String decrypt(@RequestParam("encodedKey") String encodedKey,
                          @RequestParam("encodedIv") String encodedIv,
                          @RequestParam("encryptedData") String encryptedData) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        SecretKey secretKey = KeyUtil.recoverKey(encodedKey);
        IvParameterSpec iv = IVUtil.recoverIv(encodedIv);
        return decryptorService.decrypt(secretKey, iv, encryptedData);
    }

    @PostMapping("/file")
    public void decrypt(@RequestParam("encodedKey") String encodedKey,
                        @RequestParam("encodedIv") String encodedIv,
                        @RequestParam("encryptedFileDestination") String encryptedFileDestination,
                        @RequestParam("outputDestination") String outputDestination) throws IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        SecretKey secretKey = KeyUtil.recoverKey(encodedKey);
        IvParameterSpec iv = IVUtil.recoverIv(encodedIv);
        File encrypted = new File(encryptedFileDestination);
        File output = new File(outputDestination);
        decryptorService.decrypt(secretKey, iv, encrypted, output);
    }
}
