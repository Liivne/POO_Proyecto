/**
 * Clase base para representar un torneo de eliminación directa.
 * Este torneo permite la creación de rondas en las que los participantes
 * se enfrentan en partidas directas. Los ganadores avanzan a la siguiente
 * ronda hasta que se determina un campeón.
 */

package org.example.Visual;

import org.example.Logica.Equipo;
import org.example.Logica.Torneo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;

import static org.example.Logica.FORMATO.CAMPEONATO;
import static org.example.Logica.TIPOPARTICIPANTES.ENEQUIPOS;

// Clase base abstracta
public abstract class TorneoEliminacionDirectaBase extends JFrame {

    protected Torneo torneo;

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

    /**
     * Constructor de la clase TorneoEliminacionDirectaBase.
     * Inicializa la ventana y los componentes gráficos.
     *
     * @param torneo El objeto Torneo con los datos y participantes.
     */
    public TorneoEliminacionDirectaBase(Torneo torneo) {
        this.torneo = torneo;
        panelesRondas = new ArrayList<>();
        botonesRondas = new ArrayList<>();
        labelsEstado = new ArrayList<>();

        configurarVentana();
        inicializarEquiposDesdeTorneo();
        inicializarComponentesComunes();
        inicializarRondas();
        configurarLayout();
        asignarEventos();
        poblarEquiposIniciales();
    }

    /**
     * Inicializa los equipos participantes desde el objeto Torneo
     *
     * @throws IllegalArgumentException si el número de participantes
     * no coincide con el número esperado de equipos.
     */
    protected void inicializarEquiposDesdeTorneo() {
        List<String> nombresParticipantes = torneo.getListaParticipantes().stream()
                .map(p -> p.getNombre())
                .collect(Collectors.toList());

        Collections.shuffle(nombresParticipantes);

        int numEquipos = getNumeroEquipos();

        if (nombresParticipantes.size() != numEquipos) {
            JOptionPane.showMessageDialog(this,
                    "El número de participantes no coincide con el tipo de torneo (" +
                            nombresParticipantes.size() + "/" + numEquipos + ").",
                    "Error", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Número de participantes incorrecto.");
        }

        this.equiposIniciales = nombresParticipantes.toArray(new String[0]);
    }

    protected abstract void inicializarRondas();
    protected abstract int getNumeroEquipos();
    protected abstract int getNumeroRondas();
    protected abstract String[] getNombresRondas();

    /**
     * Configura las propiedades básicas de la ventana.
     */
    private void configurarVentana() {
        setTitle("Gestión de Torneo - Eliminación Directa (" + getNumeroEquipos() + " Equipos)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));
    }

    /**
     * Inicializa los paneles y botones comunes (campeón, controles, estado).
     */
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

    /**
     * Crea un panel para una ronda del torneo.
     *
     * @param titulo Título del panel.
     * @param numEnfrentamientos Número de enfrentamientos en la ronda.
     * @param habilitado Si los botones de esta ronda deben estar habilitados al inicio.
     * @return JPanel con el diseño de la ronda.
     */
    protected JPanel crearPanelRonda(String titulo, int numEnfrentamientos, boolean habilitado) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(numEnfrentamientos, 1, 5, 10));
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        panel.setBackground(Color.WHITE);
        return panel;
    }

