package org.example.Logica;

public class Jugador extends Participantes {

    public Jugador(String nombreyApellido, String contacto) {
        super(nombreyApellido, contacto);
    }

    public String getTipo() {
        return "Jugador";
    }
}
