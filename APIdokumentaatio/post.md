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
    "id": 99,
    "description": "Kalevala Ooppera",
    "url": "http://testserver/api/events/99/"
}
```

## Error Responses

**Condition** : Jos tapahtumalle ei annettu nimeä.

**Code** : `400 BAD REQUEST`

**Content example**

```json
{
    "description": [
        "This field is required."
    ]
}
```