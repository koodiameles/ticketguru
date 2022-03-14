# Luo Tapahtuma

Luo tapahtuma. Tapahtuman luonti vaatii Admin-oikeudet.

**URL** : `/events`

**Method** : `POST`

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
    "datetime" : "2022-05-22T18:00:00",
    "duration" :  "130"
}
```

## Success Response

**Condition** : Jos kaikki on OK ja tapahtumalle annettiin nimi.

**Code** : `201 CREATED`

**Content example**

```json
{
    "eventid": 5,
    "description": "Sinfoniaorkesteri",
    "location": "Musiikkitalo",
    "city": "Tamperee",
    "ticketcount": 3000,
    "datetime": "2023-08-12T12:30:00.000+00:00",
    "duration": 120
}
```

## Error Responses

**Condition** : Jos tapahtumalle ei annettu nimeä.

**Code** : `400 BAD REQUEST`

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

**Code** : `400 BAD REQUEST`

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

**Condition** : Jos lippumäärä ylittää maksimin.

**Code** : `400 BAD REQUEST`

**Content example**

```json
{
    "timestamp": "2022-02-23T17:20:55.061+00:00",
    "status": 400,
    "error": "Bad request",    
    "message": "must be less than or equal to 40000",
    "path": "/events",
}
```
