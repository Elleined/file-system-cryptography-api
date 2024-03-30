package com.elleined.filesystemcryptographyapi.encryptor;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface EncryptorService {
    String encrypt(Cipher cipher, SecretKey secretKey, IvParameterSpec iv, String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException;
    void encrypt(Cipher cipher, SecretKey secretKey, IvParameterSpec iv, File normalFile, File output) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException;
    void encrypt(Cipher cipher, SecretKey secretKey, IvParameterSpec iv, Path directory, boolean isRecursive) throws IOException;
}
