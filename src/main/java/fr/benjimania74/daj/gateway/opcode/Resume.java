package fr.benjimania74.daj.gateway.opcode;

import fr.benjimania74.daj.gateway.Gateway;
import org.json.simple.JSONObject;

import java.io.IOException;

public class Resume extends OPCode {
    public Resume(int code, OPCodeManager opCodeManager) {
        super(6, opCodeManager);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void perform(int s, String t, JSONObject dReceived) throws IOException {
        Gateway gateway = getOpCodeManager().getGateway();
        try {
            gateway.resumeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }

        JSONObject toSend = new JSONObject();

        toSend.put("op", getCode());

        JSONObject d = new JSONObject();

        d.put("token", gateway.getToken());
        d.put("session_id", gateway.getSessionID());
        d.put("seq", getOpCodeManager().getLastSequenceEvent());

        toSend.put("d", d);

        gateway.sendMessage(toSend.toJSONString());
    }
}
