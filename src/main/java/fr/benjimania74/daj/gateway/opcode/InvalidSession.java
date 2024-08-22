package fr.benjimania74.daj.gateway.opcode;

import org.json.simple.JSONObject;

import java.io.IOException;

public class InvalidSession extends OPCode{
    public InvalidSession(int code, OPCodeManager opCodeManager) {super(9, opCodeManager);}

    @Override
    public void perform(JSONObject info) throws IOException {
        if((boolean) info.get("continue")){
            try {
                getOpCodeManager().getGateway().reconnect();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else{
            // CAN'T RECONNECT
        }
    }
}
