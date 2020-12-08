# final_Fund_Accounting
Projekt końcowy_Grzegorz Grablewski


Cel projektu: system do księgowości funduszy inwestycyjnych.


Założenia systemu

1. Wymagane tabele:
- fundusze (i share classy)
- dane statyczne funduszu, konta funduszu
- expense’y na share classach

- portfolio

- papiery wartościowe

- baza danych transakcji, łącząca papiery z funduszami

- użytkownicy

2. Wymagane widoki: różny widok dla każdego zespołu
W tej wersji systemu jako osobne zakładki w menu, 
a może jako różne menu w zależności od uprawnień użytkownika?

3. Określony proces księgowy

4. Raporty umożliwiające dostęp do określonych danych

Zakres:
    • Dodawanie funduszu (określamy ile ma gotówki na start, ile jednostek uczestnictwa), 
    • Definiowanie kont (standardowy zestaw kont), 
    • Dodać papier, 
    • Dodać transakcje (aktualizacja z aktualizacją portfolio), 
    • Aktualizacja wartości papierów, 
    • Prezentacja bieżących aktywów funduszu (NAV + prezentacja portfolio)


Szczegółowe funkcje systemu:
- kalkulacja NAV (Net Asset Value) – must have (niektóre elementy should have)
- prezentacja wybranych danych funduszu (wyników) w trybie Live! – should have
- sporządzanie wymaganych raportów podatkowych – could have 
- sporządzanie okresowych sprawozdań finansowych – could have

Opis problemu (dla osób nie-z-branży):

Celem pracy Fund Accountingu jest wyliczenie NAV = Net Asset Value, czyli wartości jednostki uczestnictwa w funduszu.
NAV = Net Assets / Total Shares 

Czyli w celu obliczenia NAV, potrzebujemy przeprowadzić kompletny proces księgowy (wyliczyć aktywa netto) i podzielić je przez liczbę jednostek uczestnictwa w funduszu.

Krok wstecz: co to jest fundusz inwestycyjny i jak działa.
Fundusz inwestycyjny to forma prawna umożliwiająca powierzenie środków pieniężnych celem prowadzenia inwestycji.
Inwestor przekazuje środki pieniężne funduszowi, nabywając jednostki uczestnictwa.
Fundusz inwestuje. Tu mamy pełne wyodrębnienie funduszu rozumianego jako zgromadzone środki finansowe od podmiotu zarządzającego, który nie ma bezpośredniego dostępu do środków finansowych funduszu. Podejmuje jedynie decyzje, jak fundusz inwestuje, za co otrzymuje wynagrodzenie.
Fundusz prezentuje bieżącą wartość jednostki uczestnictwa. <---  to właśnie mamy policzyć
Inwestor może wycofać swoją inwestycję, zbywając lub umarzając jednostki uczestnictwa i odbierając pieniądze.

Podmioty zaangażowane w proces:

Trade Agent – odpowiada za kontakt z inwestorem. Jako jedyny posiada dane osobowe inwestorów. Zawiera umowę z inwestorem i przekazuje środki pieniężne do funduszu. W razie wypłaty, odbiera środki pieniężne od funduszu i przekazuje z powrotem inwestorowi.
Ta część procesu będzie obsługiwana przez obecną wersję systemu tylko w sposób widziany przez fund accounting – czyli:
- wartość nowych inwestycji (liczba nowych jednostek, wartość)
- wartość umorzeń (liczba umorzonych jednostek, wartość)
Dane będą wprowadzane na poziomie funduszu (każdy fundusz ma inne emisje).
Natomiast obecna wersja systemu nie będzie obsługiwała pracy trade agenta – identyfikacja inwestora, dane osobowe (ta część opisu jest tu tylko informacyjnie).

Custody – przechowuje środki pieniężne oraz papiery wartościowe funduszu. Bierze udział w procesie reconciliation – uzgadniania sald z Fund Accountingiem (co wg księgowości powinniśmy mieć kontra co naprawdę mamy w danej chwili).

Trade Capture – zbiera dane o transakcjach dokonanych przez fundusz i wprowadza je do systemu w formacie:
- fundusz
- papier wartościowy
- kierunek transakcji: zakup, sprzedaż
- liczba jednostek
- cena
Dane są wprowadzane na poziomie funduszu (każdy fundusz przeprowadza inne transakcje).

