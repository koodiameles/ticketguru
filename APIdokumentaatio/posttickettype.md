# Luo lipputyyppi

Luo lipputyyppi. Lipputyypin luonti vaatii Admin-oikeudet.

**URL** : `/tickettypes`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : None

**Data example** 

```json
{
    "event": 3,
    "name": "Adult",
    "price": 50.5
}
```

## Success Response

**Condition** : Jos kaikki on OK.

**Code** : `201 CREATED`

**Content example**

```json
{
    "tickettypeid": 7,
    "name": "Adult",
    "price": 50.5
}
```

## Error Responses

**Condition** : Tapahtuman id puuttuu tai on virheellinen.

**Code** : `400 BAD REQUEST`

**Content example**

```json
{
    "timestamp": "2022-05-01T16:48:41.542+00:00",
    "status": 400,
    "error": "Bad Request",
    "message": "Event not valid",
    "path": "/tickettypes"
}
```

### Or

**Condition** : Lipputyypin nimi puuttuu.

**Code** : `400 BAD REQUEST`

**Content example**

```json
{
    "timestamp": "2022-05-01T16:32:42.024+00:00",
    "status": 400,
    "error": "Bad Request",
    "message": "must not be null",
    "path": "/tickettypes"
}
```
### Or

**Condition** : Henkilöllä ei ole oikeuksia luoda lipputyyppiä (esim. käyttäjänä user).

**Code** : `401 Unauthorized`