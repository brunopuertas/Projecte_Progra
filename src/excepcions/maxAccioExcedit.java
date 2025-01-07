package excepcions;
import dades.*;

public class maxAccioExcedit extends Exception {
    public maxAccioExcedit(accio accio) {
        super ("No es pot afegir l'acció " + accio.getNom() + " perque la llista està plena.");
    }
}