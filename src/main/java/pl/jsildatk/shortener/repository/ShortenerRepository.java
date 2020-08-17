package pl.jsildatk.shortener.repository;

import pl.jsildatk.shortener.entity.ShortenerEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class ShortenerRepository implements PanacheMongoRepository<ShortenerEntity> {
    
    public Optional<ShortenerEntity> findByName(String name) {
        return find("name", name).firstResultOptional();
    }
    
}
