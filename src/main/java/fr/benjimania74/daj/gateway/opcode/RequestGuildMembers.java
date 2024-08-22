package fr.benjimania74.daj.gateway.opcode;

import org.json.simple.JSONObject;

import java.io.IOException;

public class RequestGuildMembers extends OPCode{
    public RequestGuildMembers(int code, OPCodeManager opCodeManager) {
        super(8, opCodeManager);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void perform(JSONObject received) throws IOException {
        JSONObject toSend = new JSONObject();

        toSend.put("op", getCode());

        JSONObject d = new JSONObject();

        d.put("guild_id", "ID");
        d.put("query", "");
        d.put("limit", 0);
        d.put("user_ids", "ID"); // ou JSONArray d'ID

        toSend.put("d", d);

        getOpCodeManager().getGateway().sendMessage(toSend.toJSONString());
    }
}
