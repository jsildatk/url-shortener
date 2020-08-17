package pl.jsildatk.shortener.service;

import pl.jsildatk.shortener.dto.ShortenerDTO;

public interface ShortenerService {
    
    String EXISTS_PATH = "/nameExists/{name}";
    
    String REDIRECT_TO_URL_PATH = "/{name}";
    
    /**
     * Check if shortener's name already exists in database. Used only for validation on GUI
     *
     * @param name shortener's name
     * @return true if name exists, otherwise false
     * @since 1.0
     */
    boolean nameExists(String name);
    
    /**
     * Get URL for provided shortener's name
     *
     * @param name shortener's name
     * @return name if shortener was found, otherwise null
     * @since 1.0
     */
    String getUrl(String name);
    
    /**
     * Add new shortener to the database and validate its name for duplicates
     *
     * @param shortenerDTO shortener object containing url and name
     * @return true if shortener was added, otherwise false
     * @since 1.0
     */
    boolean addNew(ShortenerDTO shortenerDTO);
    
}
