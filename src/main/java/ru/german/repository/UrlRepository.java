package ru.german.repository;

import generated.Sequences;
import generated.tables.daos.UrlDao;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import ru.german.model.UrlPojo;

import java.util.List;

import static generated.tables.Url.URL;

@Repository
public class UrlRepository extends UrlDao {

    @Autowired
    DSLContext dslContext;

    public Long getNextValueForUrl() {
        return dslContext.nextval(Sequences.SEQ_URL_ID);
    }

    public void delete(String fullUrl) {
        dslContext.delete(URL)
                .where(URL.FULL_URL.eq(fullUrl))
                .execute();
    }

    public ResponseEntity<UrlPojo> getUrlByBase62Key(String shortUrl) {
        UrlPojo url = dslContext.selectFrom(URL)
                .where(URL.SHORT_URL.eq(shortUrl))
                .fetchAnyInto(UrlPojo.class);
        if (url != null) {
            return ResponseEntity.ok(url);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<List<String>> getAllExistedShortUrls() {
        List<String> result = dslContext.select(URL.SHORT_URL)
                .from(URL)
                .orderBy(URL.REDIRECTS.desc())
                .fetchInto(String.class);
        return result != null ?
                ResponseEntity.ok(result) : ResponseEntity.notFound().<List<String>>build();
    }

    public ResponseEntity<List<String>> getTopUrls(int top) {
        List<String> result = dslContext.select(URL.FULL_URL)
                .from(URL)
                .orderBy(URL.REDIRECTS.desc())
                .limit(top)
                .fetchInto(String.class);
        return result != null ?
                ResponseEntity.ok(result) : ResponseEntity.notFound().<List<String>>build();
    }
}
