# Näytä kaikki työntekijät

Näytä lista kaikista työntekijöistä. Listan näkeminen vaatii Admin-oikeudet. 

**URL** : `/employees`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

## Success Response

**Code** : `200 OK`

**Content example**

```json
    {
        "employeeid": 1,
        "firstname": "Liisa",
        "lastname": "Ihmemaa",
        "username": "user",
        "sales": []
    },    
    {
        "employeeid": 2,
        "firstname": "cpt Jaakko",
        "lastname": "Varpunen",
        "username": "admin",
        "sales": []
    }
```
## Error Responses

**Condition** : Henkilöllä ei ole oikeuksia nähdä listausta (esim. käyttäjänä user).

**Code** : `401 Unauthorized`
