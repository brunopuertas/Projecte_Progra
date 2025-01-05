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
}

