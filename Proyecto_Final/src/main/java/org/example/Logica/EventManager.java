package org.example.Logica;

import java.util.*;


/**
 * La clase {@code EventManager} implementa el patrón de diseño Observer
 * Permite suscribir, cancelar suscripciones y notificar a los oyentes
 * cuando ocurren determinados eventos.
 *
 * Cada tipo de evento está asociado a una lista de {@link EventListener}.
 * Cuando un evento ocurre, todos los oyentes suscritos a ese evento son notificados.
 */
public class EventManager {
    /**
     * Mapa que almacena las listas de oyentes (listeners) suscritos a cada tipo de evento.
     * La clave es el nombre del evento y el valor es la lista de {@code EventListener}.
     */
    Map<String, List<EventListener>> listeners = new HashMap<>();

    /**
     * Crea un nuevo {@code EventManager} e inicializa las listas de oyentes
     *
     * @param operations Los tipos de eventos a gestionar (ej. "open", "save").
     */
    public EventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    /**
     * Suscribe un oyente a un tipo de evento específico.
     *
     * @param eventType El nombre del evento al que el oyente quiere suscribirse.
     * @param listener  El {@code EventListener} que será notificado cuando ocurra el evento.
     */
    public void subscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    /**
     * Cancela la suscripción de un oyente a un tipo de evento específico.
     *
     * @param eventType El nombre del evento del que se quiere cancelar la suscripción.
     * @param listener  El {@code EventListener} que se desea eliminar.
     */
    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    /**
     * Notifica a todos los oyentes suscritos a un tipo de evento específico.
     *
     * @param eventType El nombre del evento que ocurrió.
     */
    public void notify(String eventType, Torneo data) {
        List<EventListener> users = listeners.get(eventType);
        for (EventListener listener : users) {
            listener.update(eventType, data);
        }
    }
}