import tipusMembre.*;

public class Associacio {
    // Atributs
    private final String nom;
    private final String correu;
    private final Membre[] llistaMembres;
    private final Accio[] llistaAccions;
    private int totalMembres;
    private int totalAccions;

    // Constructor
    public Associacio(String nom, String correu, int maxMembres, int maxAccions) {
        this.nom = nom;
        this.correu = correu;
        this.llistaMembres = new Membre[maxMembres];
        this.llistaAccions = new Accio[maxAccions];
        this.totalMembres = 0;
        this.totalAccions = 0;
    }

    // Mètode per obtenir el nom
    public String getNom() {
        return nom;
    }

    // Mètode per obtenir el correu
    public String getCorreu() {
        return correu;
    }

    // Afegir membre
    public boolean afegirMembre(Membre membre) {
        if (totalMembres < llistaMembres.length) {
            llistaMembres[totalMembres++] = membre;
            return true;
        }
        return false;
    }

    // Afegir acció
    public boolean afegirAccio(Accio accio) {
        if (totalAccions < llistaAccions.length) {
            llistaAccions[totalAccions++] = accio;
            return true;
        }
        return false;
    }

    // Llistar membres amb filtre
    public Membre[] llistaMembresFiltrat(boolean solsAlumnes, boolean solsProfessors) {
        int contador = 0;
        for (int i = 0; i < totalMembres; i++) {
            Membre m = llistaMembres[i];
            if ((solsAlumnes && m instanceof Alumne) || (solsProfessors && m instanceof Professor) || (!solsAlumnes && !solsProfessors)) {
                contador++;
            }
        }
        Membre[] finalResultat = new Membre[contador];
        int index = 0;
        for (int i = 0; i < totalMembres; i++) {
            Membre m = llistaMembres[i];
            if ((solsAlumnes && m instanceof Alumne) || (solsProfessors && m instanceof Professor) || (!solsAlumnes && !solsProfessors)) {
                finalResultat[index++] = m;
            }
        }
        return finalResultat;
    }

    // Llistar accions
    public Accio[] llistaAccions() {
        Accio[] resultat = new Accio[totalAccions];
        for (int i = 0; i < totalAccions; i++) {
            resultat[i] = llistaAccions[i];
        }
        return resultat;
    }
}
