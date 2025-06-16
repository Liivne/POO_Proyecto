package org.example.Logica;
/**
 * Representa a una persona como una entidad abstracta.
 * Se registran datos básicos de cada persona (nombre, apellidos, correo).
 */
public abstract class Participantes {

    private String nombreyApellido;
    private String correo;
    /**
     * Constructor principal para crear una persona con todos sus datos.
     *
     * @param nombreyApellido Nombre de la persona y apellido.
     * @param correo Correo electrónico de la persona.
     */
    public Participantes(String nombreyApellido, String correo) {
        this.nombreyApellido = nombreyApellido;
        this.correo = correo;
    }

    public String getNombre() {
        return nombreyApellido;
    }

    public String getCorreo() {
        return correo;
    }

    public abstract String getTipo();
}