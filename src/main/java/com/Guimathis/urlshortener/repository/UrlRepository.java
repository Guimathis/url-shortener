package com.Guimathis.urlshortener.repository;

import com.Guimathis.urlshortener.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<UrlEntity, String> {
}