package org.example.Logica;

import java.util.ArrayList;
import java.util.List;

public class LoginPersonas {
    private List<Editor> EditoresRegistrados = new ArrayList<>(); // Almacenaría usuarios, por simplicidad aquí es solo una lista

    public LoginPersonas(){
        EditoresRegistrados.add(new Editor("Link", "Hyrule"));
        EditoresRegistrados.add(new Editor("Ganon", "Gerudo"));
    }

    public boolean login(String usuario, String contra) {
        for (Editor editor : EditoresRegistrados) {
            if (editor.getUsuario().equals(usuario) && editor.getContra().equals(contra)) {
                System.out.println("Inicio de sesión exitoso para: " + usuario);
                return true;
            }
        }
        System.out.println("Credenciales incorrectas para: " + usuario);
        return false;
    }
}