    /**
     * Crea un arreglo de botones para representar equipos en una ronda.
     *
     * @param numBotones Cantidad de botones a crear.
     * @param habilitado Estado inicial de habilitación de los botones.
     * @return Arreglo de botones.
     */
    protected JButton[] crearBotonesRonda(int numBotones, boolean habilitado) {
        JButton[] botones = new JButton[numBotones];
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

    /**
     * Crea un panel de enfrentamiento con dos botones y un texto "VS" entre ellos.
     *
     * @param equipo1 Botón del primer equipo.
     * @param equipo2 Botón del segundo equipo.
     * @return JPanel con el enfrentamiento.
     */
    protected JPanel crearEnfrentamiento(JButton equipo1, JButton equipo2) {
        JPanel enfrentamiento = new JPanel(new GridLayout(3, 1, 2, 2));
        enfrentamiento.add(equipo1);

        JLabel vs = new JLabel("VS", SwingConstants.CENTER);
        vs.setFont(new Font("Arial", Font.BOLD, 10));
        enfrentamiento.add(vs);

        enfrentamiento.add(equipo2);
        return enfrentamiento;
    }

    /**
     * Crea una etiqueta que muestra el estado de una ronda específica.
     *
     * @param nombreRonda Nombre de la ronda.
     * @param actual Número actual de enfrentamientos jugados.
     * @param total Total de enfrentamientos.
     * @return JLabel con el estado de la ronda.
     */
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

    /**
     * Maneja el evento cuando un botón es presionado en una ronda.
     *
     * @param ronda Índice de la ronda.
     * @param indice Índice del botón presionado en la ronda.
     */
    protected void manejarSeleccion(int ronda, int indice) {
        if (torneoConfirmado) return;

        // Obtener el equipo ganador
        JButton equipoGanador = botonesRondas.get(ronda)[indice];
        String nombreGanador = equipoGanador.getText();

        // Verificar que el equipo tiene nombre válido
        if (nombreGanador.equals("Esperando...") || nombreGanador.trim().isEmpty()) {
            return;
        }
        // Pasar el ganador a la siguiente ronda
        pasarGanadorSiguienteRonda(ronda, indice, nombreGanador);

        // Actualizar el estado del torneo
        actualizarEstadoTorneo();
    }

    /**
     * Pasa el equipo ganador a la siguiente ronda o lo declara campeón si es la final.
     *
     * @param ronda Índice de la ronda actual.
     * @param indice Índice del botón del equipo ganador.
     * @param nombreGanador Nombre del equipo ganador.
     */
    protected void pasarGanadorSiguienteRonda(int ronda, int indice, String nombreGanador) {
        // Verificar que existe una ronda siguiente
        if (ronda + 1 >= botonesRondas.size()) {
            // Si es la última ronda, es el campeón
            labelCampeon.setText(nombreGanador);
            btnConfirmar.setEnabled(true);
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

    //Método para actualizar el estado del torneo
    protected void actualizarEstadoTorneo() {
        String[] nombresRondas = getNombresRondas();

        for (int i = 0; i < labelsEstado.size(); i++) {
            int rondaActual = i + 1; // Las rondas en labelsEstado empiezan desde la segunda

            // Contar los equipos que ya tienen nombre asignado
            long equiposCompletados = 0;
            if (rondaActual < botonesRondas.size()) { // Asegurarse de que no sea la ronda de campeón
                equiposCompletados = botonesRondas.get(rondaActual)[0].getParent().getComponents().length / 2;
            }

            int totalEquipos = getNumeroEquipos() / (int)Math.pow(2, rondaActual);

            // Actualizar el label de estado
            labelsEstado.get(i).setText("<html><center>" + nombresRondas[rondaActual-1] + "<br>" + equiposCompletados + "/" + totalEquipos + "</center></html>");
        }
    }

    /**
     * Confirma el torneo una vez que se ha definido un campeón.
     */
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
            labelsEstado.get(i).setText("<html><center>" + nombresRondas[i] + "<br>0/" + equiposPorRonda + "</center></html>");
        }
    }

    // Getters para acceso externo
    public List<JButton[]> getBotonesRondas() { return botonesRondas; }
    public JLabel getLabelCampeon() { return labelCampeon; }
    public JButton getBtnConfirmar() { return btnConfirmar; }
    public JButton getBtnReiniciar() { return btnReiniciar; }
    public List<JLabel> getLabelsEstado() { return labelsEstado; }
}

//Implementación para 16 equipos
class Torneo16Equipos extends TorneoEliminacionDirectaBase {
    public Torneo16Equipos(Torneo torneo) {
        super(torneo);
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
    @Override protected int getNumeroEquipos() { return 16; }
    @Override protected int getNumeroRondas() { return 4; }
    @Override protected String[] getNombresRondas() {
        return new String[] {"OCTAVOS DE FINAL", "CUARTOS DE FINAL", "SEMIFINALES", "FINAL"};
    }
}

//Implementación para 8 equipos
class Torneo8Equipos extends TorneoEliminacionDirectaBase {
    public Torneo8Equipos(Torneo torneo) {
        super(torneo);
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
    @Override protected int getNumeroEquipos() { return 8; }
    @Override protected int getNumeroRondas() { return 3; }
    @Override protected String[] getNombresRondas() {
        return new String[] {"CUARTOS DE FINAL", "SEMIFINALES", "FINAL"};
    }
}

//Implementación para 4 equipos
class Torneo4Equipos extends TorneoEliminacionDirectaBase {
    public Torneo4Equipos(Torneo torneo) {
        super(torneo);
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
    @Override protected int getNumeroEquipos() { return 4; }
    @Override protected int getNumeroRondas() { return 2; }
    @Override protected String[] getNombresRondas() {
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

            // Ejemplo para un torneo de 4 equipos
            Torneo torneoDeCuatro = new Torneo("Copa Primavera 2025", CAMPEONATO, ENEQUIPOS, LocalDate.of(2025, 7, 15),"Estadio Central","Fútbol");
            for (int i = 1; i <= 4; i++) {
                torneoDeCuatro.addParticipantes(new Equipo("Equipo " + i, "correo" + i + "@test.com"));
            }
            new Torneo4Equipos(torneoDeCuatro).setVisible(true);
        });
    }
}