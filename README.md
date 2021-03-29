
# Build

## Shortener URLs (tested with Postman)

## Run (spring boot conf)

```
-DdbHost=localhost
-DdbUser=postgres
-DdbPassword=postgres
-DdatabaseName=postgres
-DdbPortNumber=5432
-DcurrentHost=localhost:8080
```

## JOOQ Codegen (maven conf)
#### Not necessary if you use a folder @generated@ in java package
```
install -Dhost=localhost -Dport=5432 -Ddb=postgres -Dlogin=postgres -Dpassword=postgres jooq-codegen:generate -P codegen
```


## sql config (postgres) (done with pgAdmin and DataGrip)

```
CREATE TABLE url (
              id bigint primary key,
              full_url text NOT NULL,
              short_url text NOT NULL,
              source_name text NOT NULL, 
              redirect_count bigint,
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

## Api Description

### Url entities
##### Generate Short Url:
```
localhost:8080/url/get, method POST, body {fullLink} 
(example: https://ria.ru/20210329/razgruzka-1603232673.html)

return new short url;
(example localhost:8080/redirect/a)
```

##### Delete Short Url:
```
localhost:8080/url/delete, method POST, body {shortLink} 
(example: localhost:8080/redirect/a)

return message;
(example "localhost:8080/redirect/a was successfully deleted")
```
### Statistic
##### Getting info about generated short link:
```
localhost:8080/statistic/get/info, method POST, body {fullLink} 
(example: https://ria.ru/20210329/razgruzka-1603232673.html)

return info;
(example return:
{
    "id": 44,
    "fullUrl": "https://yandex.ru/news/story/WSJ_zablokirovavshij_Suehckij_kanal_kontejnerovoz_mogut_snyat_smeli_27_marta--4ba9bcb20c94ed242951e0861caad9e9?fan=1&from=main_portal&lang=ru&lr=50&mlid=1616875419.glob_225.4ba9bcb2&msid=1616876030.49613.85209.144817&persistent_id=136874012&stid=ktCH0tm2sTGCg9KMeunK&t=1616875419&utm_medium=topnews_news&utm_source=morda_yabrotab",
    "shortUrl": "localhost:8080/redirect/G",
    "createdDateTime": 1616905513223,
    "redirectCount": 7,
    "sourceName": "yandex.ru"
})
```

##### Getting all generated short links:
```
localhost:8080/statistic/getAll, method POST

return message;
(example return:
[
    "localhost:8080/redirect/M",
    "localhost:8080/redirect/I",
    "localhost:8080/redirect/W",
    "localhost:8080/redirect/C",
    "localhost:8080/redirect/A",
    "localhost:8080/redirect/U",
    "localhost:8080/redirect/S",
    "localhost:8080/redirect/K",
    "localhost:8080/redirect/G",
    "localhost:8080/redirect/9",
    "localhost:8080/redirect/E",
    "localhost:8080/redirect/Q"
])
```

##### Getting top 20 host-link by redirect-count:
```
localhost:8080/statistic/top20, method POST

return list of (short url);
(example return:
[
    {
        "sourceName": "youtube.com",
        "redirectCount": 19
    },
    {
        "sourceName": "yandex.ru",
        "redirectCount": 14
    },
    {
        "sourceName": "life.ru",
        "redirectCount": 3
    },
    {
        "sourceName": "hightech-fm.turbopages.org",
        "redirectCount": 2
    },
    {
        "sourceName": "kp.ru",
        "redirectCount": 1
    },
]
```

##### The Grouped statistic (day, hour, minute) by a short link:
```
localhost:8080/statistic/grouped, method POST, body {shortLink} 
(example: localhost:8080/redirect/a)

return message;
(example return:
{
  "shortUrl" : "localhost:8080/redirect/G",
  "totalRedirectCount" : 6,
  "dayGroup" : [ {
    "dayOfMonth" : 27,
    "hourGroup" : {
      "9" : {
        "minuteGroup" : {
          "25" : {
            "redirectCount" : 2
          }
        },
        "redirectCount" : 2
      }
    },
    "redirectCount" : 2
  }, {
    "dayOfMonth" : 28,
    "hourGroup" : {
      "8" : {
        "minuteGroup" : {
          "25" : {
            "redirectCount" : 1
          }
        },
        "redirectCount" : 1
      },
      "9" : {
        "minuteGroup" : {
          "25" : {
            "redirectCount" : 1
          },
          "27" : {
            "redirectCount" : 2
          }
        },
        "redirectCount" : 3
      }
    },
    "redirectCount" : 4
  } ]
})
```

### Redirect Api
##### Generate Short Url:
```
localhost:8080/redirect/{shortLink postfix}, method GET
(example: localhost:8080/redirect/a)

return redirect to full link;
(example redirect to https://ria.ru/20210329/razgruzka-1603232673.html)
```

### Example entities
##### rewrite timestamp date(according your request) before testing:
```
insert into url values
(44, 
'https://yandex.ru/news/story/WSJ_zablokirovavshij_Suehckij_kanal_kontejnerovoz_mogut_snyat_smeli_27_marta--4ba9bcb20c94ed242951e0861caad9e9?fan=1&from=main_portal&lang=ru&lr=50&mlid=1616875419.glob_225.4ba9bcb2&msid=1616876030.49613.85209.144817&persistent_id=136874012&stid=ktCH0tm2sTGCg9KMeunK&t=1616875419&utm_medium=topnews_news&utm_source=morda_yabrotab', 
'localhost:8080/redirect/G',
 now(), 
 'yandex.ru');

insert into redirect values (45,44, now()),
(46,44, now()),
(47,44, now()),
(42,44, now()),
(44,44, now()),
(43,44, now());
```