package org.example.Logica;

import java.util.ArrayList;

public class Torneo {

    private String nombreTorneo;
    private ArrayList<Participantes> listaParticipantes;
    //public static ArrayList<Partidos> partidos;
    private FORMATO formatoTorneo;
    private GenerarCalendario calendario;
    private TIPOPARTICIPANTES tipoParticipantes;

    public Torneo (String nombreTorneo, FORMATO formatoTorneo, GenerarCalendario calendario, TIPOPARTICIPANTES tipoParticipantes) {
        this.nombreTorneo = nombreTorneo;
        this.formatoTorneo = formatoTorneo;
        this.calendario = calendario;
        this.tipoParticipantes = tipoParticipantes;

        this.listaParticipantes = new ArrayList<>();
    }

    public void addParticipantes (Participantes participantes) {
        boolean esEquipo = participantes instanceof Equipo;
        boolean esJugador = participantes instanceof Jugador;

        switch (tipoParticipantes) {
            case ENEQUIPOS:
                if (!esEquipo) {
                    System.out.println("Este torneo solo admite equipos");
                    return;
                } break;
            case INDIVIDUAL:
                if (!esJugador) {
                    System.out.println("Este torneo no admite equipos");
                    return;
                } break;
        }
        listaParticipantes.add(participantes);
        System.out.println("el participante agregado " + participantes.getNombre());
    }

    public ArrayList<Participantes> getListaParticipantes() {
        return listaParticipantes;
    }

    public void registrarResultado(){}
}
