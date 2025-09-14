package com.sakshi.sra.smartresearchassistant.controller;

import com.sakshi.sra.smartresearchassistant.dto.DocumentCreateRequest;
import com.sakshi.sra.smartresearchassistant.dto.DocumentResponse;
import com.sakshi.sra.smartresearchassistant.entity.Document;
import com.sakshi.sra.smartresearchassistant.service.DocumentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * REST endpoints for registering and fetching documents.
 * We'll add endpoints incrementally and test each in Postman.
 */
@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {

    private final DocumentService service;

    public DocumentController(DocumentService service) {
        this.service = service;
    }

    // --- STEP 3A: CREATE ---
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentResponse create(@Valid @RequestBody DocumentCreateRequest req) {
        Document d = service.create(req);
        return new DocumentResponse(
                d.getId(), d.getTitle(), d.getAuthor(), d.getSourceUrl(), d.getNote(), d.getCreatedAt()
        );
    }

    // We'll add LIST, GET-BY-ID, DELETE in the next sub-steps
}
