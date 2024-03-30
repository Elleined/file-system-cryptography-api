package com.elleined.filesystemcryptographyapi.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class FileUtilTest {

    @Test
    void createFileFrom() throws IOException {
        // Expected Value

        // Mock data

        // Set up method

        // Stubbing methods

        // Calling the method
        File file = new File("./src/test/resources/util/file.txt");
        File createdFile = FileUtil.createFileFrom(file);

        // Behavior Verifications

        // Assertions
        assertTrue(createdFile.exists());
    }
}