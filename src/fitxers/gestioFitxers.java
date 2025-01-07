package fitxers;

import dades.*;
import dades.tipusMembre.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.StringTokenizer;
import utilitats.*;

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
        SiglasTitulacio ensenyament;int dataMatricula;boolean graduat;

        // Variables carrec
        membre[] carrec = new membre[3];

        accio[] llistaAccions = new accio[100];
        int totalAccions = 0;
        // Variables accio
        String associacio = "";String titol = "" ;String responsable = "" ;String codi_Accio = "";
        Date data = null;
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
                switch (tokens.nextToken()) {
                    case "Professor" -> {
                        alias = tokens.nextToken(); correuElectronic = tokens.nextToken(); associacions = Integer.parseInt(tokens.nextToken());
                        nomDepartament = tokens.nextToken();
                        numDespatx = Integer.parseInt(tokens.nextToken());
                        llistaMembres[totalMembres++] = new professor(alias, correuElectronic, associacions, nomDepartament, numDespatx);
                    }
                    case "Alumne" -> {
                        alias = tokens.nextToken(); correuElectronic = tokens.nextToken(); associacions = Integer.parseInt(tokens.nextToken());
                        ensenyament = SiglasTitulacio.valueOf(tokens.nextToken());
                        dataMatricula = Integer.parseInt(tokens.nextToken());
                        graduat = Boolean.parseBoolean(tokens.nextToken());
                        llistaMembres[totalMembres++] = new alumne(alias, correuElectronic, associacions, ensenyament, dataMatricula, graduat);
                    }
                    case "President" -> {
                        alias = tokens.nextToken(); correuElectronic = tokens.nextToken(); associacions = Integer.parseInt(tokens.nextToken());
                        ensenyament = SiglasTitulacio.valueOf(tokens.nextToken());
                        dataMatricula = Integer.parseInt(tokens.nextToken());
                        graduat = Boolean.parseBoolean(tokens.nextToken());
                        carrec[0] = new alumne(alias, correuElectronic, associacions, ensenyament, dataMatricula, graduat);
                    }
                    case "Secretari" -> {
                        alias = tokens.nextToken(); correuElectronic = tokens.nextToken(); associacions = Integer.parseInt(tokens.nextToken());
                        ensenyament = SiglasTitulacio.valueOf(tokens.nextToken());
                        dataMatricula = Integer.parseInt(tokens.nextToken());
                        graduat = Boolean.parseBoolean(tokens.nextToken());
                        carrec[1] = new alumne(alias, correuElectronic, associacions, ensenyament, dataMatricula, graduat);
                    }
                    case "Tresorer" -> {
                        alias = tokens.nextToken(); correuElectronic = tokens.nextToken(); associacions = Integer.parseInt(tokens.nextToken());
                        ensenyament = SiglasTitulacio.valueOf(tokens.nextToken());
                        dataMatricula = Integer.parseInt(tokens.nextToken());
                        graduat = Boolean.parseBoolean(tokens.nextToken());
                        carrec[2] = new alumne(alias, correuElectronic, associacions, ensenyament, dataMatricula, graduat);
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
                // Procesar les accions
                while (tokens.hasMoreTokens()) {
                    switch (tokens.nextToken()) {
                        case "Associacio" -> {
                            associacio = tokens.nextToken();
                            titol = tokens.nextToken();
                            responsable = tokens.nextToken();
                            codi_Accio = tokens.nextToken();
                        }
                        case "Xerrada" -> {
                            data = Date.valueOf(tokens.nextToken());
                            assistencies = Integer.parseInt(tokens.nextToken());
                            valoracioMitjana = Float.parseFloat(tokens.nextToken());
                            totalMembresXerrada = Integer.parseInt(tokens.nextToken());
                        }
                        case "Demostracio" -> {
                            data = Date.valueOf(tokens.nextToken());
                            activa = Boolean.parseBoolean(tokens.nextToken());
                            repeticions = Integer.parseInt(tokens.nextToken());
                            costMaterials = Float.parseFloat(tokens.nextToken());
                            llistaAccions[totalAccions++] = new demostracio(associacio, titol, responsable, data, activa, costMaterials);
                        }
                        case "Professor" -> {
                            alias = tokens.nextToken();
                            correuElectronic = tokens.nextToken();
                            associacions = Integer.parseInt(tokens.nextToken());
                            nomDepartament = tokens.nextToken();
                            numDespatx = Integer.parseInt(tokens.nextToken());
                            membresXerrada[QmembresXerrada++] = new professor(alias, correuElectronic, associacions, nomDepartament, numDespatx);
                        }
                        case "Alumne" -> {
                            alias = tokens.nextToken();
                            correuElectronic = tokens.nextToken();
                            associacions = Integer.parseInt(tokens.nextToken());
                            ensenyament = SiglasTitulacio.valueOf(tokens.nextToken());
                            dataMatricula = Integer.parseInt(tokens.nextToken());
                            graduat = Boolean.parseBoolean(tokens.nextToken());
                            membresXerrada[QmembresXerrada++] = new alumne(alias, correuElectronic, associacions, ensenyament, dataMatricula, graduat);
                        }
                        case "XerradaAmbMembres" -> {
                            Xerrada xerrada = new Xerrada(associacio, titol, responsable, data, totalMembresXerrada, assistencies, valoracioMitjana);
                            membresXerrada = new membre[totalMembresXerrada];
                            for (int i = 0; i < totalMembresXerrada; i++) {
                                xerrada.afegirMembre(membresXerrada[i]);
                            }
                            llistaAccions[totalAccions++] = xerrada;
                        }
                    }
                }
            }

            if (linea.isBlank()) {
                associacio a = new associacio(nom, correu, totalMembres, totalAccions);
                for (int i = 0; i < totalMembres; i++) {
                    a.afegirMembre(llistaMembres[i]);
                }
                a.definirPresident((alumne) carrec[0]); a.definirSecretari((alumne) carrec[1]); a.definirTresorer((alumne) carrec[2]);
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

        private static void escriureFitxer(String nomFitxer, associacions Associacions) throws IOException {
        BufferedWriter escriptor = new BufferedWriter(new FileWriter(nomFitxer));
        try {
            for (int i = 0; i < Associacions.getCount(); i++) {
            associacio a = Associacions.getAssociacio(i);
            escriptor.write(a.getNom() + ";" + a.getCorreu() + "\n");
            membre presi  = a.getPresident();
            escriptor.write("President;" + presi.getAlias() + ";" + presi.getCorreuElectronic() + ";" + presi.getAssociacions() + ";" + ((alumne) presi).getEnsenyament() + ";" + ((alumne) presi).getDataMatricula() + ";" + ((alumne) presi).getGraduat() + "\n");
            membre secre  = a.getSecretari();
            escriptor.write("Secretari;" + secre.getAlias() + ";" + secre.getCorreuElectronic() + ";" + secre.getAssociacions() + ";" + ((alumne) secre).getEnsenyament() + ";" + ((alumne) secre).getDataMatricula() + ";" + ((alumne) secre).getGraduat() + "\n");
            membre treso  = a.getTresorer();
            escriptor.write("Tresorer;" + treso.getAlias() + ";" + treso.getCorreuElectronic() + ";" + treso.getAssociacions() + ";" + ((alumne) treso).getEnsenyament() + ";" + ((alumne) treso).getDataMatricula() + ";" + ((alumne) treso).getGraduat() + "\n"); 
            for (int j = 0; j < a.getTotalMembres(); j++) {
                membre m = a.getMembre(j);
                if (m instanceof professor p) {
                escriptor.write("Professor;" + p.getAlias() + ";" + p.getCorreuElectronic() + ";" + p.getAssociacions() + ";" + p.getNomDepartament() + ";" + p.numDespatx() + "\n");
                } else if (m instanceof alumne al) {
                escriptor.write("Alumne;" + al.getAlias() + ";" + al.getCorreuElectronic() + ";" + al.getAssociacions() + ";" + al.getEnsenyament() + ";" + al.getDataMatricula() + ";" + al.getGraduat() + "\n");
                }
            }
            escriptor.write("---\n");
            for (int k = 0; k < a.getTotalAccions(); k++) {
                accio ac = a.getAccio(k);
                escriptor.write("Associacio;" + ac.getAssociacio() + ";" + ac.getNom() + ";" + ac.getMembreResponsable() + ";" + ac.getCodAccio() + ";\n");
            }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
        escriptor.close();
    }

    public static void main(String[] args) throws Exception {
        try {
            associacions a = llegirFitxer("associacions.txt");
            escriureFitxer("associacions2.txt", a);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
