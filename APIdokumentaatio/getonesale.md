# Näytä yksi myyntitapahtuma (by ID)

Näytä yhden myyntitapahtuman tiedot.

**URL** : `/sales/{id}`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

**Data example** 

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
## Success Response

**Code** : `200 OK`

## Error Responses

**Condition** : Jos id:llä ei löydy myyntitapahtumaa.

**Code** : `404 NOT FOUND`