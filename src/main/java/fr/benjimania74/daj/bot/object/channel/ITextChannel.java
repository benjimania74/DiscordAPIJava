package fr.benjimania74.daj.bot.object.channel;

import fr.benjimania74.daj.bot.object.Message;

public interface ITextChannel extends IChannel{
    Message getLastMessage();
    String getLastPinTimestamp();
}
