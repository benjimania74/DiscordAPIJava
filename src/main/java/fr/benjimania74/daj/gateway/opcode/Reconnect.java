package fr.benjimania74.daj.gateway.opcode;

import org.json.simple.JSONObject;

import java.io.IOException;

public class Reconnect extends OPCode {
    public Reconnect(int code, OPCodeManager opCodeManager) {
        super(7, opCodeManager);
    }

    @Override
    public void perform(JSONObject received) throws IOException {

    }
}
