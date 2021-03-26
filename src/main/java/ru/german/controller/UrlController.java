package ru.german.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.german.model.UrlPojo;
import ru.german.service.RedirectService;
import ru.german.service.RewriteService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/manual")
public class UrlController {

    @Autowired
    RewriteService rewriteService;

    @Autowired
    RedirectService redirectService;

    @RequestMapping(value = "/get/{fullUrl}", method = GET)
    public ResponseEntity<String> getShortUrl(@PathVariable String fullUrl) {
        return rewriteService.getShortUrl(fullUrl);
    }

    @RequestMapping(value = "/delete/{url}", method = POST)
    public ResponseEntity<String> deleteShortUrl(@PathVariable String url) {
        return rewriteService.deleteShortUrl(url);
    }

    @RequestMapping(value = "/get/info/{shortUrl}", method = GET)
    public ResponseEntity<UrlPojo> getInfo(@PathVariable String shortUrl) {
        return rewriteService.getInfo(shortUrl);
    }

    @RequestMapping(value = "/{fullUrl}", method = GET)
    public RedirectView redirect(@PathVariable String fullUrl) {
        return new RedirectView(redirectService.doRedirect(fullUrl));
    }
}
