package ru.german.service;

import generated.tables.pojos.Redirect;
import org.jooq.tools.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.german.repository.RedirectRepository;
import ru.german.repository.UrlRepository;

@Service
public class RedirectService {
    
    @Autowired
    UrlRepository repository;

    @Autowired
    RedirectRepository redirectRepository;

    @Value("${currentHost}")
    String currentHost;

    private Logger logger = LoggerFactory.getLogger(RedirectService.class);

    public String doRedirect(String shortUrl) {
        Long urlId = repository.getIdByShortUrl(currentHost + "/redirect/" + shortUrl);
        if (urlId != null) {
            Redirect redirect = new Redirect();
            redirect.setUrlId(urlId);
            redirectRepository.insert(redirect);

            repository.redirectCounter(urlId);
            return repository.getFullUrlById(urlId);
        } else {
            logger.info("Url {} not found", shortUrl);
            return StringUtils.EMPTY;
        }

    }
}
