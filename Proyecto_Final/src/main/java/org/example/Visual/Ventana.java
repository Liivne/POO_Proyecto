package org.example.Visual;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {

    public Ventana(){
        setTitle("Organizador de Competencias");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1214, 716));
        setSize(1214, 716);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
