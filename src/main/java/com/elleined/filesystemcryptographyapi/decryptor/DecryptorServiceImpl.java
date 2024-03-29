package com.elleined.filesystemcryptographyapi.decryptor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

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
    public void decrypt(SecretKey secretKey, IvParameterSpec iv, File encryptedFile, File output) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(encryptedFile));
             BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(output))) {

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
            log.debug("Successfully decrypted the encrypted file named {} into output file named {}", encryptedFile.getName(), output.getName());
        } catch (RuntimeException | IOException | NoSuchAlgorithmException | NoSuchPaddingException |
                 InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException |
                 BadPaddingException e) {
            log.error("Error occured in encrypting normalFIle and output file {}", e.getMessage());
        }
    }

    @Override
    public void decrypt(SecretKey secretKey, IvParameterSpec iv, Path directory, boolean isRecursive) throws IOException {
        if (isRecursive) {
            Files.list(directory)
                    .filter(Files::isDirectory)
                    .forEach(path -> {
                        try {
                            this.decrypt(secretKey, iv, path, true);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }

        List<File> files = Files.list(directory)
                .filter(path -> !Files.isDirectory(path))
                .map(Path::toFile)
                .toList();

        files.forEach(file -> this.decrypt(secretKey, iv, file, file));
    }
}
