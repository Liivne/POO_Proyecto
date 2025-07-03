package org.example.Logica;

import java.time.LocalDate;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the final proyect!");

        LoginPersonas iniciosesion = new LoginPersonas();

        //Implementación del factory method (aplicado de manera simplificada)
        Participantes kfc1 = ParticipanteFactory.crearParticipante(TIPOPARTICIPANTES.ENEQUIPOS, "KFC1", "KFC1@gmail.com");
        Participantes kfc2 = ParticipanteFactory.crearParticipante(TIPOPARTICIPANTES.ENEQUIPOS, "KFC2", "KFC2@gmail.com");
        Participantes kfc3 = ParticipanteFactory.crearParticipante(TIPOPARTICIPANTES.ENEQUIPOS, "KFC3", "KFC3@gmail.com");
        Participantes kfc4 = ParticipanteFactory.crearParticipante(TIPOPARTICIPANTES.ENEQUIPOS, "KFC4", "KFC4@gmail.com");

        /*Equipo kfc1 = new Equipo("kfc1","kfc1@gmail.com");
        Equipo kfc2 = new Equipo("kfc2","kfc2@gmail.com");
        Equipo kfc3 = new Equipo("kfc3","kfc3@gmail.com");
        Equipo kfc4 = new Equipo("kfc4","kfc4@gmail.com");*/

        ArrayList<Participantes> participantes = new ArrayList<>();
        participantes.add(kfc1);
        participantes.add(kfc2);
        participantes.add(kfc3);
        participantes.add(kfc4);

        //GenerarCalendario c2 = new GenerarCalendarioEquipos(LocalDate.now(),participantes,FORMATO.CAMPEONATO);

        Torneo torneo1 = new TorneoBuilder() //aplicacion del Builder
                .conNombre("Campeonato del KFC")
                .conFormato(FORMATO.CAMPEONATO)
                .conTipoParticipantes(TIPOPARTICIPANTES.ENEQUIPOS)
                .conFechaInicio(LocalDate.now())
                .conLugar("Cancha UdeC")
                .build();


        torneo1.addParticipantes(kfc1);
        torneo1.addParticipantes(kfc2);
        torneo1.addParticipantes(kfc3);
        torneo1.addParticipantes(kfc4);
        torneo1.crearCalendario();

        // Mostrar fechas generadas
        ArrayList<LocalDate> fechasTentativas = torneo1.calendario.getFechas_partidos();
        System.out.println("Fechas de los partidos:");
        for (LocalDate fecha : fechasTentativas) {
            System.out.println(fecha);
        }
    }
}



