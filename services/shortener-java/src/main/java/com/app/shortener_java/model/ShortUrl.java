package com.app.shortener_java.model;

import jakarta.persistence.*;

@Entity
public class ShortUrl {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String shortCode;

    @Column(nullable=false)
    private String originalUrl;

    //Getters y setters
    public Long getId(){
        return this.id;
    }
    public String getShortCode(){
        return this.shortCode;
    }
    public String getOriginalUrl(){
        return this.originalUrl;
    }
    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }
    public void setOriginalUrl(String originalUrl){
        this.originalUrl = originalUrl;
    }
}
