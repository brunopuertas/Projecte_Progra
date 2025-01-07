package excepcions;
import dades.tipusMembre.*;

public class maxMembresExcedit extends Exception {

    /**
     * Constructor de la excepció maxMembresExcedit
     * Salta quan el membre excedeix el nombre de membres de l'associació
     * @param membre
     */
    public maxMembresExcedit(membre membre) {
        super("No es pot afegir el membre " + membre.getAlias() + " perque es supera el màxim de membres.");
    }
}


