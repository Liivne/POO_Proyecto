package org.example.Logica;
import java.time.LocalDate;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        System.out.printf("Welcome to the final proyect!");

        //Forma de uso del GenerarCalendario
        ArrayList<LocalDate> fechas_tentativas = new ArrayList<LocalDate>();
        ArrayList<Equipo> equipos = new ArrayList<Equipo>();

        Equipo kfc1 = new Equipo("kfc1","kfc1@gmail.com");
        Equipo kfc2 = new Equipo("kfc2","kfc2@gmail.com");
        Equipo kfc3 = new Equipo("kfc3","kfc3@gmail.com");
        Equipo kfc4 = new Equipo("kfc4","kfc4@gmail.com");

        equipos.add(kfc1);
        equipos.add(kfc2);
        equipos.add(kfc3);
        equipos.add(kfc4);

        GenerarCalendario c = new GenerarCalendarioEquipos(LocalDate.now(),equipos,FORMATO.CAMPEONATO);
        fechas_tentativas = c.getFechas_partidos();
        System.out.println(fechas_tentativas);
    }
}
