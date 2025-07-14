package org.example.Logica;
/**
 * Representa a una persona como una entidad abstracta.
 * Se registran datos básicos de cada persona (nombre, apellidos, correo).
 */
public abstract class Participantes {

    private String nombreyApellido;
    private String contacto;
    /**
     * Constructor principal para crear una persona con todos sus datos.
     *
     * @param nombreyApellido Nombre de la persona y apellido.
     * @param contacto Medio de contacto (correo o numero) de la persona.
     */
    public Participantes(String nombreyApellido, String contacto) {
        this.nombreyApellido = nombreyApellido;
        this.contacto = contacto;
    }

    public String getNombre() {
        return nombreyApellido;
    }

    public abstract String getTipo();
}