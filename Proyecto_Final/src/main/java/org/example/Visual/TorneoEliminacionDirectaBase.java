package org.example.Visual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Clase base abstracta
public abstract class TorneoEliminacionDirectaBase extends JFrame {

    // Componentes comunes
    protected JPanel panelPrincipal;
    protected JPanel panelTorneo;
    protected JPanel panelControles;
    protected JPanel panelEstado;
    protected JPanel panelCampeon;

    protected JButton btnConfirmar;
    protected JButton btnReiniciar;
    protected JLabel labelCampeon;

    // Listas para manejar todas las rondas dinámicamente
    protected List<JPanel> panelesRondas;
    protected List<JButton[]> botonesRondas;
    protected List<JLabel> labelsEstado;

    protected String[] equiposIniciales;
    protected boolean torneoConfirmado = false;

    // Constructor
    public TorneoEliminacionDirectaBase() {
        panelesRondas = new ArrayList<>();
        botonesRondas = new ArrayList<>();
        labelsEstado = new ArrayList<>();

        configurarVentana();
        inicializarEquipos();
        inicializarComponentesComunes();
        inicializarRondas(); // Método abstracto
        configurarLayout();
        asignarEventos();
        poblarEquiposIniciales();
    }

    // Métodos abstractos que deben implementar las subclases
    protected abstract void inicializarEquipos();
    protected abstract void inicializarRondas();
    protected abstract int getNumeroEquipos();
    protected abstract int getNumeroRondas();
    protected abstract String[] getNombresRondas();

    // Métodos comunes
    private void configurarVentana() {
        setTitle("Gestión de Torneo - Eliminación Directa (" + getNumeroEquipos() + " Equipos)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));
    }

    protected void inicializarComponentesComunes() {
        // Panel principal
        panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(240, 248, 255));

        // Panel del torneo
        panelTorneo = new JPanel();
        panelTorneo.setLayout(new GridLayout(1, getNumeroRondas() + 1, 10, 10));

        // Panel de campeón
        panelCampeon = new JPanel(new FlowLayout());
        panelCampeon.setBorder(BorderFactory.createTitledBorder("CAMPEÓN"));
        panelCampeon.setBackground(new Color(255, 215, 0));

        labelCampeon = new JLabel("Esperando campeón...");
        labelCampeon.setFont(new Font("Arial", Font.BOLD, 18));
        labelCampeon.setHorizontalAlignment(SwingConstants.CENTER);
        labelCampeon.setPreferredSize(new Dimension(250, 60));
        labelCampeon.setBorder(BorderFactory.createEtchedBorder());
        panelCampeon.add(labelCampeon);

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
        panelEstado = new JPanel();
        panelEstado.setLayout(new GridLayout(1, getNumeroRondas() - 1, 10, 5));
        panelEstado.setBorder(BorderFactory.createTitledBorder("Estado del Torneo"));
        panelEstado.setBackground(Color.WHITE);
    }

