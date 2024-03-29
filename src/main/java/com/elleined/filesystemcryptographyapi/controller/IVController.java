package com.elleined.filesystemcryptographyapi.controller;

import com.elleined.filesystemcryptographyapi.util.IVUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.spec.IvParameterSpec;
import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/iv")
public class IVController {

    @GetMapping("/generate")
    public IvParameterSpec generateIv() {
        byte[] encodedIv = IVUtil.generateIvBytes();
        System.out.println("Make sure to save this encoded iv bytes this will be your key for encrypting and decrypting file system!!!: " + Arrays.toString(encodedIv));
        return IVUtil.generateIv(encodedIv);
    }
}
