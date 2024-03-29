package com.elleined.filesystemcryptographyapi.decryptor;


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
public class DecryptorServiceImpl implements DecryptorService {

    @Override
    public String decrypt(SecretKey secretKey, IvParameterSpec iv, String cipheredText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        byte[] cipheredTextByteArray = cipher.doFinal(Base64.getDecoder().decode(cipheredText));
        String originalValue = new String(cipheredTextByteArray);
        log.debug("Successfully decoded ciphered text: {} to original value which is: {}", cipheredText, originalValue);
        return originalValue;
    }

    @Override
    public void decrypt(SecretKey secretKey, IvParameterSpec iv, File input, File output) {

    }
}
