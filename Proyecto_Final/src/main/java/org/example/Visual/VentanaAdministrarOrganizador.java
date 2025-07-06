package org.example.Visual;

import org.example.Logica.FORMATO;

import javax.swing.*;
import java.awt.*;
import static org.example.Logica.FORMATO.*;

public class VentanaAdministrarOrganizador extends JFrame {
    public VentanaAdministrarOrganizador() {
        initComponents();
        setupwindow();
        // cargarDatosPrueba();
    }

    private void setupwindow() {
    }

    private void initComponents() {
    }

    /**
     * Recibe la lista de torneos en proceso y te permite escoger cual administrar
     * al confirmar pasas a la pestaña actualizarResultados
     */
    // private void elegirTorneo(Torneos t){}
    /**
     * actualizarResultados simplemente deriva según el tipo de torneo
     * si es campeonato simple, deriva a una pestaña con llaves de octavos, cuartos, semis...
     * si es liga, otra pestaña con los partidos programados
     */
    private void actualizarResultados(FORMATO f){
        if (LIGASIMPLE == f){
            abrirVentanaLiga();
        }
        else{
            abrirVentanaCampeonato();
        }
    }
    private void abrirVentanaLiga() {
        // new VentanaLiga().setVisible(true);
    }
    private void abrirVentanaCampeonato() {
        // case torneo == torneo16
        // v = new Torneo16Equipos
        // v.setVisible(true);
    }

    // Método main para ejecutar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            new VentanaAdministrarOrganizador().setVisible(true);
        });
    }

}
