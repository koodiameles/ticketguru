# Poista Tapahtuma

Poista tapahtuma. Tapahtuman poistaminen vaatii Admin-oikeudet.

**URL** : `/events/{id}`

**Method** : `DELETE`

**Auth required** : YES

**Permissions required** : None

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
    "path": "/events/"
}
```

### Or

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