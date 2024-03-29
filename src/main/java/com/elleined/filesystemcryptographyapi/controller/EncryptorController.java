package com.elleined.filesystemcryptographyapi.controller;

import com.elleined.filesystemcryptographyapi.encryptor.EncryptorService;
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
@RequestMapping("/encrypt")
public class EncryptorController {
    private final EncryptorService encryptorService;

    @PostMapping("/string")
    public String encrypt(@RequestParam("encodedKey") String encodedKey,
                          byte[] encodedIv,
                          @RequestParam("data") String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        SecretKey secretKey = KeyUtil.recoverKey(encodedKey);
        IvParameterSpec iv = IVUtil.recoverIv(encodedIv);
        return encryptorService.encrypt(secretKey, iv, data);
    }

    @PostMapping("/file")
    public void encrypt(@RequestParam("encodedKey") String encodedKey,
                        byte[] encodedIv,
                        @RequestParam("normalFileDestination") String normalFileDestination,
                        @RequestParam("outputDestination") String outputDestination) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        SecretKey secretKey = KeyUtil.recoverKey(encodedKey);
        IvParameterSpec iv = IVUtil.recoverIv(encodedIv);
        File normalFile = new File(normalFileDestination);
        File output = new File(outputDestination);

        encryptorService.encrypt(secretKey, iv, normalFile, output);
    }
}
