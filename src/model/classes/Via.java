package model.classes;

public class Via {
    private String sector;
    private String tipus;
    private String ancoratge;
    private String tipus_roca;
    private String escalador;
    private String nom;
    private int llargada;
    private int numero_via;
    private String orientacio;
    private String estat;

    public Via(String sector, String tipus, String ancoratge, String tipus_roca, String escalador, String nom, int llargada, int numero_via, String orientacio, String estat) {
        this.sector = sector;
        this.tipus = tipus;
        this.ancoratge = ancoratge;
        this.tipus_roca = tipus_roca;
        this.escalador = escalador;
        this.nom = nom;
        this.llargada = llargada;
        this.numero_via = numero_via;
        this.orientacio = orientacio;
        this.estat = estat;
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

    @Override
    public String toString() {
        return "sector: " + sector + "\n" +
                "tipus: " + tipus + "\n" +
                "ancoratge: " + ancoratge + "\n" +
                "tipus_roca: " + tipus_roca + "\n" +
                "escalador: " + escalador + "\n" +
                "nom: " + nom + "\n" +
                "llargada: " + llargada + "\n" +
                "numero_via: " + numero_via + "\n" +
                "orientacio: " + orientacio + "\n" +
                "estat: " + estat + "\n";
    }
}