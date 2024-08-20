package fr.benjimania74.daj.gateway.opcode;

import org.json.simple.JSONObject;

import java.io.IOException;

public class RequestGuildMembers extends OPCode{
    public RequestGuildMembers(int code, OPCodeManager opCodeManager) {
        super(8, opCodeManager);
    }

    @Override
    public void perform(int s, String t, JSONObject d) throws IOException {

    }
}
