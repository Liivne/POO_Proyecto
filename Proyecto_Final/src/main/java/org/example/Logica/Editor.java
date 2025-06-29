package org.example.Logica;

public class Editor {
    public EventManager events;

    public Editor() {
        this.events = new EventManager("open", "save");
    }

}