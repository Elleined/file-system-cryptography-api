package com.elleined.filesystemcryptographyapi.controller;

import com.elleined.filesystemcryptographyapi.util.AESUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/key")
public class SecretKeyController {

    @GetMapping("/generate")
    public SecretKey generateKey() throws NoSuchAlgorithmException {
        SecretKey secretKey = AESUtil.generateKey();
        System.out.println("Make sure to save this encoded key this will be your key for encrypting and decrypting file system!!!: " + Arrays.toString(secretKey.getEncoded()));
        return secretKey;
    }

    @GetMapping("/generate/{n}")
    public SecretKey generateKey(@RequestParam("n") int n,
                                 @RequestParam("algorithm") String algorithm) throws NoSuchAlgorithmException {
        SecretKey secretKey = AESUtil.generateKey(n, algorithm);
        System.out.println("Make sure to save this encoded key this will be your key for encrypting and decrypting file system!!!: " + Arrays.toString(secretKey.getEncoded()));
        return secretKey;
    }

    @GetMapping("/recover")
    public SecretKey recoverKey(byte[] encodedKey) {
        return AESUtil.recoverSecretKey(encodedKey);
    }
}
