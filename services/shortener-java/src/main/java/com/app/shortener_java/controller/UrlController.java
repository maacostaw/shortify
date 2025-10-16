package com.app.shortener_java.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.shortener_java.model.ShortUrl;
import com.app.shortener_java.service.UrlShortenerService;

@RestController
@RequestMapping("/")
public class UrlController {

    private final UrlShortenerService service;

    public UrlController(UrlShortenerService service) {
        this.service = service;
    }

    @PostMapping("/shorten")
    public ResponseEntity<ShortUrl> shorten(@RequestParam String url) {
        ShortUrl shortUrl = service.shorten(url);
        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping("/expand/{code}")
    public ResponseEntity<String> expand(@PathVariable("code") String code) {
        return service.expand(code)
                .map(ShortUrl::getOriginalUrl)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
