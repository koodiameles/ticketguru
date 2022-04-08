# Päivitä lippu käytetyksi

Päivitä tapahtuma. Tapahtuman päivitys vaatii Admin- tai user-oikeudet.

**URL** : `/useticket/{id}`

**Method** : `PUT`

**Auth required** : YES

**Permissions required** : None

**Data example** 

```json
{
    "valid": false
}
```

## Success Response

**Condition** : Jos kaikki on OK ja lipun päivittäminen onnistui.

**Code** : `201 CREATED`

**Content example**

```json
{

    "ticketid": 2,
    "valid": false,
    "ticketprice": 25.5
    
}
```

## Error Responses

**Condition** : Jos tapahtumalle ei annettu nimeä.

**Code** : `400 BAD REQUEST`

**Content example**

```json
{
    "timestamp": "2022-02-23T17:20:55.061+00:00",
    "status": 400,
    "error": "Bad request",    
    "message": "Event must have a name/description",
    "path": "/events",
}
```
### Or

**Condition** : Jos päivämäärä/aika on jo mennyt.

**Code** : `404 BAD NOT FOUND`

**Content example**

```json
{
    
    "timestamp": "2022-04-08T13:23:14.870+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "Ticket id 10 not found",
    "path": "/useticket/10"

}
```