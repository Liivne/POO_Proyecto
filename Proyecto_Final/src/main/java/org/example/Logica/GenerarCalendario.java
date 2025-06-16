package org.example.Logica;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.LowerCase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GenerarCalendario {
    private LocalDate fechaBase;
    private DateTimeFormatter fecha_tipo = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private int cantidad_partidos;
    private String fechaBase_string;
    private ArrayList<LocalDate> fechas_partidos;

    public GenerarCalendario(LocalDate comienzo, ArrayList<Equipos<Persona>> lista_equipos, FORMATO formato){
        fechaBase_string = comienzo.format(fecha_tipo);
        fechaBase = LocalDate.parse(fechaBase_string, fecha_tipo);
        
        cantidad_partidos = generarCantidadPartidos(formato, lista_equipos.size());
        fechas_partidos = generarFechasPartidos(cantidad_partidos, fechaBase);
    }

    private ArrayList<LocalDate> generarFechasPartidos(int cantidadPartidos, LocalDate fechainicio) {
        ArrayList<LocalDate> fechaspropuestas = new ArrayList<LocalDate>();
        for (int i = 2; i <= 2*cantidadPartidos; i+=2) {
            fechaspropuestas.add(fechainicio.plusDays(i));
        }
        return fechaspropuestas;
    }

    private int generarCantidadPartidos(FORMATO formato, int equipos) {
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

    public void setFechaBase(LocalDate fechaBase) {
        this.fechaBase = fechaBase;
    }
}
