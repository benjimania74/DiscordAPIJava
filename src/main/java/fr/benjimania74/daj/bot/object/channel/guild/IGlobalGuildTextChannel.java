package fr.benjimania74.daj.bot.object.channel.guild;

import fr.benjimania74.daj.bot.object.channel.ITextChannel;

public interface IGlobalGuildTextChannel extends IGuildChannel, ITextChannel {
    int getMessageDelay();
}
