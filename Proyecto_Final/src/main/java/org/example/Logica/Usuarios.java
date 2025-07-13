package org.example.Logica;

public abstract class Usuarios {
    private String nombre_usuario;
    private String contra;

    public Usuarios(String nombre_usuario, String contra){
        this.nombre_usuario = nombre_usuario;
        this.contra = contra;
    }

    public abstract boolean isEditable();

    public String getNombre_Usuario() {
        return nombre_usuario;
    }

    public String getContra() {
        return contra;
    }


}
