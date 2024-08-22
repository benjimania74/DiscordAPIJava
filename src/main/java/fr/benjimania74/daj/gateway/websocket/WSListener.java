package fr.benjimania74.daj.gateway.websocket;

import fr.benjimania74.daj.gateway.Gateway;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class WSListener implements WebSocketListener {
    private final Gateway gateway;

    private final JSONParser parser = new JSONParser();

    public WSListener(Gateway gateway){
        this.gateway = gateway;
    }

    @Override
    public void onWebSocketBinary(byte[] bytes, int i, int i1) {

    }

    @Override
    public void onWebSocketText(String msg) {
        System.out.println(msg);

        JSONObject object;
        try {
            object = (JSONObject) parser.parse(msg);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        int opcode = Integer.parseInt(String.valueOf((long) object.get("op")));

        JSONObject d;
        if(opcode == 9){
            d = new JSONObject();
            d.put("continue", object.get("d"));
        }else {
            d = (JSONObject) object.get("d");
        }

        try {
            if(opcode == 0){
                String t = (String) object.get("t");
                int s = Integer.parseInt(String.valueOf((long) object.get("s")));
                gateway.getOpCodeManager().callEvent(s,t,d);
                return;
            }

            gateway.getOpCodeManager().callOPCode(opcode, d);
        } catch (IOException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onWebSocketClose(int i, String s) {
        System.out.println("Connection closed");
        System.out.println(i + " : " + s);
    }

    @Override
    public void onWebSocketConnect(Session session) {
        System.out.println("Connection etablished");
    }

    @Override
    public void onWebSocketError(Throwable throwable) {
        throwable.printStackTrace();
    }
}
