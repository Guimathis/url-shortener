package com.Guimathis.urlshortener.controller;

import com.Guimathis.urlshortener.controller.dto.ShortenUrlRequest;
import com.Guimathis.urlshortener.controller.dto.ShortenUrlResponse;
import com.Guimathis.urlshortener.controller.service.UrlService;
import com.Guimathis.urlshortener.entity.UrlEntity;
import com.Guimathis.urlshortener.repository.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;

    private final UrlRepository urlRepository;

    public UrlController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @PostMapping(value = "/shorten-url")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest request,
                                                         HttpServletRequest servletRequest) {
        String id;
        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (urlRepository.existsById(id));
        urlRepository.save(new UrlEntity(id, request.url()));

        var redirectUrl = servletRequest.getRequestURL().toString().replace("shorten-url", id);

        return ResponseEntity.ok(new ShortenUrlResponse(redirectUrl));
    }

    @GetMapping("{id}")
    public ResponseEntity<UrlEntity> getUrl(@PathVariable String id) {
        var url = urlService.getValidUrl(id);
        if (url.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.get().getFullUrl()));
        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }
}
