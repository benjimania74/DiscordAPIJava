package fr.benjimania74.daj.gateway;

import lombok.Getter;

public enum ActivityType {
    PLAYING(0),
    STREAMING(1),
    LISTENING(2),
    WATCHING(3),
    CUSTOM(4),
    COMPETING(5);

    @Getter
    private final int id;

    ActivityType(int id){
        this.id = id;
    }
}
