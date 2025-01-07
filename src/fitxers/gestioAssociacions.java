package fitxers;
import java.io.*;
import utilitats.*;

public class gestioAssociacions {
    
    public static void storeData (associacions[] associacions) {
        ObjectOutputStream outputFile;
        try {
        outputFile = new ObjectOutputStream(new FileOutputStream("storeData.ser"));
            for (associacions associacio : associacions) {
            outputFile.writeObject(associacio);
            }
            outputFile.close();
        } catch (IOException e) {
            System.out.println("Error en l'arxiu de sortida.");
        }
    }

    public static void readData (associacions[] associacions) {
        ObjectInputStream inputFile;
        try {
            inputFile = new ObjectInputStream(new FileInputStream("storeData.ser"));
            for (int i=0; i<associacions.length; i++) {
                associacions[i] = (associacions)inputFile.readObject();
            }
            inputFile.close();
        } catch (IOException e) {
            System.out.println("Error en l'arxiu d'entrada.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error, no es troba la classe Vehicle."+e);
        } catch (ClassCastException e){
            System.out.println("Error, el format de l'arxiu no és correcte per la definicióactual de la classe Vehicle."+e);
        }
    }
}
