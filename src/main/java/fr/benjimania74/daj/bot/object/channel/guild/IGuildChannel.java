package fr.benjimania74.daj.bot.object.channel.guild;

import fr.benjimania74.daj.bot.object.Guild;
import fr.benjimania74.daj.bot.object.Permission;
import fr.benjimania74.daj.bot.object.Role;
import fr.benjimania74.daj.bot.object.channel.IChannel;

import java.util.EnumSet;
import java.util.Map;

public interface IGuildChannel extends IChannel {
    Guild getGuild();
    String getName();
    Map<Role, EnumSet<Permission>> getPermissions();
    String getTopic();
    int getPosition();
}
