package ru.german.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.german.utils.Base62;

import java.net.MalformedURLException;
import java.net.URL;

@Getter
@Setter
@NoArgsConstructor
public class UrlPojo {
    Long id;
    String fullUrl;
    String shortUrl;
    Long redirectCount;

    public UrlPojo(Long id, String fullUrl) {
        this.fullUrl = fullUrl;
        this.redirectCount = 1L;
        try {
            URL url = new URL(fullUrl);
            String protocol = url.getProtocol();
            String host = url.getHost();
            int port = url.getPort();

            if (port == -1) {
                this.shortUrl = String.format("%s://%s", protocol, host) + "/" + Base62.convertToBase62(id);
            } else {
                this.shortUrl = String.format("%s://%s:%d", protocol, host, port) + "/" + Base62.convertToBase62(id);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
