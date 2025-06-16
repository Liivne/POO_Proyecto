package org.example.Logica;

public class Jugador extends Participantes {

    public Jugador(String nombreyApellido, String correo) {
        super(nombreyApellido, correo);
    }

    public String getTipo() {
        return "Jugador";
    }
}
