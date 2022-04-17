# Testiraportti

Tähän esittelevä teksti alla olevan taulukon testeistä (perustelu, miksi ovat esim. yksikkötestejä).

## JUnit testit

Testitapaus | Syötteet | Odotetu tulokset | Saadut tulokset
------ | ------ | ------ | ------
EventRepoTest: findByNameShouldReturnOneEvent | Hae tapahtuma "Sinfoniaorkesteri", lisää se listaan | Listan koko on  1. Tapahtuman lokaatio on oikein (="Musiikkitalo") | ok
EventRepoTest: createEventAndUpdateTicketCount | Luo uusi tapahtuma. Vähennä lippumäärää: 1000-200. | Tapahtumalla on id. Tapahtuman lippumäärä on 800. | ok
TickettypeRepoTest: findByNameAndCount | Hae lipputyyppi "Aikuinen", lisää se listaan | Listan koko on 2 | ok
TickettypeRepoTest: createNewTickettype | Luo uusi lipputyyppi | Lipputyypillä on id. | ok
EmployeeRepoTest: findByLastname | Etsitään työntekijää sukunimellä | Järjestelmä vahvistaa työntekijän etunimen | OK
EmployeeRepoTest: createNewEmployee | Asetetaan työntekijälle etu- ja sukunimi, salasana ja käyttäjätunnus | Työntekijällä on id. | ok
text | text | text | ok

## Integraatiotestit (rajapintatestit)

Testitapaus | Syötteet | Odotetu tulokset | Saadut tulokset
------ | ------ | ------ | ------
text | text | text | ok
text | text | text | ok

## End-to-end -testit

Testitapaus | Syötteet | Odotetu tulokset | Saadut tulokset
------ | ------ | ------ | ------
UI:n testausta. Syötetään lippukoodi ja haetaan lippu koodilla. Muutetaan lippu käytetyksi. Tarkistetaan, että lipun tila on muuttunut. | Lippukoodi | Järjetelmä kertoo, löytyykö tunnuksella lippua. Jos lippu löytyy, järjestelmä kertoo, kuinka lipun tila muuttuu. | ok
text | text | text | ok