Pricing – wprowadza dane o wycenie poszczególnych papierów wartościowych na zadany tzw. pricing point. Każdy fundusz może mieć inny pricing point. W praktyce próbuje się to ujednolicić poprzez oferowanie klientom kilku punktów w ciągu dnia. Dla celów projektu założę np. 3 pricing points: 12:00GMT, 18:00GMT, 18:00EST (GMT-5).
Dane są wprowadzane w formacie:
- papier wartościowy
- pricing point
- cena
Dane są wprowadzane na poziomie papieru wartościowego (każdy papier ma taką samą wartość w danym pricing point dla każdego funduszu).

Corporate Actions – dział, który zajmuje się wprowadzaniem danych o szczególnych wydarzeniach na papierach wartościowych, typu merger, stock split, share dividend (dywidenda w postaci dodatkowych akcji) – czyli wszystkie, które zmieniają liczbę lub rodzaj akcji posiadanych przez fundusz. 
Ta funkcjonalność nie będzie obsługiwana przez system w obecnej wersji (jest tu tylko informacyjnie).

Cash reconciliation – proces uzgadniania sald z Custody. W obecnej wersji systemu będzie obecna jako dostępność raportów do porównania z Custody, zawierających następujące dane:
- fundusz
- lista papierów wartościowych: papier wartościowy, liczba jednostek
- lista środków pieniężnych: waluta, ilość
Raporty będą generowane na poziomie funduszu.

Expenses – dział zajmujący się naliczaniem wydatków (kosztów) ponoszonych przez fundusz, wyliczeniem ich, z podziałem na różne rodzaje expensów, również z uwzględnieniem maksymalnego dopuszczalnego poziomu łącznych expensów (tzw. cap).
Expense’y są naliczane na poziomie share class funduszu.

(każdy fundusz może mieć różne klasy; środki pieniężne trafiają do jednego funduszu i są inwestowane razem, jednak każda klasa może mieć inaczej zdefiniowane expense’y, wskutek czego klasy mają osobno liczony NAV, który się różni od NAV innych klas)

Fund accounting – główny dział, zajmuje się wyliczeniem NAV w oparciu o dane wprowadzone do systemu. Dokonuje sprawdzenia poprawności wprowadzonych danych w oparciu o założone poziomy tolerancji zmian.
W tym zakresie system powinien:
- generować listę alertów (naruszeń założonych tolerancji zmian NAV)
- generować raport transakcji wpływających na NAV
- istotna jest precyzyjna identyfikacja transakcji i pozycji wpływających na NAV w sposób budzący wątpliwości (większość systemów na rynku ma z tym problem – w sumie nie wiem dlaczego)

Dodatkowo system w obecnej wersji może generować dodatkowe raporty:
- sprawozdanie finansowe w formacie IFRS (wymagane okresowo)
- sprawozdania podatkowe dla fundusz lub inwestorów

Oczywiście, jako element obowiązkowy, system musi liczyć sam NAV.
Powyższe raporty mają być generowane  na poziomie share class funduszu i/lub na poziomie całego funduszu.


Na koniec trochę informacji źródłowych – opisują lub prezentują wybrane kwestie.

Trade Agent


Trade Capture
https://finpricing.com/faq/bookTrade.html

Pricing

Corporate Actions

Expenses

Custody

Cash Reconciliation

Fund Accounting

Hedge share classes
https://global.pimco.com/en-gbl/resources/education/understanding-hedged-share-classes

Performance fee
https://www.investopedia.com/ask/answers/040915/whats-difference-between-hurdle-rate-and-high-water-mark.asp

ISIN, SEDOL, CUSIP..
https://www.isindb.com/validate-isin/
https://www.360t.com/regulatory-affairs/regulatory-topics/international-securities-identification-number-isin/
https://www.franklintempleton.lu/downloadsServlet/?docid=hbb898wh

Multimanager
https://www.stanlibmultimanager.com/59462

FoF + expense cap
https://en.wikipedia.org/wiki/Fund_of_funds
https://www.investopedia.com/terms/f/fundsoffunds.asp

Fund distribution


Fund dashboard
https://www.blackrock.com/hk/en/literature/fact-sheet/bgf-asean-leaders-fund-class-d2-eur-factsheet-lu0811452373-hk-en-retail.pdf
https://www.majedie.com/wp-content/uploads/LF-Majedie-UK-Income-Fund-Factsheet.pdf
https://stanlib.com/secure/src/assets/files/factsheets/STANLIB_Balanced_Fund_SMSTAB_Comprehensive.pdf


Tax:
    • equity ratio
    • capital gains tax
    • withholding tax (TTF)

