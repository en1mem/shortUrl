package ru.german.repository;

import generated.tables.daos.RedirectDao;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RedirectRepository extends RedirectDao {

    @Autowired
    DSLContext dslContext;

    public RedirectRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
        super.setConfiguration(this.dslContext.configuration());
    }
}
