package fr.benjimania74.daj.bot.object.channel.dm;

import fr.benjimania74.daj.bot.object.User;
import fr.benjimania74.daj.bot.object.channel.ITextChannel;

public interface IDMChannel extends ITextChannel {
    User getUser();
}