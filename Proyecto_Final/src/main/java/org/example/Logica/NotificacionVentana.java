package org.example.Logica;

/**
 * La clase {@code NotificacionVentana} implementa la interfaz {@link EventListener}
 * y actúa como un listener concreto para el patrón Observer.
 *
 * Esta clase simula el envío de notificaciones por correo electrónico cuando
 * se produce un evento en el {@link EventManager}.
 */
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