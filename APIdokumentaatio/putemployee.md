# Päivitä työntekijä

Päivitä työntekijä. Työntekijän päivitys vaatii Admin-oikeudet.

**URL** : `/employees/{id}`

**Method** : `PUT`

**Auth required** : YES

**Permissions required** : None

**Data constraints**

Työntekijällä on oltava etunimi ja sukunimi.

**Data example** 

```json
{
    "roleid": 1,
    "firstname": "Liisa",
    "lastname": "Ihmemaa",
    "username": "user"
}
```

## Success Response

**Condition** : Jos kaikki on OK ja tapahtumalle annettiin nimi.

**Code** : `201 CREATED`

**Content example**

```json
{
    "roleid": 1,
    "firstname": "Liisa",
    "lastname": "Ihmemaa",
    "username": "user"
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
    "message": "Employee id {id} not found",
    "path": "/employees/{id}"
}
```

### Or

**Condition** : Jos työntekijälle ei annettu etunimeä.

**Code** : `400 BAD REQUEST`

**Content example**

```json
{
    "timestamp": "2022-02-23T17:20:55.061+00:00",
    "status": 400,
    "error": "Bad request",    
    "message": "Employee must have a firstname",
    "path": "/employees",
}
```
### Or

**Condition** : Jos työntekijälle ei annettu sukunimeä.

**Code** : `400 BAD REQUEST`

**Content example**

```json
{
    "timestamp": "2022-02-23T17:20:55.061+00:00",
    "status": 400,
    "error": "Bad request",    
    "message": "Employee must have a lastname",
    "path": "/employees",
}
```

### Or

**Condition** : Jos työntekijän role id on virheellinen.

**Code** : `400 BAD REQUEST`

**Content example**

```json
{
    "timestamp": "2022-02-23T17:20:55.061+00:00",
    "status": 400,
    "error": "Bad request",    
    "message": "Role id {id} not valid",
    "path": "/employees",
}
```
