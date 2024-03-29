package com.elleined.filesystemcryptographyapi.controller;


import com.elleined.filesystemcryptographyapi.util.AESUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/generate")
public class FileSystemCryptographyController {

    @GetMapping("/key/{n}")
    public SecretKey generateKey(@PathVariable("n") int n) throws NoSuchAlgorithmException {
        SecretKey secretKey = AESUtil.generateKey(n);
        System.out.println("Make sure to save this encoded key this will be your key for encrypting and decrypting file system!!!: " + Arrays.toString(secretKey.getEncoded()));
        return secretKey;
    }

    @GetMapping("/key/recover")
    public SecretKey recoverKey(byte[] encodedKey) {
        return AESUtil.recoverSecretKey(encodedKey);
    }

    @GetMapping("/iv")
    public IvParameterSpec generateIv() {
        return AESUtil.generateIv();
    }
}
