package fr.benjimania74.daj.gateway.opcode;

import org.json.simple.JSONObject;

import java.io.IOException;

public class VoiceStateUpdate extends OPCode{
    public VoiceStateUpdate(int code, OPCodeManager opCodeManager) {
        super(4, opCodeManager);
    }

    @Override
    public void perform(int s, String t, JSONObject d) throws IOException {

    }
}