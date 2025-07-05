package org.example.Logica;
import java.util.ArrayList;
import java.util.List;


public class Equipo extends Participantes {
    private String nombre;
    private ArrayList<String> integrantes;

    public Equipo(String nombre, String contacto){
        super(nombre, contacto);
        this.integrantes = new ArrayList<>();
        this.nombre = nombre;
    }

    public String getNombreEquipo() {
        return nombre;
    }

    /**
     * Agrega un persona al equipo.
     *
     * @param miembro es la persona que se desea agregar.
     */
    public void addPersona(String miembro){
        if (!integrantes.contains(miembro)) {
            integrantes.add(miembro);
        } else {
            System.out.println("Jugador ya registrado");
        }
    }

    public List<String> getIntegrantes() {
        return integrantes;
    }
    
    public int size(){
        return integrantes.size();
    }

    @Override
    public String toString() {
        return getNombreEquipo() + " (" + getContacto() + ")";
    }

    public String getTipo() {
        return "Equipo";
    }
}

