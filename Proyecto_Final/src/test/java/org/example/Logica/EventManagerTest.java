package org.example.Logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventManagerTest {

    private EventManager eventManager;
    private TestListener listener1;
    private TestListener listener2;

    @BeforeEach
    void setUp() {
        eventManager = new EventManager("inicio", "fin");
        listener1 = new TestListener();
        listener2 = new TestListener();
    }

    @Test
    void testNotificarUnListener() {
        eventManager.subscribe("inicio", listener1);
        eventManager.notify("inicio", null);
        assertTrue(listener1.notificado);
    }

    @Test
    void testNotificarMultiplesListeners() {
        eventManager.subscribe("inicio", listener1);
        eventManager.subscribe("inicio", listener2);

        eventManager.notify("inicio", null);

        assertTrue(listener1.notificado);
        assertTrue(listener2.notificado);
    }

    @Test
    void testNotificarSinListeners() {
        assertDoesNotThrow(() -> eventManager.notify("inicio", null));
    }

    static class TestListener implements EventListener {
        boolean notificado = false;

        @Override
        public void update(String eventType, Torneo data) {
            notificado  = true;
        }
    }
}


