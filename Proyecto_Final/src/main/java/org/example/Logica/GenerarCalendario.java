package org.example.Logica;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * La clase {@code GenerarCalendario} se encarga de generar un calendario
 * de fechas para los partidos de un torneo, basado en la fecha de inicio,
 * el número de participantes y el formato del torneo.
 */
public class GenerarCalendario {
    protected LocalDate fechaBase;
    protected DateTimeFormatter fecha_tipo = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    protected int cantidad_partidos;
    protected String fechaBase_string;
    protected ArrayList<LocalDate> fechas_partidos;

    /**
     * Crea un nuevo {@code GenerarCalendario} generando automáticamente
     * las fechas de los partidos según el formato y número de participantes.
     *
     * @param comienzo Fecha de inicio del torneo.
     * @param cant_contrincantes Numero de participantes en el torneo.
     * @param formato Formato del torneo (Liga Simple o Campeonato).
     */
    public GenerarCalendario(LocalDate comienzo, int cant_contrincantes, FORMATO formato){
        fechaBase_string = comienzo.format(fecha_tipo);
        fechaBase = LocalDate.parse(fechaBase_string, fecha_tipo);
        cantidad_partidos = generarCantidadPartidos(formato, cant_contrincantes);
        fechas_partidos = generarFechasPartidos(cantidad_partidos, fechaBase);
    }

    /**
     * Genera las fechas de los partidos, cada 2 días desde la fecha de inicio.
     *
     * @param cantidadPartidos Número total de partidos a programar.
     * @param fechainicio Fecha de inicio del torneo.
     * @return Lista de fechas para cada partido.
     */
    protected ArrayList<LocalDate> generarFechasPartidos(int cantidadPartidos, LocalDate fechainicio) {
        ArrayList<LocalDate> fechaspropuestas = new ArrayList<LocalDate>();
        for (int i = 0; i < 2*cantidadPartidos; i+=2) {
            fechaspropuestas.add(fechainicio.plusDays(i));
        }
        return fechaspropuestas;
    }

    /**
     * Calcula el número total de partidos según el formato del torneo.
     *
     * @param formato Formato del torneo.
     * @param contrincantes Número de participantes.
     * @return Número total de partidos a programar.
     */
    protected int generarCantidadPartidos(FORMATO formato, int contrincantes) {
        int resultado = 0;
        switch (formato){
            case FORMATO.LIGASIMPLE:
                resultado = (contrincantes * (contrincantes - 1))/2;
                break;
            case FORMATO.CAMPEONATO:
                if ((contrincantes % 2) == 0) {
                    resultado = contrincantes - 1;
                } else {
                    int totalEquipos = contrincantes;
                    while (totalEquipos > 1) {
                        int partidos = totalEquipos / 2;
                        int pasodirecto = totalEquipos % 2;
                        resultado += partidos;
                        totalEquipos = partidos + pasodirecto;
                    }
                }
                break;

            default:
                throw new IllegalStateException("Este formato no está contemplado: " + formato);
        }
            
        return resultado;
    }

    public ArrayList<LocalDate> getFechas_partidos() {
        return fechas_partidos;
    }

    public LocalDate getFechaBase() {
        return fechaBase;
    }
}
