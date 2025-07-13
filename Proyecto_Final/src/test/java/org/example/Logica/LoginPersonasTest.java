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
        assertFalse(iniciosesion.loginUsuario("Eduardo","contra"));
    }
    @Test
    void loginEditor() {
        assertTrue(iniciosesion.loginEditor("Link","Hyrule"));
    }
}