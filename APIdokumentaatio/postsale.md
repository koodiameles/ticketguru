# Luo myyntitapahtuma

Luo uusi myyntitapahtuma. Myyntitapahtuman luonti vaatii Admin- tai User-oikeudet.

**URL** : `/sales`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : None

**Data example** 

```json
{
    "employee": null
}
```

## Success Response

**Condition** : Jos uuden myyntitapahtuman luominen onnistuu.

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
