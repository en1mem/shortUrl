package ru.german.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.german.exception.RedirectException;
import ru.german.service.RedirectService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/redirect")
public class RedirectController {

    @Autowired
    RedirectService redirectService;

    @RequestMapping(value = "/{shortUrl}", method = GET)
    public RedirectView redirect(@PathVariable String shortUrl) {
        String redirectLink = redirectService.doRedirect(shortUrl);
        if (redirectLink.isEmpty()) {
            throw new RedirectException(shortUrl);
        } else {
            return new RedirectView(redirectLink);
        }
    }
}
