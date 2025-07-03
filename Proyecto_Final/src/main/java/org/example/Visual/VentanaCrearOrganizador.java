package org.example.Visual;

import org.example.Logica.*;

import javax.swing.*;
import java.awt.*;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Set;

public class VentanaCrearOrganizador extends JFrame {

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

    public VentanaCrearOrganizador() { //Debería pedir como parámetro a Editor?
        //this.editor = editor; ???
        initComponents();
        setupWindow();
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
                "Fútbol", "Baloncesto", "Volleyball", "Ajedrez", "Rugby", "Boxeo"
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
        chkInscripcionAbierta = new JCheckBox("Inscripción abierta inmediatamente");
        chkInscripcionAbierta.setSelected(true);
        chkInscripcionAbierta.setBackground(Color.WHITE);
        panelFormulario.add(chkInscripcionAbierta, gbc);

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

            java.util.Date fecha = (java.util.Date) spnFecha.getValue();
            LocalDate fechaInicio = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            //creacion de Torneo con Builder
            Torneo torneo = new TorneoBuilder()
                    .conNombre(txtNombreTorneo.getText().trim())
                    .conFormato(formato)
                    .conTipoParticipantes(tipo)
                    .conFechaInicio(fechaInicio)
                    .build();
            //agregar torneo al editor
            editor.addTorneo(torneo);

            StringBuilder info = new StringBuilder();
            info.append("¡Torneo creado exitosamente!\n\n");
            info.append("Nombre: ").append(torneo.getNombreTorneo()).append("\n");
            info.append("Disciplina: ").append(disciplina).append("\n");
            info.append("Lugar: ").append(txtLugar.getText().isEmpty() ? "No especificado" : txtLugar.getText()).append("\n");
            info.append("Formato: ").append(formatoSeleccionado).append("\n");
            info.append("Máx. Participantes: ").append(spnMaxParticipantes.getValue()).append("\n");
            info.append("Premio: ").append(txtPremio.getText().isEmpty() ? "No especificado" : txtPremio.getText()).append("\n");
            info.append("Inscripción: ").append(chkInscripcionAbierta.isSelected() ? "Abierta" : "Cerrada").append("\n");

            JOptionPane.showMessageDialog(this, info.toString(), "Torneo Creado", JOptionPane.INFORMATION_MESSAGE);

            limpiarFormulario();

        } catch (Exception ex) {
            mostrarError("Error al crear el torneo: " + ex.getMessage());
        }
    }

   /* private void crearTorneo() {
        // Validaciones básicas
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

        FORMATO tipo;
        if (cbFormato.getSelectedItem() == "Eliminatoria Directa"){
            tipo = FORMATO.CAMPEONATO;
        } else {
            tipo = FORMATO.LIGASIMPLE;
        }

        TIPOPARTICIPANTES participa;
        switch (cbDisciplina.getSelectedItem()){
            case ("Fútbol", "Baloncesto", "Tenis", "Volleyball",
                 "Ping Pong", "Natación", "Atletismo",
                 "Ciclismo", "Rugby", "Bádminton", "Boxeo")
                participa = TIPOPARTICIPANTES.ENEQUIPOS;
                break;
            case ("Ajedrez")
                participa = TIPOPARTICIPANTES.INDIVIDUAL;
                break;

        }

        Torneo = new Torneo(txtNombreTorneo.getText(),cbFormato.getSelectedItem(), cbDisciplina.getSelectedItem(), LocalDate)


        //editor.addTorneo(Torneo)

        // Mostrar información del torneo creado (prototipo)
        StringBuilder info = new StringBuilder();
        info.append("¡Torneo creado exitosamente!\n\n");
        info.append("Nombre: ").append(txtNombreTorneo.getText()).append("\n");
        info.append("Disciplina: ").append(cbDisciplina.getSelectedItem()).append("\n");
        info.append("Lugar: ").append(txtLugar.getText().isEmpty() ? "No especificado" : txtLugar.getText()).append("\n");
        info.append("Formato: ").append(cbFormato.getSelectedItem()).append("\n");
        info.append("Máx. Participantes: ").append(spnMaxParticipantes.getValue()).append("\n");
        info.append("Premio: ").append(txtPremio.getText().isEmpty() ? "No especificado" : txtPremio.getText()).append("\n");
        info.append("Inscripción: ").append(chkInscripcionAbierta.isSelected() ? "Abierta" : "Cerrada").append("\n");

        JOptionPane.showMessageDialog(this, info.toString(), "Torneo Creado", JOptionPane.INFORMATION_MESSAGE);
/*


    // Limpiar formulario después de crear

    limpiarFormulario();
} */

    private void limpiarFormulario() {
        txtNombreTorneo.setText("");
        cbDisciplina.setSelectedIndex(0);
        txtLugar.setText("");
        spnFecha.setValue(new java.util.Date());
        cbFormato.setSelectedIndex(0);
        spnMaxParticipantes.setValue(16);
        txtDescripcion.setText("");
        txtPremio.setText("");
        chkInscripcionAbierta.setSelected(true);
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

            new VentanaCrearOrganizador().setVisible(true);
        });
    }
}
