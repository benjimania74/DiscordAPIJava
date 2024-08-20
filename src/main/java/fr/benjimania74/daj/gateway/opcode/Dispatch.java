package fr.benjimania74.daj.gateway.opcode;

import org.json.simple.JSONObject;

import java.io.IOException;

public class Dispatch extends OPCode{
    public Dispatch(int code, OPCodeManager opCodeManager) {
        super(0, opCodeManager);
    }

    @Override
    public void perform(int s, String eventName, JSONObject d) throws IOException {
        if(eventName.equals("READY")){
            getOpCodeManager().callOPCode(3, 0, null, null);
        }
    }
}
