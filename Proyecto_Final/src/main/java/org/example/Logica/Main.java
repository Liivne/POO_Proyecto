package org.example.Logica;
import java.time.LocalDate;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        System.out.printf("Welcome to the final proyect!");

        //Forma de uso del GenerarCalendario
        ArrayList<LocalDate> fechas_tentativas = new ArrayList<LocalDate>();
        ArrayList<Equipos<Persona>> equipos = new ArrayList<Equipos<Persona>>();

        Equipos<Persona> kfc1 = new Equipos<>("kfc1");
        Equipos<Persona> kfc2 = new Equipos<>("kfc2");
        Equipos<Persona> kfc3 = new Equipos<>("kfc3");
        Equipos<Persona> kfc4 = new Equipos<>("kfc4");

        equipos.add(kfc1);
        equipos.add(kfc2);
        equipos.add(kfc3);
        equipos.add(kfc4);

        GenerarCalendario c = new GenerarCalendarioEquipos(LocalDate.now(),equipos,FORMATO.CAMPEONATO);
        fechas_tentativas = c.getFechas_partidos();

    }
}
