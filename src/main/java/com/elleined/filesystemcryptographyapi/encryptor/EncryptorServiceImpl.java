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
        String encryptedData = Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
        log.debug("Successfully encrypted supplied data: {} to {}", data, encryptedData);
        return encryptedData;
    }

    @Override
    public void encrypt(SecretKey secretKey, IvParameterSpec iv, File normalFile, File output) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(normalFile));
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

        log.debug("Successfully encrypted normalFile file named {} into output file named {}", normalFile.getName(), output.getName());
    }
}
