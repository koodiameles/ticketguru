# Luo myyntitapahtuma

Luo uusi myyntitapahtuma. Myyntitapahtuman luonti vaatii Admin-oikeudet.

**URL** : `/sales`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : None

**Data constraints**

Myyntitapahtumalla on oltava ajankohta (datetime).

```json
{
    "datetime": "[yyyy-MM-dd-HH-mm-ss-ns]"
}
```

**Data example** 

```json
{
    "datetime": "2022-02-17T13:00:00.000",
    "employee": null
}
```

## Success Response

**Condition** : Jos kaikki on OK ja myyntitapahtumalle annettiin ajankohta.

**Code** : `201 CREATED`

**Content example**

```json
{
    "saleid": 3,
    "datetime": "2022-02-17T10:00:00.000+00:00",
    "employee": null,
    "tickets": null
}
```

## Error Responses

**Condition** : Jos myyntitapahtumalle ei annettu ajankohtaa.

**Code** : `400 BAD REQUEST`

**Content example**

```json
{
    "timestamp": "2022-02-23T17:20:55.061+00:00",
    "status": 400,
    "error": "Bad request",    
    "message": "Sale must have a datetime",
    "path": "/sales",
}
```
