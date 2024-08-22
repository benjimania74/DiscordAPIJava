package fr.benjimania74.daj.gateway;

import fr.benjimania74.daj.bot.DAJ;
import fr.benjimania74.daj.gateway.event.EventManager;
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
    private final DAJ daj;
    private final OPCodeManager opCodeManager;
    private final EventManager eventManager;

    private final String gateway = "wss://gateway.discord.gg";

    private String sessionID;
    private String resumeGateURL;

    private final String token;
    private Status status = Status.ONLINE;
    private boolean afk = false;
    private String activityName = "";
    private String state = "";
    private String activityURL = "";
    private ActivityType activityType = ActivityType.PLAYING;

    private WSClient client;
    private final int intentValues;

    private final String API_VERSION = "10";

    public Gateway(DAJ daj, String token, Collection<Intents> intents) throws Exception {
        this.daj = daj;
        opCodeManager = new OPCodeManager(this);
        eventManager = new EventManager(this);

        this.token = token;
        this.intentValues = Intents.calculate(intents);

        client = new WSClient(this, false);
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

    public void reconnect() throws Exception {
        if(this.isConnected()){return;}
        client = new WSClient(this, sessionID != null);
    }

    public void resumeConnection() throws Exception {
        if(this.getResumeGateURL() == null){return;}
        if(this.isConnected()){this.close();}
        this.client = new WSClient(this, true);
    }
}
