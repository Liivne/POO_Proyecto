package org.example.Logica;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase {@code LoginPersonas} gestiona el inicio de sesión
 * de usuarios tipo {@link Editor}. Almacena una lista de editores
 * registrados de manera simplificada y verifica las credenciales proporcionadas durante el login.
 */
public class LoginPersonas {
    private List<Editor> EditoresRegistrados = new ArrayList<>(); // Almacenaría Editores
    private List<UsuarioBasico> UsuariosRegistrados = new ArrayList<>(); //Almacenaría Usuarios no editores

    public Editor e1 = new Editor("Link", "Hyrule");
    public Editor e2 = new Editor("Ganon", "Gerudo");

    public UsuarioBasico u1 = new UsuarioBasico("Jake el perro", "ay canijote");
    public UsuarioBasico u2 = new UsuarioBasico("Geoffrey Hinton", "IAmevoy");
    public UsuarioBasico u3 = new UsuarioBasico("Demis Hassabis", "4lphaZer0");
    /**
     * Crea una nueva instancia de {@code LoginPersonas} y
     * agrega algunos editores y usuarios predefinidos, quienes están autoriaados para ingresar a la plataforma.
     */
    public LoginPersonas(){
        EditoresRegistrados.add(e1);
        EditoresRegistrados.add(e2);

        UsuariosRegistrados.add(u1);
        UsuariosRegistrados.add(u2);
        UsuariosRegistrados.add(u3);
    }

    /**
     * Verifica las credenciales proporcionadas contra la lista de editores registrados.
     *
     * @param nombre_usuario Nombre de usuario ingresado.
     * @param contra Contraseña ingresada.
     * @return {@code true} si las credenciales coinciden con un usuario registrado,
     *         {@code false} en caso contrario.
     */
    public boolean loginEditor(String nombre_usuario, String contra) {
        for (Editor editor : EditoresRegistrados) {
            if (editor.getNombre_Usuario().equals(nombre_usuario) && editor.getContra().equals(contra)) {
                System.out.println("Credenciales válidas como editor: " + nombre_usuario);
                return true;
            }
        }
        return false;
    }
    public boolean loginUsuario(String nombre_usuario, String contra){
        for(UsuarioBasico usuario: UsuariosRegistrados){
            if (usuario.getNombre_Usuario().equals(nombre_usuario) && usuario.getContra().equals(contra)) {
                System.out.println("Credenciales válidas como usuario: " + nombre_usuario);
                return true;
            }
        }
        return false;
    }
}
