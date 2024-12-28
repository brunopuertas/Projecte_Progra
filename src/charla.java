import java.util.Date;
import tipusMembre.membre;

public class charla {
    // Atributos
    private Date fecha;
    private final membre[] membres;
    private int asistencias;
    private float mediaValoracion;
    private int totalMiembros;

    // Constructor
    public charla(Date fecha, int maxMiembros, int asistencias, float mediaValoracion) {
        this.fecha = fecha;
        this.membres = new membre[maxMiembros];
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

    public membre getMembre(int index) {
            if (index >= 0 && index < membres.length && membres[index] != null) {
                return membres[index].copia();
            }
            return null;
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
    public void agregarMiembro(membre membre) {
        if (totalMiembros < membres.length) {
            membres[totalMiembros++] = membre.copia();
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

    public membre obtenerMiembroConMayorParticipacion() {
        membre max = null;
        for (membre m : membres) {
            if (m != null && (max == null || m.getAssociacions() > max.getAssociacions())) {
                max = m;
            }
        }
        return max;
    }

    @Override
    public String toString() {
        return "Charla el " + fecha + ", Asistencias: " + asistencias + ", Valoración Media: " + mediaValoracion;
    }
}
