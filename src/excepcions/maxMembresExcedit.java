package excepcions;
import dades.tipusMembre.*;

public class maxMembresExcedit extends Exception {

    public maxMembresExcedit(membre membre) {
        super("No es pot afegir el membre " + membre.getAlias() + " perque es supera el màxim de membres.");
    }
}


