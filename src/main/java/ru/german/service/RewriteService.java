package ru.german.service;

import generated.tables.pojos.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.german.model.UrlPojo;
import ru.german.repository.UrlRepository;

@Service
public class RewriteService {

    @Autowired
    UrlRepository repository;

    public ResponseEntity<String> createShortUrl(String fullUrl) {
        Long id = repository.getNextValueForUrl();
        UrlPojo urlPojo = new UrlPojo(id, fullUrl);

        Url result = mapPojo(urlPojo);
        repository.insert(result);

        return ResponseEntity.ok(result.getShortUrl());
    }

    private Url mapPojo(UrlPojo urlPojo) {
        Url result = new Url();
        result.setId(urlPojo.getId());
        result.setFullUrl(urlPojo.getFullUrl());
        result.setShortUrl(urlPojo.getShortUrl());
        return result;
    }

    public ResponseEntity<String> deleteShortUrl(String fullUrl) {
        repository.delete(fullUrl);
        return ResponseEntity.ok(fullUrl + "was successfully deleted");
    }

    public ResponseEntity<UrlPojo> getInfo(String shortUrl) {
        return repository.getUrlByBase62Key(shortUrl);
    }
}
