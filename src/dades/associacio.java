package dades;
import dades.tipusMembre.*;

public class associacio {
    // Atributs
    private final String nom;
    private final String correu;
    private final membre[] llistaMembres;
    private final accio[] llistaAccions;
    private int totalMembres;
    private int totalAccions;

    // Constructor
    public associacio(String nom, String correu, int maxMembres, int maxAccions) {
        this.nom = nom;
        this.correu = correu;
        this.llistaMembres = new membre[maxMembres];
        this.llistaAccions = new accio[maxAccions];
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
    public boolean afegirMembre(membre membre) {
        if (totalMembres < llistaMembres.length) {
            llistaMembres[totalMembres++] = membre;
            return true;
        }
        return false;
    }

    // Afegir acció
    public boolean afegirAccio(accio accio) {
        if (totalAccions < llistaAccions.length) {
            llistaAccions[totalAccions++] = accio;
            return true;
        }
        return false;
    }

    // Llistar membres amb filtre
    public membre[] llistaMembresFiltrat(boolean solsAlumnes, boolean solsProfessors) {
        int contador = 0;
        for (int i = 0; i < totalMembres; i++) {
            membre m = llistaMembres[i];
            if ((solsAlumnes && m instanceof alumne) || (solsProfessors && m instanceof professor) || (!solsAlumnes && !solsProfessors)) {
                contador++;
            }
        }
        membre[] finalResultat = new membre[contador];
        int index = 0;
        for (int i = 0; i < totalMembres; i++) {
            membre m = llistaMembres[i];
            if ((solsAlumnes && m instanceof alumne) || (solsProfessors && m instanceof professor) || (!solsAlumnes && !solsProfessors)) {
                finalResultat[index++] = m;
            }
        }
        return finalResultat;
    }

    // Llistar accions
    public accio[] llistaAccions() {
        accio[] resultat = new accio[totalAccions];
        for (int i = 0; i < totalAccions; i++) {
            resultat[i] = llistaAccions[i];
        }
        return resultat;
    }
}
