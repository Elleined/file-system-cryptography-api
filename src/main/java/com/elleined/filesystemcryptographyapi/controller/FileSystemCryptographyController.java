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

@RestController
@RequiredArgsConstructor
@RequestMapping("/generate")
public class FileSystemCryptographyController {

    @GetMapping("/key/{n}")
    public SecretKey generateKey(@PathVariable("n") int n) throws NoSuchAlgorithmException {
        return AESUtil.generateKey(n);
    }

    @GetMapping("/iv")
    public IvParameterSpec generateIv() {
        return AESUtil.generateIv();
    }
}
