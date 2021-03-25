package ru.german.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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

    public ResponseEntity<List<String>> getTop20FullUrls() {
        return repository.getTopUrls(20);
    }
//todo
//    public ResponseEntity<Object> getGroupedStatistic() {
//        return
//    }
}
