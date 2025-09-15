package com.sakshi.sra.smartresearchassistant.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Outgoing payload returned by our API.
 * We separate this from internal entity so we can evolve the API safely.
 */
public class DocumentResponse {

    private UUID id;
    private String title;
    private String author;
    private String sourceUrl;
    private String note;
    private OffsetDateTime createdAt;

    public DocumentResponse() {}

    public DocumentResponse(UUID id, String title, String author,
                            String sourceUrl, String note, OffsetDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.sourceUrl = sourceUrl;
        this.note = note;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getSourceUrl() { return sourceUrl; }
    public void setSourceUrl(String sourceUrl) { this.sourceUrl = sourceUrl; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
