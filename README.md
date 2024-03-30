# file-system-cryptography-api
API for encryption and decryption of file system

## input or output file in Linux (Ubuntu)
```
inputDestination: //home//path//to//<file_name>.<file_extension>
outputDestinaion: //home//path//to//<file_name>.<file_extension>

Example:
inputDestination: //home//juan/Documents//your_file.txt
outputDestinaion: //home//juan/Documents//your_file.txt
```

## input or output file in Windows
```
inputDestination: C:\\path\\to\\<file_name>.<file_extension>
outputDestination: C:\\path\\to\\<file_name>.<file_extension>

Example:
inputDestination: C:\\your_file.txt
outputDestination: C:\\your_file.txt
```

# Run with Docker
- Create network
```
docker create network fsca_network
```

- Run docker container
```
docker run -itd --rm -p 8090:8090 --name fsca_app --network fsca_network elleined/fsca:latest
```

# Run with Docker Compose
```
docker composse up -d
```

# Test API with Postman
[<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://app.getpostman.com/run-collection/26932885-4758b3a8-d2b9-4c5c-9e52-e66259b23108?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D26932885-4758b3a8-d2b9-4c5c-9e52-e66259b23108%26entityType%3Dcollection%26workspaceId%3Dc11d26ef-8a73-4fb9-bd7e-87dee0c4aade)

# References
[Baeldung File and String Encryption and Decryption](https://www.baeldung.com/java-aes-encryption-decryption)
[Code Sample](https://github.com/PacktPublishing/Hands-On-Cryptography-with-Java/blob/master/src/main/java/com/packtpub/crypto/section5/FileEncryptor.java)