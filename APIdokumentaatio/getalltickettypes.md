# Näytä kaikki lipputyypit

Näytä lista kaikista lipputyypeistä. Kaikki **käyttäjät** voivat hakea listan.

**URL** : `/tickettypes`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

## Success Response

**Code** : `200 OK`

**Data example** 

```json
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
```