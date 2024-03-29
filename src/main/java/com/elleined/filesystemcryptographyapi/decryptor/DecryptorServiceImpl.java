package com.elleined.filesystemcryptographyapi.decryptor;


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
public class DecryptorServiceImpl implements DecryptorService {

    @Override
    public String decrypt(SecretKey secretKey, IvParameterSpec iv, String encryptedData) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        String originalValue = new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
        log.debug("Successfully decoded ciphered text: {} to original value which is: {}", encryptedData, originalValue);
        return originalValue;
    }

    @Override
    public void decrypt(SecretKey secretKey, IvParameterSpec iv, File encryptedFile, File output) throws IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(encryptedFile));
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(output));

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

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

        log.debug("Successfully decrypted the encrypted file named {} into output file named {}", encryptedFile.getName(), output.getName());
    }
}
