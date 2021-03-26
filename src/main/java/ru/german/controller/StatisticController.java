package ru.german.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.german.model.GroupedStatisticPojo;
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

    @RequestMapping(value = "/getAll", method = POST)
    public ResponseEntity<List<String>> getAllExistedShortUrls() {
        return statisticService.getAllExistedShortUrls();
    }

    @RequestMapping(value = "/get/top20", method = GET)
    public ResponseEntity<List<UrlPojo>> getTop20FullUrls() {
        return statisticService.getTop20FullUrls();
    }

    @RequestMapping(value = "/get/statistic", method = POST)
    public ResponseEntity<GroupedStatisticPojo> getGroupedStatisticByDetailedTime(String shortUrl) {
        return statisticService.getGroupedStatisticByDetailedTime(shortUrl);
    }
}
