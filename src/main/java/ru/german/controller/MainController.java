package ru.german.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.german.service.MainService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/manual/url")
public class MainController {

    @Autowired
    MainService mainService;

    @RequestMapping(value = "/get/{fullUrl}", method = GET)
    public ResponseEntity<String> createShortUrl(@PathVariable String fullUrl) {
        return mainService.createShortUrl(fullUrl);
    }

    @RequestMapping(value = "/get/info/{shortUrl}", method = GET)
    public ResponseEntity<String> getShortUrl(@PathVariable String shortUrl) {
        return mainService.getInfo(shortUrl);
    }

    @RequestMapping(value = "/get/top20", method = GET)
    public ResponseEntity<String> getTop20FullUrls() {
        return mainService.getTop20FullUrls(shortUrl);
    }

    @RequestMapping(value = "/get/statistic", method = POST)
    public ResponseEntity<Object> getGroupedStatistic() {
        return mainService.getGroupedStatistic();
    }

    @RequestMapping(value = "/getAll", method = POST)
    public ResponseEntity<String> getAllExistedShortUrls() {
        return mainService.getAllExistedShortUrls();
    }

    @RequestMapping(value = "/delete/{url}", method = POST)
    public ResponseEntity<String> deleteShortUrl(@PathVariable String url) {
        return mainService.deleteShortUrl(url);
    }
}
