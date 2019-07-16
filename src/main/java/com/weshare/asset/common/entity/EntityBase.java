package com.weshare.asset.common.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(columnDefinition = "varchar(255) default 'system' COMMENT '创建人'")
    protected String creator;
    @Column(columnDefinition = "varchar(255) default 'system' COMMENT '修改人'")
    protected String modifier;

    @CreationTimestamp
    protected LocalDateTime createDateTime;

    @UpdateTimestamp
    protected LocalDateTime updateDateTime;
}
