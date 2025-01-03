package dades.tipusMembre;

public class membre {
    protected String alias; 
    protected String correuElectronic; 
    protected int associacions;

       /**
     * Constructor inicialitzat amb parametres d'entrada
     * Encarregat de crearla classe alumnes amb els parametres d'entrada
     * @param alias
     * @param correuElectronic
     * @param associacions
     */
    public membre (String alias, String correuElectronic, int associacions){ 
        this.alias = alias;
        this.correuElectronic = correuElectronic;
        this.associacions = associacions;
    }

    /**
     * Metode que retorna l'alias del membre
     * @return alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Metode que retorna el correu electronic del membre
     * @return correuElectronic
     */
    public String getCorreuElectronic() {
        return correuElectronic;
    }

    /**
     * Metode que retorna el numero d'associacions del membre
     * @return associacions
     */
    public int getAssociacions(){
        return associacions;
    }

    /**
     * Metode que modifica l'alias del membre
     * @param alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Metode que modifica el correu electronic del membre
     * @param correuElectronic
     */
    public void setCorreuElectronic(String correuElectronic) {
        this.correuElectronic = correuElectronic;
    }

    /**
     * Metode que modifica el numero d'associacions del membre
     * @param associacions
     */
    public void setAssociacions(int associacions) {
        this.associacions = associacions;
    }

    /**
     * Metode que retorna un string amb les dades del membre
     * @return string
     */
    @Override
    public String toString() {
        return "Alias: " + alias + ", Correu electronic: " + correuElectronic + ", Num actiu associacions: " + associacions;
    }

    /**
     * Metode que retorna un objecte membre amb les dades del membre
     * @return membre
     */
    public membre copia () {
        return new membre(this.alias, this.correuElectronic, this.associacions);
    }
}