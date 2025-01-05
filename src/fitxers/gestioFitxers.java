package fitxers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import dades.*;
import dades.tipusMembre.*;
import utilitats.associacions;

public class gestioFitxers {

    private static associacions llegirFitxer (String nomFitxer) {
        associacions aux = new associacions(100);

        String linea; String nom = ""; String correu = ""; int numLinia = 0;

        membre[] llistaMembres = new membre[100];  int totalMembres=0;
        // Variables membre
        String alias; String correuElectronic; int associacions;
        // Variables professor
        String nomDepartament; int numDespatx;

        // Variables alumne
        String ensenyament; int dataMatricula;  boolean graduat;

        accio[] llistaAccions = new accio[100]; int totalAccions=0;

        boolean processantMembres = false; boolean processantAccions = false;
    

        BufferedReader lector = new BufferedReader(new FileReader(nomFitxer));

        while ((linea = lector.readLine()) != null) {
            numLinia++;
            linea = lector.readLine();
            StringTokenizer tokens = new StringTokenizer(linea,";");

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

                    if (tokens.countTokens() == 2) {
                        nomDepartament = tokens.nextToken();
                        numDespatx = Integer.parseInt(tokens.nextToken());
                        llistaMembres[totalMembres++] = new professor(alias, correuElectronic, associacions, nomDepartament, numDespatx);
                    } else if (tokens.countTokens() == 3) {
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
    
}
