package org.example.Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La idea de esta clase es que inicialice elementos comunes de los otros campeonatos
 * de eliminación simple, así no tengo que copiar y pegar muchas veces el mismo código
 * (código mas extensible)
 */

abstract public class VentanaCampeonato extends JFrame{



        // Componentes principales
       ;
       protected JPanel panelPrincipal;
        protected JPanel panelCampeon;
        protected JPanel panelControles;
        protected JPanel panelEstado;
        protected JButton[] botones;

        // Listas para manejar todas las rondas dinámicamente
        protected List panelesRondas;
        protected List botonesRondas;
        protected List labelsEstado;


        // Botón y label para el campeón
        protected JLabel labelCampeon;

        // Botones de control
        protected JButton btnConfirmar;
        protected JButton btnReiniciar;

        // Labels de estado
        protected JLabel labelEstadoCuartos;
        protected JLabel labelEstadoSemifinales;
        protected JLabel labelEstadoFinalistas;
        protected JLabel labelEstadoCampeon;

        // Dependerá de la clase concreta
        protected int numEquipos;

        // Nombres de equipos iniciales (puedes modificar estos)
        protected String[] equiposIniciales = {
                "Equipo Alpha", "Equipo Beta", "Equipo Gamma", "Equipo Delta",
                "Equipo Epsilon", "Equipo Zeta", "Equipo Eta", "Equipo Theta",
                "Equipo Iota", "Equipo Kappa", "Equipo Lambda", "Equipo Mu",
                "Equipo Nu", "Equipo Xi", "Equipo Omicron", "Equipo Pi"
        };

        public VentanaCampeonato() {
            configurarVentana();
            inicializarComponentes();
            configurarLayout();
            asignarEventos();
            inicializarEquipos();
        }

        protected void configurarVentana() {
            setTitle("Gestión de Torneo - Eliminación Directa (16 Equipos)");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setMinimumSize(new Dimension(1200, 800));
        }

        protected void inicializarComponentes() {
            // Panel principal
            panelPrincipal = new JPanel(new BorderLayout());
            panelPrincipal.setBackground(new Color(240, 248, 255));


            panelCampeon = new JPanel(new FlowLayout());
            panelCampeon.setBorder(BorderFactory.createTitledBorder("CAMPEÓN"));
            panelCampeon.setBackground(new Color(255, 215, 0)); // Dorado


            // Label del campeón
            labelCampeon = new JLabel("Esperando campeón...");
            labelCampeon.setFont(new Font("Arial", Font.BOLD, 18));
            labelCampeon.setHorizontalAlignment(SwingConstants.CENTER);
            labelCampeon.setPreferredSize(new Dimension(250, 60));
            labelCampeon.setBorder(BorderFactory.createEtchedBorder());

            // Panel de controles
            panelControles = new JPanel(new FlowLayout());
            panelControles.setBackground(new Color(240, 248, 255));

            btnConfirmar = new JButton("Confirmar Torneo");
            btnConfirmar.setFont(new Font("Arial", Font.BOLD, 14));
            btnConfirmar.setBackground(new Color(34, 139, 34));
            btnConfirmar.setForeground(Color.WHITE);
            btnConfirmar.setPreferredSize(new Dimension(150, 40));
            btnConfirmar.setEnabled(false);

            btnReiniciar = new JButton("Reiniciar Torneo");
            btnReiniciar.setFont(new Font("Arial", Font.BOLD, 14));
            btnReiniciar.setBackground(new Color(220, 20, 60));
            btnReiniciar.setForeground(Color.WHITE);
            btnReiniciar.setPreferredSize(new Dimension(150, 40));

            // Panel de estado
            panelEstado = new JPanel(new GridLayout(1, 4, 10, 5));
            panelEstado.setBorder(BorderFactory.createTitledBorder("Estado del Torneo"));
            panelEstado.setBackground(Color.WHITE);

            labelEstadoCuartos = new JLabel("<html><center>Cuartos<br>0/8</center></html>");
            labelEstadoCuartos.setHorizontalAlignment(SwingConstants.CENTER);
            labelEstadoCuartos.setBorder(BorderFactory.createEtchedBorder());

            labelEstadoSemifinales = new JLabel("<html><center>Semifinales<br>0/4</center></html>");
            labelEstadoSemifinales.setHorizontalAlignment(SwingConstants.CENTER);
            labelEstadoSemifinales.setBorder(BorderFactory.createEtchedBorder());

            labelEstadoFinalistas = new JLabel("<html><center>Finalistas<br>0/2</center></html>");
            labelEstadoFinalistas.setHorizontalAlignment(SwingConstants.CENTER);
            labelEstadoFinalistas.setBorder(BorderFactory.createEtchedBorder());

            labelEstadoCampeon = new JLabel("<html><center>Campeón<br>0/1</center></html>");
            labelEstadoCampeon.setHorizontalAlignment(SwingConstants.CENTER);
            labelEstadoCampeon.setBorder(BorderFactory.createEtchedBorder());
        }

