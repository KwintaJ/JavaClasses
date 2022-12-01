import java.util.*;

public class SerwisAukcyjny implements Aukcja {
    Map<String, Powiadomienie> uzytkownicy;
    Map<Integer, List<String>> subskrypcje;
    Map<Integer, Przedmiot> przedmioty;

    class Przedmiot implements PrzedmiotAukcji {
        public boolean stan;
        private int id;
        private String nazwa;
        private int aktOf;
        private int aktCen;
        public String wygrywajacy;
        public Map<String, Integer> licytujacy;

        public Przedmiot(int id, String nazwa, int aktOf, int aktCen) {
            this.stan = true;
            this.id = id;
            this.nazwa = nazwa;
            this.aktOf = aktOf;
            this.aktCen = aktCen;
            this.licytujacy = new HashMap<>();
        }

        public void setAktOf(int a) {
            aktOf = a;
        }

        public void setAktCen(int a) {
            aktCen = a;
        }

        @Override
        public int identyfikator() {
            return id;
        }

        @Override
        public String nazwaPrzedmiotu() {
            return nazwa;
        }

        @Override
        public int aktualnaOferta() {
            return aktOf;
        }

        @Override
        public int aktualnaCena() {
            return aktCen;
        }
    }

    public SerwisAukcyjny() {
        uzytkownicy = new HashMap<>();
        subskrypcje = new HashMap<>();
        przedmioty = new HashMap<>();
    }

    @Override
    public void dodajUżytkownika(String username, Powiadomienie kontakt) {
        uzytkownicy.put(username, kontakt);
    }

    @Override
    public void dodajPrzedmiotAukcji(PrzedmiotAukcji przedmiot) {
        przedmioty.put(przedmiot.identyfikator(),
                new Przedmiot(
                        przedmiot.identyfikator(),
                        przedmiot.nazwaPrzedmiotu(),
                        przedmiot.aktualnaOferta(),
                        przedmiot.aktualnaCena()));

        subskrypcje.put(przedmiot.identyfikator(), new ArrayList<>());
    }

    @Override
    public void subskrypcjaPowiadomień(String username, int identyfikatorPrzedmiotuAukcji) {
        subskrypcje.get(identyfikatorPrzedmiotuAukcji).add(username);
        przedmioty.get(identyfikatorPrzedmiotuAukcji).licytujacy.put(username, -1);
    }

    @Override
    public void rezygnacjaZPowiadomień(String username, int identyfikatorPrzedmiotuAukcji) {
        subskrypcje.get(identyfikatorPrzedmiotuAukcji).remove(username);
        przedmioty.get(identyfikatorPrzedmiotuAukcji).licytujacy.remove(username);
    }

    @Override
    public void oferta(String username, int identyfikatorPrzedmiotuAukcji, int oferowanaKwota) {
        if(!przedmioty.get(identyfikatorPrzedmiotuAukcji).stan) {
            return;
        }

        if(oferowanaKwota <= przedmioty.get(identyfikatorPrzedmiotuAukcji).aktualnaCena()) {
            return;
        }

        if(oferowanaKwota > przedmioty.get(identyfikatorPrzedmiotuAukcji).aktualnaCena()) {
            przedmioty.get(identyfikatorPrzedmiotuAukcji).setAktCen(oferowanaKwota);
            przedmioty.get(identyfikatorPrzedmiotuAukcji).wygrywajacy = username;
        }

        przedmioty.get(identyfikatorPrzedmiotuAukcji).setAktOf(oferowanaKwota);
        if (przedmioty.get(identyfikatorPrzedmiotuAukcji).licytujacy.containsKey(username)) {
            przedmioty.get(identyfikatorPrzedmiotuAukcji).licytujacy.replace(username, oferowanaKwota);
        }
        else {
            przedmioty.get(identyfikatorPrzedmiotuAukcji).licytujacy.put(username, oferowanaKwota);
        }

        for (String x : subskrypcje.get(identyfikatorPrzedmiotuAukcji)) {
            if (!x.equals(username)) {
                if (przedmioty.get(identyfikatorPrzedmiotuAukcji).licytujacy.get(x) < oferowanaKwota) {
                    uzytkownicy.get(x).przebitoTwojąOfertę(przedmioty.get(identyfikatorPrzedmiotuAukcji));
                }
            }
        }
    }

    @Override
    public void koniecAukcji(int identyfikatorPrzedmiotuAukcji) {
        przedmioty.get(identyfikatorPrzedmiotuAukcji).stan = false;
    }

    @Override
    public String ktoWygrywa(int identyfikatorPrzedmiotuAukcji) {
        if(przedmioty.containsKey(identyfikatorPrzedmiotuAukcji)) {
            return przedmioty.get(identyfikatorPrzedmiotuAukcji).wygrywajacy;
        }
        else {
            return null;
        }
    }

    @Override
    public int najwyższaOferta(int identyfikatorPrzedmiotuAukcji) {
        if(przedmioty.containsKey(identyfikatorPrzedmiotuAukcji)) {
            return przedmioty.get(identyfikatorPrzedmiotuAukcji).aktCen;
        }
        else {
            return 0;
        }
    }
}
