package ru.german.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RedirectException extends RuntimeException {
    public RedirectException(String link) {
        super(String.format("link '%s' not found", link));
    }
}
