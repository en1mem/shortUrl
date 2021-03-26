package ru.german.service;

import generated.tables.pojos.Redirect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.german.model.GroupedStatisticPojo;
import ru.german.model.UrlPojo;
import ru.german.repository.UrlRepository;

import java.util.List;

@Service
public class StatisticService {

    @Autowired
    UrlRepository repository;

    public ResponseEntity<List<String>> getAllExistedShortUrls() {
        return repository.getAllExistedShortUrls();
    }

    public ResponseEntity<List<UrlPojo>> getTop20FullUrls() {
        return repository.getTopUrls(20);
    }

    public ResponseEntity<GroupedStatisticPojo> getGroupedStatisticByDetailedTime(String shortUrl) {
        //todo divide stat by time
        GroupedStatisticPojo groupedStatisticPojo = new GroupedStatisticPojo();

        Long urlId = repository.getIdByShortUrl(shortUrl);
        Redirect redirect = repository.getRedirectByUrlId(urlId);

        return ResponseEntity.ok(groupedStatisticPojo);

    }

}
