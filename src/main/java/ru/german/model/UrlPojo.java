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
    Long redirectCount;

    private final String API = "/redirect/";

    public UrlPojo(Long id, String fullUrl, String currentHost) {
        this.fullUrl = fullUrl;
        this.redirectCount = 1L;

        this.shortUrl = currentHost + API + Base62.convertToBase62(id);
    }
}
