package fr.benjimania74.daj.gateway.opcode;

import org.json.simple.JSONObject;

import java.io.IOException;

public class HeartBeat extends OPCode{
    public HeartBeat(int code, OPCodeManager opCodeManager) {
        super(1, opCodeManager);
    }

    @Override
    public void perform(JSONObject received) throws IOException {
        JSONObject obj = new JSONObject();
        int lastSequenceEvent = getOpCodeManager().getLastSequenceEvent();
        obj.put("op", getCode());
        obj.put("d", (lastSequenceEvent == -1 ? null : lastSequenceEvent));
        opCodeManager.getGateway().sendMessage(obj.toJSONString());
        opCodeManager.hasHeartbeated();
    }
}
