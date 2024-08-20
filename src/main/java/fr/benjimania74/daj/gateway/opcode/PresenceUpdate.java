package fr.benjimania74.daj.gateway.opcode;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;

public class PresenceUpdate extends OPCode {
    public PresenceUpdate(int code, OPCodeManager opCodeManager) {
        super(3, opCodeManager);
    }

    @Override
    public void perform(int s, String t, JSONObject d) throws IOException {
        JSONObject toSend = new JSONObject();
        toSend.put("op", getCode());

        JSONObject presence = new JSONObject();
        presence.put("since", null);
        presence.put("status", "dnd");
        presence.put("afk", false);

        JSONObject activity = new JSONObject();
        activity.put("name", "Je suis connecté !");
        activity.put("type", 4);
        activity.put("details", "Test");
        activity.put("state", "Bonjour :)");

        JSONArray activities = new JSONArray();
        activities.add(activity);
        presence.put("activities", activities);

        toSend.put("d", presence);

        System.out.println(toSend.toJSONString());
        getOpCodeManager().getGateway().sendMessage(toSend.toJSONString());
    }
}
