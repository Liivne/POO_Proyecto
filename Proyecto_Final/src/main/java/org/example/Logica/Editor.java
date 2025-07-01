package org.example.Logica;

import java.util.ArrayList;
import java.util.List;


public class Editor {
    public EventManager events;
    public List<TorneoBuilder> Torneos;

    public Editor() {
        this.events = new EventManager("open", "save");
        Torneos = new ArrayList<TorneoBuilder>();
    }

    public void addTorneo(TorneoBuilder torneo){
    Torneos.add(torneo);
    }


}