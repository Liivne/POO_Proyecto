package org.example.Visual;

import org.example.Logica.Editor;
import org.example.Logica.FORMATO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import static org.example.Logica.FORMATO.*;

public class VentanaAdministrarOrganizador extends JFrame {
    private Editor editor;
    public VentanaAdministrarOrganizador(Editor editor) {
        this.editor = editor;
        initComponents();
        setupWindow();
        cargarDatosPrueba();
    }

    private void setupWindow() {
        setSize(900, 650);
        setLocationRelativeTo(null);
        setResizable(true);
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

    // Componentes principales
    private JTabbedPane pestanas;
    private JTable tablaTorneos;
    private DefaultTableModel modeloTorneos;
    private JTextField txtBuscar;
    private JComboBox<String> cbFiltroDeporte;
    private JComboBox<String> cbFiltroEstado;

    private void initComponents() {
        setTitle("Competidor - Torneos y Resultados");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior con título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(30, 144, 255));
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titulo = new JLabel("PANEL DE ADMNISTRADOR");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        panelTitulo.add(titulo);

        // Crear pestañas
        pestanas = new JTabbedPane();
        pestanas.setFont(new Font("Arial", Font.BOLD, 12));

        // Pestaña de Torneos Disponibles
        JPanel panelTorneos = crearPanelTorneos();
        pestanas.addTab("Mis Torneos ", new ImageIcon(), panelTorneos, "Ver torneos");


        // Pestaña de Mis Torneos
        JPanel panelMisTorneos = crearPanelMisTorneos();
        pestanas.addTab("Perfil", new ImageIcon(), panelMisTorneos, "Torneos en los que participo");

        add(panelTitulo, BorderLayout.NORTH);
        add(pestanas, BorderLayout.CENTER);
    }

    private JPanel crearPanelTorneos() {
        // Panel contenedor principal con posicionamiento absoluto para el botón circular
        JPanel panelContenedor = new JPanel();
        panelContenedor.setLayout(new OverlayLayout(panelContenedor));

        // Panel base que contiene el contenido principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Panel de filtros
        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltros.setBackground(new Color(240, 248, 255));
        panelFiltros.setBorder(BorderFactory.createTitledBorder("Filtros de búsqueda"));

        panelFiltros.add(new JLabel("Buscar:"));
        txtBuscar = new JTextField(15);
        panelFiltros.add(txtBuscar);

        panelFiltros.add(Box.createHorizontalStrut(10));
        panelFiltros.add(new JLabel("Deporte:"));
        String[] deportes = {"Todos", "Fútbol", "Baloncesto", "Tenis", "Volleyball", "Ping Pong", "Ajedrez"};
        cbFiltroDeporte = new JComboBox<>(deportes);
        panelFiltros.add(cbFiltroDeporte);

        panelFiltros.add(Box.createHorizontalStrut(10));
        panelFiltros.add(new JLabel("Estado:"));
        String[] estados = {"Todos", "Inscripción Abierta", "En Curso", "Finalizado"};
        cbFiltroEstado = new JComboBox<>(estados);
        panelFiltros.add(cbFiltroEstado);


        JButton btnFiltrar = crearBotonPequeno("Filtrar", new Color(30, 144, 255));
        panelFiltros.add(btnFiltrar);

        // Tabla de torneos
        String[] columnasTorneos = {"Nombre", "Deporte", "Fecha", "Lugar", "Formato", "Participantes", "Estado", "Premio"};
        modeloTorneos = new DefaultTableModel(columnasTorneos, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Solo lectura
            }
        };

