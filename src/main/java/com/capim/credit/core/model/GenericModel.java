package com.capim.credit.core.model;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@MappedSuperclass
public abstract class GenericModel {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at" )
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at" )
    private Timestamp updatedAt;
}
