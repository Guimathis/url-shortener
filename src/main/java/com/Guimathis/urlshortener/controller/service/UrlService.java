package com.Guimathis.urlshortener.controller.service;

import com.Guimathis.urlshortener.entity.UrlEntity;
import com.Guimathis.urlshortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlService {
    @Autowired
    private  UrlRepository urlRepository;

    public Optional<UrlEntity> getValidUrl(String id) {
        return urlRepository.findById(id).filter(entity -> !entity.isExpired());
    }
}
