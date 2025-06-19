package org.example.Visual;

import javax.swing.*;

public class VentanaCampeonato4 extends VentanaCampeonato{
    public VentanaCampeonato4(){
        super();
    }

    @Override
    protected void inicializarEquipos() {
        /** Debería recibir la lista de equipos entregada por admin
        Además, se vuelve a llamar cuando un nuevo equipo se suma
         desde la pestaña de usuario
         **/
        super.inicializarEquipos();
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

                new VentanaCampeonato4().setVisible(true);
            }
        });
    }
}
