package com.sakshi.sra.smartresearchassistant.service;

import com.sakshi.sra.smartresearchassistant.dto.DocumentCreateRequest;
import com.sakshi.sra.smartresearchassistant.entity.Document;
import com.sakshi.sra.smartresearchassistant.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.*;

/**
 * Simple in-memory "repository" and business logic layer.
 * Thread-safety and persistence aren't goals yet (dev/demo only).
 * We'll replace with a real DB + repository later.
 */
@Service
public class DocumentService {

    private final Map<UUID, Document> store = new LinkedHashMap<>();

    public Document create(DocumentCreateRequest req) {
        UUID id = UUID.randomUUID();
        Document doc = new Document(
                id,
                req.getTitle(),
                req.getAuthor(),
                req.getSourceUrl(),
                req.getNote(),
                OffsetDateTime.now()
        );
        store.put(id, doc);
        return doc;
    }

    public List<Document> findAll() {
        return new ArrayList<>(store.values());
    }

    public Document findById(UUID id) {
        Document doc = store.get(id);
        if (doc == null) {
            throw new NotFoundException("Document not found: " + id);
        }
        return doc;
    }

    public void delete(UUID id) {
        if (store.remove(id) == null) {
            throw new NotFoundException("Document not found: " + id);
        }
    }
}