        protected void inicializarBotones(){}

        protected void configurarLayout() {
            // Agregar enfrentamientos a los paneles
            for(JPanel panel: panelesRondas){
                panelTorneo.add(panel);
            }

            // Campeón
            panelCampeon.add(labelCampeon);

            // Controles
            panelControles.add(btnReiniciar);
            panelControles.add(Box.createHorizontalStrut(20));
            panelControles.add(btnConfirmar);

            // Estado
            panelEstado.add(labelEstadoCuartos);
            panelEstado.add(labelEstadoSemifinales);
            panelEstado.add(labelEstadoFinalistas);
            panelEstado.add(labelEstadoCampeon);

            // Layout principal
            JPanel panelTorneo = new JPanel(new GridLayout(1, 5, 10, 10));
            panelTorneo.add(panelOctavos);
            panelTorneo.add(panelCuartos);
            panelTorneo.add(panelSemifinales);
            panelTorneo.add(panelFinal);
            panelTorneo.add(panelCampeon);

            JPanel panelSur = new JPanel(new BorderLayout());
            panelSur.add(panelControles, BorderLayout.NORTH);
            panelSur.add(panelEstado, BorderLayout.SOUTH);

            panelPrincipal.add(panelTorneo, BorderLayout.CENTER);
            panelPrincipal.add(panelSur, BorderLayout.SOUTH);

            add(panelPrincipal);
        }

        protected void asignarEventos() {
            // Aquí puedes agregar los ActionListeners para cada botón


            // Botón confirmar
            btnConfirmar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica para confirmar torneo
                    confirmarTorneo();
                }
            });

            // Botón reiniciar
            btnReiniciar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica para reiniciar torneo
                    reiniciarTorneo();
                }
            });
        }

        protected abstract void inicializarEquipos();

        /**

        // Métodos para manejar la lógica (aquí es donde implementarás tu lógica)
        private void manejarSeleccionOctavos(int indice) {
            // TODO: Implementar lógica de selección para octavos
            System.out.println("Seleccionado equipo en octavos: " + indice);
        }

        private void manejarSeleccionCuartos(int indice) {
            // TODO: Implementar lógica de selección para cuartos
            System.out.println("Seleccionado equipo en cuartos: " + indice);
        }

        private void manejarSeleccionSemifinales(int indice) {
            // TODO: Implementar lógica de selección para semifinales
            System.out.println("Seleccionado equipo en semifinales: " + indice);
        }

        private void manejarSeleccionFinal(int indice) {
            // TODO: Implementar lógica de selección para final
            System.out.println("Seleccionado equipo en final: " + indice);
        }
         **/

        protected abstract void confirmarTorneo();
        protected abstract void reiniciarTorneo();

        // Métodos auxiliares para acceder a los componentes desde fuera de la clase
        public JButton[] getBotones() { return botones; }
        public JLabel getLabelCampeon() { return labelCampeon; }
        public JButton getBtnConfirmar() { return btnConfirmar; }
        public JButton getBtnReiniciar() { return btnReiniciar; }
        public JLabel getLabelEstadoCuartos() { return labelEstadoCuartos; }
        public JLabel getLabelEstadoSemifinales() { return labelEstadoSemifinales; }
        public JLabel getLabelEstadoFinalistas() { return labelEstadoFinalistas; }
        public JLabel getLabelEstadoCampeon() { return labelEstadoCampeon; }
    }


