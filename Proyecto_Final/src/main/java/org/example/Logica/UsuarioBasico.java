package org.example.Logica;

public class UsuarioBasico extends Usuarios{

    public UsuarioBasico(String nombre_usuario, String contra){
        super(nombre_usuario,contra);
    }
    public boolean isEditable(){return false;}
}
