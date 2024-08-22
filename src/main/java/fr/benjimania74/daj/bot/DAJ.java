package fr.benjimania74.daj.bot;

import fr.benjimania74.daj.bot.listener.Listener;
import fr.benjimania74.daj.gateway.Gateway;
import fr.benjimania74.daj.gateway.Intents;
import fr.benjimania74.daj.gateway.event.Event;
import fr.benjimania74.daj.gateway.event.ReadyEvent;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DAJ {
    private final DAJ instance;

    private Gateway gateway;

    private final List<Listener<?>> listeners;

    public DAJ(String token, Collection<Intents> intents) {
        this.instance = this;
        this.listeners = new ArrayList<>();

        Thread botThread = new Thread(() -> {
            try {
                gateway = new Gateway(instance, token, intents);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        botThread.start();
    }

    public DAJ(String token, Intents... intents) throws Exception {
        this(token, Arrays.stream(intents).collect(Collectors.toList()));
    }

    public void registerListener(Listener<?> listener){
        listeners.add(listener);
    }

    public void unregisterListener(Listener<?> listener){
        listeners.remove(listener);
    }

    public void callListener(Event event){
        for(Listener listener : listeners){
            Class clazz = listener.getClass();
            Method[] methods = clazz.getDeclaredMethods();
            Stream<Method> s = Arrays.stream(methods).filter(
                    method -> method.getName().equals(Arrays.stream(Listener.class.getDeclaredMethods()).findFirst().get().getName())
                            && Arrays.stream(method.getParameters()).findFirst().get().getType().equals(event.getClass())
            );
            if(s.findAny().isPresent()){ listener.onEvent(event); }
        }
    }
}
