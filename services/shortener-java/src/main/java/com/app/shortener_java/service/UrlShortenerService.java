package com.app.shortener_java.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.app.shortener_java.model.ShortUrl;
import com.app.shortener_java.repository.ShortUrlRepository;

@Service
public class UrlShortenerService {
    private final ShortUrlRepository repository;
    private final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final int SHORT_CODE_LENGTH = 6;
    private final Random random = new Random();

    public UrlShortenerService(ShortUrlRepository repository) {
        this.repository = repository;
    }

    public ShortUrl shorten(String originalUrl) {
        String shortCode;
        do {
            shortCode = generateCode();
        } while(repository.findByShortCode(shortCode).isPresent());

        ShortUrl entity = new ShortUrl();
        entity.setOriginalUrl(originalUrl);
        entity.setShortCode(shortCode);

        return repository.save(entity);
    }

    public Optional<ShortUrl> expand(String shortCode) {
        return repository.findByShortCode(shortCode);
    }

    private String generateCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<SHORT_CODE_LENGTH; i++){
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
