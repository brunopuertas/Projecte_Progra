import java.util.Date;

public class Demostracio extends Accio{
    private Date dataDisseny;
    private boolean activa;
    private int repeticions;
    private float costMaterials;

    // Constructor
    public Demostracio(String associacio, String titol, String responsable, Date dataDisseny, boolean activa, float costMaterials) {
        super(associacio, titol, responsable);
        this.dataDisseny = dataDisseny;
        this.activa = activa;
        this.repeticions = 0;
        this.costMaterials = costMaterials;
    }

    // Getters i Setters
    public boolean esActiva() {
        return activa;
    }

    public Date getDataDisseny (){
        return dataDisseny;
    }

    public int getRepeticions (){
        return repeticions;
    } 

    public float getCostMaterials (){
        return costMaterials;
    }
    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public void incrementarRepeticions() {
        this.repeticions++;
    }

    public String toString() {
        return super.toString() + "\n" + 
               "Data de disseny: " + dataDisseny + "\n" +
               "Activa: " + activa + "\n" +
               "Repeticions: " + repeticions + "\n" +
               "Cost dels materials: " + costMaterials;

    }

}
