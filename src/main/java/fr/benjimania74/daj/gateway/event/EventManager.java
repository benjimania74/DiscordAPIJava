package fr.benjimania74.daj.gateway.event;

import fr.benjimania74.daj.gateway.Gateway;
import org.json.simple.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;

public class EventManager {
    private final Gateway gateway;

    public EventManager(Gateway gateway){
        this.gateway = gateway;

    }

    public void callEvent(String name, JSONObject details) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Events e = Events.getEvent(name);
        Class<? extends Event> c = e.getEventClass();
        if(c == null){return;}
        Optional<Constructor<?>> optionalConstructor = Arrays.stream(c.getConstructors()).findFirst();
        if(!optionalConstructor.isPresent()){return;}
        Constructor<?> constructor = optionalConstructor.get();

        gateway.getDaj().callListener((Event) constructor.newInstance(details));
    }
}