    protected JPanel crearPanelRonda(String titulo, int numEnfrentamientos, boolean habilitado) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(numEnfrentamientos, 1, 5, 10));
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        panel.setBackground(Color.WHITE);
        return panel;
    }

    protected JButton[] crearBotonesRonda(int numBotones, boolean habilitado) {
        JButton[] botones = new JButton[numBotones];
        // Para los equipos iniciales
        for (int i = 0; i < numBotones; i++) {
            botones[i] = new JButton(habilitado ? equiposIniciales[i] : "Esperando...");
            botones[i].setPreferredSize(new Dimension(180, 40));
            botones[i].setFont(new Font("Arial", Font.BOLD, 12));
            botones[i].setBackground(Color.LIGHT_GRAY);
            botones[i].setEnabled(habilitado);
            botones[i].setFocusPainted(false);
        }
        return botones;
    }

    protected JPanel crearEnfrentamiento(JButton equipo1, JButton equipo2) {
        JPanel enfrentamiento = new JPanel(new GridLayout(3, 1, 2, 2));
        enfrentamiento.add(equipo1);

        JLabel vs = new JLabel("VS", SwingConstants.CENTER);
        vs.setFont(new Font("Arial", Font.BOLD, 10));
        enfrentamiento.add(vs);

        enfrentamiento.add(equipo2);
        return enfrentamiento;
    }

    protected JLabel crearLabelEstado(String nombreRonda, int actual, int total) {
        JLabel label = new JLabel(nombreRonda + actual + "/" + total);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEtchedBorder());
        return label;
    }

    private void configurarLayout() {
        // Agregar paneles de rondas al panel del torneo
        for (JPanel panel : panelesRondas) {
            panelTorneo.add(panel);
        }
        panelTorneo.add(panelCampeon);

        // Agregar labels de estado
        for (JLabel label : labelsEstado) {
            panelEstado.add(label);
        }

        // Controles
        panelControles.add(btnReiniciar);
        panelControles.add(Box.createHorizontalStrut(20));
        panelControles.add(btnConfirmar);

        // Layout principal
        JPanel panelSur = new JPanel(new BorderLayout());
        panelSur.add(panelControles, BorderLayout.NORTH);
        panelSur.add(panelEstado, BorderLayout.SOUTH);

        panelPrincipal.add(panelTorneo, BorderLayout.CENTER);
        panelPrincipal.add(panelSur, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private void asignarEventos() {
        // Asignar eventos a todos los botones de todas las rondas
        for (int ronda = 0; ronda < botonesRondas.size(); ronda++) {
            JButton[] botones = botonesRondas.get(ronda);
            for (int i = 0; i < botones.length; i++) {
                final int rondaFinal = ronda;
                final int indiceFinal = i;
                botones[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manejarSeleccion(rondaFinal, indiceFinal);
                    }
                });
            }
        }

        btnConfirmar.addActionListener(e -> confirmarTorneo());
        btnReiniciar.addActionListener(e -> reiniciarTorneo());
    }

    private void poblarEquiposIniciales() {
        if (!botonesRondas.isEmpty()) {
            JButton[] primeraRonda = botonesRondas.getFirst();
            for (int i = 0; i < equiposIniciales.length && i < primeraRonda.length; i++) {
                primeraRonda[i].setText(equiposIniciales[i]);
            }
        }
    }

    // Métodos que pueden ser sobrescritos por las subclases
    // Método principal para manejar la selección de equipos ganadores
    protected void manejarSeleccion(int ronda, int indice) {
        if (torneoConfirmado) return;

        // Obtener el equipo ganador
        JButton equipoGanador = botonesRondas.get(ronda)[indice];
        String nombreGanador = equipoGanador.getText();

        // Verificar que el equipo tiene nombre válido
        if (nombreGanador.equals("Esperando...") || nombreGanador.trim().isEmpty()) {
            return;
        }

        System.out.println("Selección en ronda " + (ronda + 1) + ", equipo " + nombreGanador);

        // Pasar el ganador a la siguiente ronda
        pasarGanadorSiguienteRonda(ronda, indice, nombreGanador);

        // Actualizar el estado del torneo
        actualizarEstadoTorneo();
    }

    // Método para pasar el ganador a la siguiente ronda
    protected void pasarGanadorSiguienteRonda(int ronda, int indice, String nombreGanador) {
        // Verificar que existe una ronda siguiente
        if (ronda + 1 >= botonesRondas.size()) {
            return;
        }

        // Calcular la posición en la siguiente ronda
        int enfrentamiento = indice / 2;
        int posicionSiguienteRonda = enfrentamiento;

        // Obtener los botones de la siguiente ronda
        JButton[] siguienteRonda = botonesRondas.get(ronda + 1);

        // Verificar que la posición es válida
        if (posicionSiguienteRonda < siguienteRonda.length) {
            // Asignar el ganador a la siguiente ronda
            siguienteRonda[posicionSiguienteRonda].setText(nombreGanador);
            siguienteRonda[posicionSiguienteRonda].setEnabled(true);
            siguienteRonda[posicionSiguienteRonda].setBackground(Color.LIGHT_GRAY);
            siguienteRonda[posicionSiguienteRonda].setForeground(Color.BLACK);
        }
    }

    // Método para actualizar el estado del torneo
    protected void actualizarEstadoTorneo() {
        String[] nombresRondas = getNombresRondas();

        for (int i = 0; i < labelsEstado.size(); i++) {
            int rondaActual = i + 1; // Las rondas en labelsEstado empiezan desde la segunda
            // int equiposCompletados = contarEquiposCompletados(rondaActual);
            int totalEquipos = getNumeroEquipos() / (int)Math.pow(2, rondaActual);

            // Actualizar el label de estado
            labelsEstado.get(i).setText(nombresRondas[rondaActual-1]);
        }
    }

    protected void confirmarTorneo() {
        if (!labelCampeon.getText().equals("Esperando campeón...")) {
            torneoConfirmado = true;
            btnConfirmar.setEnabled(false);
            JOptionPane.showMessageDialog(this, "¡Torneo confirmado!\nCampeón: " + labelCampeon.getText());
        } else {
            JOptionPane.showMessageDialog(this, "Debe completar todo el torneo antes de confirmar.");
        }
    }

    protected void reiniciarTorneo() {
        torneoConfirmado = false;
        btnConfirmar.setEnabled(false);
        labelCampeon.setText("Esperando campeón...");

        // Reiniciar primera ronda
        if (!botonesRondas.isEmpty()) {
            JButton[] primeraRonda = botonesRondas.getFirst();
            for (int i = 0; i < equiposIniciales.length && i < primeraRonda.length; i++) {
                primeraRonda[i].setText(equiposIniciales[i]);
                primeraRonda[i].setEnabled(true);
                primeraRonda[i].setBackground(Color.LIGHT_GRAY);
            }
        }

        // Reiniciar rondas siguientes
        for (int ronda = 1; ronda < botonesRondas.size(); ronda++) {
            JButton[] botones = botonesRondas.get(ronda);
            for (JButton boton : botones) {
                boton.setText("Esperando...");
                boton.setEnabled(false);
                boton.setBackground(Color.LIGHT_GRAY);
            }
        }

        // Reiniciar labels de estado
        String[] nombresRondas = getNombresRondas();
        for (int i = 0; i < labelsEstado.size(); i++) {
            int equiposPorRonda = getNumeroEquipos() / (int)Math.pow(2, i + 1);
            labelsEstado.get(i).setText("<html><center>" + nombresRondas[i + 1] + "<br>0/" + equiposPorRonda + "</center></html>");
        }
    }

    protected void pasarSiguienteRonda(){
    }

    // Getters para acceso externo
    public List<JButton[]> getBotonesRondas() { return botonesRondas; }
    public JLabel getLabelCampeon() { return labelCampeon; }
    public JButton getBtnConfirmar() { return btnConfirmar; }
    public JButton getBtnReiniciar() { return btnReiniciar; }
    public List<JLabel> getLabelsEstado() { return labelsEstado; }
}

