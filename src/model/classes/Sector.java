package model.classes;

public class Sector {
    private String escola;
    private String nom;
    private String latitud;
    private String longitud;
    private String aproximacio;
    private int num_vies;
    private String popularitat;
    private String restriccions;

    public Sector(String escola, String nom, String latitud, String longitud, String aproximacio, int num_vies, String popularitat, String restriccions) {
        this.escola = escola;
        this.nom = nom;
        this.latitud = latitud;
        this.longitud = longitud;
        this.aproximacio = aproximacio;
        this.num_vies = num_vies;
        this.popularitat = popularitat;
        this.restriccions = restriccions;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
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
        return "poblacio: " + escola + "\n" +
                "nom: " + nom + "\n" +
                "latitud: " + latitud + "\n" +
                "longitud: " + longitud + "\n" +
                "aproximacio: " + aproximacio + "\n" +
                "num_vies: " + num_vies + "\n" +
                "popularitat: " + popularitat + "\n" +
                "restriccions: " + restriccions + "\n";
    }
}