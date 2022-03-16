# Luo Tapahtuma

Luo Lippu. Lippu kuuluu aina myyntitapahtumaan (sale). Lipun luonti vaatii Admin-oikeudet.

**URL** : `/sales/{id}/tickets`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : None

**Data example** 

*Ilman hintaa (Hinta tulee tällöin automaattisesti tickettype:sta)*

```json
{
    "eventid": 1,
    "tickettypeid": 4
}
```

*Hinnan kanssa*
```json
{
    "eventid": 1,
    "tickettypeid": 4,
    "price": 200.00
}
```

## Success Response

**Condition** : Jos kaikki on OK.

**Code** : `201 CREATED`

**Content example**

*Hinta tulee automaattisesti tickettype:sta*
```json
{
    "ticketid": 4,
    "eventid": 1,
    "tickettypeid": 4,
    "tickettype": "Child",
    "valid": true,
    "description": "Konsertti",
    "price": 5.5
}
```
*Hinta syötetty post:n mukana*
```json
{
    "ticketid": 4,
    "eventid": 1,
    "tickettypeid": 4,
    "tickettype": "Child",
    "valid": true,
    "description": "Konsertti",
    "price": 200.0
}
```

## Error Responses

**Condition** : Annettu eventid on virheellinen.

**Code** : `400 BAD REQUEST`

**Content example**

```json
{
    "timestamp": "2022-02-23T17:20:55.061+00:00",
    "status": 400,
    "error": "Bad request",    
    "message": "Event id 666  not valid",
    "path": "/sales/1/tickets"
}
```

### Or

**Condition** : Annettu tickettypeid on virheellinen.

**Code** : `400 BAD REQUEST`

**Content example**

```json
{
    "timestamp": "2022-02-23T17:20:55.061+00:00",
    "status": 400,
    "error": "Bad request",    
    "message": "Tickettype id 666 not valid",
    "path": "/sales/1/tickets"
}
```

### Or

**Condition** : Annettu price on virheellinen (Huom! Price kohtaan ei tarvitse merkitä mitään ja se saa olla myös null)

**Code** : `400 BAD REQUEST`

**Content example**

```json
{
    "timestamp": "2022-02-23T17:20:55.061+00:00",
    "status": 400,
    "error": "Bad request",    
    "message": "Price must be a positive number (e.g 5.50) or null. (If null, price is set automatically according to tickettype)",
    "path": "/sales/1/tickets"
}
```

