import java.util.List;
import java.util.Map;
import java.util.Set;

public interface LinesInterface {

    /**
     * Punkt
     */
    public interface Point {
        public String getName();
    }

    /**
     * Odcinek. Odcinek nie ma kierunku.
     */
    public interface Segment {
        /**
         * Pierwszy kraniec odcinka
         *
         * @return punkt bedacy pierwszym krancem odcinka
         */
        public Point getEndpoint1();

        /**
         * Drugi kraniec odcinka
         *
         * @return punkt bedacy drugim krancem odcinka
         */
        public Point getEndpoint2();
    }

    /**
     * Metoda pozwala na dodanie zbioru punktow.
     *
     * @param points zbior punktow
     */
    public void addPoints(Set<Point> points);

    /**
     * Metoda pozwala na dodanie zbioru odcinkow laczacych punkty.
     *
     * @param segments zbior odcinkow
     */
    public void addSegments(Set<Segment> segments);

    /**
     * Metoda wyszukuje polczenie pomiedzy para punktow. Kazde poprawne polaczenie
     * podanej pary i bez petli uznane zostanie za poprawne. W przypadku braku
     * polaczenia metoda zwraca liste o rozmiarze 0.
     *
     * @param start punkt poczatkowy
     * @param end   punkt koncowy
     * @return lista kolejnych odcinkow prowadzacych od start do end.
     */
    public List<Segment> findConnection(Point start, Point end);

    /**
     * Metoda generuje mape, ktorej kluczem jest punkt. Punkt wskazuje na zbior
     * odcinkow, ktorych jednym z punktow krancowych jest ten punkt.
     *
     * @return mapa powiazan punktow z odcinkami
     */
    public Map<Point, Set<Segment>> getMapEndpointToSegments();

    /**
     * Metoda generuje mape, ktorej kluczem jest punkt. Punkt jest punktem startowym
     * sciezek. We wskazywanej przez punkt mapie maja znalezc sie punkty, ktore
     * mozna osiagnac poprzez trase zawierajajaca 1, 2, 3 lub 4 odcinki. Jesli trasa o
     * okreslonej dlugosci nie istnieje zbior punktow nia dostepny jest zbiorem
     * pustym (zbior o rozmiarze 0).
     *
     * @return mapa powiazan punktow z innymi punktami
     */
    public Map<Point, Map<Integer, Set<Point>>> getReachableEndpoints();

}