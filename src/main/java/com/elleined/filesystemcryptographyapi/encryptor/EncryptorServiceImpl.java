package com.elleined.filesystemcryptographyapi.encryptor;

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
    public void encrypt(SecretKey secretKey, IvParameterSpec iv, File normalFile, File output) {
        normalFile.setWritable(true);
        normalFile.setReadable(true);
        output.setWritable(true);
        output.setReadable(true);
        
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(normalFile));
             BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(output))) {

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

            byte[] buffer = new byte[64];
            int readBytes;
            while ((readBytes = inputStream.read(buffer)) != -1) {
                byte[] outputBytes = cipher.update(buffer, 0, readBytes);
                if (outputBytes != null) outputStream.write(outputBytes);
            }

            byte[] outputBytes = cipher.doFinal();
            if (outputBytes != null) outputStream.write(outputBytes);
            log.debug("Successfully encrypted normalFile file named {} into output file named {}", normalFile.getName(), output.getName());
        } catch (RuntimeException | IOException | NoSuchAlgorithmException | NoSuchPaddingException |
                 InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException |
                 BadPaddingException e) {
            log.error("Error occured in encrypting normalFIle and output file {}", e.getMessage());
        }
    }


    @Override
    public void encrypt(SecretKey secretKey, IvParameterSpec iv, Path directory, boolean isRecursive) throws IOException {
        if (isRecursive) {
            Files.list(directory)
                    .filter(Files::isDirectory)
                    .forEach(path -> {
                        try {
                            this.encrypt(secretKey, iv, path, true);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }

        List<File> files = Files.list(directory)
                .filter(path -> !Files.isDirectory(path))
                .map(Path::toFile)
                .toList();

        files.forEach(file -> this.encrypt(secretKey, iv, file, file));
    }
}
