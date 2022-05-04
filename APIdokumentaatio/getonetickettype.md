# Näytä yksi lipputyyppi (by ID)

Näytä yhden lipputyypin tiedot. Kaikki **käyttäjät** voivat hakea lipun tiedot.

**URL** : `/tickettypes/{id}`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

## Success Response

**Code** : `200 OK`

**Content examples**

Haettu lipputyypin (ID 1) tiedot. /tickettypes/1

```json
{
    "tickettypeid": 1,
    "name": "Adult",
    "price": 50.5
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
    "message": "Tickettype id {id} is not valid",
    "path": "/tickettypes/{id}"
}
```
### Or

**Condition** : Jos lipputyyppi on olemassa, mutta henkilöllä ei ole oikeuksia sen hakemiseen (esim. käyttäjä ei ole kirjautunut).

**Code** : `401 Unauthorized`