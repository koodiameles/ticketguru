**URL** : `/sales`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

**Data example** 

```json
{
        "saleid": 8,
        "datetime": "2022-03-13T17:13:11.610+00:00",
        "employee": null,
        "tickets": [
            {
                "ticketid": 10,
                "valid": true,
                "ticketprice": 50.5,
                "event": {
                    "eventid": 1,
                    "description": "Konsertti",
                    "location": "Finlandia-Talo",
                    "city": "Helsinki",
                    "ticketcount": 400,
                    "datetime": "2022-06-24T15:00:00.000+00:00",
                    "duration": 90,
                    "tickettypes": [
                        {
                            "tickettypeid": 4,
                            "name": "Adult",
                            "price": 50.5
                        },
                        {
                            "tickettypeid": 5,
                            "name": "Child",
                            "price": 25.5
                        }
                    ]
                },
                
    {
        "saleid": 9,
        "datetime": "2022-07-22T09:37:00.000+00:00",
        "employee": null,
        "tickets": []
    }
```
## Success Response

**Code** : `200 OK`

## Error Responses

**Condition** : Jos ei ole myyntitapahtumia.

**Code** : `404 NOT FOUND`