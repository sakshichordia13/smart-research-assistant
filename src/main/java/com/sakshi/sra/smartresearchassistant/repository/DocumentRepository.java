package com.sakshi.sra.smartresearchassistant.repository;

import com.sakshi.sra.smartresearchassistant.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> {
    // Add derived queries later (e.g., findByTitleContainingIgnoreCase)
}
