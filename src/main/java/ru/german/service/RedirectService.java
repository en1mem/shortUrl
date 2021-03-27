package ru.german.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.german.repository.UrlRepository;

@Service
public class RedirectService {
    
    @Autowired
    UrlRepository repository;

    @Value("${currentHost}")
    String currentHost;

    public String doRedirect(String shortUrl) {
        Long urlId = repository.getIdByShortUrl(currentHost + "/redirect/" + shortUrl);
        repository.redirectCounter(urlId);
        return repository.getFullUrlById(urlId);
    }
}
