package com.example.applicationonlineshop.entity;


import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Upload extends Auditable {

    @Column(nullable = false, unique = true)
    private String generatedName;

    @Column(nullable = false)
    private String originalName;

    @Column(nullable = false)
    private String mimeType;

    private long size;

    @Column(nullable = false)
    private String extension;

    @Builder(builderMethodName = "childBuilder")
    public Upload(Integer id, Timestamp createdAt, Timestamp updatedAt, String generatedName, String originalName, String mimeType, long size, String extension) {
        super(id, createdAt, updatedAt);
        this.generatedName = generatedName;
        this.originalName = originalName;
        this.mimeType = mimeType;
        this.size = size;
        this.extension = extension;
    }
}
