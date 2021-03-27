package ru.german.service;

import generated.tables.pojos.Redirect;
import generated.tables.pojos.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.german.model.GroupedStatisticPojo;
import ru.german.model.TopUrlResponse;
import ru.german.model.UrlPojo;
import ru.german.repository.UrlRepository;

import java.util.List;

@Service
public class StatisticService {

    @Autowired
    UrlRepository repository;

    public ResponseEntity<UrlPojo> getInfo(String shortUrl) {
        return repository.getUrlByBase62Key(shortUrl);
    }

    public ResponseEntity<List<String>> getAllExistedShortUrls() {
        return repository.getAllExistedShortUrls();
    }

    public ResponseEntity<List<TopUrlResponse>> getTop20FullUrls() {
        return repository.getTopUrls(20);
    }

    public ResponseEntity<GroupedStatisticPojo> getGroupedStatisticByDetailedTime(String shortUrl) {
        //todo divide stat by time
        GroupedStatisticPojo groupedStatisticPojo = new GroupedStatisticPojo();

        Long urlId = repository.getIdByShortUrl(shortUrl);
        List<Redirect> redirects = repository.getRedirectListByUrlId(urlId);

        return ResponseEntity.ok(groupedStatisticPojo);

    }

}
