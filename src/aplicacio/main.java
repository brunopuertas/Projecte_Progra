package aplicacio;

import dades.*;
import dades.tipusMembre.*;
import excepcions.*;
import fitxers.*;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import utilitats.*;


public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false); // Asegura que solo acepte fechas válidas
        associacions a = null;


        // Inicializar la llista d'asociacions
        try {
            a = gestioFitxers.llegirFitxer("store.txt");
            System.out.println("Fitxer llegit correctament.");
        } catch (maxAssociacionsExcedit e) {
            System.out.println("Error: " + e.getMessage());
            a = new associacions(100); // Inicializar con una capacidad por defecto
        } catch (maxMembresExcedit e) {
            System.out.println("Error: " + e.getMessage());
            a = new associacions(100); // Inicializar con una capacidad por defecto
        } catch (maxAccioExcedit e) {
            System.out.println("Error: " + e.getMessage());
            a = new associacions(100); // Inicializar con una capacidad por defecto
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            a = new associacions(100); // Inicializar con una capacidad por defecto
        } 

        while (!exit) {
            System.out.println("\n\nSelecciona una opció:");
            System.out.println("1. Mostrar les dades de la llista d'assossiacions");
            System.out.println("2. Mostrar les dades de la llista de membres que formen part d'una associació, afegint filtre per a professors, alumnes o ambdós.");
            System.out.println("3. Mostrar les dades de la llista de membres actius, que formen part de qualsevol associació, afegint filtre per a professors, alumnes o ambdós");
            System.out.println("4. Mostrar les dades de la llista d'accions, afegint filtre o no per tipus d'acció.");
            System.out.println("5. Obtenir i mostrar la llista d'accions que ofereix una associació concreta.");
            System.out.println("6. Obtenir i mostrar la llista de les xerrades que es duen a terme en una franja de dates.");
            System.out.println("7. Afegir una nova associació");
            System.out.println("8. Alta d'un membre a una associació.");
            System.out.println("9. Afegir una nova xerrada.");
            System.out.println("10. Afegir una nova demostració");
            System.out.println("11. Consultar i mostrar les dades de les demostracions que es consideren no actives. Calcular el cost econòmic total que va suposar preparar totes aquestes demostracions.");
            System.out.println("12. Calcular la persona més activa, és a dir, la que participa en més associacions.");
            System.out.println("13. Consultar i mostrar les dades de les xerrades que ha tingut més d'un cert nombre indicat d'assistents.");
            System.out.println("14. Valorar una xerrada per part d'un assistent.");
            System.out.println("15. Consultar i mostrar la xerrada que està millor valorada.");
            System.out.println("16. Mostrar les dades de les xerrades que farà una persona concreta.");
            System.out.println("17. Donar de baixa les demostracions que no estiguin actives i que es van dissenyar abans d'una certa data.");
            System.out.println("18. EXIT");
            System.out.println("19. Guardar les dades en un fitxer.");
            System.out.println("20. Mostrar les dades en una finestra gràfica.");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println(a);
                    break;
                case 2:
                    System.out.println("\nDigues l'associació de la qual vols la llista:");
                    String nomAssociacio = scanner.next();
                    System.out.println("\nVols afegir un filtre per a la llista de membres?\n(S/N)");
                    String eleccio = scanner.next();
                    membre[] llista;
                    if (eleccio.equals("S")) {
                        System.out.println("\nFiltrar per professors o filtrar per alumnes?\n(P/A)");
                        String eleccio2 = scanner.next();
                        if (eleccio2.equals("P")) {
                            llista = a.llistaMembresActiusAssociacioFiltre(nomAssociacio, true, false);
                        }else if (eleccio2.equals("A")) {
                            llista = a.llistaMembresActiusAssociacioFiltre(nomAssociacio, false, true);
                        }else{
                            System.out.println("Error al determinar el filtre, es retorna la llista per defecte");
                            llista = a.llistaMembresActiusAssociacioFiltre(nomAssociacio);
                        }
                        for ( int i = 0; i < llista.length; i++) {
                            System.out.println("\n"+ i + "- " + llista[i].getAlias());
                        }
                    }else if (eleccio.equals("N")) {
                        llista = a.llistaMembresActiusAssociacioFiltre(nomAssociacio);
                        for ( int i = 0; i < llista.length; i++) {
                            System.out.println("\n"+ (i + 1) + "- " + llista[i].getAlias());
                        }
                    }
                    break;
                case 3:
                    System.out.println("\nVols afegir un filtre per a la llista de membres?\n(S/N)");
                    String eleccio3 = scanner.next();
                    membre[] llista3;

                    if (eleccio3.equals("S")) {
                        System.out.println("\nFiltrar per professors o filtrar per alumnes?\n(P/A)");
                        String eleccio2 = scanner.next();
                        if (eleccio2.equals("P")) {
                            llista = a.llistaMembresActiusFiltre(true, false);
                        }else if (eleccio2.equals("A")) {
                            llista = a.llistaMembresActiusFiltre(false, true);
                        }else{
                            System.out.println("Error al determinar el filtre, es retorna la llista sense filtre");
                            llista = a.llistaMembresActiusFiltre();
                        }
                        for ( int i = 0; i < llista.length; i++) {
                            System.out.println("\t"+ (i + 1) + "- " + llista[i].getAlias());
                        }
                    }else if (eleccio3.equals("N")) {
                        llista = a.llistaMembresActiusFiltre();
                        for ( int i = 0; i < llista.length; i++) {
                            System.out.println("\t"+ (i + 1) + "- " + llista[i].getAlias());
                        }
                    }
                    break;
                case 4:
                    System.out.println("\nVols filtrar per tipus d'accio?\t(S/N)");
                    String eleccio4 = scanner.next();
                    accio[] llista4;
                    if (eleccio4.equals("S")){
                        System.out.println("Quin tipus d'accions vols, xerrades o demostracions?\t(X/D)");
                        String eleccio2 = scanner.next();
                        if ( eleccio2.equals("X")) {
                            llista4 = a.llistaAccionsFiltre(true, false);
                        }else if ( eleccio2.equals("D")) {
                            llista4 = a.llistaAccionsFiltre(false, true);
                        }else{
                            System.out.println("\nError al determinar el filtre, es retorna la llista sense filtre");
                            llista4 = a.llistaAccionsFiltre();
                        }
                        for ( int i = 0; i < llista4.length; i++) {
                            System.out.println("\t"+ i + "- " + llista4[i].toString());
                        }
                    }else if (eleccio4.equals("N")) {
                        llista4 = a.llistaAccionsFiltre();
                        for ( int i = 0; i < llista4.length; i++) {
                            System.out.println("\t"+ i + "- " + llista4[i].toString());
                        }
                    }else{
                        System.out.println("\nError al determinar el filtre, es retorna la llista sense filtre");
                    }
                    break;
                case 5:
                    accio[] llista5;
                    System.out.println("Digues el nom de la associacio per la qual vols la llista:");
                    String eleccio5 = scanner.next();
                    System.out.println("Vols aplicar un filtre?\t(S/N)");
                    String eleccio52 = scanner.next();
                    if ( eleccio52.equals("S")) {
                        System.out.println("Quin tipus d'acció vols, xerrada o demostracio?\t(X/D)");
                        String eleccio2 = scanner.next();
                        if ( eleccio2.equals("X") ){        
                            llista5 = a.llistaAccionsAssociacioFiltre(eleccio5, true, false);                    
                        }else if( eleccio2.equals("D")){
                            llista5 = a.llistaAccionsAssociacioFiltre(eleccio5, false, true);
                        }else{
                            System.out.println("\nError al determinar el filtre, es retorna la llista sense filtre");
                            llista5 = a.llistaAccionsAssociacioFiltre(eleccio5);
                        }
                    }else{
                        llista5 = a.llistaAccionsAssociacioFiltre(eleccio5);
                    }

                    for ( int i = 0; i < llista5.length; i++) {
                        System.out.println("\t"+ i + "- "+ llista5[i].toString());
                    }
                    break;
                case 6:
                    System.out.println("Introdueix la data inicial pel rang de xerrades en el següent format (dd-MM-6):");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    dateFormat.setLenient(false);
                    Date data1 = null;
                    Date data2 = null;
                    
                    try {
                        String data1String = scanner.next();
                        data1 = dateFormat.parse(data1String); 
                        System.out.println("Data vàlida: " + data1);
                    } catch (ParseException e) {
                        System.out.println("Format incorrecte, fes servir el format --> dd-MM-yyyy.");
                        return; 
                    }
                    
                    System.out.println("Ara fes el mateix per a la data final:");
                    try {
                        String data2String = scanner.next();
                        data2 = dateFormat.parse(data2String); 
                        System.out.println("Data vàlida: " + data2);
                    } catch (ParseException e) {
                        System.out.println("Format incorrecte, fes servir el format --> dd-MM-yyyy.");
                        return; 
                    }
                    
                    accio[] llista6 = a.mostrarXerradesEnRangDeDates(data1, data2);
                    for (int i = 0; i < llista6.length; i++) {
                        System.out.println("\t"+i+"- "+llista6[i].toString());
                    }
                    break;
                case 7:
                    //Afegir una nova associacio
                    System.out.println("Escriu el nom de la nova associacio:");
                    String nomAssociacio7 = scanner.next();
                    System.out.println("Escriu el correu de la nova associacio:");
                    String correuAssociacio7 = scanner.next();
                    System.out.println("Escriu el maxim de membres de la nova associacio:");
                    int maxMembres7 = scanner.nextInt();
                    System.out.println("Escriu el maxim d'accions de la nova associacio:");
                    int maxAccions7 = scanner.nextInt();
                    associacio novAssociacio = new associacio(nomAssociacio7, correuAssociacio7, maxMembres7, maxAccions7);
                    a.afegirAssociacio(novAssociacio);
                    System.out.println("Associacio afegida!");
                    break;
                case 8:
                    System.out.println("A quina associacio vols afegir el membre?");
                    String associacio8 = scanner.next();
                    if ( a.existeixAssociacio(associacio8)) {
                        System.out.println("Digues l'alias del membre per veure si ja pertany a alguna associacio:");
                            String nomMembre = scanner.next();
                            if ( a.existeixMembre(nomMembre)) {
                                try {
                                    a.buscarAssociacio(associacio8).afegirMembre(a.buscarMembre(nomMembre));
                                } catch (maxAssociacionsExcedit | maxMembresExcedit e) {
                                    System.out.println("Error: " + e.getMessage());
                                }
                            }else{
                                System.out.println("El membre no existeix escriu les seves dades, Vols afegir alumne o professor?\t(A/P)");
                                String eleccio8 = scanner.next();
                                if ( eleccio8.equals("A")) {
                                    System.out.println("\tIntrodueix les dades de l'alumne:");
                                    System.out.println("\tEscriu el seu correu electronic:");
                                    String correu8 = scanner.next();
                                    System.out.println("\tEscriu el nombre corresponent a l'ensenyament");
                                    a.mostrarSiglasTitulacions();
                                    int nombre8 = scanner.nextInt();
                                    System.out.println("\tEscriu el nombre corresponent a la data de la matricula (ex. 2020)");
                                    int data8 = scanner.nextInt();
                                    alumne alumne8 = new alumne(nomMembre, correu8, 0, a.seleccionarEnsenyament(nombre8), data8, false);
                                    try {
                                        a.buscarAssociacio(associacio8).afegirMembre(alumne8);
                                    } catch (maxAssociacionsExcedit | maxMembresExcedit e) {
                                        System.out.println("Error: " + e.getMessage());
                                    }
                                }else if ( eleccio8.equals("P")) {
                                    System.out.println("\tIntrodueix les dades del professor:");
                                    System.out.println("\tIntrodueix el correu electronic:");
                                    String correu8 = scanner.next();
                                    System.out.println("\tIntrodueix el nom del departament:");
                                    String nomDepart8 = scanner.next();
                                    System.out.println("\tIntrodueix el nombre del despatx:");
                                    int despatx8 = scanner.nextInt();
                                    professor professor8 = new professor(nomMembre, correu8, 0, nomDepart8, despatx8);
                                    try {
                                        a.buscarAssociacio(associacio8).afegirMembre(professor8);
                                    } catch (maxAssociacionsExcedit | maxMembresExcedit e) {
                                        System.out.println("Error: " + e.getMessage());
                                    }
                                }else{
                                    System.out.println("Valor incorrecte, torna a provar.");
                                }
                            }
                    }else{
                        System.out.println("Aquesta associacio no existeix, revisa la ortografia");
                    }
                    System.out.println("Membre afegit correctament!");
                    break;
                case 9:
                    sdf = new SimpleDateFormat("dd-MM-yyyy");
        
                    System.out.println("Introdueix el nom de l'associació: ");
                    String associacio = scanner.next();
                    
                    System.out.println("Introdueix el títol de la xerrada: ");
                    String titol = scanner.next();
                    
                    System.out.println("Introdueix el responsable de la xerrada: ");
                    String responsable = scanner.next();
                    
                    System.out.println("Introdueix la data de la xerrada (yyyy-MM-dd): ");
                    String dataStr = scanner.next();
                    Date data = null;
                    try {
                        data = sdf.parse(dataStr);
                    } catch (Exception e) {
                        System.out.println("Data no vàlida.");
                        return;
                    }
                
                    System.out.println("Introdueix el nombre màxim de membres: ");
                    int maxMembres = Integer.parseInt(scanner.next());
                    
                    System.out.println("Introdueix el nombre d'assistències: ");
                    int assistencies = Integer.parseInt(scanner.next());
                    
                    System.out.println("Introdueix la valoració mitjana: ");
                    float valoracioMitjana = Float.parseFloat(scanner.next());
                
                    // Crear la nova xerrada
                    Xerrada novaXerrada = new Xerrada(associacio, titol, responsable, data, maxMembres, assistencies, valoracioMitjana);
                
                    // Afegir la nova xerrada a la llista de xerrades
                    // Asegurar-se d'augmentar el nombre d'elements a la llista de xerrades
                    System.out.println("Xerrada afegida correctament!");
                    break;
                case 10:
                    sdf = new SimpleDateFormat("dd-MM-yyyy");
                    sdf.setLenient(false); 
                    
                    System.out.println("Introdueix el nom de l'associació: ");
                    String associacio10 = scanner.next();
                    
                    System.out.println("Introdueix el títol de la demostració: ");
                    String titol10 = scanner.next();
                    
                    System.out.println("Introdueix el responsable de la demostració: ");
                    String responsable10 = scanner.next();
                    
                    System.out.println("Introdueix la data de disseny de la demostració (yyy-MM-dd): ");
                    String dataStr10 = scanner.next(); 
                    Date dataDisseny10 = null;
                    try {
                        dataDisseny10 = sdf.parse(dataStr10); 
                    } catch (Exception e) {
                        System.out.println("Data no vàlida.");
                        return;
                    }
                    
                    System.out.println("La demostració està activa? (true/false): ");
                    boolean activa = Boolean.parseBoolean(scanner.next());
                    
                    System.out.println("Introdueix el cost dels materials: ");
                    float costMaterials = Float.parseFloat(scanner.next());
                    
                    // Crear la nova demostració
                    demostracio novaDemostracio = new demostracio(associacio10, titol10, responsable10, dataDisseny10, activa, costMaterials);
                    
                    // Afegeix la demostració a l'associació corresponent
                    try {
                        a.buscarAssociacio(associacio10).afegirAccio(novaDemostracio);
                    } catch (maxAccioExcedit e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                
                case 11:
                    //Consultar les valoracions inactives i consultar la perdua total
                    demostracio[] llistaDemostracions11 = a.llistaDemostracionsNoActives();
                    System.out.println("La llista de demostracions inactives es la següent:");
                    for (int i = 0; i < llistaDemostracions11.length; i++) {
                        System.out.println("\t"+i+"- "+llistaDemostracions11[i].toString());
                    }
                    System.out.println("\nLes perdues totals son de "+a.calcularPerduaTotal(llistaDemostracions11)+" euros.");
                    break;
                case 12:
                    //Persona mes activa
                    System.out.println("\tLa persona mes activa es "+ a.personaMesActiva());
                    break;
                case 13:
                    System.out.println("Intrudueix el valor d'assistencies minimes:");
                    int assistenciesMinimes = scanner.nextInt();
                    Xerrada[] llistaXerrAssistMin = a.mostrarXerradesAmbMésAssistents(assistenciesMinimes);
                    for ( int i = 0; i < llistaXerrAssistMin.length; i++ ){
                        System.out.println("\t"+i+"- "+ llistaXerrAssistMin[i].toString());
                    }
                    break;
                case 14:
                    System.out.println("Escriu el nom de la xerrada que vols valorar");
                    String nom14 = scanner.next();
                    if ( a.existeixXerrada(nom14) ){
                        System.out.println("Escriu la valoracio que li vols posar a la xerrada (1-10)");
                        int valoracio14 = scanner.nextInt();
                        a.valorarXerradaPerAssistent(valoracio14, a.buscarXerrada(nom14));
                    }
                    break;
                case 15:
                    //Xerrada mes ben valorada
                    System.out.println("La xerrada més ben valorada es la següent:\t"+ a.millorXerrada(a.llistaXerrades()));
                    break;
                case 16:
                    System.out.println("Escriu el nom de la persona de la qual vols la llista de xerrades que fara:");
                    String nom16 = scanner.next();
                    if ( a.existeixMembre(nom16)){
                        try {
                            Xerrada[] llista16 = a.llistaXerradesMembre(a.llistaXerrades(), a.buscarMembre(nom16));
                            for ( int i = 0; i < llista16.length; i++) {
                                System.out.println("\t"+i+"- "+ llista16[i]);
                            }
                        } catch (llistaMembresBuida e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;
                case 17:
                    //Donar de baixa les xerrades inactives i les mes antigues d'una certa data
                    System.out.println("Escriu la data limit de les demostracions que vols eliminar");
                    String dataStr17 = scanner.next(); // Usamos la variable correcta
                    Date dataDisseny17 = null;
                    try {
                        dataDisseny17 = sdf.parse(dataStr17); // Corregido: usamos dataStr10
                    } catch (Exception e) {
                        System.out.println("Data no vàlida.");
                        return;
                    }
                    a.donarDeBaixaDemostracions(a.llistaDemostracions(), dataDisseny17);
                    System.out.println("Demostracions donades de baixa correctament!");
                    break;
                case 18:
                    exit = true;
                    System.out.println("Sortint del programa...");
                    break;
                case 19:
                    try {
                        gestioFitxers.escriureFitxer("store.txt", a);
                        System.out.println("Fitxer guardat correctament.");
                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 20:
                    JButton btnMostrarDemostracions;
                    JTable taulaDemostracions;
                    JTextArea textAreaDetall;
                    JFrame frame = new JFrame();

                    frame.setTitle("Aplicació de Demostracions");
                    frame.setSize(800, 600);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setLocationRelativeTo(null); // Centrar la finestra
                    
                    // Crear el JButton per mostrar les demostracions
                    btnMostrarDemostracions = new JButton("Mostrar Demostracions");
                    // Crear la taula per mostrar les demostracions
                    taulaDemostracions = new JTable();
                   
                    // Exemple de configuració bàsica per a la taula
                    DefaultTableModel model = new DefaultTableModel(new Object[]{"Nom", "Data", "Assistents"}, 0);
                    taulaDemostracions.setModel(model);

                    // Crear el JTextArea per mostrar el detall d'una demostració
                    textAreaDetall = new JTextArea();
                    textAreaDetall.setEditable(false);

                    // Organitzar els components amb un Layout
                    JPanel panel = new JPanel(new BorderLayout());
                    panel.add(btnMostrarDemostracions, BorderLayout.NORTH);
                    panel.add(new JScrollPane(taulaDemostracions), BorderLayout.CENTER);
                    panel.add(new JScrollPane(textAreaDetall), BorderLayout.SOUTH);
                    frame.add(panel);
                    frame.setVisible(true);

                    break;
                default:
                    System.out.println("Valor fora de rang, verifica que has introduït el nombre de la opció correctament i torna a provar.");
            }
        }
        scanner.close();
    }
}
