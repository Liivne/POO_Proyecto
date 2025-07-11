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
    private List<UsuarioBasico> UsuariosBasicos = new ArrayList<>(); //Almacenaría Usuarios no editores
    /**
     * Crea una nueva instancia de {@code LoginPersonas} y
     * agrega algunos editores predefinidos para realizar pruebas.
     */
    public LoginPersonas(){
        EditoresRegistrados.add(new Editor("Link", "Hyrule"));
        EditoresRegistrados.add(new Editor("Ganon", "Gerudo"));
    }

    /**
     * Verifica las credenciales proporcionadas contra la lista de editores registrados.
     *
     * @param nombre_usuario Nombre de usuario ingresado.
     * @param contra Contraseña ingresada.
     * @return {@code true} si las credenciales coinciden con un editor registrado,
     *         {@code false} en caso contrario.
     */
    public boolean login(String nombre_usuario, String contra) {
        for (Editor editor : EditoresRegistrados) {
            if (editor.getNombre_Usuario().equals(nombre_usuario) && editor.getContra().equals(contra)) {
                System.out.println("Credenciales validas como editor: " + nombre_usuario);
                return true;
            }
        }
        System.out.println("Credenciales no validas como editor: " + nombre_usuario);
        UsuariosBasicos.add(new UsuarioBasico(nombre_usuario,contra));
        return false;
    }
}
