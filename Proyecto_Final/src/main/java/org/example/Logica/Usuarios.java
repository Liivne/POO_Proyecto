package org.example.Logica;

/**
 * Clase abstracta que representa a un usuario genérico en el sistema.
 */
public abstract class Usuarios {
    private String nombre_usuario;
    private String contra;

    /**
     * Constructor para crear una nueva instancia de {@code Usuarios}.
     *
     * @param nombre_usuario El nombre de usuario.
     * @param contra La contraseña del usuario.
     */
    public Usuarios(String nombre_usuario, String contra){
        this.nombre_usuario = nombre_usuario;
        this.contra = contra;
    }

    public String getNombre_Usuario() {
        return nombre_usuario;
    }

    public String getContra() {
        return contra;
    }
}
