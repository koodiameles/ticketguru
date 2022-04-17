# Testiraportti

Tähän esittelevä teksti alla olevan taulukon testeistä (perustelu, miksi ovat esim. yksikkötestejä).

Testitapaus | Syötteet | Odotetu tulokset
------ | ------ | ------
id | long PK | Myyntitapahtuman id
employee | long FK | Myynnin suorittanut työntekijä, viittaus tyontekija -tauluun
date | datetime | Myyntitapahtuman ajakohta