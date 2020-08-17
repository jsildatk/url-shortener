package pl.jsildatk.shortener.dto;

import pl.jsildatk.shortener.entity.ShortenerEntity;

public class ShortenerDTO {
    
    private final String url;
    
    private final String name;
    
    public ShortenerDTO(String url, String name) {
        this.url = url;
        this.name = name;
    }
    
    public String getUrl() {
        return url;
    }
    
    public String getName() {
        return name;
    }
    
    public ShortenerEntity toEntity() {
        ShortenerEntity shortenerEntity = new ShortenerEntity();
        shortenerEntity.name = name;
        shortenerEntity.url = url;
        return shortenerEntity;
    }
    
}
