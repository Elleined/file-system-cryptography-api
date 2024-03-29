package com.elleined.filesystemcryptographyapi.encryptor;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;

public interface EncryptorService {
    void encrypt(String algorithm, SecretKey secretKey, IvParameterSpec iv, String data);
    void encrypt(String algorithm, SecretKey secretKey, IvParameterSpec iv, File input, File output);
}
