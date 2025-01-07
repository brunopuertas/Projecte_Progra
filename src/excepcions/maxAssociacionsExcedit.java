package excepcions;
import dades.tipusMembre.*;

public class maxAssociacionsExcedit extends Exception {
  
  public maxAssociacionsExcedit(membre membre) {
    super("no es pot afegir el membre " + membre.getAlias() + " perque excedeix el nombre d'associacions.");
  }
}

