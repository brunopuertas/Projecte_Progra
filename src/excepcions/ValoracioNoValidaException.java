package excepcions;

public class ValoracioNoValidaException extends Exception {
    public ValoracioNoValidaException(float valoracio) {
        super("El valor " + valoracio + " esta fora de rang. Ha de ser un valor entre el 0 i el 10.");
    }
}