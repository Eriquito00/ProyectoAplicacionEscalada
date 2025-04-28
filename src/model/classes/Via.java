package model.classes;

public class Via {
    private int id;
    private String sector;
    private String tipus;
    private String ancoratge;
    private String tipus_roca;
    private String escalador;
    private String dificultat;
    private String nom;
    private int llargada;
    private int numero_via;
    private String orientacio;
    private String estat;

    // TODO: Afegir un constructor amb la ID per poder actualitzar la via a la bbdd
    public Via(String sector, String tipus, String ancoratge, String tipus_roca, String escalador, String dificultat, String nom, int llargada, int numero_via, String orientacio, String estat) {
        this.sector = sector;
        this.tipus = tipus;
        this.ancoratge = ancoratge;
        this.tipus_roca = tipus_roca;
        this.escalador = escalador;
        this.dificultat = dificultat;
        this.nom = nom;
        this.llargada = llargada;
        this.numero_via = numero_via;
        this.orientacio = orientacio;
        this.estat = estat;
    }

    public Via(int id,String sector, String tipus, String ancoratge, String tipus_roca, String escalador, String dificultat, String nom, int llargada, int numero_via, String orientacio, String estat) {
        this.id = id;
        this.sector = sector;
        this.tipus = tipus;
        this.ancoratge = ancoratge;
        this.tipus_roca = tipus_roca;
        this.escalador = escalador;
        this.dificultat = dificultat;
        this.nom = nom;
        this.llargada = llargada;
        this.numero_via = numero_via;
        this.orientacio = orientacio;
        this.estat = estat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public String getAncoratge() {
        return ancoratge;
    }

    public void setAncoratge(String ancoratge) {
        this.ancoratge = ancoratge;
    }

    public String getTipus_roca() {
        return tipus_roca;
    }

    public void setTipus_roca(String tipus_roca) {
        this.tipus_roca = tipus_roca;
    }

    public String getEscalador() {
        return escalador;
    }

    public void setEscalador(String escalador) {
        this.escalador = escalador;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getLlargada() {
        return llargada;
    }

    public void setLlargada(int llargada) {
        this.llargada = llargada;
    }

    public int getNumero_via() {
        return numero_via;
    }

    public void setNumero_via(int numero_via) {
        this.numero_via = numero_via;
    }

    public String getOrientacio() {
        return orientacio;
    }

    public void setOrientacio(String orientacio) {
        this.orientacio = orientacio;
    }

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public String getDificultat() {
        return dificultat;
    }

    public void setDificultat(String dificultat) {
        this.dificultat = dificultat;
    }

    @Override
    public String toString() {
        return String.format(
                        "%-15s %-60s\n" +
                        "%-15s %-15s\n" +
                        "%-15s %-15s\n" +
                        "%-15s %-15s\n" +
                        "%-15s %-60s\n" +
                        "%-15s %-60s\n" +
                        "%-15s %-25s\n" +
                        "%-15s %-15s\n" +
                        "%-15s %-15s\n" +
                        "%-15s %-15s\n",
                "Sector:", sector,
                "Tipo:", tipus,
                "Ancorage:", ancoratge,
                "Tipo roca:", tipus_roca,
                "Escalador:", escalador,
                "Nombre:", nom,
                "Longitud:", llargada,
                "Numero via:", numero_via,
                "Orientacion:", orientacio,
                "Estado:", estat);
    }
}