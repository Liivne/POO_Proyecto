package org.example.Visual;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipalEditor extends JFrame {

    public VentanaPrincipalEditor() {
        initComponents();
        setupWindow();
    }

    private void initComponents() {
        setTitle("Sistema Organizador de Torneos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal con fondo
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(new Color(240, 248, 255));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Título principal
        JLabel titulo = new JLabel("ORGANIZADOR DE TORNEOS");
        titulo.setFont(new Font("Arial", Font.BOLD, 32));
        titulo.setForeground(new Color(25, 25, 112));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtítulo
        JLabel subtitulo = new JLabel("Sistema de Gestión Deportiva");
        subtitulo.setFont(new Font("Arial", Font.ITALIC, 16));
        subtitulo.setForeground(new Color(70, 70, 70));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel para los botones principales
        JPanel panelBotones = new JPanel(new GridLayout(2, 1, 0, 20));
        panelBotones.setOpaque(false);
        panelBotones.setMaximumSize(new Dimension(400, 160));

        // Botón para Organizadores
        JButton btnOrganizadores = crearBotonPrincipal(
                "ORGANIZADORES",
                "Crear y gestionar torneos",
                new Color(34, 139, 34),
                new Color(46, 125, 50)
        );

        // Botón para Competidores
        JButton btnCompetidores = crearBotonPrincipal(
                "COMPETIDORES",
                "Ver resultados e inscribirse",
                new Color(30, 144, 255),
                new Color(25, 118, 210)
        );

        // Panel para botones secundarios
        JPanel panelSecundario = new JPanel(new FlowLayout());
        panelSecundario.setOpaque(false);

        JButton btnAcerca = crearBotonSecundario("Acerca de");
        JButton btnSalir = crearBotonSecundario("Salir");

        // Agregar ActionListeners (sin funcionalidad por ahora)
        btnOrganizadores.addActionListener(e -> abrirVentanaOrganizador());
        btnCompetidores.addActionListener(e -> abrirVentanaCompetidor());
        btnAcerca.addActionListener(e -> mostrarAcercaDe());
        btnSalir.addActionListener(e -> System.exit(0));

        // Agregar componentes al panel de botones
        panelBotones.add(btnOrganizadores);
        panelBotones.add(btnCompetidores);

        panelSecundario.add(btnAcerca);
        panelSecundario.add(Box.createHorizontalStrut(20));
        panelSecundario.add(btnSalir);

        // Agregar todo al panel principal
        panelPrincipal.add(titulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        panelPrincipal.add(subtitulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 50)));
        panelPrincipal.add(panelBotones);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 40)));
        panelPrincipal.add(panelSecundario);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    private JButton crearBotonPrincipal(String titulo, String descripcion, Color colorBase, Color colorHover) {
        JButton boton = new JButton();
        boton.setLayout(new BorderLayout());
        boton.setBackground(colorBase);
        boton.setBorder(BorderFactory.createRaisedBevelBorder());
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Panel interno del botón
        JPanel panelInterno = new JPanel();
        panelInterno.setLayout(new BoxLayout(panelInterno, BoxLayout.Y_AXIS));
        panelInterno.setOpaque(false);
        panelInterno.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblDescripcion = new JLabel(descripcion);
        lblDescripcion.setFont(new Font("Arial", Font.PLAIN, 12));
        lblDescripcion.setForeground(new Color(230, 230, 230));
        lblDescripcion.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelInterno.add(lblTitulo);
        panelInterno.add(Box.createRigidArea(new Dimension(0, 5)));
        panelInterno.add(lblDescripcion);

        boton.add(panelInterno);

        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorHover);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorBase);
            }
        });

        return boton;
    }

    private JButton crearBotonSecundario(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.PLAIN, 12));
        boton.setBackground(new Color(220, 220, 220));
        boton.setBorder(BorderFactory.createRaisedBevelBorder());
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setPreferredSize(new Dimension(100, 30));

        // Efecto hover para botones secundarios
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(200, 200, 200));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(220, 220, 220));
            }
        });

        return boton;
    }

    private void setupWindow() {
        setSize(600, 500);
        setLocationRelativeTo(null); // Centrar en pantalla
        setResizable(false);
    }

    private void abrirVentanaOrganizador() {
        new VentanaAdministrarOrganizador().setVisible(true);
    }
    private void abrirVentanaCompetidor() {
        new VentanaCompetidor().setVisible(true);
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarAcercaDe() {
        String mensaje = "Sistema Organizador de Torneos v1.0\n\n" +
                "Prototipo de aplicación para la gestión\n" +
                "de torneos deportivos.\n\n" +
                "Desarrollado con Java Swing";

        JOptionPane.showMessageDialog(this, mensaje, "Acerca de", JOptionPane.INFORMATION_MESSAGE);
        // Esta pestaña podría ser usada como guía de uso
    }

    // Método main para ejecutar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            new VentanaPrincipalEditor().setVisible(true);
        });
    }
}
