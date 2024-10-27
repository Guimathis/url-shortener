package com.Guimathis.urlshortener.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Table(name = "urls")
@Entity
public class UrlEntity {

    @Id
    private String id;

    private String fullUrl;

    private LocalDateTime expiresAt;

    public boolean isExpired() {
        LocalDateTime expirationTime = expiresAt.plusMinutes(1);
        return LocalDateTime.now().isAfter(expirationTime);
    }

    public UrlEntity(String id, String fullUrl) {
        this.id = id;
        this.fullUrl = fullUrl;
        this.expiresAt = LocalDateTime.now().plusMinutes(1);
    }

    public UrlEntity() {}

    public void setId(String id) {
        this.id = id;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }


    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime createdAt) {
        this.expiresAt = createdAt;
    }

    public String getId() {
        return id;
    }
}
