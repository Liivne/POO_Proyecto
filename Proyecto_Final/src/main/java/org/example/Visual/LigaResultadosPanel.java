package org.example.Visual;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LigaResultadosPanel extends JPanel {
    private List<String> equipos;
    private JTable tablaResultados;
    private DefaultTableModel modeloTabla;
    private JButton btnGuardar;
    private JButton btnCargarEquipos;
    private JLabel lblEstado;

    public LigaResultadosPanel() {
        equipos = new ArrayList<>();
        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        // Configurar modelo de tabla
        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                // Solo editable si no es la diagonal principal (mismo equipo)
                return row != col - 1 && col > 0;
            }

            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0) return String.class;
                return JComponent.class;
            }
        };

        tablaResultados = new JTable(modeloTabla);
        tablaResultados.setRowHeight(35);
        tablaResultados.setDefaultRenderer(JComponent.class, new ResultadoCellRenderer());
        tablaResultados.setDefaultEditor(JComponent.class, new ResultadoCellEditor());

        btnCargarEquipos = new JButton("Cargar Equipos");
        btnGuardar = new JButton("Guardar Resultados");
        btnGuardar.setEnabled(false);

        lblEstado = new JLabel("Cargue los equipos para comenzar");
        lblEstado.setForeground(Color.BLUE);

        // Configurar listeners
        btnCargarEquipos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarEquipos();
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarResultados();
            }
        });
    }

    private void setupLayout() {
        setLayout(new BorderLayout());

        // Panel superior con controles
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel("Liga Simple (Todos contra Todos)"));
        panelSuperior.add(Box.createHorizontalStrut(20));
        panelSuperior.add(btnCargarEquipos);
        panelSuperior.add(btnGuardar);

        // Panel central con tabla
        JScrollPane scrollPane = new JScrollPane(tablaResultados);
        scrollPane.setPreferredSize(new Dimension(800, 400));

        // Panel inferior con estado
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInferior.add(lblEstado);

        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void cargarEquipos() {
        String input = JOptionPane.showInputDialog(this,
                "Ingrese los nombres de los equipos separados por comas:");

        if (input != null && !input.trim().isEmpty()) {
            equipos.clear();
            String[] nombresEquipos = input.split(",");
            for (String nombre : nombresEquipos) {
                equipos.add(nombre.trim());
            }

            if (equipos.size() < 2) {
                JOptionPane.showMessageDialog(this,
                        "Debe ingresar al menos 2 equipos",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            crearTablaResultados();
            lblEstado.setText("Equipos cargados: " + equipos.size() + " equipos");
            lblEstado.setForeground(Color.GREEN);
            btnGuardar.setEnabled(true);
        }
    }

    private void crearTablaResultados() {
        // Limpiar tabla
        modeloTabla.setRowCount(0);
        modeloTabla.setColumnCount(0);

        // Crear columnas
        modeloTabla.addColumn("Equipo");
        for (String equipo : equipos) {
            modeloTabla.addColumn(equipo);
        }

        // Crear filas
        for (int i = 0; i < equipos.size(); i++) {
            Object[] fila = new Object[equipos.size() + 1];
            fila[0] = equipos.get(i);

            for (int j = 1; j <= equipos.size(); j++) {
                if (i == j - 1) {
                    // Diagonal principal - no se puede jugar contra sí mismo
                    fila[j] = new JLabel("-");
                } else {
                    // Crear panel para resultado
                    fila[j] = crearPanelResultado();
                }
            }
            modeloTabla.addRow(fila);
        }

        // Ajustar ancho de columnas
        tablaResultados.getColumnModel().getColumn(0).setPreferredWidth(150);
        for (int i = 1; i < tablaResultados.getColumnCount(); i++) {
            tablaResultados.getColumnModel().getColumn(i).setPreferredWidth(100);
        }
    }

    private JPanel crearPanelResultado() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2));
        panel.setBackground(Color.WHITE);

        JTextField txtLocal = new JTextField(2);
        JTextField txtVisitante = new JTextField(2);
        JLabel lblSeparador = new JLabel("-");

        txtLocal.setHorizontalAlignment(JTextField.CENTER);
        txtVisitante.setHorizontalAlignment(JTextField.CENTER);

        panel.add(txtLocal);
        panel.add(lblSeparador);
        panel.add(txtVisitante);

        return panel;
    }

    private void guardarResultados() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== RESULTADOS DE LA LIGA ===\n\n");

        boolean hayResultados = false;

        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            String equipoLocal = (String) modeloTabla.getValueAt(i, 0);

            for (int j = 1; j < modeloTabla.getColumnCount(); j++) {
                if (i != j - 1) { // No es la diagonal principal
                    Object celda = modeloTabla.getValueAt(i, j);
                    if (celda instanceof JPanel) {
                        JPanel panel = (JPanel) celda;
                        Component[] componentes = panel.getComponents();

                        if (componentes.length >= 3) {
                            JTextField txtLocal = (JTextField) componentes[0];
                            JTextField txtVisitante = (JTextField) componentes[2];

                            String golLocal = txtLocal.getText().trim();
                            String golVisitante = txtVisitante.getText().trim();

                            if (!golLocal.isEmpty() && !golVisitante.isEmpty()) {
                                try {
                                    int golesLocal = Integer.parseInt(golLocal);
                                    int golesVisitante = Integer.parseInt(golVisitante);

                                    String equipoVisitante = equipos.get(j - 1);
                                    sb.append(String.format("%s %d - %d %s\n",
                                            equipoLocal, golesLocal, golesVisitante, equipoVisitante));
                                    hayResultados = true;
                                } catch (NumberFormatException e) {
                                    // Ignorar resultados inválidos
                                }
                            }
                        }
                    }
                }
            }
        }

        if (hayResultados) {
            // Mostrar resultados en un diálogo
            JTextArea areaResultados = new JTextArea(sb.toString());
            areaResultados.setEditable(false);
            areaResultados.setFont(new Font("Monospaced", Font.PLAIN, 12));

            JScrollPane scrollResultados = new JScrollPane(areaResultados);
            scrollResultados.setPreferredSize(new Dimension(400, 300));

            JOptionPane.showMessageDialog(this, scrollResultados,
                    "Resultados Guardados", JOptionPane.INFORMATION_MESSAGE);

            lblEstado.setText("Resultados guardados exitosamente");
            lblEstado.setForeground(Color.GREEN);
        } else {
            JOptionPane.showMessageDialog(this,
                    "No hay resultados válidos para guardar",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Renderer personalizado para las celdas de resultado
    private class ResultadoCellRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {

            if (value instanceof JComponent) {
                JComponent comp = (JComponent) value;
                if (isSelected) {
                    comp.setBackground(table.getSelectionBackground());
                } else {
                    comp.setBackground(Color.WHITE);
                }
                return comp;
            }

            JLabel label = new JLabel(value != null ? value.toString() : "");
            label.setHorizontalAlignment(JLabel.CENTER);
            return label;
        }
    }

    // Editor personalizado para las celdas de resultado
    private class ResultadoCellEditor extends AbstractCellEditor implements TableCellEditor {
        private JPanel panel;

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {

            if (value instanceof JPanel) {
                panel = (JPanel) value;
                return panel;
            }

            return new JLabel();
        }

        @Override
        public Object getCellEditorValue() {
            return panel;
        }
    }

    // Método main para pruebas
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestor de Torneos - Liga Simple");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new LigaResultadosPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
/** Esta pestaña gestiona torneos todos contra todos de ida y vuelta
 *  podría hacerse otra similar solo para vuelta
 *
 *  Falta gestionar que es lo que pasa cuando faltan equipos, no debería estar permitido
 *  inicializar el torneo hasta que estén todos los equipos propuestos al ocupar crearTorneo
 *
 */