        tablaTorneos = new JTable(modeloTorneos);
        tablaTorneos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaTorneos.setRowHeight(25);
        tablaTorneos.getTableHeader().setBackground(new Color(70, 130, 180));
        tablaTorneos.getTableHeader().setForeground(Color.WHITE);
        tablaTorneos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 11));

        // Configurar ancho de columnas
        tablaTorneos.getColumnModel().getColumn(0).setPreferredWidth(150); // Nombre
        tablaTorneos.getColumnModel().getColumn(1).setPreferredWidth(80);  // Deporte
        tablaTorneos.getColumnModel().getColumn(2).setPreferredWidth(80);  // Fecha
        tablaTorneos.getColumnModel().getColumn(3).setPreferredWidth(100); // Lugar
        tablaTorneos.getColumnModel().getColumn(4).setPreferredWidth(120); // Formato
        tablaTorneos.getColumnModel().getColumn(5).setPreferredWidth(80);  // Participantes
        tablaTorneos.getColumnModel().getColumn(6).setPreferredWidth(100); // Estado
        tablaTorneos.getColumnModel().getColumn(7).setPreferredWidth(100); // Premio

        JScrollPane scrollTorneos = new JScrollPane(tablaTorneos);
        scrollTorneos.setPreferredSize(new Dimension(800, 300));

        // Panel de botones para torneos
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnAdministrar = crearBoton("Subir Resultados", new Color(34, 139, 34));
        JButton btnVerDetalles = crearBoton("Ver Detalles", new Color(255, 140, 0));
        JButton btnActualizar = crearBoton("Actualizar", new Color(70, 130, 180));

        // btnAdministrar.addActionListener(e -> actualizarResultados());
        btnVerDetalles.addActionListener(e -> verDetallesTorneo());
        btnActualizar.addActionListener(e -> actualizarTorneos());

        panelBotones.add(btnAdministrar);
        panelBotones.add(btnVerDetalles);
        panelBotones.add(btnActualizar);

        panel.add(panelFiltros, BorderLayout.NORTH);
        panel.add(scrollTorneos, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        // Crear el botón circular simple con el signo +
        JButton btnCircular = crearBotonCrearTorneos();

        // Panel para posicionar el botón circular en la esquina inferior derecha
        JPanel panelBotonCircular = new JPanel();
        panelBotonCircular.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        panelBotonCircular.setOpaque(false);
        panelBotonCircular.add(btnCircular);

        // Crear un panel auxiliar para alinear el botón en la parte inferior
        JPanel panelAux = new JPanel(new BorderLayout());
        panelAux.setOpaque(false);
        panelAux.add(panelBotonCircular, BorderLayout.SOUTH);

        // Agregar ambos paneles al contenedor con overlay
        panelContenedor.add(panel);
        panelContenedor.add(panelAux);

        return panelContenedor;
    }

    private JPanel crearPanelMisTorneos() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel de información personal
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBackground(new Color(245, 245, 220));
        panelInfo.setBorder(BorderFactory.createTitledBorder("Mi Información"));

        JLabel lblNombre = new JLabel("Nombre: " + editor.getNombre_Usuario());
        JLabel lblTorneosActivos = new JLabel("Torneos Activos: 0");
        JLabel lblTorneosCompletados = new JLabel("Torneos Completados: 0");
        JLabel lblVictorias = new JLabel("Victorias: 0");

        lblNombre.setFont(new Font("Arial", Font.BOLD, 12));

        panelInfo.add(Box.createRigidArea(new Dimension(0, 10)));
        panelInfo.add(lblNombre);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 5)));
        panelInfo.add(lblTorneosActivos);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 5)));
        panelInfo.add(lblTorneosCompletados);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 5)));
        panelInfo.add(lblVictorias);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 10)));



        // Panel de botones
        JPanel panelBotonesMis = new JPanel(new FlowLayout());

        panel.add(panelInfo, BorderLayout.NORTH);
        panel.add(panelBotonesMis, BorderLayout.SOUTH);

        return panel;
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 11));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setBorder(BorderFactory.createRaisedBevelBorder());
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(120, 30));

        // Efecto hover
        Color colorHover = color.darker();
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorHover);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(color);
            }
        });

        return boton;
    }

    private JButton crearBotonPequeno(String texto, Color color) {
        JButton boton = crearBoton(texto, color);
        boton.setPreferredSize(new Dimension(80, 25));
        boton.setFont(new Font("Arial", Font.BOLD, 10));
        return boton;
    }

    private void cargarDatosPrueba() {
        // Datos de prueba para torneos
        // for(VentanaCrearTorneo(editor))
        modeloTorneos.addRow(new Object[]{"Copa Primavera 2025", "Fútbol", "15/07/2025", "Estadio Central", "Eliminatoria Directa", "12/16", "Inscripción Abierta", "$1000"});
        modeloTorneos.addRow(new Object[]{"Torneo Relámpago", "Ping Pong", "20/06/2025", "Club Deportivo", "Liga Simple", "6/8", "Inscripción Abierta", "Trofeo"});
        modeloTorneos.addRow(new Object[]{"Championship Basketball", "Baloncesto", "10/07/2025", "Polideportivo Norte", "Eliminatoria Doble", "8/16", "En Curso", "$500"});
        modeloTorneos.addRow(new Object[]{"Masters de Tenis", "Tenis", "01/06/2025", "Club de Tenis", "Round Robin", "16/16", "Finalizado", "Medalla"});
    }

    // Métodos de acción (prototipos)

    // A esta clase le falta reglamento y tipo de inscripción

    private void verDetallesTorneo() {
        int filaSeleccionada = tablaTorneos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un torneo.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder detalles = new StringBuilder();
        detalles.append("DETALLES DEL TORNEO\n\n");
        for (int i = 0; i < modeloTorneos.getColumnCount(); i++) {
            detalles.append(modeloTorneos.getColumnName(i)).append(": ")
                    .append(modeloTorneos.getValueAt(filaSeleccionada, i)).append("\n");
        }
        detalles.append("\nReglamento: Estándar internacional\n");
        detalles.append("Inscripción: Gratuita\n");

        JOptionPane.showMessageDialog(this, detalles.toString(), "Detalles del Torneo", JOptionPane.INFORMATION_MESSAGE);
    }


    private void actualizarTorneos() {
        JOptionPane.showMessageDialog(this, "Lista de torneos actualizada", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void verResultados() {
        JOptionPane.showMessageDialog(this, "Abriendo bracket del torneo (función en desarrollo)", "Información", JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * Crea un botón circular simple con el signo "+"
     */
    private JButton crearBotonCrearTorneos() {
        JButton btnCircular = new JButton("+");

        // Configurar el botón
        btnCircular.setPreferredSize(new Dimension(50, 50));
        btnCircular.setFont(new Font("Arial", Font.BOLD, 20));
        btnCircular.setForeground(Color.WHITE);
        btnCircular.setBackground(new Color(30, 144, 255));
        btnCircular.setBorder(BorderFactory.createEmptyBorder());
        btnCircular.setFocusPainted(false);
        btnCircular.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hacer el botón circular usando un borde redondeado
        btnCircular.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(30, 144, 255), 2),
                BorderFactory.createEmptyBorder()
        ));

        btnCircular.addActionListener(e -> abrirVentanaCrearTorneo());

        // Tooltip para el botón
        btnCircular.setToolTipText("Agregar nuevo torneo");

        return btnCircular;
    }
    private void abrirVentanaCrearTorneo() {
        new VentanaCrearTorneo(editor).setVisible(true);
    }



    // Método para probar la ventana independientemente
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            Editor e = new Editor("Link", "Hyrule");
            new VentanaAdministrarOrganizador(e).setVisible(true);
        });
    }
}
