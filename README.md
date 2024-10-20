# BookerApp
Organizacija i rezervacija putovanja


Aplikacija za Booking izleta

Ova aplikacija je dizajnirana za upravljanje rezervacijama i organizaciju izleta.
Sustav omogućava različitim korisnicima da se prijave i upravljaju rezervacijama izleta ovisno o svojoj ulozi.
Postoje tri vrste korisnika:

Booker: Upravljatelj rezervacijama. Unosi podatke o sudionicima i njihove preferencije te upravlja rezervacijama.
Vodič: Osoba koja vodi izlet i ima uvid u popis sudionika, njihove preferencije te logističke informacije za uspješno vođenje izleta.
Admin: Osoba koja upravlja sustavom i dodjeljuje uloge korisnicima (određuje tko će biti booker, a tko vodič).



Značajke


Autentifikacija korisnika: Korisnici se mogu prijaviti svojim podacima te im se dodjeljuje uloga (booker, vodič ili admin).

Rezervacija izleta: Korisnici s ulogom booker mogu unositi podatke o sudionicima, uključujući:

Osobne podatke (ime, prezime, dob, kontakt)

Preferencije vezane uz izlet (prehrambene potrebe, fizička ograničenja itd.)

Izlet koji rezerviraju

Nadzorna ploča za vodiča: Vodič može vidjeti popis sudionika, njihove preferencije i ostale potrebne informacije za vođenje izleta.

Administracija: Admin može dodijeliti uloge korisnicima i odlučiti tko će biti booker ili vodič za određeni izlet.

Pregled izleta: Booker i vodič mogu pregledavati detalje izleta, uključujući broj sudionika, logistiku i dodijeljene uloge.

Uloge

Admin: Upravlja ulogama korisnika.

Booker: Rezervira izlete i upravlja detaljima sudionika.

Vodič: Upravlja izletom i sudionicima.

Dizajn baze podataka

Tablica: Korisnici

id (Primarni ključ)
korisničko_ime (String)
lozinka (String)
email (String)
uloga (Enum: "booker", "vodič", "admin")

Tablica: Izleti

id (Primarni ključ)
naziv_izleta (String)
datum (Datum)
lokacija (String)
kapacitet (Integer)
vodič_id (Strani ključ na tablicu Korisnici)


Tablica: Rezervacije

id (Primarni ključ)
korisnik_id (Strani ključ na tablicu Korisnici)
izlet_id (Strani ključ na tablicu Izleti)
broj_osoba (Integer)

Tablica: Sudionici

id (Primarni ključ)
rezervacija_id (Strani ključ na tablicu Rezervacije)
ime (String)
dob (Integer)
preferencije (Text)
