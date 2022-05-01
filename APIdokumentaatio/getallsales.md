# Näytä kaikki myyntitapahtumat

Näytä lista kaikista myyntitapahtumista. Kaikki **käyttäjät** voivat hakea myyntitapahtumien tiedot.

**URL** : `/sales`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

## Success Response

**Code** : `200 OK`

**Data examples**

```json
    {
        "saleid": 1,
        "datetime": "2022-03-19T07:58:08.600+00:00",
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
    },
```
## Error Responses

**Condition** : Henkilöllä ei ole oikeuksia nähdä listausta (esim. käyttäjä ei ole kirjautunut).

**Code** : `401 Unauthorized`
