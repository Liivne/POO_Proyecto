package org.example.Logica;
import java.time.LocalDate;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        System.out.printf("Welcome to the final proyect!");

        Equipo kfc1 = new Equipo("kfc1","kfc1@gmail.com");
        Equipo kfc2 = new Equipo("kfc2","kfc2@gmail.com");
        Equipo kfc3 = new Equipo("kfc3","kfc3@gmail.com");
        Equipo kfc4 = new Equipo("kfc4","kfc4@gmail.com");

        ArrayList<Participantes> participantes = new ArrayList<>();
        participantes.add(kfc1);
        participantes.add(kfc2);
        participantes.add(kfc3);
        participantes.add(kfc4);

        GenerarCalendario c2 = new GenerarCalendarioEquipos(LocalDate.now(),participantes,FORMATO.CAMPEONATO);

        Torneo torneo1 = new Torneo("ChampionsLeague", FORMATO.CAMPEONATO, c2, TIPOPARTICIPANTES.ENEQUIPOS);

        torneo1.addParticipantes(kfc1);
        torneo1.addParticipantes(kfc2);
        torneo1.addParticipantes(kfc3);
        torneo1.addParticipantes(kfc4);

        // Mostrar fechas generadas
        ArrayList<LocalDate> fechasTentativas = c2.getFechas_partidos();
        System.out.println("Fechas de los partidos:");
        for (LocalDate fecha : fechasTentativas) {
            System.out.println(fecha);
        }
    }
}



