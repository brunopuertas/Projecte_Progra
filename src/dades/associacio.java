package dades;
import dades.tipusMembre.*;
import excepcions.*;

public class associacio {
    private final String nom;
    private final String correu;
    private final membre[] llistaMembres;
    private final accio[] llistaAccions;
    private membre[] carrecs; // 1-President 2-Secretari 3-Tresorer
    private int totalMembres;
    private int totalAccions;
    

    /**
     * Constructor inicialitzat amb parametres d'entrada
     * Encarregat de crear la classe associacio amb els parametres d'entrada
     * @param nom
     * @param correu
     * @param maxMembres
     * @param maxAccions
     */
    public associacio(String nom, String correu, int maxMembres, int maxAccions) {
        this.nom = nom;
        this.correu = correu;
        this.llistaMembres = new membre[maxMembres];
        this.llistaAccions = new accio[maxAccions];
        this.totalMembres = 0;
        this.totalAccions = 0;
        this.carrecs = new membre[3];
    }

    /**
     * Mètode per obtenir el nom
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Mètode per obtenir el correu
     * @return correu
     */
    public String getCorreu() {
        return correu;
    }

    /**
     * Mètode encarregat de retornar un string amb les dades de l'associació
     * @return aux
     */
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

    /**
     * Mètode per afegir un membre a l'associació
     * Implementa les excepcions maxAssociacionsExcedit i maxMembresExcedit
     * @param membre
     * @throws maxAssociacionsExcedit
     * @throws maxMembresExcedit
     */
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

    /**
     * Mètode per afegir una acció a l'associació
     * Implementa l'excepció maxAccioExcedit
     * @param accio
     * @throws maxAccioExcedit
     */
    public void afegirAccio(accio accio) throws maxAccioExcedit {
        if (totalAccions >= llistaAccions.length) {
            throw new maxAccioExcedit(accio);
            
        } else {
            llistaAccions[totalAccions++] = accio;
        }
    }

    /**
     * Mètode per obtenir els membres de l'associació
     * @param membre
     * @return llistaMembres
     */
    public membre[] getLlistaMembres() {
        return this.llistaMembres;
    }

    /**
     * Mètode per obtenir un membre de l'associació
     * @param accio
     * @return llistaMembres[index]
     */
    public membre getMembre(int index) {
        return this.llistaMembres[index];
    }

    /**
     * Mètode per obtenir el total de membres
     * @return totalMembres
     */
    public int getTotalMembres() {
        return totalMembres;
    }

    /**
     * Mètode per obtenir una acció de l'associació
     * @param index
     * @return llistaAccions[index]
     */
    public accio getAccio(int index) {
        return this.llistaAccions[index];
    }

    /**
     * Mètode per obtenir el total d'accions
     * @return totalAccions
     */
    public int getTotalAccions() {
        return totalAccions;
    }

    /**
     * Mètode per obtenir els membres d'una maera filtrada
     * Els paràmetres solsAlumnes i solsProfessors permeten filtrar la llista
     * @param solsAlumnes
     * @param solsProfessors
     * @return finalResultat
     */
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

    /**
     * Mètode que retorna totes les accions
     * @return resultat
     */
    public accio[] llistaAccions() {
        accio[] resultat = new accio[totalAccions];
        for (int i = 0; i < totalAccions; i++) {
            resultat[i] = llistaAccions[i];
        }
        return resultat;
    }

    /**
     * Mètode per definir un President
     * @param a
     */
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

    /**
     * Mètode per definir un Secretari
     * @param a
     */
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

    /**
     * Mètode per definir un Tresorer
     * @param a
     */
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

    /**
     * Mètode per obtenir el President
     * @return carrecs[0]
     */
    public membre getPresident() {
        return carrecs[0];
    }

    /**
     * Mètode per obtenir el Secretari
     * @return carrecs[1]
     */
    public membre getSecretari() {
        return carrecs[1];
    }

    /**
     * Mètode per obtenir el Tresorer
     * @return carrecs[2]
     */
    public membre getTresorer() {
        return carrecs[2];
    }
}
