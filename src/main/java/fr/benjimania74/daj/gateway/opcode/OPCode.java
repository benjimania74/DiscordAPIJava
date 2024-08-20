package fr.benjimania74.daj.gateway.opcode;

import lombok.Getter;
import org.json.simple.JSONObject;

import java.io.IOException;

@Getter
public abstract class OPCode {
    public final int code;

    public final OPCodeManager opCodeManager;

    public OPCode(int code, OPCodeManager opCodeManager){
        this.code = code;
        this.opCodeManager = opCodeManager;
    }

    public abstract void perform(int s, String t, JSONObject d) throws IOException;

    public void sendJSON(JSONObject toSend) throws IOException {
        opCodeManager.getGateway().sendMessage(toSend.toJSONString());
    }
}