package pl.jsildatk.shortener.service;

import pl.jsildatk.shortener.dto.ShortenerDTO;
import pl.jsildatk.shortener.repository.ShortenerRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ShortenerServiceImpl implements ShortenerService {
    
    @Inject
    ShortenerRepository shortenerRepository;
    
    @Override
    public boolean nameExists(String name) {
        return shortenerRepository.findByName(name)
                .isPresent();
    }
    
    @Override
    public String getUrl(String name) {
        return shortenerRepository.findByName(name)
                .map(shortenerEntity -> shortenerEntity.url)
                .orElse(null);
    }
    
    @Override
    public boolean addNew(ShortenerDTO shortenerDTO) {
        if ( shortenerRepository.findByName(shortenerDTO.getName())
                .isPresent() ) {
            return false;
        }
        shortenerRepository.persist(shortenerDTO.toEntity());
        return true;
    }
    
}
