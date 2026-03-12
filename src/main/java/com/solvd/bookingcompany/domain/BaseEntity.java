package com.solvd.bookingcompany.domain;

import com.solvd.bookingcompany.interfaces.Identifiable;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class BaseEntity implements Identifiable {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected BaseEntity() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    protected BaseEntity(Long id) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public abstract String getEntityName();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        BaseEntity baseObject = (BaseEntity) o;

        if (baseObject.hashCode() != this.hashCode()) return false;

        if (this.getId() != baseObject.getId()) return false;
        if (this.getCreatedAt() != baseObject.getCreatedAt()) return false;
        if (this.getUpdatedAt() != baseObject.getUpdatedAt()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreatedAt(), getUpdatedAt());
    }
}
