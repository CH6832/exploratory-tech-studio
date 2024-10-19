package com.example.service;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class DataProcessingService {

    public void saveFile(MultipartFile file, String uploadDir) throws IOException {
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }
        File destFile = new File(uploadDir + file.getOriginalFilename());
        file.transferTo(destFile);
    }

    public String performEDA(String dataPath) throws IOException {
        // Implement EDA logic (e.g., generating summary statistics)
        // For simplicity, return dummy summary statistics as HTML
        return "<h2>Summary Statistics</h2><p>Mean: 0.5</p><p>Std: 0.2</p>";
    }

    public List<String> getColumns(String dataPath) throws IOException {
        // Implement logic to read CSV and get column names
        return List.of("Feature1", "Feature2", "Feature3"); // Example columns
    }
}
