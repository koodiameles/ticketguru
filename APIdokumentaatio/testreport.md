# Testiraportti

Tämä raportti esittelee sovellukselle tehdyt testit testityyppikerrallaan. Jokaisen osion johdanto perustelee, miksi testit ovat kyseistä tyyppiä.


## Yksikkötestit (JUnit)

Alla olevassa taulukossa olevat testit testaavat yksittäisen eniteetin ja siihen liittyvän repositoryn toimintaa.

Testitapaus | Syötteet | Odotetut tulokset | Saadut tulokset
------ | ------ | ------ | ------
[EventRepoTest](/ticketguru/TicketGuru/src/test/java/fi/ohjelmistoprojekti1/TicketGuru/EventRepositoryTest.java): findByNameShouldReturnOneEvent | Hae tapahtuma "Sinfoniaorkesteri", lisää se listaan. | Listan koko on  1. Tapahtuman lokaatio on oikein (="Musiikkitalo"). | ok
[EventRepoTest](/ticketguru/TicketGuru/src/test/java/fi/ohjelmistoprojekti1/TicketGuru/EventRepositoryTest.java): createEventAndUpdateTicketCount | Luo uusi tapahtuma. Vähennä lippumäärää: 1000-200. | Tapahtumalla on id. Tapahtuman lippumäärä on 800. | ok
[TickettypeRepoTest](/ticketguru/TicketGuru/src/test/java/fi/ohjelmistoprojekti1/TicketGuru/TickettypeRepositoryTest.java): findByNameAndCount | Hae lipputyyppi "Aikuinen", lisää se listaan. | Listan koko on 2. | ok
[TickettypeRepoTest](/ticketguru/TicketGuru/src/test/java/fi/ohjelmistoprojekti1/TicketGuru/TickettypeRepositoryTest.java): createNewTickettype | Luo uusi lipputyyppi. | Lipputyypillä on id. | ok
[EmployeeRepoTest](/ticketguru/TicketGuru/src/test/java/fi/ohjelmistoprojekti1/TicketGuru/EmployeeRepositoryTest.java): findByLastname | Etsitään työntekijää sukunimellä. | Järjestelmä vahvistaa työntekijän etunimen. | ok
[EmployeeRepoTest](/ticketguru/TicketGuru/src/test/java/fi/ohjelmistoprojekti1/TicketGuru/EmployeeRepositoryTest.java): createNewEmployee | Asetetaan työntekijälle etu- ja sukunimi, salasana ja käyttäjätunnus. | Työntekijällä on id. | ok
text | text | text | ok

## Integraatiotestit (rajapintatestit)

Seuraavassa taulukossa esitellyt testit testaavat kontrollerin kautta rajapinnan toimintaa.

Testitapaus | Syötteet | Odotetut tulokset | Saadut tulokset
------ | ------ | ------ | ------
[EventControllerWithAuthorizationTest](/ticketguru/TicketGuru/src/test/java/fi/ohjelmistoprojekti1/TicketGuru/EventControllerWithAuthorizationTest.java): testEventIsCreatedAndDeleted (käyttäjäoikeutena admin) | 1. Luo testitapahtuma 2. Hae luotu testitapahtuma 3. Poista testitapahtuma. | 1. Status 201: testitapahtuma luodaan 2. Status 200: hakeminen onnistui, testitapahtuman nimi on "TestingHappen" 3. Status 200: testitapahtuma poistettiin. | ok
[EventControllerWithAuthorizationTest](/ticketguru/TicketGuru/src/test/java/fi/ohjelmistoprojekti1/TicketGuru/EventControllerWithAuthorizationTest.java): testEventIsCreated (käyttäjäoikeutena user) | Luo testitapahtuma. | Status 403: tapahtuman luonti on estetty. | ok

## End-to-end-testit

Taulukossa esitellyt testitapaukset testaavat koko sovelluksen toimintaa clientin käyttöliittymästä alas päin hierarkiassa aina tietokannan toimintaan asti.

Testitapaus | Syötteet | Odotetut tulokset | Saadut tulokset
------ | ------ | ------ | ------
UI:n testausta. Syötetään lippukoodi ja haetaan lippu koodilla. Muutetaan lippu käytetyksi. Tarkistetaan, että lipun tila on muuttunut. | Lippukoodi | Järjetelmä kertoo, löytyykö tunnuksella lippua. Jos lippu löytyy, järjestelmä kertoo, kuinka lipun tila on muuttuu. | Järjestelmä kertoo, jos koodilla löytyy lippu. Virheellisesti koodista ilmoitetaan. Lipun käyttämisen jälkeen järjestelmä kertoo lipun tulleen käytetyksi. Kun koodin syöttää uudelleen, järjestelmä kertoo lipun olevan jo käytetty. Testi OK.
Syötetään ostettavan lipun tai lippujen tiedot. Tehdään osto. Tarkistetaan, että oston perusteella generoituu oikea määrä oikeita lippuja. | Tapahtuma, tapahtumaan liittyvä lipputyyppi, lippujen määrä | Järjestelmä palauttaa tapahtuman ja lipputyyppien tiedot. Jos lippujen ostaminen onnistuu, järjestemä palauttaa ostettujen lippujen tiedot. | Järjestelmä palauttaa halutun määrän lippuja. Lippujen tiedot (tapahtuma, lipputyyppi, hinta) ovat oikein. Testi OK.