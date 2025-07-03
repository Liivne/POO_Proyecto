package org.example.Logica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Torneo {

    private String nombreTorneo;
    private ArrayList<Participantes> listaParticipantes;
    //public static ArrayList<Partidos> partidos;
    private FORMATO formatoTorneo;
    public GenerarCalendario calendario;
    private TIPOPARTICIPANTES tipoParticipantes;
    private LocalDate comienzo;


    public Torneo (String nombreTorneo, FORMATO formatoTorneo, TIPOPARTICIPANTES tipoParticipantes, LocalDate comienzo) {
        this.nombreTorneo = nombreTorneo;
        this.formatoTorneo = formatoTorneo;
        this.tipoParticipantes = tipoParticipantes;
        this.comienzo = comienzo;

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

    public void crearCalendario(){
        calendario = new GenerarCalendario(comienzo,getListaParticipantes().size(),formatoTorneo);
    }

    public String getNombreTorneo() {
        return nombreTorneo;
    }

    public FORMATO getFormatoTorneo() {
        return formatoTorneo;
    }

    public TIPOPARTICIPANTES getTipoParticipantes() {
        return tipoParticipantes;
    }
}
