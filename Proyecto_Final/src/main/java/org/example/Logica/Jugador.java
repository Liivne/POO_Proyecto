package org.example.Logica;

/**
 * {@code Jugador} representa un Jugador participante de un torneo.
 *
 * Se extiende de Participantes.
 */
public class Jugador extends Participantes {

    /**
     * Crea un Jugador
     *
     * @param nombreyApellido Nombre del jugador.
     * @param contacto Contacto del jugador (email, número, correo, étc).
     */
    public Jugador(String nombreyApellido, String contacto) {
        super(nombreyApellido, contacto);
    }

    /**
     * Obtiene el tipo de participante
     *
     * @return la cadena "Jugador" para referir al tipo de participante
     */
    public String getTipo() {
        return "Jugador";
    }
}
