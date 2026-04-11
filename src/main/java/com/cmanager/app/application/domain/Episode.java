package com.cmanager.app.application.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "EPISODE")
@Data
public class Episode {

    @Id
    private String id;
    @Column(name = "ID_INTEGRATION", nullable=false, unique=true)
    private Integer idIntegration;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "FK_SHOW", nullable=false)
    private Show show;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SEASON")
    private Integer season;
    @Column(name = "NUMBER")
    private Integer number;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "AIRDATE")
    private LocalDate airdate;
    @Column(name = "AIRTIME")
    private LocalTime airtime;
    @Column(name = "AIRSTAMP", columnDefinition = "TIMESTAMPTZ")
    private OffsetDateTime airstamp;
    @Column(name = "RUNTIME")
    private Integer runtime;
    @Column(name = "RATING", precision = 5, scale = 2)
    private BigDecimal rating;
    @Column(name = "SUMMARY", columnDefinition = "TEXT")
    private String summary;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMPTZ")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMPTZ")
    private OffsetDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        final OffsetDateTime now = OffsetDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        this.id = UUID.randomUUID().toString();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }

}
