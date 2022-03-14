# Näytä yksi tapahtuma (by ID)

Näytä yhden tapahtuman tiedot. Kaikki **käyttäjät** voivat hakea tapahtuman tiedot.

**URL** : `/events/{id}`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

## Success Response

**Code** : `200 OK`

**Content examples**

Haettu tapahtuman (ID 99) tiedot. /events/99

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

Haettu tapahtuman (ID 2) tiedot. Tapahtumalla ei ole kaikkia arvoja alustettu. /events/2

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

**Condition** : Id on virheellinen.

**Code** : `404 Not Found`

**Content example**
```json
{
    "timestamp": "2022-02-23T17:20:55.061+00:00",
    "status": 404,
    "error": "Not Found",    
    "message": "Event id {id} not found",
    "path": "/events/{id}"
}
```

### Or

**Condition** : Jos tapahtuma on olemssa, mutta henkilöllä ei ole oikeuksia sen hakemiseen (esim. käyttäjä ei ole kirjautunut).

**Code** : `403 FORBIDDEN`

**Content** :

```json
{"detail": "You do not have permission to perform this action."}
```
