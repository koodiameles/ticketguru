# Näytä yksi myyntitapahtuma (by ID)

Näytä yhden myyntitapahtuman tiedot. Kaikki **käyttäjät** voivat hakea myyntitapahtuman tiedot.

**URL** : `/sales/{id}`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

## Success Response

**Code** : `200 OK`

**Content examples**
```json
{
    "saleid": 1,
    "datetime": "2022-03-15T20:37:12.664+00:00",
    "employee": null,
    "tickets": [
        {
            "ticketid": 1,
            "valid": true,
            "ticketprice": 50.5,
            "event": {
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
            },
            "tickettype": {
                "tickettypeid": 1,
                "name": "Adult",
                "price": 50.5
            }
        }
    ]
}
```

## Error Responses

**Condition** : Jos id:llä ei löydy myyntitapahtumaa.

**Code** : `404 Not Found`

**Content example**
```json
{
    "timestamp": "2022-05-01T18:09:38.446+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "Sale id 14 not found",
    "path": "/sales/14"
}
```

### Or

**Condition** : Jos myyntitapahtuma on olemassa, mutta henkilöllä ei ole oikeuksia sen hakemiseen (esim. käyttäjä ei ole kirjautunut).

**Code** : `401 Unauthorized`