package org.example.Visual;

import org.example.Logica.Editor;
import org.example.Logica.LoginPersonas;
import org.example.Logica.UsuarioBasico;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import static org.example.Logica.LoginPersonas.*;

public class VentanaPrincipal extends JFrame {
    // Lista de ventanas
    static List<VentanaCompetidor> ventanasCompetidor = new ArrayList<>(
            List.of(
                    new VentanaCompetidor(u1),
                    new VentanaCompetidor(u2),
                    new VentanaCompetidor(u3)
            )
    );

    private LoginPersonas iniciosesion = new LoginPersonas();
    private JTextField txtUsuario;
    private JPasswordField txtContra;

    public VentanaPrincipal() {
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

        // Panel de inicio de sesión
        JPanel panelLogin = new JPanel();
        panelLogin.setLayout(new GridBagLayout());
        panelLogin.setOpaque(false); 
        panelLogin.setMaximumSize(new Dimension(400, 120));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblUsuario = new JLabel("Usuario:");
        txtUsuario = new JTextField(20);

        JLabel lblContra = new JLabel("Contraseña:");
        txtContra = new JPasswordField(20);

        JButton btnLogin = new JButton("Iniciar sesión");
        btnLogin.addActionListener(e -> iniciarSesion());

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelLogin.add(lblUsuario, gbc);

        gbc.gridx = 1;
        panelLogin.add(txtUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelLogin.add(lblContra, gbc);

        gbc.gridx = 1;
        panelLogin.add(txtContra, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panelLogin.add(btnLogin, gbc);

        // Panel para botones secundarios
        JPanel panelSecundario = new JPanel(new FlowLayout());
        panelSecundario.setOpaque(false);

        JButton btnAcerca = crearBotonSecundario("Acerca de");
        JButton btnSalir = crearBotonSecundario("Salir");

        // Agregar ActionListeners (sin funcionalidad por ahora)
        btnAcerca.addActionListener(e -> mostrarAcercaDe());
        btnSalir.addActionListener(e -> System.exit(0));


        panelSecundario.add(btnAcerca);
        panelSecundario.add(Box.createHorizontalStrut(20));
        panelSecundario.add(btnSalir);

        // Agregar todo al panel principal
        panelPrincipal.add(titulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));
        panelPrincipal.add(subtitulo);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 50)));
        //panelPrincipal.add(panelBotones);
        panelPrincipal.add(panelLogin);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 40)));
        panelPrincipal.add(panelSecundario);

        add(panelPrincipal, BorderLayout.CENTER);
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

    private void iniciarSesion() {
            String usuario = txtUsuario.getText();
            String contra = new String(txtContra.getPassword());

            if (iniciosesion.loginEditor(usuario, contra)){
                mostrarMensaje("Inicio de sesión como editor exitoso");
                Editor e = new Editor(usuario, contra);
                abrirVentanaOrganizador(e);
            }
            else if(iniciosesion.loginUsuario(usuario, contra)) {
                mostrarMensaje("Inicio de sesión como usuario exitoso");
                UsuarioBasico u = new UsuarioBasico(usuario, contra);
                abrirVentanaCompetidor(u);
            }
            else { System.out.println("Credenciales inválidas");}
    }

    private void abrirVentanaOrganizador(Editor editor) {
        // Suscribir la ventana del admin al CrearTorneo
        VentanaAdministrarOrganizador ventana = new VentanaAdministrarOrganizador(editor);
        ventana.setVisible(true);
    }
    private void abrirVentanaCompetidor(UsuarioBasico usuario) {
        VentanaCompetidor ventana = new VentanaCompetidor(usuario);
        ventana.setVisible(true);

    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarAcercaDe() {
        String mensaje = """
                Sistema Organizador de Torneos v1.0
                
                Prototipo de aplicación para la gestión
                de torneos deportivos.
                
                Desarrollado con Java Swing""";

        JOptionPane.showMessageDialog(this, mensaje, "Acerca de", JOptionPane.INFORMATION_MESSAGE);
    }
    public static List<VentanaCompetidor> getVentanasCompetidor() {
        return ventanasCompetidor;
    }

    // Método main para ejecutar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            new VentanaPrincipal().setVisible(true);
        });
    }
}
