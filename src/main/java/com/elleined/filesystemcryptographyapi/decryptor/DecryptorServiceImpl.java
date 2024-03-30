package com.elleined.filesystemcryptographyapi.decryptor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;

@Service
@Slf4j
public class DecryptorServiceImpl implements DecryptorService {

    @Override
    public String decrypt(Cipher cipher, SecretKey secretKey, IvParameterSpec iv, String encryptedData) throws IllegalBlockSizeException, BadPaddingException {
        String originalValue = new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
        log.debug("Successfully decoded ciphered text: {} to original value which is: {}", encryptedData, originalValue);
        return originalValue;
    }

    @Override
    public void decrypt(Cipher cipher, SecretKey secretKey, IvParameterSpec iv, File encryptedFile, File output) {
        encryptedFile.setWritable(true);
        encryptedFile.setReadable(true);
        output.setWritable(true);
        output.setReadable(true);

        try (CipherInputStream cipherInputStream = new CipherInputStream(new FileInputStream(encryptedFile), cipher);
             BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(output))) {

            final byte[] bytes = new byte[1024];
            for(int length = cipherInputStream.read(bytes); length != -1; length = cipherInputStream.read(bytes)) outputStream.write(bytes, 0, length);
            log.debug("Successfully decrypted the encrypted file named {} into output file named {}", encryptedFile.getName(), output.getName());
        } catch (RuntimeException | IOException e) {
            log.error("Error occurred in encrypting normalFIle and output file {}", e.getMessage());
        }
    }

    @Override
    public void decrypt(Cipher cipher, SecretKey secretKey, IvParameterSpec iv, Path directory, boolean isRecursive) throws IOException {
        if (isRecursive) {
            Files.list(directory)
                    .filter(Files::isDirectory)
                    .forEach(path -> {
                        try {
                            this.decrypt(cipher, secretKey, iv, path, true);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }

        List<File> files = Files.list(directory)
                .filter(path -> !Files.isDirectory(path))
                .map(Path::toFile)
                .toList();

        files.forEach(file -> this.decrypt(cipher, secretKey, iv, file, file));
    }
}
