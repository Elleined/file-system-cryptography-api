package com.elleined.filesystemcryptographyapi.util;

import com.google.common.io.Files;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;

public interface FileUtil {

    static File createFileFrom(File file) {
        try {
            if (file.isDirectory()) return file;
            if (file.getName().contains("encrypted") && file.exists()) file.delete();

            String parentDirectory = file.getParent() + "/";
            String fileName = Files.getNameWithoutExtension(file.getName()) + "-encrypted." + StringUtils.getFilenameExtension(file.getName());
            File createdFile = new File(parentDirectory + fileName);
            createdFile.createNewFile();
            return createdFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
