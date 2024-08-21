package fr.benjimania74.daj.gateway;

import fr.benjimania74.daj.gateway.opcode.OPCodeManager;
import fr.benjimania74.daj.gateway.websocket.WSClient;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Getter @Setter
public class Gateway {
    private final OPCodeManager opCodeManager;

    private final String gateway = "wss://gateway.discord.gg";

    private String sessionID;
    private String resumeGateURL;

    private final String token;
    private Status status = Status.DND;
    private boolean afk = false;
    private String activityName = "Name";
    private String state = "State";
    private String activityURL = "https://discord.dev";
    private ActivityType activityType = ActivityType.CUSTOM;

    private WSClient client;
    private final int intentValues;

    private final String API_VERSION = "10";

    public Gateway(String token, Collection<Intents> intents) throws Exception {
        opCodeManager = new OPCodeManager(this);

        this.token = token;
        this.intentValues = Intents.calculate(intents);

        client = new WSClient(this, false);
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

    public void resumeConnection() throws Exception {
        if(this.getResumeGateURL() == null){return;}
        if(this.isConnected()){this.close();}
        this.client = new WSClient(this, true);
    }
}
