/*
 * This file is generated by jOOQ.
*/
package generated.tables.daos;


import generated.tables.Redirect;
import generated.tables.records.RedirectRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Generated;
import java.sql.Timestamp;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Repository
public class RedirectDao extends DAOImpl<RedirectRecord, generated.tables.pojos.Redirect, Long> {

    /**
     * Create a new RedirectDao without any configuration
     */
    public RedirectDao() {
        super(Redirect.REDIRECT, generated.tables.pojos.Redirect.class);
    }

    /**
     * Create a new RedirectDao with an attached configuration
     */
    @Autowired
    public RedirectDao(Configuration configuration) {
        super(Redirect.REDIRECT, generated.tables.pojos.Redirect.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Long getId(generated.tables.pojos.Redirect object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<generated.tables.pojos.Redirect> fetchById(Long... values) {
        return fetch(Redirect.REDIRECT.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public generated.tables.pojos.Redirect fetchOneById(Long value) {
        return fetchOne(Redirect.REDIRECT.ID, value);
    }

    /**
     * Fetch records that have <code>url_id IN (values)</code>
     */
    public List<generated.tables.pojos.Redirect> fetchByUrlId(Long... values) {
        return fetch(Redirect.REDIRECT.URL_ID, values);
    }

    /**
     * Fetch records that have <code>created_date_time IN (values)</code>
     */
    public List<generated.tables.pojos.Redirect> fetchByCreatedDateTime(Timestamp... values) {
        return fetch(Redirect.REDIRECT.CREATED_DATE_TIME, values);
    }
}
