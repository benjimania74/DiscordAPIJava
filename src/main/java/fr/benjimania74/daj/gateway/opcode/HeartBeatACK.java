package fr.benjimania74.daj.gateway.opcode;

import org.json.simple.JSONObject;

public class HeartBeatACK extends OPCode{
    public HeartBeatACK(int code, OPCodeManager opCodeManager) {
        super(11, opCodeManager);
    }

    @Override
    public void perform(JSONObject info) {
        getOpCodeManager().gotHeartbeatACK();
    }
}
