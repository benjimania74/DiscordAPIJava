package fr.benjimania74.daj.bot.object.channel.dm;

import fr.benjimania74.daj.bot.object.User;
import fr.benjimania74.daj.bot.object.channel.ITextChannel;

import java.util.Set;

public interface IGroupChannel extends ITextChannel {
    String getName();
    Set<User> getUsers();
    User getOwner();
}
