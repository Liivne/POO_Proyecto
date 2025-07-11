package org.example.Logica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * La clase {@code Torneo} representa un Torneo con sus características.
 *
 * Aquí se agregan los participantes y se genera el calendario
 */
public class Torneo {

    private String nombreTorneo;
    private ArrayList<Participantes> listaParticipantes;
    private FORMATO formatoTorneo;
    public GenerarCalendario calendario;
    private TIPOPARTICIPANTES tipoParticipantes;
    private LocalDate comienzo;

    /**
     * Crea un nuevo torneo.
     *
     * @param nombreTorneo Nombre que tendrá el torneo.
     * @param formatoTorneo Formato del torneo (liga simple o eliminación directa).
     * @param tipoParticipantes Tipo de participantes (Individuales o en equipos).
     * @param comienzo Refiere a fecha inicial del torneo para generar calendario.
     */
    public Torneo (String nombreTorneo, FORMATO formatoTorneo, TIPOPARTICIPANTES tipoParticipantes, LocalDate comienzo) {
        this.nombreTorneo = nombreTorneo;
        this.formatoTorneo = formatoTorneo;
        this.tipoParticipantes = tipoParticipantes;
        this.comienzo = comienzo;

        this.listaParticipantes = new ArrayList<>();
    }

    /**
     * Agrega participantes al torneo
     *
     * @param participantes Participante que se agregará a la Lista de participantes.
     */
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

    /**
     * Obtiene la Lista de participantes del torneo
     * @return Lista de participantes.
     */
    public ArrayList<Participantes> getListaParticipantes() {
        return listaParticipantes;
    }

    public void registrarResultado(){}

    /**
     * Crea un calendario a partir de la fecha de inicio del Torneo
     *
     * También utiliza cantidad de participantes para deducir la cantidad de enfrentamientos.
     */
    public void crearCalendario(){
        calendario = new GenerarCalendario(comienzo,getListaParticipantes().size(),formatoTorneo);
    }

    /**
     * Genera las fechas de los enfrentamientos para el torneo
     * @return Calendario creado.
     */
    public GenerarCalendario getCalendario() {
        return calendario;
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
