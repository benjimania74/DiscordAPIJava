package fr.benjimania74.daj.gateway.opcode;

import org.json.simple.JSONObject;

import java.io.IOException;

public class Hello extends OPCode{
    public Hello(int code, OPCodeManager opCodeManager) {
        super(10, opCodeManager);
    }

    @Override
    public void perform(int s, String t, JSONObject d) throws IOException {
        long hbTime = (long) d.get("heartbeat_interval");
        getOpCodeManager().heartBeat((long) (hbTime*0.9));
        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        getOpCodeManager().callOPCode(2,0,null, null);
    }
}
