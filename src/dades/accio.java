package dades;

public abstract class accio {
    private String cod_Accio;
    private String nom;
    private String associacio;
    private static int comptador = 100;
    private String membreResponsable; 

    // Constructor
    public accio (String associacio, String titol, String responsable) {
        this.associacio = associacio;
        this.nom = titol;
        this.membreResponsable = responsable;
        this.cod_Accio = generateCodAccio(associacio);
    }

    // Mètode per generar el codi d'acció
    private String generateCodAccio(String nomAssociacio) {
        // si hi ha dubtes com es genera el codi dieu 
        String prefix = nomAssociacio.substring(0, Math.min(3, nomAssociacio.length())).toUpperCase();
        String codi = prefix + comptador;
        comptador++;
        return codi;
    }

    // Getters
    public String getCodAccio() {
        return cod_Accio;
    }

    public String getMembreResponsable () {
        return membreResponsable;
    }

    public String setCodiAccio(String codi) {
        return this.cod_Accio = codi;
    }

    public String getNom() {
        return nom;
    }

    public String getAssociacio() {
        return associacio;
    }

    public String toString() {
        return "L'acció amb codi " + cod_Accio + "\nAnomenada " + nom +
               "\nLa realitza l'associació " + associacio;
    }
}

