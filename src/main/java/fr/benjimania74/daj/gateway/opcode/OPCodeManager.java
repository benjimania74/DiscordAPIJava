package fr.benjimania74.daj.gateway.opcode;

import fr.benjimania74.daj.gateway.Gateway;
import fr.benjimania74.daj.utils.Classes;
import lombok.Getter;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class OPCodeManager {
    @Getter
    private final Gateway gateway;

    private final HashMap<Integer, OPCode> opCodes;

    private boolean isHeartBeating = false;
    public void hasHeartbeated(){isHeartBeating = true;}
    private boolean heartbeatACK = false;
    public void gotHeartbeatACK(){heartbeatACK = true;}

    @Getter
    private int lastSequenceEvent = -1;

    public OPCodeManager(Gateway gateway){
        this.gateway = gateway;
        opCodes = new HashMap<>();
        registerOPCode();
    }

    private void registerOPCode(){
        Set<Class> opCodes = Classes.findPackageClasses(this.getClass());
        opCodes.stream().filter(c -> c.getSuperclass().equals(OPCode.class)).forEach(c -> {
            Optional<Constructor> opt = Arrays.stream(c.getConstructors()).findFirst();
            if(opt.isPresent()){
                Constructor constr = opt.get();
                OPCode instance = null;
                try {
                    instance = (OPCode) constr.newInstance(0, this);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
                this.opCodes.put(instance.code, instance);
            }
        });
    }

    public void callOPCode(int code, int s, String t, JSONObject d) throws IOException {
        if(this.opCodes.containsKey(code)){
            this.opCodes.get(code).perform(s, t, d);
            if(code == 0){
                lastSequenceEvent = s;
            }
        }
    }

    public void heartBeat(long time){
        if(isHeartBeating || time <= 0){return;}
        Thread heartbeat = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    heartbeatACK = false;
                    if(gateway.isConnected()) {
                        callOPCode(1, 0, null, null);
                    }
                    Thread.sleep(time);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(isHeartBeating && gateway.isConnected() && heartbeatACK){
                    run();
                    return;
                }
                isHeartBeating = false;
                gateway.close();
            }
        });
        heartbeat.start();
    }
}
