package pl.jsildatk.shortener.entity;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity(collection = "shorteners")
public class ShortenerEntity extends PanacheMongoEntity {
    
    public String url;
    
    public String name;
    
}
