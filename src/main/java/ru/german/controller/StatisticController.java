package ru.german.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.german.model.TopUrlResponse;
import ru.german.model.UrlPojo;
import ru.german.service.StatisticService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/statistic")
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @RequestMapping(value = "/get/info", method = POST)
    public ResponseEntity<UrlPojo> getInfo(@RequestBody String shortUrl) {
        return statisticService.getInfo(shortUrl);
    }

    @RequestMapping(value = "/getAll", method = POST)
    public ResponseEntity<List<String>> getAllExistedShortUrls() {
        return statisticService.getAllExistedShortUrls();
    }

    @RequestMapping(value = "/top20", method = POST)
    public ResponseEntity<List<TopUrlResponse>> getTop20FullUrls() {
        return statisticService.getTop20FullUrls();
    }

    @RequestMapping(value = "/grouped", method = POST)
    public ResponseEntity<String> getGroupedStatisticByDetailedTime(@RequestBody String shortUrl) {
        return statisticService.getGroupedStatisticByDetailedTime(shortUrl);
    }
}
