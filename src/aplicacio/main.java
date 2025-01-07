package aplicacio;

import java.util.Scanner;
import java.util.Date;
import utilitats.*;
import fitxers.*;
import dades.*;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        associacions a = new associacions(10);

        while (!exit) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Llistar Membres Actius");
            System.out.println("2. Llistar Accions Per Tipus");
            System.out.println("3. Xerrada Per Horari");
            System.out.println("4. Crear Associació");
            System.out.println("5. Alta Membre Associació");
            System.out.println("6. Crear Xerrada");
            System.out.println("7. Crear Demostració");
            System.out.println("8. Demostracions Actives");
            System.out.println("9. Persona Més Activa");
            System.out.println("10. Xerrades Per Assistents");
            System.out.println("11. Valorar Xerrada");
            System.out.println("12. Xerrada Millor Valorada");
            System.out.println("13. Xerrades Per Membre");
            System.out.println("14. Donar De Baixa Demostració");
            System.out.println("15. Exit");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    a.llistaMembresActius();
                    break;
                case 2:
                    a.llistaAccionsPerTipus();
                    break;
                case 3:
                    System.out.println("Introdueix la data d'inici (dd/MM/yyyy): ");
                    String dataIniciStr = scanner.next();
                    System.out.println("Introdueix la data de finalització (dd/MM/yyyy): ");
                    String dataFiStr = scanner.next();

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date dataInici = sdf.parse(dataIniciStr);
                        Date dataFi = sdf.parse(dataFiStr);
                        a.mostrarXerradesEnRangDeDates(dataInici, dataFi);
                    } catch (Exception e) {
                        System.out.println("Les dates no són vàlides.");
                    }
                    break;
                case 4:
                    a.crearAssociacio();
                    break;
                case 5:
                    a.altaMembreAssociacio();
                    break;
                case 6:
                    a.crearXerrada();
                    break;
                case 7:
                    a.crearDemostracio();
                    break;
                case 8:
                    a.demostracionsActives();
                    break;
                case 9:
                    a.personaMesActiva();
                    break;
                case 10:
                    a.xerradesPerAssistents();
                    break;
                case 11:
                    a.valorarXerrada();
                    break;
                case 12:
                    a.xerradaMillorValorada();
                    break;
                case 13:
                    a.xerradesPerMembre();
                    break;
                case 14:
                    System.out.println("Introdueix la data límit (dd/MM/yyyy): ");
                    String dataLimitStr = scanner.next();
                    try {
                        Date dataLimit = sdf.parse(dataLimitStr);
                        a.donarDeBaixaDemostracio(dataLimit);
                    } catch (Exception e) {
                        System.out.println("La data no és vàlida.");
                    }
                    break;
                case 15:
                    exit = true;
                    System.out.println("Sortint del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Per favor, selecciona una opció vàlida.");
            }
        }

        scanner.close();
    }
}
