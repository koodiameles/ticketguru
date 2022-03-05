# Näytä kaikki tapahtumat

Näytä lista kaikista tapahtumista. Kaikilla käyttöoikeuksilla saa etsittyä listauksen. 

**URL** : `/events`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : None

**Data example** 

```json
{
    "id": 1,
    "description": "Kalevala Ooppera",
    "location" : "Finlandia-Talo",
    "city" : "Helsinki",
    "ticketcount" : "400" ,
    "datetime" : "2022-05-22T18:00:00",
    "duration" :  "130"
}, 

{
    "eventid": 2,
    "description": "Event2",
    "location": null,
    "city": null,
    "ticketcount": 0,
    "datetime": null,
    "duration": 0
},
    
{
    "eventid": 3,
    "description": "Event3",
    "location": null,
    "city": null,
    "ticketcount": 0,
    "datetime": null,
    "duration": 0
}
```
## Success Response

**Code** : `200 OK`