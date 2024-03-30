package com.elleined.filesystemcryptographyapi.encryptor;

import com.elleined.filesystemcryptographyapi.util.FileUtil;
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
public class EncryptorServiceImpl implements EncryptorService {

    @Override
    public String encrypt(Cipher cipher, SecretKey secretKey, IvParameterSpec iv, String data) throws IllegalBlockSizeException, BadPaddingException {
        String encryptedData = Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
        log.debug("Successfully encrypted supplied data: {} to {}", data, encryptedData);
        return encryptedData;
    }

    @Override
    public void encrypt(Cipher cipher, SecretKey secretKey, IvParameterSpec iv, File normalFile, File output) {
        normalFile.setWritable(true);
        normalFile.setReadable(true);
        output.setWritable(true);
        output.setReadable(true);
        
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(normalFile));
             CipherOutputStream cipherOutputStream = new CipherOutputStream(new FileOutputStream(output), cipher)) {

            final byte[] bytes = new byte[1024];
            for(int length = inputStream.read(bytes); length !=- 1; length = inputStream.read(bytes))cipherOutputStream.write(bytes, 0, length);
            log.debug("Successfully encrypted normalFile file named {} into output file named {}", normalFile.getName(), output.getName());
        } catch (RuntimeException | IOException e) {
            log.error("Error occurred in encrypting normalFIle and output file {}", e.getMessage());
        }
    }


    @Override
    public void encrypt(Cipher cipher, SecretKey secretKey, IvParameterSpec iv, Path directory, boolean isRecursive) throws IOException {
        if (isRecursive) {
            Files.list(directory)
                    .filter(Files::isDirectory)
                    .forEach(path -> {
                        try {
                            this.encrypt(cipher, secretKey, iv, path, true);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }

        List<File> files = Files.list(directory)
                .filter(path -> !Files.isDirectory(path))
                .map(Path::toFile)
                .toList();

        files.forEach(file -> {
            File output = FileUtil.createFileFrom(file);
            this.encrypt(cipher, secretKey, iv, file, output);
        });
    }
}
