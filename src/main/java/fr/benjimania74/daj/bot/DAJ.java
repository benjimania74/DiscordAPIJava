package fr.benjimania74.daj.bot;

import com.sun.istack.internal.NotNull;
import fr.benjimania74.daj.bot.listener.EventHandler;
import fr.benjimania74.daj.bot.listener.IListener;
import fr.benjimania74.daj.gateway.Gateway;
import fr.benjimania74.daj.gateway.Intents;
import fr.benjimania74.daj.gateway.event.Event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class DAJ {
    private final DAJ instance;

    private Gateway gateway;

    private final HashMap<Class<? extends Event>, HashMap<Method, IListener>> listenersMethods;

    public DAJ(@NotNull String token, @NotNull Collection<Intents> intents) {
        this.instance = this;
        this.listenersMethods = new HashMap<>();

        Thread botThread = new Thread(() -> {
            try {
                gateway = new Gateway(instance, token, intents);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        botThread.start();
    }

    public DAJ(@NotNull String token, @NotNull Intents... intents) throws Exception {
        this(token, Arrays.stream(intents).collect(Collectors.toList()));
    }

    @SuppressWarnings("unchecked")
    public void registerListener(@NotNull IListener listener){
        Arrays.stream(listener.getClass().getDeclaredMethods())
                .filter(method -> method.getAnnotation(EventHandler.class) != null &&
                        method.getParameters().length == 1 &&
                        Event.class.isAssignableFrom(method.getParameterTypes()[0]))
                .forEach(method -> {
                    Class<?> eventClass = method.getParameterTypes()[0];
                    HashMap<Method, IListener> methods = listenersMethods.getOrDefault(eventClass, new HashMap<>());
                    methods.put(method, listener);
                    if(methods.size() == 1){ listenersMethods.put((Class<? extends Event>) eventClass, methods); }
                });
    }

    public void unregisterListener(@NotNull IListener listener){
        Arrays.stream(listener.getClass().getDeclaredMethods())
                .filter(method -> method.getAnnotation(EventHandler.class) != null &&
                        method.getParameters().length == 1 &&
                        Event.class.isAssignableFrom(method.getParameterTypes()[0]))
                .forEach(method -> {
                    Class<?> event = method.getParameterTypes()[0];
                    if(listenersMethods.containsKey(event)){
                        listenersMethods.get(event).remove(method, listener);
                    }
                });
    }

    public void callListener(Event event){
        listenersMethods.getOrDefault(event.getClass(), new HashMap<>()).forEach((method, listener) -> {
            try {
                method.invoke(listener, event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
