import org.example.Visual.VentanaPrincipal;

import javax.swing.*;

// Método main para ejecutar la aplicación
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new VentanaPrincipal().setVisible(true);
    });
}
