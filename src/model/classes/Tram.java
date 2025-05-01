package model.classes;

public class Tram {
    private int llargada;
    private String dificultat;
    private int numero_tram;

    public Tram(int llargada, String dificultat, int numero_tram) {
        this.llargada = llargada;
        this.dificultat = dificultat;
        this.numero_tram = numero_tram;
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

    public int getNumero_tram() {
        return numero_tram;
    }

    public void setNumero_tram(int numero_tram) {
        this.numero_tram = numero_tram;
    }

    @Override
    public String toString() {
        return String.format(
                        "%-15s %-10s\n" +
                        "%-15s %-10s\n" +
                        "%-15s %-10s\n",
                "Longitud:", llargada,
                "Dificultad:", dificultat,
                "Numero_tramo:", numero_tram);
    }
}
