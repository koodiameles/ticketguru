# Näytä yksi tapahtuma (by ID)

Näytä yhden tapahtuman tiedot. Kaikki **käyttäjät** voivat hakea tapahtuman tiedot.

**URL** : `/events/{id}`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

## Success Response

**Code** : `200 OK`

**Content examples**

Haettu tapahtuman (ID 1) tiedot. /events/1

```json
{
    "eventid": 1,
    "description": "Konsertti",
    "location": "Finlandia-Talo",
    "city": "Helsinki",
    "ticketcount": 400,
    "datetime": "2022-06-24T15:00:00.000+00:00",
    "duration": 90,
    "tickettypes": [
        {
            "tickettypeid": 1,
            "name": "Adult",
            "price": 50.5
        },
        {
            "tickettypeid": 2,
            "name": "Child",
            "price": 25.5
        }
    ]
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

**Condition** : Jos tapahtuma on olemassa, mutta henkilöllä ei ole oikeuksia sen hakemiseen (esim. käyttäjä ei ole kirjautunut).

**Code** : `401 Unauthorized`