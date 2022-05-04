# Poista työntekijä

Poista työntekijä. Työntekijän poistaminen vaatii Admin-oikeudet.

**URL** : `/employees/{id}`

**Method** : `DELETE`

**Auth required** : YES

**Permissions required** : None

## Success Response

**Condition** : Jos kaikki on OK ja tapahtuma poistettiin.

**Code** : `200 OK`

**Content example**
```json
{
    "message": "Deleted employee {firstname} {lastname} with the id {id}"
}
```

## Error Responses

**Condition** : Id puuttuu.

**Code** : `405 Method not allowed`

**Content example**
```json
{
    "timestamp": "2022-02-23T17:20:55.061+00:00",
    "status": 405,
    "error": "Method Not Allowed",    
    "message": "Request method 'DELETE' not supported",
    "path": "/employees/"
}
```

### Or

**Condition** : Id on virheellinen.

**Code** : `404 Not Found`

**Content example**
```json
{
    "timestamp": "2022-02-23T17:20:55.061+00:00",
    "status": 404,
    "error": "Not Found",    
    "message": "Employee id {id} not found",
    "path": "/employees/{id}"
}
```

### Or

**Condition** : Työntekijään on linkitetty myyntitietoja.

**Code** : `403 Forbidden`

**Content example**
```json
{
    "timestamp": "2022-02-23T17:20:55.061+00:00",
    "status": 403,
    "error": "Forbidden",    
    "message": "Employee has sales and can not be deleted.",
    "path": "/employees/{id}"
}
```

### Or

**Condition** : Henkilöllä ei ole oikeuksia poistaa työntekijää (esim. käyttäjänä user).

**Code** : `401 Unauthorized`
