package fr.benjimania74.daj.gateway.opcode;

import org.json.simple.JSONObject;

import java.io.IOException;

public class VoiceStateUpdate extends OPCode{
    public VoiceStateUpdate(int code, OPCodeManager opCodeManager) {
        super(4, opCodeManager);
    }

    @Override
    public void perform(JSONObject info) throws IOException {
        JSONObject toSend = new JSONObject();
        toSend.put("op", getCode());
        toSend.put("d", info);
        sendJSON(toSend);
    }
}
