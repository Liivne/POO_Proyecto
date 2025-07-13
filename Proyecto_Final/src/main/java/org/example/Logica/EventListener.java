package org.example.Logica;

/**
 * La interfaz {@code EventListener} define el contrato para los objetos
 * que desean recibir notificaciones cuando ocurren ciertos eventos.
 *
 * Es parte del patrón de diseño Observer.
 */
public interface EventListener {
    void update(String eventType, Object data);
}