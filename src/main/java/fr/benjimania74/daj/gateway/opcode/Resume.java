package fr.benjimania74.daj.gateway.opcode;

import org.json.simple.JSONObject;

import java.io.IOException;

public class Resume extends OPCode {
    public Resume(int code, OPCodeManager opCodeManager) {
        super(6, opCodeManager);
    }

    @Override
    public void perform(int s, String t, JSONObject d) throws IOException {

    }
}
