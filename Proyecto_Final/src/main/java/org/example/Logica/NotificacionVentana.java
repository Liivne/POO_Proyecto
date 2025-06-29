package org.example.Logica;

public class NotificacionVentana implements EventListener {
    private String email;

    public NotificacionVentana(String email) {
        this.email = email;
    }

    @Override
    public void update(String eventType) {
        System.out.println("Email to " + email + ": Someone has performed " + eventType);
    }
}