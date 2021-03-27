package ru.german.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
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
            RedirectView failedView = new RedirectView();
            failedView.setStatusCode(HttpStatus.NOT_FOUND);

            return failedView;
        } else {
            return new RedirectView(redirectLink);
        }
    }
}
