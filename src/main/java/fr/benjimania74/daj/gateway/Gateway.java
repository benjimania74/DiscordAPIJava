package fr.benjimania74.daj.gateway;

import fr.benjimania74.daj.gateway.opcode.OPCodeManager;
import fr.benjimania74.daj.gateway.websocket.WSClient;
import lombok.AccessLevel;
import lombok.Getter;

import java.io.IOException;

@Getter
public class Gateway {
    private final String token;

    private final WSClient client;

    private final String API_VERSION = "10";

    public Gateway(String token) throws Exception {
        new OPCodeManager(this);

        this.token = token;

        client = new WSClient(this);
    }

    public void sendMessage(String message) throws IOException {
        client.sendMessage(message);
    }

    public boolean isConnected(){
        return client.isConnected();
    }

    public void close(){
        client.close();
    }
}
