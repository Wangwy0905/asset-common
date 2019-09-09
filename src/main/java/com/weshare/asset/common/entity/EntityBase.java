package com.weshare.asset.common.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "CREATOR", length = 128, nullable = false, unique = false)
    @CreatedBy
    protected String creator = "system";
    @Column(name = "MODIFIER", length = 128, nullable = false, unique = false)
    @LastModifiedBy
    protected String modifier = "system";

    @CreationTimestamp
    protected LocalDateTime createDateTime;

    @UpdateTimestamp
    protected LocalDateTime updateDateTime;
}
