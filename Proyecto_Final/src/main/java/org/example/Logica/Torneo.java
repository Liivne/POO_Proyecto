package org.example.Logica;

import java.util.ArrayList;

public class Torneo {

    private String nombreTorneo;
    private ArrayList<Participantes> listaParticipantes;
    //public static ArrayList<Partidos> partidos;
    private FORMATO formatoTorneo;
    private GenerarCalendario calendario;
    private TIPOPARTICIPANTES tipoParticipantes;

    public Torneo (String nombreTorneo, FORMATO formatoTorneo, GenerarCalendario calendario) {
        this.nombreTorneo = nombreTorneo;
        this.formatoTorneo = formatoTorneo;
        this.calendario = calendario;

        this.listaParticipantes = new ArrayList<>();
    }

    public void registrarJugador(Participantes jugador) {}

    public void generarCalendario() {}

    public void registrarResultado(){}
}
