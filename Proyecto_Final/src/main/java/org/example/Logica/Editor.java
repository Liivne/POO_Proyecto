package org.example.Logica;


import java.util.ArrayList;
import java.util.List;

/**
 * La clase {@code Editor} representa a un editor u organizador de torneos
 * Permite gestionar y editar los Torneos y manejar los eventos asociados
 */

public class Editor extends Usuarios{

    public EventManager events;
    public List<Torneo> torneos;

    /**
     * Crea un nuevo editor con credenciales específicas.
     * @param nombre_usuario Nombre de nombre
     * @param contra Contraseña del nombre
     */
    public Editor(String nombre_usuario, String contra) {
        this.events = new EventManager("nuevoTorneo");
        this.torneos = new ArrayList<>();
        super(nombre_usuario,contra);
    }
    public void addTorneo(Torneo torneo){
        torneos.add(torneo);
    }

    public List<Torneo> getTorneos() {
        return torneos;
    }
    public boolean isEditable(){return true;}

}