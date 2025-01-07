package dades;

public abstract class accio {
    private String cod_Accio;
    private String nom;
    private String associacio;
    private static int comptador = 100;
    private String membreResponsable; 

    /**
     * Constructor inicialitzat amb parametres d'entrada
     * Encarregat de crear la classe accio amb els parametres d'entrada
     * @param associacio
     * @param titol
     * @param responsable
     */
    public accio (String associacio, String titol, String responsable) {
        this.associacio = associacio;
        this.nom = titol;
        this.membreResponsable = responsable;
        this.cod_Accio = generateCodAccio(associacio);
    }

    /**
     * Mètode encarregat de generar un codi per a l'acció
     * @param nomAssociacio
     * @return codi
     */
    private String generateCodAccio(String nomAssociacio) { 
        String prefix = nomAssociacio.substring(0, Math.min(3, nomAssociacio.length())).toUpperCase();
        String codi = prefix + comptador;
        comptador++;
        return codi;
    }

    /**
     * Mètode que retorna el codi de l'acció
     * @return codi_Accio
     */
    public String getCodAccio() {
        return cod_Accio;
    }

    /**
     * Mètode que retorna el membre responsable de l'acció
     * @return membreResponsable
     */
    public String getMembreResponsable () {
        return membreResponsable;
    }

    /**
     * Mètode que modifica el codi de l'acció
     * @param codi
     */
    public void setCodiAccio(String codi) {
        this.cod_Accio = codi;
    }

    /**
     * Mètode que retorna el nom de l'acció
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Mètode qe retorna el nom de l'associació
     * @return associacio
     */
    public String getAssociacio() {
        return associacio;
    }

    /**
     * Metode que retorna un string amb les dades de l'accio per poder visualitzar-les
     * @return string
     */
    public String toString() {
        return "L'acció amb codi " + cod_Accio + "\nAnomenada " + nom +
               "\nLa realitza l'associació " + associacio;
    }
}

