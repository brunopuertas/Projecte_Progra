package dades.tipusMembre;

public class professor extends membre {
    private String nomDepartament;
    private int numDespatx;

    /**
     * Constructor inicialitzat amb parametres d'entrada
     * Encarregat de crear la classe professor amb els parametres d'entrada
     * @param alias
     * @param correuElectronic
     * @param associacions
     * @param nomDepartament
     * @param numDespatx
     */
    public professor(String alias, String correuElectronic, int associacions, String nomDepartament, int numDespatx) {
        super(alias, correuElectronic, associacions);
        this.nomDepartament = nomDepartament;
        this.numDespatx = numDespatx;
    }

    /**
     * Metode que retorna el nom del departament del professor
     * @return nomDepartament
     */
    public String getNomDepartament() {
        return nomDepartament;
    }

    /**
     * Metode que retorna el numero de despatx del professor
     * @return numDespatx
     */
    public int numDespatx() {
        return numDespatx;
    }

    /**
     * Metode que modifica el nom del departament del professor
     * @param nomDepartament
     */
    public void setnomDepartament(String nomDepartament) {
        this.nomDepartament = nomDepartament;
    }

    /**
     * Metode que modifica el numero de despatx del professor
     * @param numDespatx
     */
    public void setnumDespatx(int numDespatx) {
        this.numDespatx = numDespatx;
    }

    /**
     * Metode que retorna un string amb les dades del professor
     * @return string
     */
    @Override
    public String toString() {
        return "Professor:\n" + super.toString() + ", Nom departament: " + nomDepartament + ", Num despatx: " + numDespatx;
    }

    /**
     * Metode que copia les dades del professor
     * @return professor
     */
    public professor Copia() {
        return new professor(this.getAlias(), this.getCorreuElectronic(), this.getAssociacions(), this.nomDepartament, this.numDespatx);
    }
}
