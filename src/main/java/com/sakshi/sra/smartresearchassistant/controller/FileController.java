package com.sakshi.sra.smartresearchassistant.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/files")
public class FileController {

    // Use absolute path under user’s home directory
    private static final Path UPLOAD_DIR =
            Paths.get(System.getProperty("user.home"), "smart-research-uploads");

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        // Ensure directory exists
        if (!Files.exists(UPLOAD_DIR)) {
            Files.createDirectories(UPLOAD_DIR);
        }

        // Save file with unique name
        String uniqueName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = UPLOAD_DIR.resolve(uniqueName);
        file.transferTo(filePath.toFile());

        // Return response
        Map<String, Object> body = new HashMap<>();
        body.put("fileName", uniqueName);
        body.put("originalName", file.getOriginalFilename());
        body.put("savedPath", filePath.toString());
        body.put("sizeBytes", file.getSize());
        body.put("uploadedAt", OffsetDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
}
