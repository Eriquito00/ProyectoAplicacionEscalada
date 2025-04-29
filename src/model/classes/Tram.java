package model.classes;

public class Tram {
    private int llargada;
    private String dificultat;

    public Tram(int llargada, String dificultat) {
        this.llargada = llargada;
        this.dificultat = dificultat;
    }

    public int getLlargada() {
        return llargada;
    }

    public void setLlargada(int llargada) {
        this.llargada = llargada;
    }

    public String getDificultat() {
        return dificultat;
    }

    public void setDificultat(String dificultat) {
        this.dificultat = dificultat;
    }
}
