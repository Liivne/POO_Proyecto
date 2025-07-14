package org.example.Logica;

import java.time.LocalDate;

/**
 * La clase {@code TorneoBuilder} implementa el patrón Builder para facilitar
 * la creación de objetos {@link Torneo} con múltiples atributos opcionales.
 *
 * Permite construir instancias de {@code Torneo} mediante una interfaz fluida
 * (métodos encadenados) para configurar sus propiedades paso a paso.
 */
public class TorneoBuilder {

    private String nombreTorneo;
    private FORMATO formatoTorneo;
    private TIPOPARTICIPANTES tipoParticipantes;
    private String lugarTorneo;
    private LocalDate comienzoTorneo;
    private String disciplina;

    //Establece nombre del Torneo.
    public TorneoBuilder conNombre(String nombre) {
        this.nombreTorneo = nombre;
        return this;
    }

    //Establece Formato del Torneo.
    public TorneoBuilder conFormato(FORMATO formato) {
        this.formatoTorneo = formato;
        return this;
    }

    //Establece el tipo de participantes del Torneo.
    public TorneoBuilder conTipoParticipantes(TIPOPARTICIPANTES tipoparticipante) {
        this.tipoParticipantes = tipoparticipante;
        return this;
    }

    //Establece el Lugador donde se llevara a cabo el Torneo.
    public TorneoBuilder conLugar(String lugar) {
        this.lugarTorneo = lugar;
        return this;
    }

    //Establece la fecha de inicio que tendrá el Torneo.
    public TorneoBuilder conFechaInicio(LocalDate fecha) {
        this.comienzoTorneo = fecha;
        return this;
    }

    //Establece los premios que podría tener el Torneo.
    public TorneoBuilder conDisciplina(String disciplina) {
        this.disciplina = disciplina;
        return this;
    }

    /**
     * Construye y devuelve una nueva instancia de {@link Torneo}
     * utilizando los valores proporcionados.
     *
     * @return una instancia configurada de {@code Torneo}
     */
    public Torneo build() {
        return new Torneo(nombreTorneo, formatoTorneo, tipoParticipantes, comienzoTorneo, lugarTorneo, disciplina);
    }
}
