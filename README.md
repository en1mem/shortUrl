
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
CREATE TABLE urlPojo (
              id bigint primary key,
              redirects bigint NOT NULL DEFAULT 0,
              full_url text NOT NULL,
              short_url text NOT NULL,
	      created_date_time timestamp NOT NULL DEFAULT now()
);

CREATE SEQUENCE seq_url_id OWNED BY urlPojo.id;
alter table urlPojo alter column id set default nextval('seq_url_id');
```