# Testiraportti

Tähän esittelevä teksti alla olevan taulukon testeistä (perustelu, miksi ovat esim. yksikkötestejä).

Testitapaus | Syötteet | Odotetu tulokset | Saadut tulokset
------ | ------ | ------ | ------
EventRepoTest: findByNameShouldReturnOneEvent | Hae tapahtuma "Sinfoniaorkesteri", lisää se listaan | Listan koko on  1. Tapahtuman lokaatio on oikein (="Musiikkitalo") | ok
EventRepoTest: createEventAndUpdateTicketCount | Luo uusi tapahtuma. Vähennä lippumäärää: 1000-200. | Tapahtumalla on id. Tapahtuman lippumäärä on 800. | ok
date | datetime | Myyntitapahtuman ajakohta