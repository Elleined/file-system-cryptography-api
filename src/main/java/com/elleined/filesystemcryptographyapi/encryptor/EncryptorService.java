package com.elleined.filesystemcryptographyapi.encryptor;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface EncryptorService {
    String encrypt(SecretKey secretKey, IvParameterSpec iv, String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException;
    void encrypt(SecretKey secretKey, IvParameterSpec iv, File normalFile, File output);
    void encrypt(SecretKey secretKey, IvParameterSpec iv, Path directory, boolean isRecursive) throws IOException;
}
