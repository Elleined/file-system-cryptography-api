package com.elleined.filesystemcryptographyapi;

import com.elleined.filesystemcryptographyapi.encryptor.EncryptorService;
import com.elleined.filesystemcryptographyapi.encryptor.EncryptorServiceImpl;
import com.elleined.filesystemcryptographyapi.util.AESUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@SpringBootApplication
public class FileSystemCryptographyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileSystemCryptographyApiApplication.class, args);
	}
}
