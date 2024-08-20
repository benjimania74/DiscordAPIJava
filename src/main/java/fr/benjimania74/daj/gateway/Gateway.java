package fr.benjimania74.daj.gateway;

import fr.benjimania74.daj.gateway.opcode.OPCodeManager;
import fr.benjimania74.daj.gateway.websocket.WSClient;
import lombok.Getter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Getter
public class Gateway {
    private final OPCodeManager opCodeManager;

    private final String token;
    private final WSClient client;
    private final int intentValues;

    private final String API_VERSION = "10";

    public Gateway(String token, Collection<Intents> intents) throws Exception {
        opCodeManager = new OPCodeManager(this);

        this.token = token;
        this.intentValues = Intents.calculate(intents);

        client = new WSClient(this);
    }

    public Gateway(String token, Intents... intents) throws Exception {
        this(token, Arrays.stream(intents).collect(Collectors.toList()));
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
