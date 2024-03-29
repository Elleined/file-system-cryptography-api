package com.elleined.filesystemcryptographyapi.encryptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
@Slf4j
public class EncryptorServiceImpl implements EncryptorService {
    @Override
    public String encrypt(SecretKey secretKey, IvParameterSpec iv, String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] cipheredText = cipher.doFinal(data.getBytes());
        String based64CipheredText = Base64.getEncoder().encodeToString(cipheredText);
        log.debug("Successfully encrypted supplied data: {} to {}", data, based64CipheredText);
        return based64CipheredText;
    }

    @Override
    public void encrypt(SecretKey secretKey, IvParameterSpec iv, File input, File output) {

    }
}
