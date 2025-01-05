package utilitats;

import dades.*;
import dades.tipusMembre.*;

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
    public membre[] llistaMembresActius (){
        membre[] membresActius = new membre[100]; // assuming a maximum of 100 active members
        int index = 0;
        for (int i = 0; i < this.count; i++) {
            associacio a = this.associacions[i];
            membre[] membres = a.llistaMembresFiltrat(false, false);
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
     * Retorna una llista amb tots els membres actius de totes les associacions, filtrats per tipus.
     * @param professor Si es volen només professors.
     * @param alumne    Si es volen només alumnes.
     * @return Una llista amb tots els membres actius.
     */
    public membre[] llistaMembresActiusFiltre(boolean professor, boolean alumne) {
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
     * Retorna la xerrada amb la valoració mitjana més alta.
     * En cas d'empat en la valoració mitjana, selecciona la xerrada amb més assistents.
     * @param xerrades, és una llista/taula de xerrades que compararem.
     * @return la xerrada amb la valoració mitjana més alta.
     */
    public static Xerrada millorXerrada(Xerrada[] xerrades) {
        Xerrada millor = null;
    
        for (Xerrada x : xerrades) {
            if (millor == null || x.getValoracioMitjana() > millor.getValoracioMitjana()) {
                millor = x;
            } else if (x.getValoracioMitjana() == millor.getValoracioMitjana()) {
                if (x.getAssistencies() > millor.getAssistencies()) {
                    millor = x;
                }
            }
        }
        return millor;
    }

        /**
     * Mostra totes les xerrades que ha fet una persona concreta.
     * @param xerrades, és una llista/taula de xerrades a consultar.
     * @param persona, el nom de la persona que ha fet les xerrades que busquem.
     */
    public static void mostrarXerradesPerPersona(Xerrada[] xerrades, String persona) {
        for (Xerrada x : xerrades) {
            if (x.getResponsable().equalsIgnoreCase(persona)) {
                System.out.println(x);
            }
        }
    }

    /**
     * Mostra les xerrades que tenen més assistents que el límit especificat.
     * @param xerrades, és una llista/taula de xerrades a consultar.
     * @param limit, el nombre mínim d'assistents per filtrar les xerrades.
     */
    public static void mostrarXerradesAmbMésAssistents(Xerrada[] xerrades, int limit) {
        for (Xerrada x : xerrades) {
            if (x.getAssistencies() > limit) {
                System.out.println(x);
            }
        }
    }

    /**
     * Mostra les xerrades que es duen a terme dins d'un rang de dates especificat.
     * @param xerrades, és una llista/taula de xerrades a consultar.
     * @param dataInici, la data d'inici de la franja de dates.
     * @param dataFi, la data final de la franja de dates.
     */
    public static void mostrarXerradesEnRangDeDates(Xerrada[] xerrades, Date dataInici, Date dataFi) {
        for (Xerrada x : xerrades) {
            if (x.getData().after(dataInici) && x.getData().before(dataFi)) {
                System.out.println(x);
            }
        }
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
    public void valorarXerradaPerAssistent(float valoracio) {
        // Comprovem que la valoració està dins del rang vàlid (0-10)
        if (valoracio >= 0 && valoracio <= 10) {
            // Recalculem la nova valoració mitjana
            valoracioMitjana = (valoracioMitjana * assistencies + valoracio) / (assistencies + 1);
            assistencies++;  // Augmentem el nombre d'assistències
    
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
    public static float mostrarDemostracionsNoActives(demostracio[] demostracions) {
        float costTotal = 0;
    
        System.out.println("Demostracions no actives:");
    
        boolean trobada = false;
    
        for (demostracio d : demostracions) {
            if (d != null && !d.esActiva()) {
                System.out.println(d);
                costTotal += d.getCostMaterials();
                trobada = true;
            }
        }
    
        if (!trobada) {
            System.out.println("No hi ha demostracions no actives.");
        }
    
        System.out.println("Cost total de les demostracions no actives: " + costTotal);
        return costTotal;
    }
    /**
     * Dona de baixa les demostracions que no estan actives i que es van dissenyar abans d'una certa data.
     * @param demostracions, l'array o llista de demostracions a consultar.
     * @param dataLimit, la data límit abans de la qual es donaran de baixa les demostracions.
     */
    public static void donarDeBaixaDemostracions(demostracio[] demostracions, Date dataLimit) {
        System.out.println("Demostracions donades de baixa:");
    
        boolean trobada = false;
    
        for (int i = 0; i < demostracions.length; i++) {
            if (demostracions[i] != null && !demostracions[i].esActiva() && demostracions[i].getDataDisseny().before(dataLimit)) {
                System.out.println(demostracions[i]);
                demostracions[i] = null;  // Eliminar la demostració donada de baixa
                trobada = true;
            }
        }
    
        if (!trobada) {
            System.out.println("No s'ha trobat cap demostració que compleixi els criteris.");
        }
    }
}

