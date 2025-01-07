package excepcions;

public class valoracioNoValida extends Exception {

    /**
     * Constructor de la excepció ValoracioNoValidaException
     * Salta quan la valoració no és vàlida (fora de rang 0-10)
     * @param valoracio
     */
    public valoracioNoValida(float valoracio) {
        super("El valor " + valoracio + " esta fora de rang. Ha de ser un valor entre el 0 i el 10.");
    }
}