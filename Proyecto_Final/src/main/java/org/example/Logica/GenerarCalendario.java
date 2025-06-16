package org.example.Logica;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class GenerarCalendario {
    protected LocalDate fechaBase;
    protected DateTimeFormatter fecha_tipo = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    protected int cantidad_partidos;
    protected String fechaBase_string;
    protected ArrayList<LocalDate> fechas_partidos;

    public GenerarCalendario(LocalDate comienzo){
        fechaBase_string = comienzo.format(fecha_tipo);
        fechaBase = LocalDate.parse(fechaBase_string, fecha_tipo);

    }

    protected ArrayList<LocalDate> generarFechasPartidos(int cantidadPartidos, LocalDate fechainicio) {
        ArrayList<LocalDate> fechaspropuestas = new ArrayList<LocalDate>();
        for (int i = 2; i <= 2*cantidadPartidos; i+=2) {
            fechaspropuestas.add(fechainicio.plusDays(i));
        }
        return fechaspropuestas;
    }

    protected int generarCantidadPartidos(FORMATO formato, int equipos) {
        int resultado = 0;
        switch (formato){
            case FORMATO.LIGASIMPLE:
                resultado = (equipos * (equipos - 1))/2;
                break;
            case FORMATO.CAMPEONATO:
                if ((equipos % 2) == 0) {
                    resultado = equipos - 1;
                } else {
                    int totalEquipos = equipos;
                    while (totalEquipos > 1) {
                        int partidos = totalEquipos / 2;
                        int pasodirecto = totalEquipos % 2;
                        resultado += partidos;
                        totalEquipos = partidos + pasodirecto;
                    }
                }
                break;

            //case FORMATO.SGTE
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
