package dades;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class interficie extends JFrame {
    private JButton btnMostrarDemostracions;
    private JTable taulaDemostracions;
    private JTextArea textAreaDetall;

    public interficie() {
        // Configurar la finestra principal
        setTitle("Aplicació de Demostracions");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la finestra
        
        // Crear el JButton per mostrar les demostracions
        btnMostrarDemostracions = new JButton("Mostrar Demostracions");
        btnMostrarDemostracions.addActionListener(e -> mostrarDemostracions());
        
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
        add(panel);
    }
    
    // Funció per mostrar les demostracions filtrades
    private void mostrarDemostracions() {
        // Aquí anirà la lògica per filtrar i mostrar les demostracions actives.
    }

    // Funció per iniciar la finestra
    public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> {
            new interficie().setVisible(true);
        //});
    }
}
