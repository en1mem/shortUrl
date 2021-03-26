/*
 * This file is generated by jOOQ.
*/
package generated.tables.pojos;


import javax.annotation.Generated;
import java.io.Serializable;
import java.sql.Timestamp;


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
public class Redirect implements Serializable {

    private static final long serialVersionUID = 1328240517;

    private Long      id;
    private Long      urlId;
    private Timestamp createdDateTime;

    public Redirect() {}

    public Redirect(Redirect value) {
        this.id = value.id;
        this.urlId = value.urlId;
        this.createdDateTime = value.createdDateTime;
    }

    public Redirect(
        Long      id,
        Long      urlId,
        Timestamp createdDateTime
    ) {
        this.id = id;
        this.urlId = urlId;
        this.createdDateTime = createdDateTime;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUrlId() {
        return this.urlId;
    }

    public void setUrlId(Long urlId) {
        this.urlId = urlId;
    }

    public Timestamp getCreatedDateTime() {
        return this.createdDateTime;
    }

    public void setCreatedDateTime(Timestamp createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Redirect (");

        sb.append(id);
        sb.append(", ").append(urlId);
        sb.append(", ").append(createdDateTime);

        sb.append(")");
        return sb.toString();
    }
}