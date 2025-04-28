package model.classes;

public class Escalador {
    private int id;
    private String nom;
    private String alies;
    private int edad;
    private String nivell_max;
    private String nombre_via_max;
    private String escola_via_max;
    private String tipo_favorito;
    private String fita;

    public Escalador(String nom, String alies, int edad, String nombre_via_max, String escola_via_max, String tipo_favorito, String fita) {
        this.nom = nom;
        this.alies = alies;
        this.edad = edad;
        this.nombre_via_max = nombre_via_max;
        this.escola_via_max = escola_via_max;
        this.tipo_favorito = tipo_favorito;
        this.fita = fita;
    }

    public Escalador(String nom, String alies, int edad, String nivell_max, String nombre_via_max, String tipo_favorito, String fita, String escola_via_max) {
        this.nom = nom;
        this.alies = alies;
        this.edad = edad;
        this.nivell_max = nivell_max;
        this.nombre_via_max = nombre_via_max;
        this.tipo_favorito = tipo_favorito;
        this.fita = fita;
    }

    public Escalador(int id, String nom, String alies, int edad, String nivell_max, String nombre_via_max, String tipo_favorito, String fita) {
        this.id = id;
        this.nom = nom;
        this.alies = alies;
        this.edad = edad;
        this.nivell_max = nivell_max;
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

    public String getNombre_via_max() {
        return nombre_via_max;
    }

    public void setNombre_via_max(String nombre_via_max) {
        this.nombre_via_max = nombre_via_max;
    }

    public String getEscola_via_max() {
        return escola_via_max;
    }

    public void setEscola_via_max(String escola_via_max) {
        this.escola_via_max = escola_via_max;
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
        return String.format(
                        "%-15s %-60s\n" +
                        "%-15s %-60s\n" +
                        "%-15s %-5s\n" +
                        "%-15s %-60s\n" +
                        "%-15s %-60s\n" +
                        "%-15s %-15s\n" +
                        "%-15s %-110s\n",
                "Nom:", nom,
                "Alies:", alies,
                "Edad:", edad,
                "Nombre via max:", nombre_via_max,
                "Escuela via max:", escola_via_max,
                "Tipo favorito:", tipo_favorito,
                "Fita:", fita
        );

    }
}