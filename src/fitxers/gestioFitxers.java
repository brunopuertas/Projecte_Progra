package fitxers;

import dades.*;
import dades.tipusMembre.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;
import utilitats.associacions;

public class gestioFitxers {

    private static associacions llegirFitxer(String nomFitxer) throws IOException {
        associacions aux = new associacions(100);

        String linea;
        String nom = "";
        String correu = "";
        int numLinia = 0;

        membre[] llistaMembres = new membre[100];
        int totalMembres = 0;
        // Variables membre
        String alias;String correuElectronic;int associacions;
        // Variables professor
        String nomDepartament;int numDespatx;
        // Variables alumne
        String ensenyament;int dataMatricula;boolean graduat;

        accio[] llistaAccions = new accio[100];
        int totalAccions = 0;
        // Variables accio
        String associacio;String titol;String responsable;String codi_Accio;
        Date data;
        // Variables demostracio
        boolean activa; int repeticions=0;float costMaterials;
        // Variables Xerrada
        int assistencies=0;float valoracioMitjana=0;int totalMembresXerrada=0;
        membre[] membresXerrada = new membre[100];int QmembresXerrada = 0;

        boolean processantMembres = false;boolean processantAccions = false;

        BufferedReader lector = new BufferedReader(new FileReader(nomFitxer));

        while ((linea = lector.readLine()) != null) {
            numLinia++;
            linea = lector.readLine();
            StringTokenizer tokens = new StringTokenizer(linea, ";");

            if (numLinia == 1) {
                // Procesar la primera línea de cada asociación (nombre, correo, etc.)
                while (tokens.hasMoreTokens()) {
                    nom = tokens.nextToken();
                    correu = tokens.nextToken();
                    llistaMembres = new membre[100];
                    llistaAccions = new accio[100];
                    totalMembres = 0;
                    totalAccions = 0;
                }
                processantMembres = true; // Pasamos a la sección de membres
                continue;
            }
            if (processantMembres && !linea.isBlank()) {
                // Procesar els membres
                while (tokens.hasMoreTokens()) {
                    alias = tokens.nextToken();
                    correuElectronic = tokens.nextToken();
                    associacions = Integer.parseInt(tokens.nextToken());

                    if (tokens.nextToken().equals("P")) {
                        nomDepartament = tokens.nextToken();
                        numDespatx = Integer.parseInt(tokens.nextToken());
                        llistaMembres[totalMembres++] = new professor(alias, correuElectronic, associacions, nomDepartament, numDespatx);
                    } else if (tokens.nextToken().equals("A")) {
                        ensenyament = tokens.nextToken();
                        dataMatricula = Integer.parseInt(tokens.nextToken());
                        graduat = Boolean.parseBoolean(tokens.nextToken());
                        llistaMembres[totalMembres++] = new alumne(alias, correuElectronic, associacions, ensenyament, dataMatricula, graduat);
                    }
                }
            }

            if (linea.equals("---")) {
                // Cambiar de secció al trobar el delimitador ---
                processantMembres = false;
                processantAccions = true;
                continue;
            }

            if (processantAccions && !linea.isBlank()) {
                // Procesar els membres
                while (tokens.hasMoreTokens()) {
                    associacio = tokens.nextToken();
                    titol = tokens.nextToken();
                    responsable = tokens.nextToken();
                    codi_Accio = tokens.nextToken();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate fecha = LocalDate.parse(tokens.nextToken(), formatter);
                    data = Date.valueOf(fecha);

                    // Xerrada
                    if (tokens.nextToken().equals("X")) {
                        assistencies = Integer.parseInt(tokens.nextToken());
                        valoracioMitjana = Float.parseFloat(tokens.nextToken());
                        totalMembresXerrada = Integer.parseInt(tokens.nextToken());
                    } else if (tokens.nextToken().equals("D")) {
                        activa = Boolean.parseBoolean(tokens.nextToken());
                        repeticions = Integer.parseInt(tokens.nextToken());
                        costMaterials = Float.parseFloat(tokens.nextToken());
                        llistaAccions[totalAccions++] = new demostracio(associacio, titol, responsable, data, activa, costMaterials);
                    } else if (tokens.nextToken().equals("P")) {
                        alias = tokens.nextToken();
                        correuElectronic = tokens.nextToken();
                        associacions = Integer.parseInt(tokens.nextToken());
                        nomDepartament = tokens.nextToken();
                        numDespatx = Integer.parseInt(tokens.nextToken());
                        membresXerrada[QmembresXerrada++] = new professor(alias, correuElectronic, associacions, nomDepartament, numDespatx);
                    } else if (tokens.nextToken().equals("A")) {
                        alias = tokens.nextToken();
                        correuElectronic = tokens.nextToken();
                        associacions = Integer.parseInt(tokens.nextToken());
                        ensenyament = tokens.nextToken();
                        dataMatricula = Integer.parseInt(tokens.nextToken());
                        graduat = Boolean.parseBoolean(tokens.nextToken());
                        membresXerrada[QmembresXerrada++] = new alumne(alias, correuElectronic, associacions, ensenyament, dataMatricula, graduat);
                    }

                    if (tokens.nextToken().equals("XerradaConMembres")) {
                        membresXerrada = new membre[totalMembresXerrada];
                        Xerrada xerrada = new Xerrada(associacio, titol, responsable, data, totalMembresXerrada, assistencies, valoracioMitjana);
                        for (int i = 0; i < totalMembresXerrada; i++) {
                            xerrada.afegirMembre(membresXerrada[i]);
                        }
                        llistaAccions[totalAccions++] = xerrada;
                    }
                }
            }

            if (linea.isBlank()) {
                associacio a = new associacio(nom, correu, totalMembres, totalAccions);
                for (int i = 0; i < totalMembres; i++) {
                    a.afegirMembre(llistaMembres[i]);
                }

                for (int i = 0; i < totalAccions; i++) {
                    a.afegirAccio(llistaAccions[i]);
                }

                aux.afegirAssociacio(a);
                numLinia = 0;
                continue;
            }
        }
        lector.close();
        return aux;
    }
private static void escriureFitxer(String nomFitxer, associacions associacions) throws IOException {
    BufferedWriter escriptor = new BufferedWriter(new FileWriter(nomFitxer));

    for (int i = 0; i < associacions.getCount(); i++) {
        associacio a = associacions.getAssociacio(i);
        escriptor.write(a.getNom() + ";" + a.getCorreu() + "\n");

        for (int j = 0; j < a.getTotalMembres(); j++) {
            membre m = a.getMembre(j);
            escriptor.write(m.getAlias() + ";" + m.getCorreuElectronic() + ";" + m.getAssociacions() + ";");
            if (m instanceof professor) {
                professor p = (professor) m;
                escriptor.write("P;" + p.getNomDepartament() + ";" + p.numDespatx() + "\n");
            } else if (m instanceof alumne) {
                alumne al = (alumne) m;
                escriptor.write("A;" + al.getEnsenyament() + ";" + al.getDataMatricula() + ";" + al.isGraduat() + "\n");
            }
        }

        escriptor.write("---\n");

        for (int k = 0; k < a.getTotalAccions(); k++) {
            accio ac = a.getAccio(k);
            escriptor.write(ac.getAssociacio() + ";" + ac.getTitol() + ";" + ac.getResponsable() + ";" + ac.getCodiAccio() + ";" + ac.getData() + ";");
            if (ac instanceof demostracio) {
                demostracio d = (demostracio) ac;
                escriptor.write("D;" + d.isActiva() + ";" + d.getRepeticions() + ";" + d.getCostMaterials() + "\n");
            } else if (ac instanceof Xerrada) {
                Xerrada x = (Xerrada) ac;
                escriptor.write("X;" + x.getAssistencies() + ";" + x.getValoracioMitjana() + ";" + x.getTotalMembresXerrada() + "\n");
                for (int l = 0; l < x.getTotalMembresXerrada(); l++) {
                    membre mx = x.getMembre(l);
                    escriptor.write(mx.getAlias() + ";" + mx.getCorreuElectronic() + ";" + mx.getAssociacions() + ";");
                    if (mx instanceof professor) {
                        professor px = (professor) mx;
                        escriptor.write("P;" + px.getNomDepartament() + ";" + px.getNumDespatx() + "\n");
                    } else if (mx instanceof alumne) {
                        alumne alx = (alumne) mx;
                        escriptor.write("A;" + alx.getEnsenyament() + ";" + alx.getDataMatricula() + ";" + alx.isGraduat() + "\n");
                    }
                }
            }
        }
        escriptor.write("\n");
    }
    escriptor.close();
}
}
