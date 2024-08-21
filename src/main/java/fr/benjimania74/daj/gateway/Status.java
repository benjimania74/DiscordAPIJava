package fr.benjimania74.daj.gateway;

public enum Status {
    ONLINE("online"),
    DND("dnd"),
    IDLE("idle"),
    INVISIBLE("invisible"),
    OFFLINE("offline");

    private final String id;

    Status(String id){
        this.id = id;
    }

    @Override
    public String toString(){
        return this.id;
    }
}
