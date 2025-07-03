package org.example.Logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginPersonasTest {
    LoginPersonas iniciosesion;
    @BeforeEach
    void setUp() {
       iniciosesion = new LoginPersonas();
    }

    @Test
    void loginUsuario() {
        assertFalse(iniciosesion.login("Eduardo","contra"));
    }
    @Test
    void loginEditor() {
        assertTrue(iniciosesion.login("Link","Hyrule"));
    }
}