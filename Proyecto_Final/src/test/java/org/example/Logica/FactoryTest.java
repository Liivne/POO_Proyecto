package org.example.Logica;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParticipanteFactoryTest {

    @Test
    void testCrearEquipo() {
        Participantes participante = ParticipanteFactory.crearParticipante(
                TIPOPARTICIPANTES.ENEQUIPOS, "FC Barcelona", "barca@gmail.com");

        assertNotNull(participante);
        assertTrue(participante instanceof Equipo);
        assertEquals("FC Barcelona", participante.getNombre());
    }

    @Test
    void testCrearJugador() {
        Participantes participante = ParticipanteFactory.crearParticipante(
                TIPOPARTICIPANTES.INDIVIDUAL, "Lionel Messi", "leo10@gmail.com");

        assertNotNull(participante);
        assertTrue(participante instanceof Jugador);
        assertEquals("Lionel Messi", participante.getNombre());
    }

    @Test
    void testCrearParticipanteNulo() {
        assertThrows(NullPointerException.class, () -> {
            ParticipanteFactory.crearParticipante(null, "Desconocido", "none");
        });
    }
}
