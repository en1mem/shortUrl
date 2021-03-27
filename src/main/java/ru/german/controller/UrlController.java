package ru.german.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.german.model.UrlPojo;
import ru.german.service.RedirectService;
import ru.german.service.RewriteService;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/manual")
//убрал get + path потому что не работало нормально со ссылкой внутри ссылки(redirect loop)
public class UrlController {

    @Autowired
    RewriteService rewriteService;

    @Autowired
    RedirectService redirectService;

    @RequestMapping(value = "/get", method = POST)
    public ResponseEntity<String> getShortUrl(@RequestBody String fullUrl) {
        return rewriteService.getShortUrl(fullUrl);
    }

    @RequestMapping(value = "/delete", method = POST)
    public ResponseEntity<String> deleteShortUrl(@RequestBody String shortUrl) {
        return rewriteService.deleteShortUrl(shortUrl);
    }

    @RequestMapping(value = "/get/info", method = POST)
    public ResponseEntity<UrlPojo> getInfo(@RequestBody String shortUrl) {
        return rewriteService.getInfo(shortUrl);
    }

    @RequestMapping(value = "/redirect", method = POST)
    public RedirectView redirect(@RequestBody String shortUrl) {
        return new RedirectView(redirectService.doRedirect(shortUrl));
    }
}
