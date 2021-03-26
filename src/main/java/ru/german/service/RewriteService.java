package ru.german.service;

import generated.tables.daos.RedirectDao;
import generated.tables.pojos.Redirect;
import generated.tables.pojos.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.german.model.UrlPojo;
import ru.german.repository.RedirectRepository;
import ru.german.repository.UrlRepository;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class RewriteService {

    @Autowired
    UrlRepository repository;

    @Autowired
    RedirectRepository redirectRepository;

    @Autowired
    RedirectService redirectService;

    @Transactional
    public ResponseEntity<String> getShortUrl(String fullUrl) {
        String shortUrl = repository.getShortUrl(fullUrl);

        Long urlId;
        if (shortUrl != null) {
            urlId = repository.getIdByShortUrl(shortUrl);
            repository.redirectCounter(urlId);
        } else {
            urlId = repository.getNextValueForUrl();
            UrlPojo urlPojo = new UrlPojo(urlId, fullUrl);

            Url result = mapPojo(urlPojo);
            shortUrl = result.getShortUrl();
            repository.insert(result);
        }
//todo maybe move redirect
        Redirect redirect = new Redirect();
        redirect.setUrlId(urlId);
        redirectRepository.insert(redirect);

        return ResponseEntity.ok(shortUrl);
    }

    private Url mapPojo(UrlPojo urlPojo) {
        Url result = new Url();
        result.setId(urlPojo.getId());
        result.setFullUrl(urlPojo.getFullUrl());
        result.setShortUrl(urlPojo.getShortUrl());

        try {
            String hostName = generateHostName(urlPojo.getFullUrl());
            result.setSourceName(hostName);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return result;
    }

    private String generateHostName(String fullUrl) throws URISyntaxException {
        URI uri = new URI(fullUrl);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

    public ResponseEntity<String> deleteShortUrl(String fullUrl) {
        repository.delete(fullUrl);
        return ResponseEntity.ok(fullUrl + "was successfully deleted");
    }

    public ResponseEntity<UrlPojo> getInfo(String shortUrl) {
        return repository.getUrlByBase62Key(shortUrl);
    }
}
