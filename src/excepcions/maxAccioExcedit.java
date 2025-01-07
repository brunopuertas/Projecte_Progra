package excepcions;
import dades.*;

public class maxAccioExcedit extends Exception {
    /**
     * Constructor de la excepció maxAccioExcedit
     * Salta quan la llista d'accions està plena
     * @param accio
     */
    public maxAccioExcedit(accio accio) {
        super ("No es pot afegir l'acció " + accio.getNom() + " perque la llista està plena.");
    }
}