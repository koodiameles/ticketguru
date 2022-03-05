# Poista Tapahtuma

Poista tapahtuma. Tapahtuman poistaminen vaatii Admin-oikeudet.

**URL** : `/events/{id}`

**Method** : `DELETE`

**Auth required** : YES

**Permissions required** : None

**Data constraints**

Tapahtumalla on oltava nimi (description).

```json
{
    "description": "[unicode 100 chars max]"
}
```

## Success Response

**Condition** : Jos kaikki on OK ja tapahtuma poistettiin.

**Code** : `200 OK`

**Content example**
```json
{
    "message": "Deleted event {description} with the id {id}"
}
```

## Error Responses

**Condition** : Id puuttuu.

**Code** : `405 Method not allowed`

**Content example**
```json
{
    "timestamp": "2022-02-23T17:20:55.061+00:00",
    "status": 405,
    "error": "Method Not Allowed",    
    "message": "Request method 'DELETE' not supported",
    "path": "api/event"
}
```

**Condition** : Id on virheellinen.

**Code** : `500 Internal Server Error`

**Content example**
```json
{
    "timestamp": "2022-02-23T17:20:55.061+00:00",
    "status": 500,
    "error": "Internal Server Error",    
    "message": "No class fi.ohjelmistoprojekti1.TicketGuru.domain.Event entity with id {id} exists!",
    "path": "api/event/{id}"
}
```