package utilitats;

import dades.*;
import dades.tipusMembre.*;
import excepcions.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class associacions {

    private associacio[] associacions; 
    private int count;              

    public associacions(int capacitatInicial) {
        if (capacitatInicial <= 0) {
            throw new IllegalArgumentException("La capacitat inicial ha de ser més gran que 0");
        }
        this.associacions = new associacio[capacitatInicial];
        this.count = 0;
    }

    /**
     * Opció 1 menu
     * toString de la llista d'associacions, retorna el nombre d'associacions i les llista
     */
    public String toString() {
        String aux = "\nHi ha un total de " + count + " associacions. Aquestes són:";
        for (int i = 0; i < this.count; i++) {
            aux = aux + "\n" + (i + 1) + "- " + associacions[i].getNom();
        }
        return aux;
    }

    /**
     * Afegeix una nova associació al conjunt.
     * @param associacio 
     * @throws IllegalArgumentException 
     * @throws IllegalStateException    
     */
    public void afegirAssociacio(associacio associacio) {
        if (buscarAssociacio(associacio.getNom()) != null) {
            throw new IllegalArgumentException("Ja existeix una associació amb el nom '" + associacio.getNom() + "'");
        }

        if (count == associacions.length) {
            throw new IllegalStateException("No hi ha espai per afegir més associacions");
        }

        associacions[count++] = associacio;
    }

    /**
     * Busca una associació pel seu nom.
     * @param nom El nom de l'associació que es vol buscar.
     * @return L'associació si existeix, o null si no es troba.
     */
    public associacio buscarAssociacio(String nom) {
        for (int i = 0; i < count; i++) {
            if (associacions[i].getNom().equalsIgnoreCase(nom)) {
                return associacions[i];
            }
        }
        return null;
    }

    public boolean existeixAssociacio(String nom) {
        boolean existeix = false;
        int i = 0;
        while ( !existeix && i < this.count ) {
            if ( associacions[i].getNom().equals(nom) ){
                existeix=true;
            }
            i++;
        }
        return existeix;
    }

    /**
     * Elimina una associació pel seu nom.
     * @param nom El nom de l'associació que es vol eliminar.
     * @return True si s'ha eliminat correctament, false si no es troba.
     */
    public boolean eliminarAssociacio(String nom) {
        for (int i = 0; i < count; i++) {
            if (associacions[i].getNom().equalsIgnoreCase(nom)) {
                // Moure totes les associacions després de l'element eliminat
                for (int j = i; j < count - 1; j++) {
                    associacions[j] = associacions[j + 1];
                }
                associacions[--count] = null; // Esborrem l'últim element
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna el nombre d'associacions actuals.
     * @return El nombre d'associacions.
     */
    public int getCount() {
        return count;
    }


    /**
     * Retorna una llista amb tots els membres actius de totes les associacions.
     * @return Una llista amb tots els membres actius.
     */
    public membre[] llistaMembresActiusAssociacioFiltre( String nom ){
        membre[] membresActius = new membre[100];
        int index = 0;
        associacio a = this.buscarAssociacio(nom);
        membre[] membres = a.llistaMembresFiltrat(false, false);

        for ( int i = 0; i < membres.length; i++) {
            if (membres[i].isActiu()) {
                membresActius[index] = membres[i];
                index++;
            }
        }
        membre[] llistaFinal = new membre[index];

        for (int i = 0; i < index; i++) {
            llistaFinal[i] = membresActius[i];
        }
        return llistaFinal;
    }

    /**
     * Opció 2 menu
     * 
     * Retorna una llista amb tots els membres actius de totes les associacions, filtrats per tipus.
     * @param nom       El nom de l'associació.
     * @param professor Si es volen només professors.
     * @param alumne    Si es volen només alumnes.
     * @return Una llista amb tots els membres actius.
     */
    public membre[] llistaMembresActiusAssociacioFiltre(String nom, boolean professor, boolean alumne) {
        membre[] membresActius = new membre[100];
        int index = 0;
        associacio a = this.buscarAssociacio(nom);

        membre[] membres;
        if ( professor ) {
            membres = a.llistaMembresFiltrat(false, true);
        }else{
            membres = a.llistaMembresFiltrat(true, false);
        }
        
        for ( int i = 0; i < membres.length; i++) {
            if (membres[i].isActiu()) {
                membresActius[index] = membres[i];
                index++;
            }
        }

        membre[] llistaFinal = new membre[index];

        for (int i = 0; i < index; i++) {
            llistaFinal[i] = membresActius[i];
        }
        return llistaFinal;
    }


    public membre[] llistaMembresActiusFiltre() {
        membre[] membresActius = new membre[100];
        int index = 0;
        for (int i = 0; i < this.count; i++) {
            associacio a = this.associacions[i];
            membre[] membres = a.llistaMembresFiltrat(false, false);
            for (membre m : membres) {
                if (m.isActiu()) {
                    membresActius[index] = m;
                    index++;
                }
            }
        }
        membre[] result = new membre[index];
        System.arraycopy(membresActius, 0, result, 0, index);
        return result;
    }


    public membre[] llistaMembresActiusFiltre( boolean professor, boolean alumne) {
        membre[] membresActius = new membre[100]; // assuming a maximum of 100 active members
        int index = 0;
        for (int i = 0; i < this.count; i++) {
            associacio a = this.associacions[i];
            membre[] membres = a.llistaMembresFiltrat(alumne, professor);
            for (membre m : membres) {
                if (m.isActiu()) {
                    membresActius[index++] = m;
                }
            }
        }
        membre[] result = new membre[index];
        System.arraycopy(membresActius, 0, result, 0, index);
        return result;
    }

    /**
     * Retorna una associació pel seu índex.
     * @param index L'índex de l'associació.
     * @return L'associació si l'índex és vàlid, o null si no ho és.
     */
    public associacio getAssociacio(int index) {
        if (index < 0 || index >= count) {
            return null;
        }
        return associacions[index];
    }


    /**
     * Opcio 4 menu
     * Dona una llista de les accions de totes les associacions sense el filtre
      */
    public accio[] llistaAccionsFiltre() {
        accio[] llista = new accio[100];
        int index = 0;
    
        for (int i = 0; i < this.count; i++) {
            accio[] accions = associacions[i].llistaAccions(); 
    
            for (int j = 0; j < accions.length; j++) {
                if (accions[j] instanceof Xerrada) {
                    llista[index] = accions[j]; 
                    index++;
                }
            }
        }
    
        accio[] resultat = new accio[index];
        for ( int i =0; i < index; i++ ){
            resultat[i] = llista[i];
        }
    
        return resultat;
    }

    /**
     * Opcio 4 menu
     * Dona una llista de les accions de totes les associacions amb filtre
      */
    public accio[] llistaAccionsFiltre(boolean xerr, boolean demo) {
        accio[] llista = new accio[100];
        int index = 0;
    
        for (int i = 0; i < this.count; i++) {
            accio[] accions = associacions[i].llistaAccions(); 
    
            for (int j = 0; j < accions.length; j++) {
                if (accions[j] instanceof Xerrada) {
                    llista[index] = accions[j]; 
                    index++;
                }
            }
        }
    
        accio[]llista2 = new accio[index];
        int index2 = 0;
        if ( xerr ) {
            for ( int i = 0; i < llista.length; i++ ){
                if ( llista[i] instanceof Xerrada ) {
                    llista2[index2] = llista[i];
                    index2++;
                }
            }
        }
        if ( demo ) {
            for ( int i = 0; i < llista.length; i++ ){
                if ( llista[i] instanceof demostracio ) {
                    llista2[index2] = llista[i];
                    index2++;
                }
            }
        }
        accio[] resultat = new accio[index];
        for ( int i =0; i < index; i++ ){
            resultat[i] = llista[i];
        }
    
        return resultat;
    }



    /**
     * Opcio 5 menu
     * @param nom
     * @param xerr
     * @param demo
     * @return
     */
    public accio[] llistaAccionsAssociacioFiltre( String nom, boolean xerr, boolean demo) {
        int index = 0;
        associacio a = this.buscarAssociacio(nom);
        accio[] llista= a.llistaAccions();
        accio[] resultat = new accio[llista.length];

        if ( xerr ) {
            for ( int i = 0; i < llista.length; i++ ){
                if (llista[i] instanceof Xerrada ){
                    resultat[index] = llista[i];
                    index++;
                }
            }
        }else{
            for ( int i = 0; i < llista.length; i++ ){
                if (llista[i] instanceof demostracio ){
                    resultat[index] = llista[i];
                    index++;
                }
            }
        }

        return resultat;
    }



    /**
     * Opcio 5 menu
     * @param nom
     * @return
     */
    public accio[] llistaAccionsAssociacioFiltre( String nom ) {
        associacio a = this.buscarAssociacio(nom);
        accio[] llista= a.llistaAccions();

        return llista;
    }
    

    /**
     * Opcio 15 menu
     * Retorna la xerrada amb la valoració mitjana més alta.
     * En cas d'empat en la valoració mitjana, selecciona la xerrada amb més assistents.
     * @param xerrades, és una llista/taula de xerrades que compararem.
     * @return la xerrada amb la valoració mitjana més alta.
     */
    public Xerrada millorXerrada(Xerrada[] xerrades) {
        Xerrada millor = xerrades[0];

        for (int i = 0; i < xerrades.length; i++) {
            if ( millor.getValoracioMitjana() < xerrades[i].getValoracioMitjana() ) {
                millor = xerrades[i];
            }
        }
        return millor;
    }

    /**
     * Opcio 16 menu
     * Mostra totes les xerrades que ha fet una persona concreta.
     * @param xerrades, és una llista/taula de xerrades a consultar.
     * @param persona, el nom de la persona que ha fet les xerrades que busquem.
     */
    public static void mostrarXerradesPerPersona(Xerrada[] xerrades, String persona) {
        for (Xerrada x : xerrades) {
            if (x.getMembreResponsable().equalsIgnoreCase(persona)) {
                System.out.println(x);
            }
        }
    }

    /**
     * Opcio 13
     * Mostra les xerrades que tenen més assistents que el límit especificat.
     * @param xerrades, és una llista/taula de xerrades a consultar.
     * @param limit, el nombre mínim d'assistents per filtrar les xerrades.
     */
    public Xerrada[] mostrarXerradesAmbMésAssistents(int limit) {
        Xerrada[] resultat = new Xerrada[100];
        int index = 0;
        for ( int i = 0; i < this.count; i++ ){
            accio[] llista = associacions[i].llistaAccions(); 
            for ( int j = 0; j < associacions[i].getTotalAccions(); j++) {
                if ( llista[j] instanceof Xerrada ) {
                    resultat[index] = (Xerrada) llista[j];
                    index++;
                }
            }
        }

        Xerrada[] llistaFinal = new Xerrada[index];
        for ( int i = 0; i < index; i++ ){
            if ( resultat[i].getAssistencies() > limit ) {
                llistaFinal[i] = resultat[i];
            }
        }

        return llistaFinal;
    }

    /**
     * Mostra les xerrades que es duen a terme dins d'un rang de dates especificat.
     * @param xerrades, és una llista/taula de xerrades a consultar.
     * @param dataInici, la data d'inici de la franja de dates.
     * @param dataFi, la data final de la franja de dates.
     */
    public accio[] mostrarXerradesEnRangDeDates(Date dataInici, Date dataFi) {
        int comptador = 0;
        int nTotalAccions = 0;
        
        // Comptar el total d'accions en totes les associacions
        for (int i = 0; i < this.count; i++) {
            nTotalAccions = nTotalAccions + associacions[i].getTotalAccions();
        }
    
        accio[] llistaFinal = new accio[nTotalAccions];
    
        // Recórrer les associacions i les seves accions
        for (int i = 0; i < this.count; i++) {
            accio[] llista = new accio[this.associacions[i].getTotalAccions()];
    
            // Recórrer les accions dins de cada associació
            for (int j = 0; j < llista.length; j++) {
                if (llista[j] instanceof Xerrada) {  // Verificar si l'acció és una instància de Xerrada
                    Xerrada xerrada = (Xerrada) llista[j];  // Fer el cast a Xerrada
                    if (xerrada.getData().after(dataInici) && xerrada.getData().before(dataFi)) {
                        llistaFinal[comptador] = llista[j];  // Afegir la Xerrada a l'array final
                        comptador++;  // Incrementar el comptador per a la següent posició a llistaFinal
                    }
                }
            }
        }

        accio[] llistaFinal2 = new accio[comptador];
        for (int i = 0; i < comptador; i++) {
            llistaFinal2[i] = llistaFinal[i];
        }
    
        return llistaFinal2;
    }

    /**
     * Afegeix una nova xerrada a la llista de xerrades.
     * Aquesta funció demana a l'usuari les dades de la nova xerrada i l'afegeix a la llista.
     * @param xerrades, és la llista/taula de xerrades on s'afegirà la nova xerrada.
     */
    public static void afegirXerrada(Xerrada[] xerrades) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
        System.out.println("Introdueix el nom de l'associació: ");
        String associacio = scanner.nextLine();
        
        System.out.println("Introdueix el títol de la xerrada: ");
        String titol = scanner.nextLine();
        
        System.out.println("Introdueix el responsable de la xerrada: ");
        String responsable = scanner.nextLine();
        
        System.out.println("Introdueix la data de la xerrada (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();
        Date data = null;
        try {
            data = sdf.parse(dataStr);
        } catch (Exception e) {
            System.out.println("Data no vàlida.");
            return;
        }
    
        System.out.println("Introdueix el nombre màxim de membres: ");
        int maxMembres = Integer.parseInt(scanner.nextLine());
        
        System.out.println("Introdueix el nombre d'assistències: ");
        int assistencies = Integer.parseInt(scanner.nextLine());
        
        System.out.println("Introdueix la valoració mitjana: ");
        float valoracioMitjana = Float.parseFloat(scanner.nextLine());
    
        // Crear la nova xerrada
        Xerrada novaXerrada = new Xerrada(associacio, titol, responsable, data, maxMembres, assistencies, valoracioMitjana);
    
        // Afegir la nova xerrada a la llista de xerrades
        // Asegurar-se d'augmentar el nombre d'elements a la llista de xerrades
        System.out.println("Xerrada afegida correctament!");
    }


    
    /**
     * Permet a un assistent valorar una xerrada.
     * Aquest mètode actualitza la valoració mitjana de la xerrada, incrementant el nombre d'assistències
     * i recalculant la valoració mitjana segons la nova valoració donada.
     * @param valoracio, la valoració que l'assistent vol donar (ha de ser entre 0 i 10).
     */
    public void valorarXerradaPerAssistent(float valoracio, Xerrada x) {
        // Comprovem que la valoració està dins del rang vàlid (0-10)
        if (valoracio >= 0 && valoracio <= 10) {
            // Recalculem la nova valoració mitjana
            float novaValoracio = ( x.getValoracioMitjana() * x.getAssistencies() + valoracio ) / ( x.getAssistencies() + 1);
            x.setValoracioMitjana(novaValoracio);
            x.setAssistencies(x.getAssistencies() + 1);;  // Augmentem el nombre d'assistències
    
            System.out.println("Valoració afegida correctament!");
        } else {
            System.out.println("La valoració ha d'estar entre 0 i 10.");
        }
    }

    /**
     * Afegeix una nova demostració a la llista de demostracions.
     * Aquest mètode demana a l'usuari les dades de la nova demostració i la crea.
     * @param demostracions, la llista o array de demostracions on s'afegirà la nova.
     */
    public static void afegirDemostracio(demostracio[] demostracions) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
        System.out.println("Introdueix el nom de l'associació: ");
        String associacio = scanner.nextLine();
    
        System.out.println("Introdueix el títol de la demostració: ");
        String titol = scanner.nextLine();
    
        System.out.println("Introdueix el responsable de la demostració: ");
        String responsable = scanner.nextLine();
    
        System.out.println("Introdueix la data de disseny de la demostració (dd/MM/yyyy): ");
        String dataStr = scanner.nextLine();
        Date dataDisseny = null;
        try {
            dataDisseny = sdf.parse(dataStr);
        } catch (Exception e) {
            System.out.println("Data no vàlida.");
            return;
        }
    
        System.out.println("La demostració està activa? (true/false): ");
        boolean activa = Boolean.parseBoolean(scanner.nextLine());
    
        System.out.println("Introdueix el cost dels materials: ");
        float costMaterials = Float.parseFloat(scanner.nextLine());
    
        // Crear la nova demostració
        demostracio novaDemostracio = new demostracio(associacio, titol, responsable, dataDisseny, activa, costMaterials);
    
        // Afegeix la nova demostració a la llista
        for (int i = 0; i < demostracions.length; i++) {
            if (demostracions[i] == null) {  // Busquem el primer lloc buit
                demostracions[i] = novaDemostracio;
                System.out.println("Demostració afegida correctament!");
                return;
            }
        }
        System.out.println("No es pot afegir més demostracions, l'array està ple.");
    }

    /**
     * Mostra les dades de les demostracions no actives i calcula el cost econòmic total.
     * @param demostracions, l'array o llista de demostracions a consultar.
     * @return el cost econòmic total de les demostracions no actives.
     */
    public demostracio[] llistaDemostracions() {
        demostracio[] llistaDemostracions = new demostracio[100];
        accio[] aux = null;
        int index = 0;
        for (int i = 0; i < this.count; i++) {
            aux = associacions[i].llistaAccions();
            for ( int j = 0; j < aux.length; j++ ){
                if ( aux[j] instanceof demostracio ) {
                    llistaDemostracions[index] = (demostracio) aux[j];
                    index++;
                }
            }
        }

        demostracio[] llistaFinal = new demostracio[index];
        for ( int i = 0; i < index; i++ ) {
            llistaFinal[i] = llistaDemostracions[i];
        }
        return llistaFinal;
    }

    public demostracio[] llistaDemostracionsNoActives() {
        demostracio[] llistaDemostracions = new demostracio[100];
        accio[] aux = null;
        int index = 0;
        for (int i = 0; i < this.count; i++) {
            aux = associacions[i].llistaAccions();
            for ( int j = 0; j < aux.length; j++ ){
                if ( aux[j] instanceof demostracio ) {
                    llistaDemostracions[index] = (demostracio) aux[j];
                    index++;
                }
            }
        }
        demostracio[] llistaFinal = new demostracio[index];
        for ( int i = 0; i < index; i++ ) {
            if (!llistaDemostracions[i].esActiva()) {
                llistaFinal[i] = llistaDemostracions[i];
            }
        }
        return llistaFinal;
    }


    public demostracio[] llistaDemostracionsActives() {
        demostracio[] llistaDemostracions = new demostracio[100];
        accio[] aux = null;
        int index = 0;
        for (int i = 0; i < this.count; i++) {
            aux = associacions[i].llistaAccions();
            for ( int j = 0; j < aux.length; j++ ){
                if ( aux[j] instanceof demostracio ) {
                    llistaDemostracions[index] = (demostracio) aux[j];
                    index++;
                }
            }
        }
        demostracio[] llistaFinal = new demostracio[index];
        for ( int i = 0; i < index; i++ ) {
            if (llistaDemostracions[i].esActiva()) {
                llistaFinal[i] = llistaDemostracions[i];
            }
        }
        return llistaFinal;
    }

    public membre personaMesActiva() {
        membre guanyador = associacions[0].getMembre(0);
        membre[] aux = null;
        for ( int i = 0; i < this.count; i++ ){
            aux = associacions[i].getLlistaMembres();
            for ( int j = 0; j < aux.length; j++) {
                if ( aux[j].getAssociacions() > guanyador.getAssociacions() ) {
                    guanyador = aux[j];
                }
            }
        }
        return guanyador;
    }

    public float calcularPerduaTotal(demostracio[] llista) {
        float perduaTotal=0;
        for ( int i = 0; i < llista.length; i++){
            perduaTotal = perduaTotal + llista[i].getCostMaterials();
        }
        return perduaTotal;
    }

    /**
     * Dona de baixa les demostracions que no estan actives i que es van dissenyar abans d'una certa data.
     * @param demostracions, l'array o llista de demostracions a consultar.
     * @param dataLimit, la data límit abans de la qual es donaran de baixa les demostracions.
     */
    public void donarDeBaixaDemostracions(demostracio[] demostracions, Date dataLimit) {
        for (int i = 0; i < demostracions.length; i++) {
            if ( !demostracions[i].esActiva() || dataLimit.after(demostracions[i].getDataDisseny())){
                demostracions[i] = null;
            }
        }
        
    }

    /**
     * comprova si un membre ja esta registrat en alguna associacio
     * per mitjà del seu alias
     * @param nomMembre
     * @return
     */
    public boolean existeixMembre(String nomMembre ) {
        boolean existeix = false;
        int i = 0;
        int j = 0;
        while ( !existeix && i < this.count ) {
            membre[] llista = associacions[i].getLlistaMembres();
            while ( !existeix && j < associacions[i].getTotalMembres() ) {
                if ( llista[j].equals(nomMembre) ) {
                    existeix = true;
                }
                j++;
            }
            j=0;
            i++;
        }
        return existeix;
    }

    /**
     * Busca el membre d'un nom en concret, si no el troba
     * retorna null
     * @param nomMembre
     * @return
     */
    public membre buscarMembre( String nomMembre ) {
        boolean trobat = false;
        int i = 0;
        int j = 0;
        membre membreTrobat = null;
        while ( !trobat && i < this.count ) {
            membre[] llista = associacions[i].getLlistaMembres();
            while ( !trobat && j < llista.length ) {
                if ( llista[j].getAlias().equals(nomMembre) ) {
                    trobat = true;
                    membreTrobat = llista[j];
                }
                j++;
            }
            j=0;
            i++;
        }
        return membreTrobat;
    }

    public void mostrarSiglasTitulacions() {
        System.out.println("1- EB");
        System.out.println("2- ESST");
        System.out.println("3- EEIA");
        System.out.println("4- EE");
        System.out.println("5- EI");
        System.out.println("6- EMF");
        System.out.println("7- TDAWM");
    }
    public SiglasTitulacio seleccionarEnsenyament(int eleccio ) {
        SiglasTitulacio resultat = null;

        switch (eleccio) {
            case 1:
                resultat = SiglasTitulacio.EB;
                break;
            case 2:
                resultat = SiglasTitulacio.ESST;
                break;
            case 3:
                resultat = SiglasTitulacio.EEIA;
                break;
            case 4:
                resultat = SiglasTitulacio.EE;
                break;
            case 5:
                resultat = SiglasTitulacio.EI;
                break;
            case 6:
                resultat = SiglasTitulacio.EMF;
                break;
            case 7:
                resultat = SiglasTitulacio.TDAWM;
                break;
            default:
                System.out.println("Opció no vàlida. Si us plau, selecciona un número entre 1 i 7.");
                break;
        }

        return resultat;
    }


    /**
     * Comprova que la xerrada introduida existeix
     * @param nom
     * @return
     */
    public boolean existeixXerrada(String nom) {
        boolean trobada = false;
        accio[] aux = null;
        for(int i = 0; i < this.count; i++){
            aux =  associacions[i].llistaAccions();
            for (int j = 0; j < aux.length; j++){
                if ( aux[j] instanceof Xerrada && aux[j].getNom().equals(nom) ){
                    trobada=true;
                }
            }
        }
        return trobada;
    }


    /**
     * Busca la xerrada pel nom
     * @param nom
     * @return
     */
    public Xerrada buscarXerrada(String nom) {
        Xerrada trobada =null;
        accio[] aux = null;
        for(int i = 0; i < this.count; i++){
            aux =  associacions[i].llistaAccions();
            for (int j = 0; j < aux.length; j++){
                if ( aux[j] instanceof Xerrada && aux[j].getNom().equals(nom) ){
                    trobada=(Xerrada) aux[j];
                }
            }
        }
        return trobada;
    }

    public Xerrada[] llistaXerrades() {
        Xerrada[] llistaXerrades = new Xerrada[100];
        accio[] aux = null;
        int index = 0;
        for (int i = 0; i < this.count; i++) {
            aux = associacions[i].llistaAccions();
            for ( int j = 0; j < aux.length; j++ ){
                if ( aux[j] instanceof Xerrada ) {
                    llistaXerrades[index] = (Xerrada) aux[j];
                    index++;
                }
            }
        }
        Xerrada[] llistaFinal = new Xerrada[index];
        for ( int i = 0; i < index; i++ ) {
            llistaFinal[i] = llistaXerrades[i];
        }
        return llistaFinal;
    }

    public Xerrada[] llistaXerradesMembre(Xerrada[] llistaXerrades, membre triat) throws llistaMembresBuida {
        Xerrada[] resultat=new Xerrada[llistaXerrades.length];
        int index = 0;
        for (Xerrada xerrada : llistaXerrades) {
            for (int j = 0; j < xerrada.getTotalMembres(); j++) {
                try {
                    if (xerrada.getMembre(j).equals(triat)) {
                        resultat[index] = xerrada;
                        index++;
                    }
                } catch (llistaMembresBuida e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        return resultat;
    } 

    public void mostrarDemostracions(JTable taulaDemostracions) {
        DefaultTableModel model = (DefaultTableModel) taulaDemostracions.getModel();
        model.setRowCount(0); // Clear existing rows
    
        demostracio[] demostracionsActives = this.llistaDemostracionsActives();
        for (demostracio demo : demostracionsActives) {
            model.addRow(new Object[]{demo.getNom(), demo.getDataDisseny(), demo.getRepeticions()});
        }
    }
}

