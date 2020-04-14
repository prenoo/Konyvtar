# Könyvtári nyílvántartás
Informatikai rendszerek építése féléves beadandó


##Általános leírás
A cél, egy képzeletbeli könyvtári kölcsönző-nyilvántartó szoftver tervezése és implementálása. A könyvtárban könyveket és multimédia anyagokat (cd, kazetta) lehet kölcsönözni, beiratkozott tagoknak. Az alkalmazást a könyvtár vezetője és dolgozói használják, hogy regisztrálják a ki és beiratkozásokat és ellenőrizzék a tagok kölcsönzéseit, listázzák azokat a tagokat, akik nem vitték vissza a kölcsönzött anyagokat időben. 
##Felhasználási esetek
###Tagnyílvántartás
A rendszer nyílvántartja a könyvtár kölcsönzőit. 
Új tagok felvételénél a rendszer bekéri a nevet, telefonszámot, személyigazolvány számot, lakcímet. 
A regisztrálás után, a rendszer egyedi azonosítóval látja el a tagokat. 
A felvettek listájában lehet keresni név, személyigazolvány szám, azonosító alapján.
A tagok adatait lehet módosítani vagy egy létező tagot törölni. A törlés csak státuszváltással jár, nem fizikai törléssel. 
###Készletnyílvántartás
A rendszer nyílvántartja a könyvek és multimédia anyagok adatait: pl. típus (cd, könyv, kotta), szerző, cím, beszerzés dátuma, sorszám, státusz (szabad, kikölcsönzött, selejtezett). 
Itt lehet az új könyveket nyílvántartásba venni. 
A könyvtár készletét lehet listázni, keresni cím, szerző szerint. 
###Kölcsönzés
Amikor egy tag kölcsönözni szeretne, szükség van az azonosító adataira, illetve egy kölcsönzendő könyv, cd azonosítójára. A rendszerben először a felhasználót kell kikeresni. Az adatlapon meg lehet nézni, hogy van-e már kölcsönözve neki valami. A kikölcsönzött tételek száma maximálva van. (pl 6) 
###Kölcsönzés befejezése
Amikor valaki visszahoz a könyvtári egységét, akkor azt azonosítója alapján lehet felszabadítani. Az ügyintéző a sorszám alapján rákeres az adott könyvre, cd-re és az adatlapján visszaállíthatja szabad státuszúra, így megszűnik a kölcsönzés. Az adatlapnak tartalmaznia kell, hogy mikor kölcsönözték ki, és ki volt az, amennyiben kikölcsönzött állapotban van. 
###Késések lekérdezése
A rendszer lehetőséget ad egy bizonyos idő után (pl 30 nap – konfigurálható) még mindig kikölcsönzött könyvek lekérdezésére. A listában szerepelnie kell a tételeknek és a kölcsönző adatainak is, illetve a kikölcsönzés dátumának, és a számított késésnek is. 
