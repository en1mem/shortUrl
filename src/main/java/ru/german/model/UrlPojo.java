package ru.german.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private final String API = "/redirect/";

    public UrlPojo(Long id, String fullUrl, String currentHost) {
        this.fullUrl = fullUrl;
        this.redirectCount = 0L;

        this.shortUrl = currentHost + API + Base62.convertToBase62(id);
    }
}
