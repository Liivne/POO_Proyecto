package org.example.Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCampeonato16 extends VentanaCampeonato {

    // Componentes principales
    private JPanel panelPrincipal;
    private JPanel panelOctavos;
    private JPanel panelCuartos;
    private JPanel panelSemifinales;
    private JPanel panelFinal;
    private JPanel panelCampeon;
    private JPanel panelControles;
    private JPanel panelEstado;

    // Botones para los enfrentamientos - Octavos de Final (16 equipos)
    private JButton[] botonesOctavos = new JButton[16];

    // Botones para Cuartos de Final (8 equipos)
    private JButton[] botonesCuartos = new JButton[8];

    // Botones para Semifinales (4 equipos)
    private JButton[] botonesSemifinales = new JButton[4];

    // Botones para Final (2 equipos)
    private JButton[] botonesFinal = new JButton[2];

    // Botón y label para el campeón
    private JLabel labelCampeon;

    // Botones de control
    private JButton btnConfirmar;
    private JButton btnReiniciar;

    // Labels de estado
    private JLabel labelEstadoCuartos;
    private JLabel labelEstadoSemifinales;
    private JLabel labelEstadoFinalistas;
    private JLabel labelEstadoCampeon;

    // Nombres de equipos iniciales (puedes modificar estos)
    private String[] equiposIniciales = {
            "Equipo Alpha", "Equipo Beta", "Equipo Gamma", "Equipo Delta",
            "Equipo Epsilon", "Equipo Zeta", "Equipo Eta", "Equipo Theta",
            "Equipo Iota", "Equipo Kappa", "Equipo Lambda", "Equipo Mu",
            "Equipo Nu", "Equipo Xi", "Equipo Omicron", "Equipo Pi"
    };

    public VentanaCampeonato16() {
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

        // Paneles para cada ronda
        panelOctavos = new JPanel(new GridLayout(8, 1, 5, 10));
        panelOctavos.setBorder(BorderFactory.createTitledBorder("OCTAVOS DE FINAL"));
        panelOctavos.setBackground(Color.WHITE);

        panelCuartos = new JPanel(new GridLayout(4, 1, 5, 20));
        panelCuartos.setBorder(BorderFactory.createTitledBorder("CUARTOS DE FINAL"));
        panelCuartos.setBackground(Color.WHITE);

        panelSemifinales = new JPanel(new GridLayout(2, 1, 5, 40));
        panelSemifinales.setBorder(BorderFactory.createTitledBorder("SEMIFINALES"));
        panelSemifinales.setBackground(Color.WHITE);

        panelFinal = new JPanel(new GridLayout(1, 1, 5, 80));
        panelFinal.setBorder(BorderFactory.createTitledBorder("FINAL"));
        panelFinal.setBackground(Color.WHITE);

        panelCampeon = new JPanel(new FlowLayout());
        panelCampeon.setBorder(BorderFactory.createTitledBorder("CAMPEÓN"));
        panelCampeon.setBackground(new Color(255, 215, 0)); // Dorado

        // Inicializar botones de octavos
        for (int i = 0; i < 16; i++) {
            botonesOctavos[i] = new JButton();
            botonesOctavos[i].setPreferredSize(new Dimension(180, 40));
            botonesOctavos[i].setFont(new Font("Arial", Font.BOLD, 12));
            botonesOctavos[i].setBackground(Color.LIGHT_GRAY);
            botonesOctavos[i].setFocusPainted(false);
        }

        // Inicializar botones de cuartos
        for (int i = 0; i < 8; i++) {
            botonesCuartos[i] = new JButton("Esperando...");
            botonesCuartos[i].setPreferredSize(new Dimension(180, 40));
            botonesCuartos[i].setFont(new Font("Arial", Font.BOLD, 12));
            botonesCuartos[i].setBackground(Color.LIGHT_GRAY);
            botonesCuartos[i].setEnabled(false);
            botonesCuartos[i].setFocusPainted(false);
        }

        // Inicializar botones de semifinales
        for (int i = 0; i < 4; i++) {
            botonesSemifinales[i] = new JButton("Esperando...");
            botonesSemifinales[i].setPreferredSize(new Dimension(180, 40));
            botonesSemifinales[i].setFont(new Font("Arial", Font.BOLD, 12));
            botonesSemifinales[i].setBackground(Color.LIGHT_GRAY);
            botonesSemifinales[i].setEnabled(false);
            botonesSemifinales[i].setFocusPainted(false);
        }

        // Inicializar botones de final
        for (int i = 0; i < 2; i++) {
            botonesFinal[i] = new JButton("Esperando...");
            botonesFinal[i].setPreferredSize(new Dimension(180, 40));
            botonesFinal[i].setFont(new Font("Arial", Font.BOLD, 12));
            botonesFinal[i].setBackground(Color.LIGHT_GRAY);
            botonesFinal[i].setEnabled(false);
            botonesFinal[i].setFocusPainted(false);
        }

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

    private void configurarLayout() {
        // Agregar enfrentamientos a los paneles

        // Octavos - crear enfrentamientos (2 botones por enfrentamiento)
        for (int i = 0; i < 8; i++) {
            JPanel enfrentamiento = new JPanel(new GridLayout(3, 1, 2, 2));
            enfrentamiento.add(botonesOctavos[i * 2]);

            JLabel vs = new JLabel("VS", SwingConstants.CENTER);
            vs.setFont(new Font("Arial", Font.BOLD, 10));
            enfrentamiento.add(vs);

            enfrentamiento.add(botonesOctavos[i * 2 + 1]);
            panelOctavos.add(enfrentamiento);
        }

        // Cuartos - crear enfrentamientos
        for (int i = 0; i < 4; i++) {
            JPanel enfrentamiento = new JPanel(new GridLayout(3, 1, 2, 2));
            enfrentamiento.add(botonesCuartos[i * 2]);

            JLabel vs = new JLabel("VS", SwingConstants.CENTER);
            vs.setFont(new Font("Arial", Font.BOLD, 10));
            enfrentamiento.add(vs);

            enfrentamiento.add(botonesCuartos[i * 2 + 1]);
            panelCuartos.add(enfrentamiento);
        }

        // Semifinales - crear enfrentamientos
        for (int i = 0; i < 2; i++) {
            JPanel enfrentamiento = new JPanel(new GridLayout(3, 1, 2, 2));
            enfrentamiento.add(botonesSemifinales[i * 2]);

            JLabel vs = new JLabel("VS", SwingConstants.CENTER);
            vs.setFont(new Font("Arial", Font.BOLD, 10));
            enfrentamiento.add(vs);

            enfrentamiento.add(botonesSemifinales[i * 2 + 1]);
            panelSemifinales.add(enfrentamiento);
        }

        // Final - crear enfrentamiento
        JPanel enfrentamientoFinal = new JPanel(new GridLayout(3, 1, 2, 2));
        enfrentamientoFinal.add(botonesFinal[0]);

        JLabel vsFinal = new JLabel("VS", SwingConstants.CENTER);
        vsFinal.setFont(new Font("Arial", Font.BOLD, 10));
        enfrentamientoFinal.add(vsFinal);

        enfrentamientoFinal.add(botonesFinal[1]);
        panelFinal.add(enfrentamientoFinal);

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

    private void asignarEventos() {
        // Aquí puedes agregar los ActionListeners para cada botón
        // Ejemplo para botones de octavos:
        for (int i = 0; i < 16; i++) {
            final int indice = i;
            botonesOctavos[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica para manejar selección de ganador en octavos
                    manejarSeleccionOctavos(indice);
                }
            });
        }

        // Ejemplo para botones de cuartos:
        for (int i = 0; i < 8; i++) {
            final int indice = i;
            botonesCuartos[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica para manejar selección de ganador en cuartos
                    manejarSeleccionCuartos(indice);
                }
            });
        }

        // Ejemplo para botones de semifinales:
        for (int i = 0; i < 4; i++) {
            final int indice = i;
            botonesSemifinales[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica para manejar selección de ganador en semifinales
                    manejarSeleccionSemifinales(indice);
                }
            });
        }

        // Ejemplo para botones de final:
        for (int i = 0; i < 2; i++) {
            final int indice = i;
            botonesFinal[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica para manejar selección de ganador en final
                    manejarSeleccionFinal(indice);
                }
            });
        }

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

    protected void inicializarEquipos() {
        // Asignar nombres iniciales a los botones de octavos
        for (int i = 0; i < 16; i++) {
            botonesOctavos[i].setText(equiposIniciales[i]);
        }
    }

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

    private void confirmarTorneo() {
        // TODO: Implementar lógica de confirmación
        System.out.println("Confirmar torneo");
    }

    private void reiniciarTorneo() {
        // TODO: Implementar lógica de reinicio
        System.out.println("Reiniciar torneo");
    }

    // Métodos auxiliares para acceder a los componentes desde fuera de la clase
    public JButton[] getBotonesOctavos() { return botonesOctavos; }
    public JButton[] getBotonesCuartos() { return botonesCuartos; }
    public JButton[] getBotonesSemifinales() { return botonesSemifinales; }
    public JButton[] getBotonesFinal() { return botonesFinal; }
    public JLabel getLabelCampeon() { return labelCampeon; }
    public JButton getBtnConfirmar() { return btnConfirmar; }
    public JButton getBtnReiniciar() { return btnReiniciar; }
    public JLabel getLabelEstadoCuartos() { return labelEstadoCuartos; }
    public JLabel getLabelEstadoSemifinales() { return labelEstadoSemifinales; }
    public JLabel getLabelEstadoFinalistas() { return labelEstadoFinalistas; }
    public JLabel getLabelEstadoCampeon() { return labelEstadoCampeon; }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                new VentanaCampeonato16().setVisible(true);
            }
        });
    }
}