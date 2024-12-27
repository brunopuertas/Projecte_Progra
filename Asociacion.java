class Asociacion {
    // Atributos
    private String nombre;
    private String correo;
    private Miembro[] listaMiembros;
    private Accion[] listaAcciones;
    private int totalMiembros;
    private int totalAcciones;

    // Constructor
    public Asociacion(String nombre, String correo, int maxMiembros, int maxAcciones) {
        this.nombre = nombre;
        this.correo = correo;
        this.listaMiembros = new Miembro[maxMiembros];
        this.listaAcciones = new Accion[maxAcciones];
        this.totalMiembros = 0;
        this.totalAcciones = 0;
    }

    // Método para listar miembros con filtro
    public Miembro[] llistaMembresFiltrat(boolean soloAlumnos, boolean soloProfesores) {
        Miembro[] resultado = new Miembro[totalMiembros];
        int contador = 0;
        for (int i = 0; i < totalMiembros; i++) {
            Miembro m = listaMiembros[i];
            if (soloAlumnos && m instanceof Alumno) {
                resultado[contador++] = m;
            } else if (soloProfesores && m instanceof Profesor) {
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
