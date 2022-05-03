# Luo työntekijä

Luo uusi työntekijä. Työntekijän luonti vaatii Admin-oikeudet.

**URL** : `/employees`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : None

**Data constraints**

Työntekijällä on oltava etunimi ja sukunimi. Role ei saa olla tyhjä ja sen tulee olla olemassa. Käyttäjänimi tulee olla uniikki.

**Data example** 

```json
{
    "firstname": "Aurinko",
    "lastname": "Kukka",
    "username": "aurinkoinen",
    "role": 1,
    "password" : "kovakuoriainen"
    }
```

## Success Response

**Condition** : Jos kaikki on OK ja työntekijälle annettiin etunimi ja sukunimi.

**Code** : `201 CREATED`

**Content example**

```json
{
    "firstname": "Aurinko",
    "lastname": "Kukka",
    "username": "aurinkoinen",
    "role": 1,
    "password" : "kovakuoriainen"
}
```

## Error Responses

**Condition** : Jos työntekijän role id on virheellinen.

**Code** : `400 Bad request`

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

### Or

**Condition** : Jos käyttäjänimi on jo olemassa.

**Code** : `400 Bad request`

**Content example**

```json
{  
    "message": "This username already exists",
}
```

### Or

**Condition** : Henkilöllä ei ole oikeuksia luoda työntekijää (esim. käyttäjänä user).

**Code** : `401 Unauthorized`
