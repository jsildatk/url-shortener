package pl.jsildatk.shortener.dto;

public class SuccessfulResponse {
    
    private final String message;
    
    private final String link;
    
    public SuccessfulResponse(String message, String link) {
        this.message = message;
        this.link = link;
    }
    
}
