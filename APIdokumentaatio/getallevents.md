# Näytä kaikki tapahtumat

Näytä lista kaikista tapahtumista. Kaikilla käyttöoikeuksilla saa etsittyä listauksen. 

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
    }
```