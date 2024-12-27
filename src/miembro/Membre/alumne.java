public class alumne extends membre {
    private String ensenyament; // Ensenyament amb les seves sigles
    private int dataMatricula; 
    private boolean graduat;


    /**
     * Constructor inicialitzat amb parametres d'entrada
     * Encarregat de crearla classe alumnes amb els parametres d'entrada
     * @param alias
     * @param correuElectronic
     * @param associacions
     * @param ensenyament
     * @param dataMatricula
     * @param graduat
     */
    public alumne (String alias, String correuElectronic, int associacions, String ensenyament, int dataMatricula, boolean graduat) {
        super(alias, correuElectronic, associacions);
        this.ensenyament = ensenyament;
        this.dataMatricula = dataMatricula;
        this.graduat = graduat;
    }

    /**
     * Metode que retorna l'ensenyament de l'alumne
     * @return ensenyament
     */
    public String getEnsenyament () {
        return ensenyament;
    }

    /**
     * Metode que retorna la data de matricula de l'alumne
     * @return dataMatricula
     */
    public int getDataMatricula () {
        return dataMatricula;
    }

    /**
     * Metode que retorna si l'alumne esta graduat
     * @return graduat
     */
    public boolean getGraduat () {
        return graduat;
    }

    /**
     * Metode que modifica l'ensenyament de l'alumne
     * @param ensenyament
     */
    public void setEnsenyament (String ensenyament) {
        this.ensenyament = ensenyament;
    }

    /**
     * Metode que modifica la data de matricula de l'alumne
     * @param dataMatricula
     */
    public void setDataMatricula (int dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    /**
     * Metode que modifica si l'alumne esta graduat
     * @param graduat
     */
    public void setGraduat (boolean graduat) {
        this.graduat = graduat;
    }

    /**
     * Metode que retorna un string amb les dades de l'alumne per poder visualitzar-les
     * @return string
     */
    @Override
    public String toString() {
        return "Alumne:\n" + super.toString() + ", Ensenyament: " + ensenyament + ", Data matricula: " + dataMatricula;
    }

    /**
     * Metode que retorna una copia de l'alumne
     * @return alumne
     */
    public alumne Copia () {
        return new alumne(this.getAlias(), this.getCorreuElectronic(), this.getAssociacions(), this.ensenyament, this.dataMatricula, this.graduat);
    }

}
