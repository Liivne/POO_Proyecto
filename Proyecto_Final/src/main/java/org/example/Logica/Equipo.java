package org.example.Logica;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Obtiene la lista de integrantes del equipo
     * @return Lista de integrantes de un equipo
     */
    public List<String> getIntegrantes() {
        return integrantes;
    }

    /**
     * Obtiene cantidad de integrantes del equipo
     * @return el numero de integrantes del equipo
     */
    public int size(){
        return integrantes.size();
    }

    /**
     * Devuelve una representación en cadena del equipo.
     *
     * El formato es: "NombreEquipo (contacto)".
     *
     * @return Una cadena con el nombre del equipo y el contacto asociado.
     */
    @Override
    public String toString() {
        return getNombreEquipo() + " (" + getContacto() + ")";
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

