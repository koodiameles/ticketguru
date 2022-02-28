# Näytä yksi tapahtuma (by ID)

Näytä yhden tapahtuman tiedot. Kaikki **käyttäjät** voivat hakea tapahtuman tiedot.

**URL** : `/api/event/`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

## Success Response

**Code** : `200 OK`

**Content examples**

Haettu tapahtuman (ID 99) tiedot. /api/event/99

```json
{
    "id": 99,
    "description": "Kalevala Ooppera",
    "location" : "Finlandia-Talo",
    "city" : "Helsinki",
    "ticketcount" : "400" ,
    "datetime" : "2022-05-22T18:00:00" ,
    "duration" :  "130"
}
```

Haettu tapahtuman (ID 2) tiedot. Tapahtumalla ei ole kaikkia arvoja alustettu. /api/event/2

```json
{
    "id": 2,
    "description": "Näytelmä1",
    "location" : null,
    "city" : null,
    "ticketcount" : null,
    "datetime" : null,
    "duration" :  null
}
```
## Error Responses

**Condition** : Jos kyseisellä `id` arvolla ei löydy tapahtumaa.

**Code** : `404 NOT FOUND`

**Content** : `{}`

### Or

**Condition** : Jos tapahtuma on olemssa, mutta henkilöllä ei ole oikeuksia sen hakemiseen (esim. käyttäjä ei ole kirjautunut).

**Code** : `403 FORBIDDEN`

**Content** :

```json
{"detail": "You do not have permission to perform this action."}
```
