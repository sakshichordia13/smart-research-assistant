package com.sakshi.sra.smartresearchassistant.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * JPA entity mapped to PostgreSQL table "documents".
 */
@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    private String sourceUrl;

    @Column(length = 2000)
    private String note;

    @Column(nullable = false, columnDefinition = "timestamp with time zone")
    private OffsetDateTime createdAt;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "content_text", columnDefinition = "text")
    private String contentText;

    protected Document() { /* JPA */ }

    public Document(UUID id, String title, String author,
                    String sourceUrl, String note, OffsetDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.sourceUrl = sourceUrl;
        this.note = note;
        this.createdAt = createdAt;
    }

    // Getters/setters
    public UUID getId() { return id; }
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
    public String getFilePath() { return filePath; }
    public void setFilePath(String fp) { this.filePath = fp; }
    public String getContentText() { return contentText; }
    public void setContentText(String ct) { this.contentText = ct; }
}
