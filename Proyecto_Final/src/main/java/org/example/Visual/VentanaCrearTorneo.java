package org.example.Visual;

import org.example.Logica.*;

import javax.swing.*;
import java.awt.*;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Set;

public class VentanaCrearTorneo extends JFrame {
    private EventManager eventManager;
    private final List<Observer> observadoresCompetidores = new ArrayList<>();

    // Componentes del formulario
    private JTextField txtNombreTorneo;
    private JComboBox<String> cbDisciplina;
    private JTextField txtLugar;
    private JSpinner spnFecha;
    private JComboBox<String> cbFormato;
    private JSpinner spnMaxParticipantes;
    private JTextArea txtDescripcion;
    private JTextField txtPremio;
    private JCheckBox chkInscripcionAbierta;
    private Editor editor;

    //Componentes para equipos
    private JTextField txtNombreEquipo;
    private JTextField txtContactoEquipo;
    private JButton btnAgregarEquipo;
    private JList<Equipo> listaEquipos;
    private DefaultListModel<Equipo> modeloListaEquipos;
    private JButton btnEliminarEquipo;
    private ArrayList<Equipo> equiposParticipantes;


    public VentanaCrearTorneo(Editor editor) {
        this.eventManager = new EventManager("nuevoTorneo");
        this.editor = editor;
        this.equiposParticipantes = new ArrayList<>();
        initComponents();
        setupWindow();
    }
    public EventManager getEventManager() {
        return eventManager;
    }

    private void initComponents() {
        setTitle("Organizador - Crear Nuevo Torneo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal con scroll
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(new Color(245, 245, 250));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Título
        JLabel titulo = new JLabel("CREAR NUEVO TORNEO");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(25, 25, 112));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel del formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 15);
        gbc.anchor = GridBagConstraints.WEST;

