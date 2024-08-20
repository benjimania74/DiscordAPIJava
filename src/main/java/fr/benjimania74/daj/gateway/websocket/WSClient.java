package fr.benjimania74.daj.gateway.websocket;

import fr.benjimania74.daj.gateway.Gateway;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class WSClient {
    private final Gateway gateway;
    private final Session session;

    public WSClient(Gateway gateway) throws Exception {
        this.gateway = gateway;
        URI uri = URI.create("wss://gateway.discord.gg/?v=" + gateway.getAPI_VERSION() + "&encoding=json");

        WebSocketClient ws = new WebSocketClient();
        ws.start();

        Future<Session> fut = ws.connect(new WSListener(gateway), uri);
        session = fut.get(5, TimeUnit.SECONDS);
    }

    public void sendMessage(String message) throws IOException {
        session.getRemote().sendString(message);
    }

    public boolean isConnected(){
        return this.session.isOpen();
    }

    public void close(){
        session.close();
    }
}
