package fr.benjimania74.daj.bot.object.channel.guild;

import java.lang.reflect.Member;
import java.util.Set;

public interface IGuildThreadChannel extends IGuildChannel, IGlobalGuildTextChannel {
    IGlobalGuildTextChannel getParent();
    int getMessageCount();
    int getTotalMessageCount();
    Set<Member> getMembers();
    String getAutoArchiveDuration();
    String getLastActivityTimestamp();
    boolean isArchived();
    boolean isLocked();
    boolean isInvitable();
    String createdAt();
    Member getOwner();
}
