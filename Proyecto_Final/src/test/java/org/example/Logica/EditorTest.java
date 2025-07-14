package org.example.Logica;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class EditorTest {

    @Test
    void agregarTorneos() {
        Editor editor = new Editor("xXCarlosXx", "carlitos123");
        Torneo torneo = new TorneoBuilder()
                .conNombre("Champions League")
                .conFormato(FORMATO.CAMPEONATO)
                .conTipoParticipantes(TIPOPARTICIPANTES.ENEQUIPOS)
                .conFechaInicio(LocalDate.of(2025,8,23))
                .build();
        editor.addTorneo(torneo);

        assertEquals(1, editor.getTorneos().size());
        assertEquals("Champions League", editor.getTorneos().get(0).getNombreTorneo());
        assertEquals(FORMATO.CAMPEONATO, editor.getTorneos().get(0).getFormatoTorneo());
        assertEquals(TIPOPARTICIPANTES.ENEQUIPOS, editor.getTorneos().get(0).getTipoParticipantes());
    }
}
