package org.example.Visual;

import org.example.Logica.UsuarioBasico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class VentanaCompetidor extends JFrame {

    private UsuarioBasico usuario;

    // Componentes principales
    private JTabbedPane pestanas;
    private JTable tablaTorneos;
    private JTable tablaResultados;
    private DefaultTableModel modeloTorneos;
    private DefaultTableModel modeloResultados;
    private JTextField txtBuscar;
    private JComboBox<String> cbFiltroDeporte;
    private JComboBox<String> cbFiltroEstado;

    public VentanaCompetidor(UsuarioBasico usuario) {
        this.usuario = usuario;
        initComponents();
        setupWindow();
        cargarDatosPrueba();
    }

    private void initComponents() {
        setTitle("Competidor - Torneos y Resultados");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior con título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(30, 144, 255));
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titulo = new JLabel("PANEL DE COMPETIDOR");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        panelTitulo.add(titulo);

        // Crear pestañas
        pestanas = new JTabbedPane();
        pestanas.setFont(new Font("Arial", Font.BOLD, 12));

        // Pestaña de Torneos Disponibles
        JPanel panelTorneos = crearPanelTorneos();
        pestanas.addTab("Torneos Disponibles", new ImageIcon(), panelTorneos, "Ver y inscribirse a torneos");

        // Pestaña de Resultados
        JPanel panelResultados = crearPanelResultados();
        pestanas.addTab("Resultados", new ImageIcon(), panelResultados, "Ver resultados de torneos");

        // Pestaña de Mis Torneos
        JPanel panelMisTorneos = crearPanelMisTorneos();
        pestanas.addTab("Mis Torneos", new ImageIcon(), panelMisTorneos, "Torneos en los que participo");

        add(panelTitulo, BorderLayout.NORTH);
        add(pestanas, BorderLayout.CENTER);
    }

    private JPanel crearPanelTorneos() {
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
        JButton btnInscribirse = crearBoton("Inscribirse", new Color(34, 139, 34));
        JButton btnVerDetalles = crearBoton("Ver Detalles", new Color(255, 140, 0));
        JButton btnActualizar = crearBoton("Actualizar", new Color(70, 130, 180));
        JButton btnVerFechas = crearBoton("Ver Fechas Tentativas", new Color(150, 0, 0));

        btnInscribirse.addActionListener(e -> inscribirseEnTorneo());
        btnVerDetalles.addActionListener(e -> verDetallesCompetidor());
        btnActualizar.addActionListener(e -> actualizarTorneos());
        btnVerFechas.addActionListener(e -> verFechasTentativas());

        panelBotones.add(btnInscribirse);
        panelBotones.add(btnVerDetalles);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnVerFechas);

        panel.add(panelFiltros, BorderLayout.NORTH);
        panel.add(scrollTorneos, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelResultados() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Tabla de resultados
        String[] columnasResultados = {"Torneo", "Deporte", "Fecha", "Ganador", "Segundo", "Tercero", "Total Participantes"};
        modeloResultados = new DefaultTableModel(columnasResultados, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaResultados = new JTable(modeloResultados);
        tablaResultados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaResultados.setRowHeight(25);
        tablaResultados.getTableHeader().setBackground(new Color(160, 82, 45));
        tablaResultados.getTableHeader().setForeground(Color.WHITE);
        tablaResultados.getTableHeader().setFont(new Font("Arial", Font.BOLD, 11));

        // Configurar ancho de columnas
        tablaResultados.getColumnModel().getColumn(0).setPreferredWidth(150); // Torneo
        tablaResultados.getColumnModel().getColumn(1).setPreferredWidth(80);  // Deporte
        tablaResultados.getColumnModel().getColumn(2).setPreferredWidth(80);  // Fecha
        tablaResultados.getColumnModel().getColumn(3).setPreferredWidth(120); // Ganador
        tablaResultados.getColumnModel().getColumn(4).setPreferredWidth(120); // Segundo
        tablaResultados.getColumnModel().getColumn(5).setPreferredWidth(120); // Tercero
        tablaResultados.getColumnModel().getColumn(6).setPreferredWidth(100); // Participantes

        JScrollPane scrollResultados = new JScrollPane(tablaResultados);

        // Panel de botones para resultados
        JPanel panelBotonesResultados = new JPanel(new FlowLayout());
        JButton btnVerResultados = crearBoton("Ver Resultados", new Color(138, 43, 226));
        btnVerResultados .addActionListener(e -> verResultados());

        panelBotonesResultados.add(btnVerResultados );

        panel.add(scrollResultados, BorderLayout.CENTER);
        panel.add(panelBotonesResultados, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelMisTorneos() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel de información personal
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBackground(new Color(245, 245, 220));
        panelInfo.setBorder(BorderFactory.createTitledBorder("Mi Información"));

        JLabel lblNombre = new JLabel("Nombre: " + usuario.getNombre_Usuario());
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

        // Área de próximos partidos
        JTextArea txtProximosPartidos = new JTextArea(10, 50);
        txtProximosPartidos.setEditable(false);
        txtProximosPartidos.setText("PRÓXIMOS PARTIDOS:\n\n" +
                "No hay partidos programados.\n\n" +
                "Inscríbete en torneos desde la pestaña 'Torneos Disponibles' " +
                "para ver aquí tus próximos enfrentamientos.");
        txtProximosPartidos.setBackground(new Color(248, 248, 255));
        txtProximosPartidos.setBorder(BorderFactory.createTitledBorder("Calendario"));

        JScrollPane scrollPartidos = new JScrollPane(txtProximosPartidos);

        // Panel de botones
        JPanel panelBotonesMis = new JPanel(new FlowLayout());

        panel.add(panelInfo, BorderLayout.NORTH);
        panel.add(scrollPartidos, BorderLayout.CENTER);
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
        modeloTorneos.addRow(new Object[]{"Copa Primavera 2025", "Fútbol", "15/07/2025", "Estadio Central", "Eliminatoria Directa", "12/16", "Inscripción Abierta", "$1000"});
        modeloTorneos.addRow(new Object[]{"Torneo Relámpago", "Ping Pong", "20/06/2025", "Club Deportivo", "Liga Simple", "6/8", "Inscripción Abierta", "Trofeo"});
        modeloTorneos.addRow(new Object[]{"Championship Basketball", "Baloncesto", "10/07/2025", "Polideportivo Norte", "Eliminatoria Doble", "8/16", "En Curso", "$500"});
        modeloTorneos.addRow(new Object[]{"Masters de Tenis", "Tenis", "01/06/2025", "Club de Tenis", "Round Robin", "16/16", "Finalizado", "Medalla"});

        // Datos de prueba para resultados
        modeloResultados.addRow(new Object[]{"Torneo Invierno 2024", "Fútbol", "15/12/2024", "Águilas FC", "Leones SC", "Tigres United", "16"});
        modeloResultados.addRow(new Object[]{"Liga de Ajedrez", "Ajedrez", "30/11/2024", "Magnus C.", "Anna K.", "Boris S.", "12"});
        modeloResultados.addRow(new Object[]{"Copa Volleyball", "Volleyball", "22/01/2025", "Spikers Team", "Net Warriors", "Ace Makers", "8"});
    }

    // Métodos de acción (prototipos)
    private void inscribirseEnTorneo() {
        int filaSeleccionada = tablaTorneos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un torneo.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String nombreTorneo = (String) modeloTorneos.getValueAt(filaSeleccionada, 0);
        String estado = (String) modeloTorneos.getValueAt(filaSeleccionada, 6);

        if (!estado.equals("Inscripción Abierta")) {
            JOptionPane.showMessageDialog(this, "Este torneo no está disponible para inscripción.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Confirma su inscripción al torneo: " + nombreTorneo + "?",
                "Confirmar Inscripción",
                JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "¡Inscripción exitosa!\n\nEn una versión completa, recibirá un email con los detalles.", "Inscripción Completada", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void verFechasTentativas() {
        int filaSeleccionada = tablaTorneos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un torneo.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        //ArrayList<LocalDate> fechasTentativas = torneo.calendario.getFechas_partidos();

        StringBuilder mensaje = new StringBuilder("Fechas de los partidos:\n");
        /*
        for (LocalDate fecha : fechasTentativas) {
            mensaje.append(fecha.toString()).append("\n");
        }
*/
        JOptionPane.showMessageDialog(this, mensaje.toString(), "Fechas Tentativas", JOptionPane.INFORMATION_MESSAGE);
    }

    private void verDetallesCompetidor() {
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
        detalles.append("Contacto: organizador@torneo.com");

        JOptionPane.showMessageDialog(this, detalles.toString(), "Detalles del Torneo", JOptionPane.INFORMATION_MESSAGE);
    }


    private void actualizarTorneos() {
        JOptionPane.showMessageDialog(this, "Lista de torneos actualizada", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void verResultados() {
        JOptionPane.showMessageDialog(this, "Abriendo bracket del torneo (función en desarrollo)", "Información", JOptionPane.INFORMATION_MESSAGE);
    }


    private void setupWindow() {
        setSize(900, 650);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    // Método para probar la ventana independientemente
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            UsuarioBasico u = new UsuarioBasico("Pepito", "Fiumba");

            new VentanaCompetidor(u).setVisible(true);
        });
    }
}