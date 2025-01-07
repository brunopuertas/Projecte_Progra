package dades;
import dades.tipusMembre.*;
import excepcions.*;
import java.util.Date;

public class Xerrada extends accio {
    private Date data;
    private int assistencies;
    private float valoracioMitjana;
    private int totalMembres;
    private final membre[] membres;

    /**
     * Constructor inicialitzat amb parametres d'entrada
     * Encarregat de crear la classe Xerrada amb els parametres d'entrada
     * @param associacio
     * @param titol
     * @param responsable
     * @param data
     * @param maxMembres
     * @param assistencies
     * @param valoracioMitjana
     */
    public Xerrada(String associacio, String titol, String responsable, Date data, int maxMembres, int assistencies, float valoracioMitjana) {
        super(associacio, titol, responsable);
        this.data = data;
        this.membres = new membre[maxMembres];  
        this.assistencies = assistencies;
        this.valoracioMitjana = valoracioMitjana;
        this.totalMembres = 0;
    }
    
   /**
    * Mètode que retorna la data de la xerrada
    * @return data
    */
    public Date getData() {
        return data;
    }

    /**
     * Mètode que modifica la data de la xerrada
     * @param data 
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Mètode que retorna un membre de la xerrada
     * @param index
     * @return membres[index]
     */
    public membre getMembre(int index) throws llistaMembresBuida {
        if (index >= 0 && index < membres.length && membres[index] != null) {
            return membres[index].copia();
        } else {
            throw new llistaMembresBuida(); // Excepcion?
        }
    }

    /**
     * Mètode que retorna les assistències de la xerrada
     * @return assistencies
     */
    public int getAssistencies() {
        return assistencies;
    }

    /**
     * Mètode que retorna el total de membres de la xerrada
     * @return totalMembres
     */
    public int getTotalMembres() {
        return totalMembres;
    }

    /**
     * Mètode que modifica les assistències de la xerrada
     * @param assistencies
     */
    public void setAssistencies(int assistencies) {
        this.assistencies = assistencies;
    }

    /**
     * Mètode que retorna la valoració mitjana de la xerrada
     * @return valoracioMitjana
     */
    public float getValoracioMitjana() {
        return valoracioMitjana;
    }

    /**
     * Mètode que modifica la valoració mitjana de la xerrada
     * @param valoracioMitjana 
     */
    public void setValoracioMitjana(float valoracioMitjana) {
        this.valoracioMitjana = valoracioMitjana;
    }

    /**
     * Mètode per afegir un membre a la xerrada
     * Implementa l'excepció maxMembresExcedit
     * @param membre
     * @throws maxMembresExcedit
     */
    public void afegirMembre(membre membre) throws maxMembresExcedit {
        if (totalMembres < membres.length) {
            membres[totalMembres++] = membre.copia();
        } else {
            throw new maxMembresExcedit(membre);
        }
    }

    /**
     * Mètode per valorar una xerrada
     * Implementa l'excepció valoracioNoValida
     * @param valoracio
     * @throws valoracioNoValida
     */
    public void valorarXerrada(float valoracio) throws valoracioNoValida {
        if (valoracio >= 0 && valoracio <= 10) {
            valoracioMitjana = (valoracioMitjana * assistencies + valoracio) / (assistencies + 1);
            assistencies++;
        } else {
            throw new valoracioNoValida(valoracio);
        }
    }

    /**
     * Mètode per saber si una xerrada és popular
     * @param limitAssistents
     * @return
     */
    public boolean esXerradaPopular(int limitAssistents) {
        return assistencies > limitAssistents;
    }

    /**
     * Mètode per obtenir el membre amb més participació
     * Implementa l'excepció llistaMembresBuida
     * @return
     * @throws llistaMembresBuida
     */
    public membre obtenirMembreAmbMesParticipacio() throws llistaMembresBuida {
        membre max = null;
        for (membre m : membres) {
            if (m != null && (max == null || m.getAssociacions() > max.getAssociacions())) {
                max = m;
            } else {
                throw new llistaMembresBuida();
            }
        } 
        return max;
    }

     /**
     * Metode que retorna un string amb les dades de la xerrada per poder visualitzar-les
     * @return string
     */
    @Override
    public String toString() {
        return super.toString() + "\nXerrada el " + data + ", Assistències: " + assistencies + ", Valoració Mitjana: " + valoracioMitjana;
    }
}
