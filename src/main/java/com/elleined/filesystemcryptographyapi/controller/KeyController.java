package com.elleined.filesystemcryptographyapi.controller;

import com.elleined.filesystemcryptographyapi.util.KeyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/key")
public class KeyController {

    @GetMapping("/generate")
    public String generateKey() throws NoSuchAlgorithmException {
        String secretKey = KeyUtil.generateKey();
        System.out.println("Make sure to save this encoded key this will be your key for encrypting and decrypting file system!!!: " + secretKey);
        return secretKey;
    }

    @GetMapping("/generate")
    public String generateKey(@RequestParam("n") int n,
                              @RequestParam("algorithm") String algorithm) throws NoSuchAlgorithmException {
        String secretKey = KeyUtil.generateKey(n, algorithm);
        System.out.println("Make sure to save this encoded key this will be your key for encrypting and decrypting file system!!!: " + secretKey);
        return secretKey;
    }
}
