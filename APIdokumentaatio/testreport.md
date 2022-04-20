# Testiraportti

Tähän esittelevä teksti alla olevan taulukon testeistä (perustelu, miksi ovat esim. yksikkötestejä).

## JUnit testit

Testitapaus | Syötteet | Odotetut tulokset | Saadut tulokset
------ | ------ | ------ | ------
EventRepoTest: findByNameShouldReturnOneEvent | Hae tapahtuma "Sinfoniaorkesteri", lisää se listaan. | Listan koko on  1. Tapahtuman lokaatio on oikein (="Musiikkitalo"). | ok
EventRepoTest: createEventAndUpdateTicketCount | Luo uusi tapahtuma. Vähennä lippumäärää: 1000-200. | Tapahtumalla on id. Tapahtuman lippumäärä on 800. | ok
TickettypeRepoTest: findByNameAndCount | Hae lipputyyppi "Aikuinen", lisää se listaan. | Listan koko on 2. | ok
TickettypeRepoTest: createNewTickettype | Luo uusi lipputyyppi. | Lipputyypillä on id. | ok
EmployeeRepoTest: findByLastname | Etsitään työntekijää sukunimellä. | Järjestelmä vahvistaa työntekijän etunimen. | ok
EmployeeRepoTest: createNewEmployee | Asetetaan työntekijälle etu- ja sukunimi, salasana ja käyttäjätunnus. | Työntekijällä on id. | ok
TicketTest: </br> createNewTicket | Luodaan kokonaan uusi lippu luomalla uusi tapahtuma ja uusi lipputyyppi  | Luodulta lipulta löytyy tickerid, ticketprice, ticketcode ja tickettype, ja lippu on käyttämätön | ok

## Integraatiotestit (rajapintatestit)

Testitapaus | Syötteet | Odotetut tulokset | Saadut tulokset
------ | ------ | ------ | ------
EventControllerWithAuthorizationTest: testEventIsCreatedAndDeleted (käyttäjäoikeutena admin) | 1. Luo testitapahtuma 2. Hae luotu testitapahtuma 3. Poista testitapahtuma. | 1. Status 201: testitapahtuma luodaan 2. Status 200: hakeminen onnistui, testitapahtuman nimi on "TestingHappen" 3. Status 200: testitapahtuma poistettiin. | ok
EventControllerWithAuthorizationTest: testEventIsCreated (käyttäjäoikeutena user) | Luo testitapahtuma. | Status 403: tapahtuman luonti on estetty. | ok

## End-to-end -testit

Testitapaus | Syötteet | Odotetut tulokset | Saadut tulokset
------ | ------ | ------ | ------
UI:n testausta. Syötetään lippukoodi ja haetaan lippu koodilla. Muutetaan lippu käytetyksi. Tarkistetaan, että lipun tila on muuttunut. | Lippukoodi | Järjetelmä kertoo, löytyykö tunnuksella lippua. Jos lippu löytyy, järjestelmä kertoo, kuinka lipun tila on muuttuu. | Järjestelmä kertoo, jos koodilla löytyy lippu. Virheellisesti koodista ilmoitetaan. Lipun käyttämisen jälkeen järjestelmä kertoo lipun tulleen käytetyksi. Kun koodin syöttää uudelleen, järjestelmä kertoo lipun olevan jo käytetty. Testi OK.
text | text | text | ok