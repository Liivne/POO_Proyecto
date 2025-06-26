package org.example.Logica;

import static org.example.Logica.TIPOPARTICIPANTES.ENEQUIPOS;
import static org.example.Logica.TIPOPARTICIPANTES.INDIVIDUAL;

public enum DISCIPLINAS {
    FUTBOL(ENEQUIPOS),
    VOLLEYBALL(ENEQUIPOS),
    AJEDREZ(INDIVIDUAL),
    BALONCESTO(ENEQUIPOS),
    RUGBY(ENEQUIPOS),
    BOXEO(INDIVIDUAL),
    ;
    private final TIPOPARTICIPANTES tipoDisciplina;
    DISCIPLINAS(TIPOPARTICIPANTES tipoDisciplina) {
        this.tipoDisciplina = tipoDisciplina;
    }
    public TIPOPARTICIPANTES getTipoDisciplina() {
        return tipoDisciplina;
    }
}
