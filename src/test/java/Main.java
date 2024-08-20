import fr.benjimania74.daj.gateway.Gateway;
import fr.benjimania74.daj.gateway.Intents;

public class Main {
    public static void main(String[] args) throws Exception {
        Gateway g = new Gateway("", Intents.GUILD_MESSAGES, Intents.MESSAGE_CONTENT);
    }
}
