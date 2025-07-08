package org.example.Logica;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase {@code Editor} representa a un editor u organizador de torneos
 * Permite gestionar y editar los Torneos y manejar los eventos asociados
 */

public class Editor {
    private String usuario;
    private String contra;
    public EventManager events;
    public List<Torneo> torneos;

    /**
     * Crea un nuevo editor con credenciales específicas.
     * @param usuario Nombre de usuario
     * @param contra Contraseña del usuario
     */
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