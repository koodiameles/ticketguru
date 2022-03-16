# Näytä yksi lippu (by ID)

Näytä yhden lipun tiedot. Kaikki **käyttäjät** voivat hakea lipun tiedot.

**URL** : `/tickets/{id}`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

## Success Response

**Code** : `200 OK`

**Content examples**

Haettu lipun (ID 2) tiedot. /tickets/2

```json
{
    "ticketid": 2,
    "valid": true,
    "ticketprice": 25.5,
    "event": {
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
            }
        ]
    },
    "tickettype": {
        "tickettypeid": 2,
        "name": "Child",
        "price": 25.5
    }
}
```
## Error Responses

**Condition** : Id on virheellinen.

**Code** : `404 Not Found`

**Content example**
```json
{
    "timestamp": "2022-02-23T17:20:55.061+00:00",
    "status": 404,
    "error": "Not Found",    
    "message": "Ticket id {id} not found",
    "path": "/tickets/{id}"
}
```


