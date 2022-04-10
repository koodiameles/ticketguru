# Näytä yksi lippu (by ticketcode)

Näytä yhden lipun tiedot. Kaikki **käyttäjät** voivat hakea lipun tiedot.

**URL** : `/tickets?code={ticketcode}`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

## Success Response

**Code** : `200 OK`

**Content examples**

Haettu lipun tiedot.

```json
{
    "ticketid": 2,
    "valid": true,
    "ticketprice": 25.5,
    "ticketcode": "6c4d2587-67a2-4781-afb2-92ebd80c3755",
    "useddatetime": null,
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

**Condition** : Lippukoodi on virheellinen.

**Code** : `404 Not Found`

**Content example**
```json
{
    "timestamp": "2022-02-23T17:20:55.061+00:00",
    "status": 404,
    "error": "Not Found",    
    "message": "Ticket code {ticketcode} not found",
}
```


