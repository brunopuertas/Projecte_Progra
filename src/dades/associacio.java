package dades;
import dades.tipusMembre.*;
import excepcions.*;

public class associacio {
    // Atributs
    private final String nom;
    private final String correu;
    private final membre[] llistaMembres;
    private final accio[] llistaAccions;
    private membre[] carrecs; // 1-President 2-Secretari 3-Tresorer
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
        this.carrecs = new membre[3];
    }

    // Mètode per obtenir el nom
    public String getNom() {
        return nom;
    }

    // Mètode per obtenir el correu
    public String getCorreu() {
        return correu;
    }

    public String toString() {
        String aux;
        aux = "La associació "+ this.nom +" té el correu: "+ this.correu +".\nTé un total de "+totalMembres+" i aquests són:";
        for (int i = 0; i<totalMembres; i++) {
            aux = aux + "\n"+i+"- "+this.llistaMembres[i].toString();
        }
        aux = aux + "\nTé un total de "+totalAccions+" i aquestes són:";
        for (int i = 0; i<totalMembres; i++) {
            aux = aux + "\n"+i+"- "+this.llistaAccions[i].toString();
        }
        return aux;
    }

    public void afegirMembre(membre membre) throws maxAssociacionsExcedit, maxMembresExcedit {
        if (totalMembres >= llistaMembres.length) {
            throw new maxMembresExcedit(membre);
        } else {
            if (membre.getAssociacions() > 3) {
                throw new maxAssociacionsExcedit(membre);
            } else {
                membre.setAssociacions(membre.getAssociacions() + 1);
                llistaMembres[totalMembres++] = membre;
                membre.isActiu();
            }
        }
    }

    // Afegir acció
    public void afegirAccio(accio accio) throws maxAccioExcedit {
        if (totalAccions >= llistaAccions.length) {
            throw new maxAccioExcedit(accio);
            
        } else {
            llistaAccions[totalAccions++] = accio;
        }
    }

    public membre[] getLlistaMembres() {
        return this.llistaMembres;
    }

    public membre getMembre(int index) {
        return this.llistaMembres[index];
    }

    public int getTotalMembres() {
        return totalMembres;
    }

    public accio getAccio(int index) {
        return this.llistaAccions[index];
    }

    public int getTotalAccions() {
        return totalAccions;
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

    public void definirPresident(alumne a) {
        boolean pertany = false;
        int i = 0;
        while ( i < totalMembres && !pertany ) {
            if ( a == llistaMembres[i] ) {
                pertany = true;
                carrecs[0] = a;
            }else{
                i++;
            }
        }
    }

    public void definirSecretari(alumne a) {
        boolean pertany = false;
        int i = 0;
        while ( i < totalMembres && !pertany ) {
            if ( a == llistaMembres[i] ) {
                pertany = true;
                carrecs[1] = a;
            }else{
                i++;
            }
        }
    }

    public void definirTresorer(alumne a) {
        boolean pertany = false;
        int i = 0;
        while ( i < totalMembres && !pertany ) {
            if ( a == llistaMembres[i] ) {
                pertany = true;
                carrecs[2] = a;
            }else{
                i++;
            }
        }
    }

    public membre getPresident() {
        return carrecs[0];
    }

    public membre getSecretari() {
        return carrecs[1];
    }

    public membre getTresorer() {
        return carrecs[2];
    }
}
