# TicketGuru

Tiimi: Max Arponen, Mirka Heikkilä, Elina Hilkko, Jussi Junnila, Emmi Sulander (Ryhmä Github Newbies)

## Johdanto

Ticketguru on lipunmyyntiin tarkoitettu sovellus. Käyttäjä lisää sovellukseen tapahtumat tietoineen ja lippuhintoineen. Hän voi myydä lippuja ja tarkastella myyntihistoriaa. Yksittäisessä myyntitapahtumassa voi olla myyty useampi lippu erilaisilla hinnoilla (opiskelijahinta, eläkeläishinta) useampaan tapahtumaan. Jokaisella lipulla on yksilöity koodi, joka on helppo tarkastaa tarvittaessa. 

Ensimmäiseen lopulliseen versioon ei tule verkkokaupan ominaisuuksia, eli lippuja haluavat asiakkaat eivät itse pysty ostamaan lippuja sovelluksella. Sovellus on siis lipputoimiston käyttöön ja myyntitapahtumia hallinnoi joku sovelluksen käyttäjistä (**myyjä**). Sovellusta ollaan aikeissa jatkokehittää ja tulevaisuudessa lisätä verkkokauppa.

Ticketgurulla käyttääjä voi muun muassa:
+ **Lisää Tapahtuma** (Nimi, ajankohta, paikka, kuvaus, kaupunki, myytävien lippujen määrä)
+ **Lisätä lipputyyppejä** (Aikuinen, lapsi, eläkäinen jne.)
+ **Myy lippuja** (Mihin tapahtumiin, montako lippua)
+ **Tulostaa myydyt liput**
+ **Tutkia myyntiraportteja** (Yhteenveto myynnistä tapahtumaan X. Halutessa selaa yksittäisiä myyntitapahtumia)

## Järjestelmän määrittely

### Käyttäjäroolit
+ **Myyjä** - Myy ja tulostaa lippuja asiakkaalle


+ **Ylläpitäjä** - Vastaa järjestelmän tapahtumien ja lippujen päivittämisestä ja ajaa haluttuja raportteja

### Käyttäjätarinat

1. Myyjänä haluan hakea tapahtumia eri hakusanoilla, jotta voin myydä asiakkaalle haluamansa lipun.

2. Myyjänä haluan valita eri lipputyyppejä, jotta saan myytyä asiakkaalle oikean lipun. 

3. Myyjänä haluan valita useamman lipun kerrallaan, jotta voin myydä asiakkaalle monta lippua kerralla. 

4. Myyjänä haluan saada liput tulostettua, jotta voin toimittaa ne asiakkaalle.

5. Myyjänä haluan nähdä monta lippua kyseiseen tapahtumaan on myyty ja myymättä, jotta voin helposti tulostaa jäljellä olevat liput ovella myytäviksi.

6. Ylläpitäjänä haluan listauksen tapahtuman myyntiraportista, jotta saan raportin myydyistä lipuista eri tarkoituksia varten.

7. Ylläpitäjänä haluan listauksen kaikista myyntitapahtumista, jotta niitä voi hallinnoida. 

8. Ylläpitäjänä haluan lisätä uusia tapahtumia, jotta niiden lippuja voisi myydä.

9. Ylläpitäjänä haluan muokata tapahtumien tietoja, jotta ne pysyisivät ajantasalla.

10. Ylläpitäjänä haluan muokata lippujen tietoja, jotta voin muokata hintoja tai lipputyyppejä.


## Käyttöliittymä

*Esitetään käyttöliittymän tärkeimmät (vain ne!) näkymät sekä niiden väliset siirtymät käyttöliittymäkaaviona.*

*Jos näkymän tarkoitus ei ole itsestään selvä, se pitää kuvata lyhyesti.*

![UML-kaavio](./Kuvat/UML-kaavio.png)

