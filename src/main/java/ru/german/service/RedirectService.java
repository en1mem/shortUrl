package ru.german.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.german.repository.UrlRepository;

@Service
public class RedirectService {
    
    @Autowired
    UrlRepository repository;

    public String doRedirect(String shortUrl) {
        Long urlId = repository.getIdByShortUrl(shortUrl);
        repository.redirectCounter(urlId);
        return repository.getFullUrl(shortUrl);
    }
}
