package fr.benjimania74.daj.gateway;

import lombok.Getter;

import java.util.Collection;
import java.util.EnumSet;

public enum Intents {
    GUILDS(0),
    GUILD_MEMBERS(1),
    GUILD_MODERATION(2),
    GUILD_EMOJIS_AND_STICKERS(3),
    GUILD_INTEGRATIONS(4),
    GUILD_WEBHOOKS(5),
    GUILD_INVITES(6),
    GUILD_VOICE_STATES(7),
    GUILD_PRESENCES(8),
    GUILD_MESSAGES(9),
    GUILD_MESSAGE_REACTIONS(10),
    GUILD_MESSAGE_TYPING(11),
    DIRECT_MESSAGES(12),
    DIRECT_MESSAGE_REACTIONS(13),
    DIRECT_MESSAGE_TYPING(14),
    MESSAGE_CONTENT(15),
    GUILD_SCHEDULED_EVENTS(16),
    AUTO_MODERATION_CONFIGURATION(20),
    AUTO_MODERATION_EXECUTION(21),
    GUILD_MESSAGE_POLLS(24),
    DIRECT_MESSAGE_POLLS(25);

    public static Collection<Intents> ALL = EnumSet.allOf(Intents.class);

    @Getter
    private final int val;

    Intents(int val){
        this.val = val;
    }

    public static int calculate(Collection<Intents> intents){
        int total = 0;
        for(Intents intent : intents){
            total |= 1 << intent.getVal();
        }
        return total;
    }

    public static int calculate(Intents intent, Intents... intents){
        int total = 1 << intent.getVal();
        for(Intents i : intents){
            total |= 1 << i.getVal();
        }
        return total;
    }
}
