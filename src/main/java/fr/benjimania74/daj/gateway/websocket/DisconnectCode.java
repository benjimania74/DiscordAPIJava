package fr.benjimania74.daj.gateway.websocket;

import lombok.Getter;

import java.util.EnumSet;

public enum DisconnectCode {
    UNKNOWN_ERROR(4000, "An Unknown Error appends", true),
    UNKNOWN_OPCODE(4001, "An Unknown Opcode has been send", true),
    DECODE_ERROR(4002, "An invalid request has been sent", true),
    NOT_AUTHENTICATED(4003, "A request has been sent before identify", true),
    AUTHENTIFICATION_FAILED(4004, "Invalid Token", false),
    ALREADY_AUTHENTIFICATED(4005, "Trying to identify more than once", true),
    INVALID_SEQ(4007, "Wrong sequence has been sent", true),
    RATE_LIMITED(4008, "Too much requests have been sent too quickly", true),
    SESSION_TIMED_OUT(4009, "Disconnected for timeout", true),
    INVALID_SHARD(4010, "An invalid shard has been sent during identifying", false),
    SHARDING_REQUIRED(4011, "Sharding must be enabled (too much guilds too handle)", false),
    INVALID_API_VERSION(4012, "Invalid API version", false),
    INVALID_INTENTS(4013, "Invalid Intents sent", false),
    DISALLOWED_INTENTS(4014, "Disallowed Intent sent", false),

    UNKNOWN_DISCONNECT_CODE(-1, "An Unknown Disconnect Code has been received", false);

    @Getter
    private final int code;
    @Getter
    private final String message;
    public final boolean canReconnect;
    DisconnectCode(int code, String message, boolean canReconnect){
        this.code = code;
        this.message = message;
        this.canReconnect = canReconnect;
    }

    public static DisconnectCode getFromCode(int code){
        for(DisconnectCode dc : EnumSet.allOf(DisconnectCode.class)){
            if(dc.getCode() == code){
                return dc;
            }
        }
        return UNKNOWN_DISCONNECT_CODE;
    }
}
