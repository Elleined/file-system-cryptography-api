package com.elleined.filesystemcryptographyapi.encryptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
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
    public void encrypt(SecretKey secretKey, IvParameterSpec iv, File input, File output) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(input));
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(output));

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        byte[] buffer = new byte[64];
        int readBytes;
        while ((readBytes = inputStream.read(buffer)) != -1) {
            byte[] outputByteArray = cipher.update(buffer, 0, readBytes);
            if (outputByteArray != null) outputStream.write(outputByteArray);
        }

        byte[] outputBytes = cipher.doFinal();
        if (outputBytes != null) outputStream.write(outputBytes);

        inputStream.close();
        outputStream.close();

        log.debug("Successfully encrypted input file named {} into output file named {}", input.getName(), output.getName());
    }
}