// Implementación para 16 equipos
class Torneo16Equipos extends TorneoEliminacionDirectaBase {

    @Override
    protected void inicializarEquipos() {
        equiposIniciales = new String[] {
                "Equipo Alpha", "Equipo Beta", "Equipo Gamma", "Equipo Delta",
                "Equipo Epsilon", "Equipo Zeta", "Equipo Eta", "Equipo Theta",
                "Equipo Iota", "Equipo Kappa", "Equipo Lambda", "Equipo Mu",
                "Equipo Nu", "Equipo Xi", "Equipo Omicron", "Equipo Pi"
        };
    }

    @Override
    protected void inicializarRondas() {
        String[] nombresRondas = getNombresRondas();

        // Octavos de final (16 equipos)
        JPanel panelOctavos = crearPanelRonda(nombresRondas[0], 8, true);
        JButton[] botonesOctavos = crearBotonesRonda(16, true);
        for (int i = 0; i < 8; i++) {
            panelOctavos.add(crearEnfrentamiento(botonesOctavos[i * 2], botonesOctavos[i * 2 + 1]));
        }
        panelesRondas.add(panelOctavos);
        botonesRondas.add(botonesOctavos);

        // Cuartos de final (8 equipos)
        JPanel panelCuartos = crearPanelRonda(nombresRondas[1], 4, false);
        JButton[] botonesCuartos = crearBotonesRonda(8, false);
        for (int i = 0; i < 4; i++) {
            panelCuartos.add(crearEnfrentamiento(botonesCuartos[i * 2], botonesCuartos[i * 2 + 1]));
        }
        panelesRondas.add(panelCuartos);
        botonesRondas.add(botonesCuartos);

        // Semifinales (4 equipos)
        JPanel panelSemifinales = crearPanelRonda(nombresRondas[2], 2, false);
        JButton[] botonesSemifinales = crearBotonesRonda(4, false);
        for (int i = 0; i < 2; i++) {
            panelSemifinales.add(crearEnfrentamiento(botonesSemifinales[i * 2], botonesSemifinales[i * 2 + 1]));
        }
        panelesRondas.add(panelSemifinales);
        botonesRondas.add(botonesSemifinales);

        // Final (2 equipos)
        JPanel panelFinal = crearPanelRonda(nombresRondas[3], 1, false);
        JButton[] botonesFinal = crearBotonesRonda(2, false);
        panelFinal.add(crearEnfrentamiento(botonesFinal[0], botonesFinal[1]));
        panelesRondas.add(panelFinal);
        botonesRondas.add(botonesFinal);

        // Crear labels de estado
        labelsEstado.add(crearLabelEstado("Cuartos", 0, 8));
        labelsEstado.add(crearLabelEstado("Semifinales", 0, 4));
        labelsEstado.add(crearLabelEstado("Finalistas", 0, 2));
        labelsEstado.add(crearLabelEstado("Campeón", 0, 1));
    }

    @Override
    protected int getNumeroEquipos() { return 16; }

    @Override
    protected int getNumeroRondas() { return 4; }

