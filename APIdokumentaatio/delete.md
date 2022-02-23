# Poista Tapahtuma

Poista tapahtuma. Tapahtuman poistaminen vaatii Admin-oikeudet.

**URL** : `/api/event/{id}`

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

**Data example**

```json
{
    "description": "Kalevala Ooppera",
    "location" : "Finlandia-Talo",
    "city" : "Helsinki",
    "ticketcount" : "400" ,
    "datetime" : "2022-05-22T18:00:00" ,
    "duration" :  "130"
}
```

## Success Response

**Condition** : Jos kaikki on OK ja tapahtuma poistettiin.

**Code** : `200 OK`

**Content example**
```json
{}
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