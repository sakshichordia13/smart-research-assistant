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

    @GetMapping
    public java.util.List<DocumentResponse> findAll() {
        return service.findAll()
                .stream()
                .map(d -> new DocumentResponse(
                        d.getId(), d.getTitle(), d.getAuthor(), d.getSourceUrl(), d.getNote(), d.getCreatedAt()
                ))
                .collect(java.util.stream.Collectors.toList());
    }

    // --- STEP 3C: GET BY ID ---
    @GetMapping("/{id}")
    public DocumentResponse findOne(@PathVariable("id") UUID id) {
        Document d = service.findById(id);
        return new DocumentResponse(
                d.getId(), d.getTitle(), d.getAuthor(), d.getSourceUrl(), d.getNote(), d.getCreatedAt()
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        service.delete(id);
    }



    @GetMapping("/search")
    public java.util.List<com.sakshi.sra.smartresearchassistant.dto.DocumentResponse> search(
            @RequestParam("q") String q) {

        if (q == null || q.trim().isEmpty()) {
            throw new org.springframework.web.server.ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "query parameter 'q' is required");
        }

        return service.search(q).stream()
                .map(d -> new com.sakshi.sra.smartresearchassistant.dto.DocumentResponse(
                        d.getId(), d.getTitle(), d.getAuthor(), d.getSourceUrl(), d.getNote(), d.getCreatedAt()
                ))
                .collect(java.util.stream.Collectors.toList());
    }

}
