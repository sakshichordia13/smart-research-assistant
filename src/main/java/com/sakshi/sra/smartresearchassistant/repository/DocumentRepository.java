package com.sakshi.sra.smartresearchassistant.repository;

import com.sakshi.sra.smartresearchassistant.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> {

    @Query("""
           SELECT d FROM Document d
           WHERE LOWER(d.title)       LIKE LOWER(CONCAT('%', :q, '%'))
              OR LOWER(d.author)      LIKE LOWER(CONCAT('%', :q, '%'))
              OR LOWER(d.note)        LIKE LOWER(CONCAT('%', :q, '%'))
              OR LOWER(d.contentText) LIKE LOWER(CONCAT('%', :q, '%'))
           """)
    List<Document> keywordSearch(@Param("q") String q);
}
