package ru.german.service;

import generated.tables.pojos.Url;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.german.exception.RedirectException;
import ru.german.model.UrlPojo;
import ru.german.repository.RedirectRepository;
import ru.german.repository.UrlRepository;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class UrlService {

    @Autowired
    UrlRepository repository;

    @Autowired
    RedirectRepository redirectRepository;

    @Autowired
    RedirectService redirectService;

    @Value("${currentHost}")
    String currentHost;

    private Logger logger = LoggerFactory.getLogger(UrlService.class);

    public ResponseEntity<String> getShortUrl(String fullUrl) {
        String shortUrl = repository.getShortUrl(fullUrl);

        Long urlId;
        if (shortUrl != null) {
            logger.info("Found same url - short Url: {}, return it", shortUrl);
        } else {
            logger.info("Not found same full url {}, let's make it", fullUrl);
            urlId = repository.getNextValueForUrl();
            UrlPojo urlPojo = new UrlPojo(urlId, fullUrl, currentHost);

            Url result = mapPojo(urlPojo);
            shortUrl = result.getShortUrl();
            repository.insert(result);
        }

        return ResponseEntity.ok(shortUrl);
    }

    private Url mapPojo(UrlPojo urlPojo) {
        Url result = new Url();
        result.setId(urlPojo.getId());
        result.setFullUrl(urlPojo.getFullUrl());
        result.setShortUrl(urlPojo.getShortUrl());
        result.setRedirectCount(urlPojo.getRedirectCount());

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

    public ResponseEntity<String> deleteShortUrl(String shortUrl) {
        Long urlId = repository.getIdByShortUrl(shortUrl);
        if (urlId != null) {
            logger.info("Delete shortUrl {}", shortUrl);
            repository.deleteByShortUrl(shortUrl);
            return ResponseEntity.ok(shortUrl + " was successfully deleted");
        } else {
            throw new RedirectException(shortUrl);
        }
    }
}
