package com.elleined.filesystemcryptographyapi.decryptor;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface DecryptorService {

    String decrypt(Cipher cipher, SecretKey secretKey, IvParameterSpec iv, String encryptedData) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException;
    void decrypt(Cipher cipher, SecretKey secretKey, IvParameterSpec iv, File encryptedFile, File output) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException;

    void decrypt(Cipher cipher, SecretKey secretKey, IvParameterSpec iv, Path directory, boolean isRecursive) throws IOException;
}
