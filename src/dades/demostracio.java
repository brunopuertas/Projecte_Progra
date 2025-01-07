package dades;
import java.util.Date;

public class demostracio extends accio{
    private Date dataDisseny;
    private boolean activa;
    private int repeticions;
    private float costMaterials;

    /**
     * Constructor inicialitzat amb parametres d'entrada
     * Encarregat de crear la classe demostracio amb els parametres d'entrada
     * @param associacio
     * @param titol
     * @param responsable
     * @param dataDisseny
     * @param activa
     * @param costMaterials
     */
    public demostracio (String associacio, String titol, String responsable, Date dataDisseny, boolean activa, float costMaterials) {
        super(associacio, titol, responsable);
        this.dataDisseny = dataDisseny;
        this.activa = activa;
        this.repeticions = 0;
        this.costMaterials = costMaterials;
    }

    /**
     * Metode per saber si una demostracio es activa
     * @return activa
     */
    public boolean esActiva() {
        return activa;
    }

    /**
     * Metode per obtenir la data de disseny
     * @return dataDisseny
     */
    public Date getDataDisseny (){
        return dataDisseny;
    }

    /**
     * Metode per obtenir les repeticions
     * @return repeticions
     */
    public int getRepeticions (){
        return repeticions;
    } 

    /**
     * Metode per obtenir el cost dels materials
     * @return costMaterials
     */
    public float getCostMaterials (){
        return costMaterials;
    }

    /**
     * Metode per modificar l'estat de la demotració
     * @param activa
     */
    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    /**
     * Metode per incrementar les repeticions
     */
    public void incrementarRepeticions() {
        this.repeticions++;
    }

     /**
     * Metode que retorna un string amb les dades de la demostracio per poder visualitzar-les
     * @return string
     */
    @Override
    public String toString() {
        return super.toString() + "\n" + 
               "Data de disseny: " + dataDisseny + "\n" +
               "Activa: " + activa + "\n" +
               "Repeticions: " + repeticions + "\n" +
               "Cost dels materials: " + costMaterials;

    }

}
