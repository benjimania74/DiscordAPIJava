package fr.benjimania74.daj.bot.object.channel.guild;

import fr.benjimania74.daj.bot.object.Category;

public interface IGuildTextChannel extends IGlobalGuildTextChannel {
    Category getParent();
}
