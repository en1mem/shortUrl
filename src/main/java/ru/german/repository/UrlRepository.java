package ru.german.repository;

import generated.Sequences;
import generated.tables.daos.RedirectDao;
import generated.tables.daos.UrlDao;
import generated.tables.pojos.Redirect;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import ru.german.model.UrlPojo;

import java.util.List;

import static generated.tables.Redirect.REDIRECT;
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
                .orderBy(URL.REDIRECT_COUNT)
                .fetchInto(String.class);
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<List<UrlPojo>> getTopUrls(int top) {

//        Field<Integer> rowNumber = DSL.rowNumber()
//                .over().partitionBy(URL.SOURCE_NAME)
//                .orderBy(URL.REDIRECT_COUNT.desc())
//                .as("row");
//
//        List<UrlPojo> result = dslContext.select()
//                .from(
//                        DSL.select(
//                                rowNumber,
//                                URL.SOURCE_NAME,
//                                URL.REDIRECT_COUNT.as("redirects")
//                        )
//                        .from(URL)
//                        .orderBy(URL.REDIRECT_COUNT.desc())
//                )
//                .where(DSL.field("row").eq(1))
//                .fetchInto(UrlPojo.class);

        List<UrlPojo> result =
                dslContext.select(
                    URL.SOURCE_NAME,
                    URL.REDIRECT_COUNT.as("redirects"))
                .from(URL)
                .orderBy(URL.REDIRECT_COUNT)
                .limit(top)
                .fetchInto(UrlPojo.class);
        return ResponseEntity.ok(result);
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
}
