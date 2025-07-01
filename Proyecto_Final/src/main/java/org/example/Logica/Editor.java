package org.example.Logica;

import java.util.ArrayList;
import java.util.List;


public class Editor {
    public EventManager events;
    public List<Torneo> torneos;

    public Editor() {
        this.events = new EventManager("open", "save");
        this.torneos = new ArrayList<>();
    }

    public void addTorneo(Torneo torneo){
        torneos.add(torneo);
    }
}