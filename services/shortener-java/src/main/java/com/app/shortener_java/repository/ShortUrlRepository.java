package com.app.shortener_java.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shortener_java.model.ShortUrl;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    Optional<ShortUrl> findByShortCode(String shortCode);
}
