package model.classes;

public class Escalador {
    private String nom;
    private String alies;
    private int edad;
    private String nivel_max;
    private String nombre_via_max;
    private String tipo_favorito;
    private String fita;

    public Escalador(String nom, String alies, int edad, String nivel_max, String nombre_via_max, String tipo_favorito, String fita) {
        this.nom = nom;
        this.alies = alies;
        this.edad = edad;
        this.nivel_max = nivel_max;
        this.nombre_via_max = nombre_via_max;
        this.tipo_favorito = tipo_favorito;
        this.fita = fita;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAlies() {
        return alies;
    }

    public void setAlies(String alies) {
        this.alies = alies;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNivel_max() {
        return nivel_max;
    }

    public void setNivel_max(String nivel_max) {
        this.nivel_max = nivel_max;
    }

    public String getNombre_via_max() {
        return nombre_via_max;
    }

    public void setNombre_via_max(String nombre_via_max) {
        this.nombre_via_max = nombre_via_max;
    }

    public String getTipo_favorito() {
        return tipo_favorito;
    }

    public void setTipo_favorito(String tipo_favorito) {
        this.tipo_favorito = tipo_favorito;
    }

    public String getFita() {
        return fita;
    }

    public void setFita(String fita) {
        this.fita = fita;
    }

    @Override
    public String toString() {
        return "nom: " + nom + "\n" +
                "alies: " + alies + "\n" +
                "edad: " + edad + "\n" +
                "nivel_max: " + nivel_max + "\n" +
                "nombre_via_max: " + nombre_via_max + "\n" +
                "tipo_favorito: " + tipo_favorito + "\n" +
                "fita: " + fita + "\n";
    }
}