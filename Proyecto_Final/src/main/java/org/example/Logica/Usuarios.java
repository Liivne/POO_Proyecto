package org.example.Logica;

public abstract class Usuarios {
    private String nombre_usuario;
    private String contra;
    private Boolean esEditor;

    public Usuarios(String nombre_usuario, String contra, Boolean esEditor){
        this.nombre_usuario = nombre_usuario;
        this.contra = contra;
        this.esEditor = esEditor;
    }

    public String getNombre_Usuario() {
        return nombre_usuario;
    }

    public String getContra() {
        return contra;
    }

    public Boolean getEsEditor() {
        return esEditor;
    }
}
