package org.example.Logica;

import java.time.LocalDate;

public class TorneoBuilder {

    private String nombreTorneo;
    private FORMATO formatoTorneo;
    private TIPOPARTICIPANTES tipoParticipantes;
    private String lugarTorneo;
    private LocalDate comienzoTorneo;
    private String premiosTorneo;

    public TorneoBuilder conNombre(String nombre) {
        this.nombreTorneo = nombre;
        return this;
    }

    public TorneoBuilder conFormato(FORMATO formato) {
        this.formatoTorneo = formato;
        return this;
    }

    public TorneoBuilder conTipoParticipantes(TIPOPARTICIPANTES tipoparticipante) {
        this.tipoParticipantes = tipoparticipante;
        return this;
    }

    public TorneoBuilder conLugar(String lugar) {
        this.lugarTorneo = lugar;
        return this;
    }

    public TorneoBuilder conFechaInicio(LocalDate fecha) {
        this.comienzoTorneo = fecha;
        return this;
    }

    public TorneoBuilder conPremios(String premios) {
        this.premiosTorneo = premios;
        return this;
    }

    public Torneo build() {
        return new Torneo(nombreTorneo, formatoTorneo, tipoParticipantes, comienzoTorneo);
    }
}
