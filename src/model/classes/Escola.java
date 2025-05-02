package model.classes;

public class Escola {
    private int id;
    private String poblacio;
    private String nom;
    private String aproximacio;
    private int num_vies;
    private String popularitat;
    private String restriccions;

    // Constructor para crear escuelas
    public Escola(String poblacio, String nom, String aproximacio, String popularitat, String restriccions) {
        this.poblacio = poblacio;
        this.nom = nom;
        this.aproximacio = aproximacio;
        this.num_vies = 0; // Valor default
        this.popularitat = popularitat;
        this.restriccions = restriccions;
    }

    // Constructor para leer escuelas
    public Escola(String poblacio, String nom, String aproximacio, int num_vies, String popularitat, String restriccions) {
        this.poblacio = poblacio;
        this.nom = nom;
        this.aproximacio = aproximacio;
        this.num_vies = num_vies;
        this.popularitat = popularitat;
        this.restriccions = restriccions;
    }

    // Constructor para actualizar escuelas
    public Escola(int id, String poblacio, String nom, String aproximacio, String popularitat, String restriccions) {
        this.id = id;
        this.poblacio = poblacio;
        this.nom = nom;
        this.aproximacio = aproximacio;
        this.popularitat = popularitat;
        this.restriccions = restriccions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAproximacio() {
        return aproximacio;
    }

    public void setAproximacio(String aproximacio) {
        this.aproximacio = aproximacio;
    }

    public int getNum_vies() {
        return num_vies;
    }

    public void setNum_vies(int num_vies) {
        this.num_vies = num_vies;
    }

    public String getPopularitat() {
        return popularitat;
    }

    public void setPopularitat(String popularitat) {
        this.popularitat = popularitat;
    }

    public String getRestriccions() {
        return restriccions;
    }

    public void setRestriccions(String restriccions) {
        this.restriccions = restriccions;
    }

    @Override
    public String toString() {
        return String.format(
                        "%-15s %-60s\n" +
                        "%-15s %-60s\n" +
                        "%-15s %-110s\n" +
                        "%-15s %-15s\n" +
                        "%-15s %-15s\n" +
                        "%-15s %-110s\n",
                "Poblacion:", poblacio,
                "Nombre:", nom,
                "Aproximacion:", aproximacio,
                "Num vias:", num_vies,
                "Popularidad:", popularitat,
                "Restricciones:", restriccions
        );
    }
}