package fr.benjimania74.daj.gateway.opcode;

import org.json.simple.JSONObject;

import java.io.IOException;

public class RequestGuildMembers extends OPCode{
    public RequestGuildMembers(int code, OPCodeManager opCodeManager) {
        super(8, opCodeManager);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void perform(JSONObject info) throws IOException {
        JSONObject toSend = new JSONObject();

        toSend.put("op", getCode());

        JSONObject d = new JSONObject();

        d.put("guild_id", info.get("guild_id"));
        d.put("limit", 0);
        d.put("presences", info.get("online"));

        if(info.get("users") != null){
            d.put("user_ids", info.get("users"));
        }else {
            d.put("query", info.get("query"));
        }

        d.put("nonce", info.get("nonce"));

        toSend.put("d", d);

        sendJSON(toSend);
    }
}
