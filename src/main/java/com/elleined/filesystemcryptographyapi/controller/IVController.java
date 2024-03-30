package com.elleined.filesystemcryptographyapi.controller;

import com.elleined.filesystemcryptographyapi.util.IVUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.spec.IvParameterSpec;

@RestController
@RequiredArgsConstructor
@RequestMapping("/iv")
public class IVController {

    @GetMapping("/generate")
    public String generateIv() {
        String encodedIv = IVUtil.generateIvBytes();
        System.out.println("Make sure to save this encoded iv bytes this will be your key for encrypting and decrypting file system!!!: " + encodedIv);
        return encodedIv;
    }

    @GetMapping("/generate")
    public String generateIv(@RequestParam("n") int n) {
        String encodedIv = IVUtil.generateIvBytes(n);
        System.out.println("Make sure to save this encoded iv bytes this will be your key for encrypting and decrypting file system!!!: " + encodedIv);
        return encodedIv;
    }
}
