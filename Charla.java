import java.util.*;

public class Charla {
    // Atributos
    private Date fecha;
    private Miembro[] miembros;
    private int asistencias;
    private float mediaValoracion;
    private int totalMiembros;

    // Constructor
    public Charla(Date fecha, int maxMiembros, int asistencias, float mediaValoracion) {
        this.fecha = fecha;
        this.miembros = new Miembro[maxMiembros];
        this.asistencias = asistencias;
        this.mediaValoracion = mediaValoracion;
        this.totalMiembros = 0;
    }
    
    // Getters y Setters
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Miembro[] getMiembros() {
        return miembros;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public float getMediaValoracion() {
        return mediaValoracion;
    }

    public void setMediaValoracion(float mediaValoracion) {
        this.mediaValoracion = mediaValoracion;
    }

    // Métodos de clase
    public void agregarMiembro(Miembro miembro) {
        if (totalMiembros < miembros.length) {
            miembros[totalMiembros++] = miembro;
        } else {
            System.out.println("No se pueden agregar más de 3 miembros a la charla.");
        }
    }

    public void valorarCharla(float valoracion) {
        if (valoracion >= 0 && valoracion <= 10) {
            mediaValoracion = (mediaValoracion * asistencias + valoracion) / (asistencias + 1);
            asistencias++;
        } else {
            System.out.println("La valoración debe estar entre 0 y 10.");
        }
    }

    public boolean esCharlaPopular(int limiteAsistentes) {
        return asistencias > limiteAsistentes;
    }

    public Miembro obtenerMiembroConMayorParticipacion() {
        Miembro max = null;
        for (Miembro m : miembros) {
            if (m != null && (max == null || m.getNumeroAsociaciones() > max.getNumeroAsociaciones())) {
                max = m;
            }
        }
        return max;
    }

    public String toString() {
        return "Charla el " + fecha + ", Asistencias: " + asistencias + ", Valoración Media: " + mediaValoracion;
    }
}
