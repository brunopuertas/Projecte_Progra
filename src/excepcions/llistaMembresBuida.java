package excepcions;

public class llistaMembresBuida extends Exception {
  /**
   * Constructor de la excepció llistaMembresBuida
   * Salta quan la llista de membres està buida
   */
  public llistaMembresBuida() {
    super("La llista de membres està buida.");
  }
    
}
