# Muuta lippu käytetyksi

Käytä Lippu. Lipun voi päivittää joko Admin- tai User-oikeuksin.

**URL** : `/useticket/{id}`

**Method** : `PUT`

**Auth required** : YES

**Permissions required** : None

**Data example** 

```json
{
    "valid": false,
}
```

## Success Response

**Condition** : Jos lipun muuttaminen käytetyksi onnistui.

**Code** : `201 CREATED`

**Content example**

```json
{
    "ticketid": 4,
    "eventid": 1,
    "tickettypeid": 4,
    "tickettype": "Child",
    "valid": false,
    "description": "Konsertti",
    "price": 5.5
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
    "message": "Tikcet id {id} not found",
    "path": "/useticket/{id}"
}
```