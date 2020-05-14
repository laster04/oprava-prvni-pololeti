import java.util.LinkedList;
import java.util.List;

public class Vlacek {

    private Vagonek lokomotiva = new Vagonek(VagonekType.LOKOMOTIVA);
    private Vagonek posledni = new Vagonek(VagonekType.POSTOVNI);
    private int delka = 2;

    public Vlacek(){
        lokomotiva.setNasledujici(posledni);
        lokomotiva.setUmisteni(1);
        posledni.setPredchozi(lokomotiva);
        posledni.setUmisteni(2);
    }

    /**
     * Přidávejte vagonky do vlaku
     * Podminky přidávání vagónků:
     *      1. UHLI musí být vždy řazeno hned za lokomotivu
     *      2. DREVO se přidává vždy za poslední vagonek s UHLI, pokud neexistuje UHLI tak za LOKOMOTIVU
     *      3. AUTA se přidávají na konec vlaku za poslední POSTOVNI vagonek
     *      4. vagonky s NAFTA budou řazeny vždy před POSTOVNI vagonek
     *
     * Při vkládání vagonku nezapomeňte vagonku přiřadit danou pozici ve vlaku
     * !!!!!!! POZOR !!!!!! pokud přidáváte vagonek jinak než na konec vlaku musíte všem následujícím vagonkům zvýšit jejich umístění - doporučuji si pro tento účel vytvořit metodu
     * @param type
     */
    public void pridatVagonek(VagonekType type) {

    }

    public Vagonek getVagonekByIndex(int index) {
        int i = 1;
        Vagonek atIndex = lokomotiva;
        while(i < index) {
            atIndex = atIndex.getNasledujici();
            i++;
        }
        return atIndex;
    }


    /**
     * Touto metodou si můžete vrátit poslední vagonek daného typu
     * Pokud tedy budu chtít vrátit vagonek typu lokomotiva dostanu hned první vagonek
     * @param type
     * @return Vagonek
     */
    public Vagonek getLastVagonekByType(VagonekType type) {
        Vagonek last = lokomotiva;
        return last;
    }


    /**
     * Přidává vagonek s TEKAVE_LATKY za posledni vagonek s NAFTA
     *  pokud za posledním vagonkem NAFTA již vagonek s TEKAVE_LATKY existuje
     *  přidá se za poslední vagonek AUTA
     */
    public void pridatVagonekTekaveLatky() {
        Vagonek tekaveLatky = new Vagonek(VagonekType.TEKAVE_LATKY);
    }

    /**
     * Funkce vrátí počet vagonků daného typu
     * Dobré využití se najde v metodě @method(addJidelniVagonek)
     * @param type
     * @return
     */
    public int getPocetVagonkuByType(VagonekType type) {
        return 0;
    }

    /**
     * Hledejte vagonky s TEKAVE_LATKY
     * Vratí seznam s vagonky obsahujici TEKAVE_LATKY
     * @return
     */
    public List<Vagonek> getVagonkyTekaveLatky() {
        List<Vagonek> tekaveLatky = new LinkedList<>();

        return tekaveLatky;
    }

    /**
     * Odebere poslední vagonek daného typu
     * !!!! POZOR !!!!! pokud odebíráme z prostředku vlaku musíme zbývající vagonky projít a snížit jejich umístění ve vlaku
     * @param type
     */
    public void odebratPosledniVagonekByType(VagonekType type) {

    }

    public int getDelka() {
        return delka;
    }
}
