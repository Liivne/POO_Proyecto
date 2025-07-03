package org.example.Logica;

import java.util.ArrayList;
import java.util.List;


public class Editor {
    private String usuario;
    private String contra;
    public EventManager events;
    public List<Torneo> torneos;

    public Editor(String usuario, String contra) {
        this.events = new EventManager("open", "save");
        this.torneos = new ArrayList<>();
        this.usuario = usuario;
        this.contra = contra;
    }

    public void addTorneo(Torneo torneo){
        torneos.add(torneo);
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContra() {
        return contra;
    }
}