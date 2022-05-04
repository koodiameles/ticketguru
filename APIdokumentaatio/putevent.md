# Päivitä tapahtuma

Päivitä tapahtuma. Tapahtuman päivitys vaatii Admin-oikeudet.

**URL** : `/events/{id}`

**Method** : `PUT`

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

**Condition** : Jos kaikki on OK ja tapahtumalle annettiin nimi.

**Code** : `201 CREATED`

**Content example**

```json
{
    "id": 99,
    "description": "Kalevala Ooppera",
    "url": "http://testserver/api/events/99/"
}
```

## Error Responses

**Condition** : Jos tapahtumalle ei annettu nimeä.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "2022-02-23T17:20:55.061+00:00",
    "status": 400,
    "error": "Bad request",    
    "message": "Event must have a name/description",
    "path": "/events",
}
```
### Or

**Condition** : Jos päivämäärä/aika on jo mennyt.

**Code** : `400 Bad Request`

**Content example**

```json
{
    "timestamp": "2022-02-23T17:20:55.061+00:00",
    "status": 400,
    "error": "Bad request",    
    "message": "must be a date in the present or in the future",
    "path": "/events",
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
### Or

**Condition** : Henkilöllä ei ole oikeuksia muokata tapahtumaa (esim. käyttäjänä user).

**Code** : `401 Unauthorized`