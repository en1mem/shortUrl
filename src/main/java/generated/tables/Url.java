/*
 * This file is generated by jOOQ.
*/
package generated.tables;


import generated.Keys;
import generated.Public;
import generated.tables.records.UrlRecord;
import org.jooq.*;
import org.jooq.impl.TableImpl;

import javax.annotation.Generated;
import java.sql.Timestamp;
import java.util.Arrays;
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
public class Url extends TableImpl<UrlRecord> {

    private static final long serialVersionUID = 1592667485;

    /**
     * The reference instance of <code>public.url</code>
     */
    public static final Url URL = new Url();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UrlRecord> getRecordType() {
        return UrlRecord.class;
    }

    /**
     * The column <code>public.url.id</code>.
     */
    public final TableField<UrlRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('seq_url_id'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.url.redirects</code>.
     */
    public final TableField<UrlRecord, Long> REDIRECTS = createField("redirects", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.url.full_url</code>.
     */
    public final TableField<UrlRecord, String> FULL_URL = createField("full_url", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.url.short_url</code>.
     */
    public final TableField<UrlRecord, String> SHORT_URL = createField("short_url", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.url.created_date_time</code>.
     */
    public final TableField<UrlRecord, Timestamp> CREATED_DATE_TIME = createField("created_date_time", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * Create a <code>public.url</code> table reference
     */
    public Url() {
        this("url", null);
    }

    /**
     * Create an aliased <code>public.url</code> table reference
     */
    public Url(String alias) {
        this(alias, URL);
    }

    private Url(String alias, Table<UrlRecord> aliased) {
        this(alias, aliased, null);
    }

    private Url(String alias, Table<UrlRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<UrlRecord, Long> getIdentity() {
        return Keys.IDENTITY_URL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<UrlRecord> getPrimaryKey() {
        return Keys.URL_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<UrlRecord>> getKeys() {
        return Arrays.<UniqueKey<UrlRecord>>asList(Keys.URL_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Url as(String alias) {
        return new Url(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Url rename(String name) {
        return new Url(name, null);
    }
}