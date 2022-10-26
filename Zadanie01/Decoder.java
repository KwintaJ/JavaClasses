/**
 * Klasa dekodera.
 */
public class Decoder {
    private StringBuilder toOut = new StringBuilder(); // zmienna wynikowa
    private String reps = "";
    private String currentlyProcessed = ""; // wszystko co wczytalismy w aktualnie przetwarzanej sekcji danych
    private int repeatSection = -1; // licznik ile sekcji powtorzen zostalo juz wykonanych;
                                    // -1 oznacza ze jestesmy w sekcji czytania danych


    /**
     * Metoda pozwalajaca na wprowadzanie danych.
     * @param value dostarczona wartosc
     */
    public void input( byte value ) {
        /* jezeli wartosc nie jest w przedziale [0, 9] ignorujemy */
        if(value > 9 || value < 0)
            return;

        /* jezeli jestesmy w sekcji powtorzen */
        if(repeatSection != -1) {

            reps += value;
            repeatSection++;

            /* jezeli wykonalismy sekcje powtorzen 4 razy przechodzimy
               spowrotem do sekcji czytania danych
            */
            if(repeatSection == 4) {
                /* dodajemy do zmiennej wynikowej wczytane dane */
                toOut.append(String.valueOf(currentlyProcessed).repeat(Integer.parseInt(reps)));
                currentlyProcessed = "";
                repeatSection = -1;
                reps = "";
            }
            return;
        }

        /* w przeciwnym wypadku jestesmy w sekcji czytania danych */
        //##################################################################

        /* pierwsze napotkane zero - zmiana sekcji */
        if(value == 0) {
            repeatSection = 0;
            return;
        }

        /* dodajemy cyfre z przedzialu [1, 9] do aktualnej sekcji danych */
        currentlyProcessed += Byte.toString(value);
    }

    /**
     * Metoda pozwalajaca na pobranie wyniku dekodowania danych.
     * @return wynik dzialania
     */
    public String output() {
        return toOut.toString();
    }

    /**
     * Przywrocenie poczatkowego stanu obiektu.
     */
    public void reset() {
        /* resetujemy wszystko: czyscimy zmienna wynikowa,
           czyscimy wszystko co przeczytalismy i
           wracamy do sekcji czytania danych jezeli bylismy w sekcji powtorzen
        */
        toOut = new StringBuilder();
        currentlyProcessed = "";
        repeatSection = -1;
        reps = "";
    }
}
