package fr.benjimania74.daj.gateway.websocket;

import fr.benjimania74.daj.gateway.Gateway;

public class DisconnectException extends RuntimeException {
    public DisconnectException(String message, Gateway gateway) throws Exception {
        super(message);
        if(gateway != null){
            gateway.reconnect();
        }
    }
}
