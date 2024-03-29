package com.elleined.filesystemcryptographyapi.encryptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;

@Service
@Slf4j
public class FileEncryptorServiceImpl implements FileEncryptorService {
    @Override
    public void encrypt(String algorithm, SecretKey secretKey, IvParameterSpec iv, String data) {

    }

    @Override
    public void encrypt(String algorithm, SecretKey secretKey, IvParameterSpec iv, File input, File output) {

    }
}
