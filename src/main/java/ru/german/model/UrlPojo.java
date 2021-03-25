package ru.german.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.german.utils.Base62;

@Getter
@Setter
@NoArgsConstructor
public class UrlPojo {
    Long id;
    String fullUrl;
    String shortUrl;
    Long redirects;

    public UrlPojo(Long id, String fullUrl) {
        this.fullUrl = fullUrl;
        this.shortUrl = Base62.convertToBase62(id);
    }
}