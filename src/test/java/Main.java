import fr.benjimania74.daj.bot.DAJ;
import fr.benjimania74.daj.bot.listener.Listener;
import fr.benjimania74.daj.gateway.Intents;
import fr.benjimania74.daj.gateway.event.ReadyEvent;

public class Main {
    public static void main(String[] args) throws Exception {
        DAJ daj = new DAJ("", Intents.GUILD_MESSAGES, Intents.MESSAGE_CONTENT);
        daj.registerListener(new Listener<ReadyEvent>() {
            @Override
            public void onEvent(ReadyEvent event) {
                System.out.println("Ready !");
            }
        });
    }
}
