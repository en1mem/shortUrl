package ru.german.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.german.service.UrlService;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/url")
public class UrlController {

    @Autowired
    UrlService urlService;

    @Transactional
    @RequestMapping(value = "/get", method = POST)
    public ResponseEntity<String> getShortUrl(@RequestBody String fullUrl) {
        return urlService.getShortUrl(fullUrl);
    }

    @Transactional
    @RequestMapping(value = "/delete", method = POST)
    public ResponseEntity<String> deleteShortUrl(@RequestBody String shortUrl) {
        return urlService.deleteShortUrl(shortUrl);
    }
}
