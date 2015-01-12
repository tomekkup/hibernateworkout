# Poligon Spring + Hibernate
Projekt ten stanowi w skrócie test JUnit-a działający w kontekście Spring-a, w którym z kolei pracuje Hibernate. Dane są przechowywane w bazie Apache Derby (in memory).

Poszczególne testy zawarte pliku UserAccountTestExecutor po kolei prezentują jak działają mechanizmy dodawania, pobierania, cacheowania danych. Dowiesz się również jak mapujemy relacje blablabla

### Jak uruchomić
Projekt uruchamiamy na dwa sposoby. Albo z poziomu ulubionego IDE albo z linii komend:
```sh
mvn -Dtest=UserAccountTestExecutor test
```

### Wymagania
Java 6, Maven 2

### Wersje w użyciu
- Spring: 4.1.0
- Hibernate: 4.3.7
- Ehcache: 2.0
- JUnit: 4.11

# FAQ
> sdf