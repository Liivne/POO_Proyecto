package org.example.Logica;
import java.util.ArrayList;

/**
 * La clase {@code Equipo} representa los equipos que participan en el torneo.
 * Se extiende de Participantes
 */

public class Equipo extends Participantes {
    private String nombre;
    private ArrayList<String> integrantes;

    /**
     * Se crea un Nuevo equipo
     * @param nombre nombre del equipo
     * @param contacto contacto del equipo (email, número, correo, étc).
     */
    public Equipo(String nombre, String contacto){
        super(nombre, contacto);
        this.integrantes = new ArrayList<>();
        this.nombre = nombre;
    }

    /**
     * Obtiene el tipo de participante
     *
     * @return la cadena "Equipo" para referir al tipo de participante
     */
    public String getTipo() {
        return "Equipo";
    }
}

