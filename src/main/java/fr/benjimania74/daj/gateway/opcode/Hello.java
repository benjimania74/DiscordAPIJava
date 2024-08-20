package fr.benjimania74.daj.gateway.opcode;

import org.json.simple.JSONObject;

public class Hello extends OPCode{
    public Hello(int code, OPCodeManager opCodeManager) {
        super(10, opCodeManager);
    }

    @Override
    public void perform(int s, String t, JSONObject d) {
        long hbTime = (long) d.get("heartbeat_interval");
        OPCodeManager.getInstance().heartBeat(hbTime);
        System.out.println(d.toJSONString());
    }
}
