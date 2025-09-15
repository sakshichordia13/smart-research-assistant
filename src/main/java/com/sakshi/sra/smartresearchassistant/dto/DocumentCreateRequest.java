package com.sakshi.sra.smartresearchassistant.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Incoming payload when a client wants to register a document.
 * We keep it small now; file upload + parsing will arrive later.
 */
public class DocumentCreateRequest {

    // Required: we want at least a title
    @NotBlank(message = "title is required")
    private String title;

    // Required: simple author name field
    @NotBlank(message = "author is required")
    private String author;

    // Optional: a link to the paper/article
    private String sourceUrl;

    // Optional: short note/abstract
    private String note;

    // --- Getters & Setters (needed for JSON <-> Java binding) ---
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getSourceUrl() { return sourceUrl; }
    public void setSourceUrl(String sourceUrl) { this.sourceUrl = sourceUrl; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
