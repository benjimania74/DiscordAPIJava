package fr.benjimania74.daj.gateway.opcode;

import org.json.simple.JSONObject;

import java.io.IOException;

public class Identify extends OPCode{
    public Identify(int code, OPCodeManager opCodeManager) {
        super(2, opCodeManager);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void perform(JSONObject received) throws IOException {
        JSONObject toSend = new JSONObject();
        toSend.put("op", getCode());

        JSONObject d = new JSONObject();
        d.put("token", getOpCodeManager().getGateway().getToken());

        JSONObject properties = new JSONObject();
        properties.put("os", System.getProperties().get("os.name"));
        properties.put("browser", "DiscordAPIJava");
        properties.put("device", "DiscordAPIJava");

        d.put("properties", properties);

        d.put("intents", getOpCodeManager().getGateway().getIntentValues());

        toSend.put("d", d);
        getOpCodeManager().getGateway().sendMessage(toSend.toJSONString());
    }
}
