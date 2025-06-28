package org.example.Logica;

public class ParticipanteFactory {

    public static Participantes crearParticipante(TIPOPARTICIPANTES tipo, String nombre, String contacto) {
        return switch (tipo) {
            case INDIVIDUAL -> new Jugador(nombre, contacto);
            case ENEQUIPOS -> new Equipo(nombre, contacto);
        };
    }
}
