package fr.benjimania74.daj.bot.listener;

import fr.benjimania74.daj.gateway.event.Event;

public interface Listener<T extends Event> {
    void onEvent(T event);
}

