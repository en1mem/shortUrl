
# Build

## JOOQ Codegen

```
install -Dhost=localhost -Dport=5432 -Ddb=postgres -Dlogin=postgres -Dpassword=postgres jooq-codegen:generate -P codegen
```

## Run

```
-DdbHost=localhost 
-DdbUser=postgres 
-DdbPassword=postgres 
-DdatabaseName=postgres 
-DdbPortNumber=5432
```

## sql config

```
CREATE TABLE url (
              id bigint primary key,
              full_url text NOT NULL,
              short_url text NOT NULL,
              source_name text NOT NULL, 
              redirect_count bigint DEFAULT 1,
	      created_date_time timestamp NOT NULL DEFAULT now()
);

CREATE SEQUENCE seq_url_id OWNED BY url.id;
alter table url alter column id set default nextval('seq_url_id');


CREATE TABLE redirect (
              id bigint primary key,
              url_id bigint NOT NULL,
	      created_date_time timestamp NOT NULL DEFAULT now()
);

CREATE SEQUENCE seq_redirect_id OWNED BY redirect.id;
alter table redirect alter column id set default nextval('seq_redirect_id');


create index idx_url_short_url on url (short_url);
```