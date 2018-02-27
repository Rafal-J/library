1. Aby uruchomić aplikację, zaimportuj projekt do środowiska IDE i uruchom klasę BookRentalApplication.
- aplikacja wymaga do działania bazy danych SQL (jest kompatybilna z MySQL)
- testy aplikacji są przeprowadzane z użyciem bazy danych H2

2. Zbiór requestów do Postmana, umożliwiających przetestowanie kontrolerów restowych oraz wygenerowanie testowych danych znajduje się w pliku Library.postman_collection.json.
- dokumentacja kontrolerów restowych jest dostępna po uruchomieniu aplikacji pod adresem http://localhost:8080/swagger-ui.html 


OPIS APLIKACJI (TRESC ZADANIA)
Zadaniem jest zbudowanie REST API dla systemu obsługującego wypożyczanie książek z biblioteki.

Założenia aplikacji
Aplikacja musi posiadać zamodelowane encje i związane z nimi tabele:

czytelników, która zawiera id czytelnika, imię, nazwisko i datę założenia konta,
tytułów, która zawiera id tytułu, tytuł, autora i rok wydania,
egzemplarzy książek, która zawiera id egzemplarza, id tytułu oraz status (np. w obiegu, zniszczona, zagubiona),
wypożyczeń, która zawiera id egzemplarza, id czytelnika, datę wypożyczenia i datę zwrotu.
Jeśli w zasobach biblioteki jest 5 książek "Buszujący w Zbożu", to w naszych tabelach znajdzie się jedna pozycja w tabeli tytułów oraz 5 pozycji w tabeli egzemplarzy. Dzięki temu każda sztuka jest identyfikowalna w bazie, ale nie powielamy informacji takich jak tytuł czy autor.

Oprócz zamodelowania tabel będzie potrzebne również utworzenie usług REST. Podstawowe usługi to:

dodanie czytelnika,
dodanie tytułu,
dodanie egzemplarza,
zmiana statusu egzemplarza,
sprawdzenie ilości egzemplarzy danego tytułu dostępnych do wypożyczenia,
wypożyczenie książki,
zwrot książki.