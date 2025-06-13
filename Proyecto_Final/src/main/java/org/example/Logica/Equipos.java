package org.example.Logica;
import java.util.ArrayList;


public class Equipos<T> {
    private String nombre;
    private ArrayList<T> equipo;

    public Equipos(String nombre){
        this.nombre = nombre;
        equipo = new ArrayList<T>();

    }

    public String getNombreEquipo() {
        return nombre;
    }

    /**
     * Agrega un persona al equipo.
     *
     * @param persona es la persona que se desea agregar.
     */
    public void addPersona(T persona){
        equipo.add(persona);
    }
    
    public int size(){
        return equipo.size();
    }

}

