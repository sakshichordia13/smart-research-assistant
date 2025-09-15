package com.sakshi.sra.smartresearchassistant.controller;

import com.sakshi.sra.smartresearchassistant.service.DocumentService;   // <-- add this

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

    private final DocumentService documentService;                      // <-- add this

    public FileController(DocumentService documentService) {            // <-- add this
        this.documentService = documentService;
    }

    // Use absolute path under user’s home directory
    private static final Path UPLOAD_DIR =
            Paths.get(System.getProperty("user.home"), "smart-research-uploads");

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (!Files.exists(UPLOAD_DIR)) {
            Files.createDirectories(UPLOAD_DIR);
        }

        String uniqueName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = UPLOAD_DIR.resolve(uniqueName);
        file.transferTo(filePath.toFile());

        Map<String, Object> body = new HashMap<>();
        body.put("fileName", uniqueName);
        body.put("originalName", file.getOriginalFilename());
        body.put("savedPath", filePath.toString());
        body.put("sizeBytes", file.getSize());
        body.put("uploadedAt", OffsetDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PostMapping("/uploadAndRegister")
    public ResponseEntity<Map<String, Object>> uploadAndRegister(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("author") String author
    ) throws IOException {
        var doc = documentService.createFromPdf(file, title, author);
        Map<String,Object> body = new HashMap<>();
        body.put("id", doc.getId());
        body.put("title", doc.getTitle());
        body.put("author", doc.getAuthor());
        body.put("filePath", doc.getFilePath());
        body.put("contentPreview", doc.getContentText() == null ? "" :
                doc.getContentText().substring(0, Math.min(400, doc.getContentText().length())));
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
}
