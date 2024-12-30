import tipusMembre.membre;
import tipusMembre.professor;
import tipusMembre.alumne;

public class Asociacion {
    // Atributos
    private final String nombre;
    private final String correo;
    private final membre[] listaMembres;
    private Accion[] listaAccions;
    private int totalMembres;
    private int totalAcciones;

    // Constructor
    public asociacion(String nombre, String correo, int maxMiembros, int maxAcciones) {
        this.nombre = nombre;
        this.correo = correo;
        this.listaMembres = new membre[maxMiembros];
        this.listaAcciones = new Accion[maxAcciones];
        this.totalMembres = 0;
        this.totalAcciones = 0;
    }

    // Método para obtener el nombre
    public String getNombre() {
        return nombre;
    }

    // Método para obtener el correo
    public String getCorreo() {
        return correo;
    }

    // Método para listar miembros con filtro
    public membre[] llistaMembresFiltrat(boolean soloAlumnos, boolean soloProfesores) {
        membre[] resultado = new membre[totalMembres];
        int contador = 0;
        for (int i = 0; i < totalMembres; i++) {
            membre m = listaMembres[i];
            if (soloAlumnos && m instanceof alumne) {
                resultado[contador++] = m;
            } else if (soloProfesores && m instanceof professor) {
                resultado[contador++] = m;
            } else if (!soloAlumnos && !soloProfesores) {
                resultado[contador++] = m;
            }
        }
        return Arrays.copyOf(resultado, contador);
    }

    // Método para listar acciones
    public Accion[] llistaAccions() {
        return Arrays.copyOf(listaAcciones, totalAcciones);
    }
}
