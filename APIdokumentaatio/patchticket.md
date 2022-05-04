# Muuta lippu käytetyksi

Käytä Lippu. Kaikki **käyttäjät** voivat päivittää lipun käytetyksi.

**URL** : `/tickets?code={ticketcode}`

**Method** : `PATCH`

**Auth required** : YES

**Permissions required** : None

## Success Response

**Condition** : Jos lipun muuttaminen käytetyksi onnistui, palautetaan lipunkäyttöajankohta.

**Code** : `200 OK`

**Content example**

```json
{
    "used": "2022-04-10T16:51:42.337+00:00"
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

### Or

**Condition** : Lippukoodilla löytyvä lippu on jo käytetty.

**Code** : `400 Forbidden`

**Content example**
```json
{
    "timestamp": "2022-02-23T17:20:55.061+00:00",
    "status": 400,
    "error": "Forbidden",    
    "message": "Ticket with ticket code {ticketcode} has already been used",
}
```
### Or

**Condition** : Henkilöllä ei ole oikeuksia muuttaa lippua käytetyksi (esim. käyttäjä ei ole kirjautunut).

**Code** : `401 Unauthorized`