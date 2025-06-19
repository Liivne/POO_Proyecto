package org.example.Visual;

import javax.swing.*;

public class VentanaCampeonato8 extends VentanaCampeonato{
    @Override
    protected void inicializarEquipos() {

    }

    @Override
    protected void confirmarTorneo() {

    }

    @Override
    protected void reiniciarTorneo() {

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                new VentanaCampeonato8().setVisible(true);
            }
        });
    }
}
