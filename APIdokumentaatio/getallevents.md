# Näytä kaikki tapahtumat

Näytä lista kaikista tapahtumista. Kaikki **käyttäjät** voivat hakea listan tapahtumista. 

**URL** : `/events`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

## Success Response

**Code** : `200 OK`

**Data examples**

```json
    {
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
    {
        "eventid": 2,
        "description": "Trio röyhkeät",
        "location": "Musiikkitalo",
        "city": "Helsinki",
        "ticketcount": 300,
        "datetime": "2022-08-12T12:30:00.000+00:00",
        "duration": 120,
        "tickettypes": [
            {
                "tickettypeid": 3,
                "name": "Adult",
                "price": 13.5
            },
            {
                "tickettypeid": 4,
                "name": "Child",
                "price": 5.5
            },
            {
                "tickettypeid": 7,
                "name": "Adult",
                "price": 50.5
            }
        ]
    }
```
## Error Responses

**Condition** : Henkilöllä ei ole oikeuksia nähdä listausta (esim. käyttäjä ei ole kirjautunut).

**Code** : `401 Unauthorized`