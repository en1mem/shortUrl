package ru.german.repository;

import generated.Sequences;
import generated.tables.daos.UrlDao;
import generated.tables.pojos.Redirect;
import generated.tables.pojos.Url;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import ru.german.exception.RedirectException;
import ru.german.model.TopUrlResponse;
import ru.german.model.UrlPojo;

import java.util.List;

import static generated.tables.Redirect.REDIRECT;
import static generated.tables.Url.URL;

@Repository
public class UrlRepository extends UrlDao {

    @Autowired
    DSLContext dslContext;

    public UrlRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
        super.setConfiguration(this.dslContext.configuration());
    }

    public Long getNextValueForUrl() {
        return dslContext.nextval(Sequences.SEQ_URL_ID);
    }

    public void deleteByShortUrl(String shortUrl) {
        dslContext.delete(URL)
                .where(URL.SHORT_URL.eq(shortUrl))
                .execute();
    }

    public Url getUrlByBase62Key(String shortUrl) {
        Url url = dslContext.selectFrom(URL)
                .where(URL.SHORT_URL.eq(shortUrl))
                .fetchAnyInto(Url.class);
        if (url != null) {
            return url;
        } else {
            throw new RedirectException(shortUrl);
        }
    }

    public ResponseEntity<List<String>> getAllExistedShortUrls() {
        List<String> result = dslContext.select(URL.SHORT_URL)
                .from(URL)
                .orderBy(URL.REDIRECT_COUNT)
                .fetchInto(String.class);
        return result.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
    }

    public ResponseEntity<List<TopUrlResponse>> getTopUrls(int top) {
        List<TopUrlResponse> result = dslContext.select(
                    URL.SOURCE_NAME,
                    DSL.sum(URL.REDIRECT_COUNT).as("redirectCount"))
                .from(URL)
                .groupBy(URL.SOURCE_NAME)
                .orderBy(DSL.sum(URL.REDIRECT_COUNT).desc().nullsLast())
                .limit(top)
                .fetchInto(TopUrlResponse.class);

        return result.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
    }

    public String getShortUrl(String fullUrl) {
        return dslContext.select(URL.SHORT_URL).from(URL)
                .where(URL.FULL_URL.eq(fullUrl))
                .fetchOneInto(String.class);
    }

    public void redirectCounter(Long urlId) {
        dslContext.update(URL)
                .set(URL.REDIRECT_COUNT, URL.REDIRECT_COUNT.plus(1))
                .where(URL.ID.eq(urlId))
                .execute();
    }

    public Long getIdByShortUrl(String shortUrl) {
        return dslContext.select(URL.ID)
                .from(URL)
                .where(URL.SHORT_URL.eq(shortUrl))
                .fetchOneInto(Long.class);
    }

    public List<Redirect> getRedirectListByUrlId(Long urlId) {
        return dslContext.selectFrom(REDIRECT)
                .where(REDIRECT.URL_ID.eq(urlId))
                .fetchInto(Redirect.class);
    }

    public String getFullUrlById(Long id) {
        return dslContext.select(URL.FULL_URL).from(URL)
                .where(URL.ID.eq(id))
                .fetchOneInto(String.class);
    }
}
