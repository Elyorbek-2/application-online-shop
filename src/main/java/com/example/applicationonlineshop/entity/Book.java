package com.example.applicationonlineshop.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book extends Auditable {
    private String title;
    private String description;
    private String author;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Upload cover;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Upload file;


}