    @Override
    protected String[] getNombresRondas() {
        return new String[] {"OCTAVOS DE FINAL", "CUARTOS DE FINAL", "SEMIFINALES", "FINAL"};
    }
}

// Implementación para 8 equipos
class Torneo8Equipos extends TorneoEliminacionDirectaBase {

    @Override
    protected void inicializarEquipos() {
        equiposIniciales = new String[] {
                "Equipo A", "Equipo B", "Equipo C", "Equipo D",
                "Equipo E", "Equipo F", "Equipo G", "Equipo H"
        };
    }

    @Override
    protected void inicializarRondas() {
        String[] nombresRondas = getNombresRondas();

        // Cuartos de final (8 equipos)
        JPanel panelCuartos = crearPanelRonda(nombresRondas[0], 4, true);
        JButton[] botonesCuartos = crearBotonesRonda(8, true);
        for (int i = 0; i < 4; i++) {
            panelCuartos.add(crearEnfrentamiento(botonesCuartos[i * 2], botonesCuartos[i * 2 + 1]));
        }
        panelesRondas.add(panelCuartos);
        botonesRondas.add(botonesCuartos);

        // Semifinales (4 equipos)
        JPanel panelSemifinales = crearPanelRonda(nombresRondas[1], 2, false);
        JButton[] botonesSemifinales = crearBotonesRonda(4, false);
        for (int i = 0; i < 2; i++) {
            panelSemifinales.add(crearEnfrentamiento(botonesSemifinales[i * 2], botonesSemifinales[i * 2 + 1]));
        }
        panelesRondas.add(panelSemifinales);
        botonesRondas.add(botonesSemifinales);

        // Final (2 equipos)
        JPanel panelFinal = crearPanelRonda(nombresRondas[2], 1, false);
        JButton[] botonesFinal = crearBotonesRonda(2, false);
        panelFinal.add(crearEnfrentamiento(botonesFinal[0], botonesFinal[1]));
        panelesRondas.add(panelFinal);
        botonesRondas.add(botonesFinal);

        // Crear labels de estado
        labelsEstado.add(crearLabelEstado("Semifinales", 0, 4));
        labelsEstado.add(crearLabelEstado("Finalistas", 0, 2));
        labelsEstado.add(crearLabelEstado("Campeón", 0, 1));
    }

    @Override
    protected int getNumeroEquipos() { return 8; }

    @Override
    protected int getNumeroRondas() { return 3; }

    @Override
    protected String[] getNombresRondas() {
        return new String[] {"CUARTOS DE FINAL", "SEMIFINALES", "FINAL"};
    }
}

// Implementación para 4 equipos
class Torneo4Equipos extends TorneoEliminacionDirectaBase {

    /**
     * Se sobreescribirá después con la lista de inscritos
     */

    @Override
    protected void inicializarEquipos() {
        equiposIniciales = new String[] {
                "Equipo 1", "Equipo 2", "Equipo 3", "Equipo 4"
        };
    }

    @Override
    protected void inicializarRondas() {
        String[] nombresRondas = getNombresRondas();

        // Semifinales (4 equipos)
        JPanel panelSemifinales = crearPanelRonda(nombresRondas[0], 2, true);
        JButton[] botonesSemifinales = crearBotonesRonda(4, true);
        for (int i = 0; i < 2; i++) {
            panelSemifinales.add(crearEnfrentamiento(botonesSemifinales[i * 2], botonesSemifinales[i * 2 + 1]));
        }
        panelesRondas.add(panelSemifinales);
        botonesRondas.add(botonesSemifinales);

        // Final (2 equipos)
        JPanel panelFinal = crearPanelRonda(nombresRondas[1], 1, false);
        JButton[] botonesFinal = crearBotonesRonda(2, false);
        panelFinal.add(crearEnfrentamiento(botonesFinal[0], botonesFinal[1]));
        panelesRondas.add(panelFinal);
        botonesRondas.add(botonesFinal);

        // Crear labels de estado
        labelsEstado.add(crearLabelEstado("Finalistas", 0, 2));
        labelsEstado.add(crearLabelEstado("Campeón", 0, 1));
    }

    @Override
    protected int getNumeroEquipos() { return 4; }

    @Override
    protected int getNumeroRondas() { return 2; }

    @Override
    protected String[] getNombresRondas() {
        return new String[] {"SEMIFINALES", "FINAL"};
    }
}

// Clase para probar
class TestTorneos {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Crear diferentes tipos de torneo
            //new Torneo8Equipos().setVisible(true);
            new Torneo16Equipos().setVisible(true);
        });
    }
}
/** Nota: Para hacer esta clase mas robusta se podría hacer que no dejara confirmar
 * hasta que el torneo esté lleno
 *
 * Hay problemas para confirmar y definir un ganador
 *
 */