[Rautalankamalli](https://github.com/koodiameles/ticketguru/blob/main/Kuvat/Wireframe.pdf) kuvaa tarkemmin eri näkymien yhteyksiä ja toimintoja.

## Tietokanta

### Tietokantakaavio
![UML malli](./Kuvat/UMLkaaviokuva.PNG) 

> ### _Employee – Työntekijä_
> 
> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------

> ### _Role – Rooli_
> Rooli-taulu sisältää työntekijöiden käyttöoikeudet järjestelmässä. Työntekijällä voi olla vain yksi rooli. Tietty rooli voi kuulua monelle eri työntekijälle.
>
> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> id | long PK | Roolin tunnus
> description | varchar |  Lyhyt kuvaus roolin oikeuksista järjestelmässä

> ### _Event – Tapahtuma_
> Tapahtuma-taulu sisältää tapahtuman tiedot. Tapahtumaan voidaan olla lisätty useita lipputyyppejä, mutta lipputyyppi kuuluu aina vain yhdelle tapahtumalle. Tapahtumaan voidaan myydä useita lippuja, mutta yksi lippu kuuluu aina vain yhteen tapahtumaan. Tapahtumaan liittyy yksi raportti ja raportti voi kuulua aina vain yhdelle tapahtumalle.
>
> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> id | long PK | Tapahtuman id
> tickettype | long FK | Viittaus lipputyypit -tauluun. Tässä määritellään tapahtumalle lipputiedot hintoineen.
> description | varchar | Tapahtuman nimi/kuvaus
> location | varchar | Tapahtumapaikka esim. Finlandiatalo
> city | varchar | Kaupunki, jossa tapahtumapaikka sijaitsee
> ticketcount | int | Myytävien lippujen maksimimäärä
> datetime | datetime | Tapahtuman ajankohta. PVM sekä KLO.
> duration | int | tapahtuman arvioitu kesto minuutteina esim. 90 min

> ### _Ticket – Lippu_
> Lippu-taulu sisältää lipun tiedot. Lippu voi kuulua vain yhteen tapahtumaan (event). Lippu voi kuulua vain yhteen myyntitapahtumaan (sale).
>
> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> id | long PK | Roolin tunnus
> event | long FK | Viittaus tapahtuma -tauluun. Mihin tapahtumaan lippu on myyty.
> valid | boolean |  Onko lippu voimassa. Esim. Onko vanhentunut, onko käytetty

> ### _Sale – Myyntitapahtuma_
> Myyntitapahtuma-taulu sisältää tiedot myyntitapahtumasta ja siihen liittyvistä lipuista. Myyntitapahtuma kertoo, kuka työntekijä liput on myynyt ja koska. Myyntitapahtumaan voi liittyä useampia lippuja, mutta ainoastaan yksi työntekijä.
> 
> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> id | long PK | Myyntitapahtuman id
> ticket | long FK | Myyntitapahtumaan liittyvä lippu, viittaus lippu-tauluun
> employee | long FK | Myynnin suorittanut työntekijä, viittaus tyontekija-tauluun
> amount | int | Myyntitapahtuman lippujen hintojen kokonaissumma
> datetime | datetime | Myyntitapahtuman ajakohta

> ### _Tickettype – Lipputyyppi_
> Lipputyyppi-taulu sisältää, minkälaisia lipputyyppejä on mahdollista saada tapahtumaan, esimerkiksi lapsi, aikuinen, eläkeläinen, opiskelija. Tapahtumalla voi olla useita lipputyyppejä.
>
> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> id | long PK | Lipputyypin id
> tickettype | varchar | kuvaus lipputyypistä, esim. opiskelija
> price | double | paljonko kyseinen lippu maksaa, esim. opiskelijalippu
> event | long FK | viittaus tapahtuma-tauluun, minkälaisia lipputyyppejä tapahtumassa on

>### _Report – Raportti_
>Raportti sisältää tiedot tapahtumasta ja tapahtumalle myydyistä lipuista. 
>
> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> id | long PK | Raportin id
> reportname | varchar | raportin nimi
>event | long FK | viittaus tapahtuma-tauluun. Raportilla on yhden tapahtuman tiedot. 