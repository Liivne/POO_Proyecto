package org.example.Logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class GenerarCalendarioEquipos extends GenerarCalendario {

    public GenerarCalendarioEquipos(LocalDate comienzo, ArrayList<Equipo> lista_equipos, FORMATO formato) {
        super(comienzo);
        this.cantidad_partidos = generarCantidadPartidos(formato, lista_equipos.size());
        this.fechas_partidos = generarFechasPartidos(cantidad_partidos, fechaBase);
    }
}
