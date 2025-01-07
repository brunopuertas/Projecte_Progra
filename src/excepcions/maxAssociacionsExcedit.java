package excepcions;
import dades.tipusMembre.*;

public class maxAssociacionsExcedit extends Exception {
  
  /**
   * Constructor de la excepció maxAssociacionsExcedit
   * Salta quan el membre excedeix el nombre d'associacions
   * @param membre
   */
  public maxAssociacionsExcedit(membre membre) {
    super("no es pot afegir el membre " + membre.getAlias() + " perque excedeix el nombre d'associacions.");
  }
}

