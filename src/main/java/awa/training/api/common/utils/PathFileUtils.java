package awa.training.api.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

@Slf4j
@Service
public class PathFileUtils {

    @Value("${application.file-path.unix}")
    private String pathFileUnix;

    @Value("${application.file-path.window}")
    private String pathFileWindow;

    public String getFullPath() {
        String os = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        String ieFolder = pathFileUnix;
        if (os.contains("win")) {
            ieFolder = pathFileWindow;
        }
        return ieFolder;
    }

    public void deleteFile(String filePath) {
        Path path = Paths.get(filePath);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            log.error("Failed to delete file: {}", filePath, e);
        }
    }

    public void createDirectory() {
        String fullPath = getFullPath();
        File dir = new File(fullPath);
        if (!dir.exists()) {
            boolean success = dir.mkdirs();
            if (!success) {
                log.error("Failed to create directory: {}", fullPath);
            } else {
                log.info("Directory created: {}", fullPath);
            }
        }
    }

}
