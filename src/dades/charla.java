package dades;
import dades.tipusMembre.*;
import java.util.Date;

// Classe filla que hereta de accio
public class Xerrada extends accio {
    // Atributs addicionals de la classe filla
    private Date data;
    private final membre[] membres;
    private int assistencies;
    private float valoracioMitjana;
    private int totalMembres;

    // Constructor de la classe filla
    public Xerrada(String associacio, String titol, String responsable, Date data, int maxMembres, int assistencies, float valoracioMitjana) {
        super(associacio, titol, responsable);
        this.data = data;
        this.membres = new membre[maxMembres];
        this.assistencies = assistencies;
        this.valoracioMitjana = valoracioMitjana;
        this.totalMembres = 0;
    }
    
    // Getters i Setters
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public membre getMembre(int index) {
        if (index >= 0 && index < membres.length && membres[index] != null) {
            return membres[index].copia();
        }
        return null;
    }

    public int getAssistencies() {
        return assistencies;
    }

    public void setAssistencies(int assistencies) {
        this.assistencies = assistencies;
    }

    public float getValoracioMitjana() {
        return valoracioMitjana;
    }

    public void setValoracioMitjana(float valoracioMitjana) {
        this.valoracioMitjana = valoracioMitjana;
    }

    // Mètodes de classe
    public void afegirMembre(membre membre) {
        if (totalMembres < membres.length) {
            membres[totalMembres++] = membre.copia();
        } else {
            System.out.println("No es poden afegir més de 3 membres a la xerrada.");
        }
    }

    public void valorarXerrada(float valoracio) {
        if (valoracio >= 0 && valoracio <= 10) {
            valoracioMitjana = (valoracioMitjana * assistencies + valoracio) / (assistencies + 1);
            assistencies++;
        } else {
            System.out.println("La valoració ha d'estar entre 0 i 10.");
        }
    }

    public boolean esXerradaPopular(int limitAssistents) {
        return assistencies > limitAssistents;
    }

    public membre obtenirMembreAmbMesParticipacio() {
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
        return super.toString() + "\nXerrada el " + data + ", Assistències: " + assistencies + ", Valoració Mitjana: " + valoracioMitjana;
    }
}
