package fr.benjimania74.daj.gateway.event;

import lombok.AccessLevel;
import lombok.Getter;
import org.json.simple.JSONObject;

@Getter(AccessLevel.PROTECTED)
public abstract class Event {
    private final JSONObject details;

    public Event(JSONObject details){
        this.details = details;
    }
}