        // Nombre del torneo
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulario.add(crearEtiqueta("Nombre del Torneo:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtNombreTorneo = new JTextField(20);
        panelFormulario.add(txtNombreTorneo, gbc);

        // Disciplina
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panelFormulario.add(crearEtiqueta("Disciplina:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        String[] disciplinas = {
                "Seleccionar disciplina...",
                "Fútbol", "Baloncesto", "Volleyball", "Ajedrez", "Rugby", "Boxeo", "Tenis", "Ping Pong"
        };
        cbDisciplina = new JComboBox<>(disciplinas);
        panelFormulario.add(cbDisciplina, gbc);

        // Lugar
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panelFormulario.add(crearEtiqueta("Lugar:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtLugar = new JTextField(20);
        panelFormulario.add(txtLugar, gbc);

        // Fecha
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panelFormulario.add(crearEtiqueta("Fecha de inicio:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Configurar spinner de fecha
        SpinnerDateModel modeloFecha = new SpinnerDateModel();
        spnFecha = new JSpinner(modeloFecha);
        JSpinner.DateEditor editorFecha = new JSpinner.DateEditor(spnFecha, "dd/MM/yyyy");
        spnFecha.setEditor(editorFecha);
        panelFormulario.add(spnFecha, gbc);

        // Formato del torneo
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panelFormulario.add(crearEtiqueta("Formato:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        String[] formatos = {
                "Seleccionar formato...",
                "Eliminatoria Directa",
                "Liga Simple (Todos contra todos)",
        };
        cbFormato = new JComboBox<>(formatos);
        panelFormulario.add(cbFormato, gbc);
        cbFormato.addActionListener(e -> actualizarMaxParticipantes());

        // Máximo de participantes
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panelFormulario.add(crearEtiqueta("Máx. Participantes:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        spnMaxParticipantes = new JSpinner(new SpinnerNumberModel(16, 4, 256, 1));
        panelFormulario.add(spnMaxParticipantes, gbc);

        // Premio
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panelFormulario.add(crearEtiqueta("Premio:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtPremio = new JTextField(20);
        txtPremio.setToolTipText("Ej: $500, Trofeo, Medalla de oro, etc.");
        panelFormulario.add(txtPremio, gbc);

        // Descripción
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelFormulario.add(crearEtiqueta("Descripción:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        txtDescripcion = new JTextArea(4, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setBorder(BorderFactory.createLoweredBevelBorder());
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        panelFormulario.add(scrollDescripcion, gbc);

        // Checkbox inscripción abierta
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;

        //Panel para agregar equipos
        JPanel panelEquipos = new JPanel(new BorderLayout());
        panelEquipos.setBorder(BorderFactory.createTitledBorder("Equipos participantes"));
        panelEquipos.setBackground(Color.WHITE);

        //Panel para agregar nuevo equipo
        JPanel panelNuevoEquipo = new JPanel(new GridLayout(0, 2, 5, 5));
        panelNuevoEquipo.setBackground(Color.WHITE);

        panelNuevoEquipo.add(new JLabel("Nombre del equipo:"));
        txtNombreEquipo = new JTextField();
        panelNuevoEquipo.add(txtNombreEquipo);

        panelNuevoEquipo.add(new JLabel("Contacto:"));
        txtContactoEquipo = new JTextField();
        panelNuevoEquipo.add(txtContactoEquipo);

        btnAgregarEquipo = new JButton("Agregar equipo");
        btnAgregarEquipo.addActionListener(e -> agregarEquipo());
        panelNuevoEquipo.add(btnAgregarEquipo);

        btnEliminarEquipo = new JButton("Eliminar seleccionado");
        btnEliminarEquipo.addActionListener(e -> eliminarEquipo());
        panelNuevoEquipo.add(btnEliminarEquipo);

        //Lista de equipos
        modeloListaEquipos = new DefaultListModel<>();
        listaEquipos = new JList<>(modeloListaEquipos);
        listaEquipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollEquipos = new JScrollPane(listaEquipos);

        panelEquipos.add(panelNuevoEquipo, BorderLayout.NORTH);
        panelEquipos.add(scrollEquipos, BorderLayout.CENTER);

        //Agregar el panel de equipos al formulario principal
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(10, 0, 10, 0);
        panelFormulario.add(panelEquipos, gbc);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setOpaque(false);

        JButton btnCrear = crearBoton("Crear Torneo", new Color(34, 139, 34));
        JButton btnLimpiar = crearBoton("Limpiar", new Color(255, 165, 0));
        JButton btnCancelar = crearBoton("Cancelar", new Color(220, 20, 60));

        // ActionListeners
        btnCrear.addActionListener(e -> crearTorneo());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        btnCancelar.addActionListener(e -> dispose());

        panelBotones.add(btnCrear);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnLimpiar);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnCancelar);

        // Agregar todo al panel principal
        panelPrincipal.add(titulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(panelFormulario);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        panelPrincipal.add(panelBotones);

        // Scroll pane para el panel principal
        JScrollPane scrollPane = new JScrollPane(panelPrincipal);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);

        spnMaxParticipantes.addChangeListener(e -> {
            int max = (int) spnMaxParticipantes.getValue();
            btnAgregarEquipo.setEnabled(equiposParticipantes.size() < max);
        });
    }

    private void actualizarMaxParticipantes() {
        String formatoSeleccionado = (String) cbFormato.getSelectedItem();

        if ("Eliminatoria Directa".equalsIgnoreCase(formatoSeleccionado)) {
            spnMaxParticipantes.setModel(new SpinnerListModel(new Integer[]{4, 8, 16}));
            spnMaxParticipantes.setValue(4); // valor por defecto
        } else {
            spnMaxParticipantes.setModel(new SpinnerNumberModel(2, 2, 100, 1));
        }
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 12));
        etiqueta.setForeground(new Color(70, 70, 70));
        return etiqueta;
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setBorder(BorderFactory.createRaisedBevelBorder());
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(120, 35));

        return boton;
    }

    private void agregarEquipo() {
        int maxParticipant = (int) spnMaxParticipantes.getValue();

        if (equiposParticipantes.size() > maxParticipant) {
            mostrarError("No se pueden agregar más equipos. Límite: " + maxParticipant);
            return;
        }

        String nombre = txtNombreEquipo.getText().trim();
        String contacto = txtContactoEquipo.getText().trim();

        if (nombre.isEmpty()) {
            mostrarError("Por favor ingrese el nombre del equipo");
            return;
        }

        if (contacto.isEmpty()) {
            mostrarError("ingrese un contacto para el equipo");
            return;
        }

        Equipo nuevoEquipo = new Equipo(nombre, contacto);
        equiposParticipantes.add(nuevoEquipo);
        modeloListaEquipos.addElement(nuevoEquipo);

        txtNombreEquipo.setText("");
        txtContactoEquipo.setText("");

        btnAgregarEquipo.setEnabled(equiposParticipantes.size() < maxParticipant);
    }

    private void eliminarEquipo() {
        int selectedIndex = listaEquipos.getSelectedIndex();
        if (selectedIndex != -1) {
            Equipo equipoAEliminar = modeloListaEquipos.getElementAt(selectedIndex);
            equiposParticipantes.remove(equipoAEliminar);
            modeloListaEquipos.remove(selectedIndex);
        } else {
            mostrarError("Por favor seleccione un equipo para eliminar");
        }
    }

    private void crearTorneo() {
        if (txtNombreTorneo.getText().trim().isEmpty()) {
            mostrarError("Por favor ingrese el nombre del torneo.");
            return;
        }
        if (cbDisciplina.getSelectedIndex() == 0) {
            mostrarError("Por favor seleccione una disciplina.");
            return;
        }
        if (cbFormato.getSelectedIndex() == 0) {
            mostrarError("Por favor seleccione un formato de torneo.");
            return;
        }

        try {
            String formatoSeleccionado = (String) cbFormato.getSelectedItem();
            FORMATO formato;
            if (formatoSeleccionado.equalsIgnoreCase("Eliminatoria Directa")) {
                formato = FORMATO.CAMPEONATO;
            } else {
                formato = FORMATO.LIGASIMPLE;
            }

            String disciplina = (String) cbDisciplina.getSelectedItem();
            Set<String> deportesEquipo = Set.of("Fútbol", "Baloncesto", "Tenis", "Volleyball",
                    "Ping Pong", "Natación", "Atletismo",
                    "Ciclismo", "Rugby", "Bádminton", "Boxeo");

            TIPOPARTICIPANTES tipo;
            if (deportesEquipo.contains(disciplina)) {
                tipo = TIPOPARTICIPANTES.ENEQUIPOS;
            } else {
                tipo = TIPOPARTICIPANTES.INDIVIDUAL;
            }

            int maxParticipantes = (int) spnMaxParticipantes.getValue();
            int participantesRegistrados = equiposParticipantes.size();

            if (participantesRegistrados != maxParticipantes) {
                mostrarError("Debe registrar" + maxParticipantes + "equipos.");
                return;
            }

            java.util.Date fecha = (java.util.Date) spnFecha.getValue();
            LocalDate fechaInicio = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            //creacion de Torneo con Builder
            Torneo torneo = new TorneoBuilder()
                    .conNombre(txtNombreTorneo.getText().trim())
                    .conFormato(formato)
                    .conTipoParticipantes(tipo)
                    .conFechaInicio(fechaInicio)
                    .conDisciplina(disciplina)
                    .conLugar(txtLugar.toString())
                    .build();
            //agregar equipos al torneo
            for (Equipo equipo : equiposParticipantes) {
                torneo.addParticipantes(equipo);
            }
            //generar calendario
            torneo.crearCalendario();
            //agregar torneo al editor y avisar
            editor.addTorneo(torneo);
            eventManager.notify("nuevoTorneo", torneo);

            StringBuilder info = new StringBuilder();
            info.append("¡Torneo creado exitosamente!\n\n");
            info.append("Nombre: ").append(torneo.getNombreTorneo()).append("\n");
            info.append("Disciplina: ").append(disciplina).append("\n");
            info.append("Lugar: ").append(txtLugar.getText().isEmpty() ? "No especificado" : txtLugar.getText()).append("\n");
            info.append("Formato: ").append(formatoSeleccionado).append("\n");
            info.append("Máx. Participantes: ").append(spnMaxParticipantes.getValue()).append("\n");
            info.append("Premio: ").append(txtPremio.getText().isEmpty() ? "No especificado" : txtPremio.getText()).append("\n");

            JOptionPane.showMessageDialog(this, info.toString(), "Torneo Creado", JOptionPane.INFORMATION_MESSAGE);

            limpiarFormulario();

        } catch (Exception ex) {
            mostrarError("Error al crear el torneo: " + ex.getMessage());
        }
    }

    private void limpiarFormulario() {
        txtNombreTorneo.setText("");
        cbDisciplina.setSelectedIndex(0);
        txtLugar.setText("");
        spnFecha.setValue(new java.util.Date());
        cbFormato.setSelectedIndex(0);
        spnMaxParticipantes.setValue(16);
        txtDescripcion.setText("");
        txtPremio.setText("");
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void setupWindow() {
        setSize(700, 600);
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

            new VentanaCrearTorneo(new Editor("Ganon", "Gerudo")).setVisible(true);
        });
    }
}