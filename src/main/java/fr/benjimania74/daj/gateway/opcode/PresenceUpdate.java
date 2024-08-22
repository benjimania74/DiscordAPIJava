package fr.benjimania74.daj.gateway.opcode;

import fr.benjimania74.daj.gateway.Gateway;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;

public class PresenceUpdate extends OPCode {
    public PresenceUpdate(int code, OPCodeManager opCodeManager) {
        super(3, opCodeManager);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void perform(JSONObject info) throws IOException {
        Gateway gateway = getOpCodeManager().getGateway();

        JSONObject toSend = new JSONObject();
        toSend.put("op", getCode());

        JSONObject presence = new JSONObject();
        presence.put("since", System.currentTimeMillis()/1000L);
        presence.put("status", gateway.getStatus().toString());
        presence.put("afk", gateway.isAfk());

        JSONObject activity = new JSONObject();
        activity.put("name", gateway.getActivityName());
        activity.put("type", gateway.getActivityType().getId());
        activity.put("url", gateway.getActivityURL());
        activity.put("state", gateway.getState());

        JSONArray activities = new JSONArray();
        activities.add(activity);
        presence.put("activities", activities);

        toSend.put("d", presence);

        sendJSON(toSend);
    }
}
