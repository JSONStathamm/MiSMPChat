package ru.team4.mismpm.messaging.chat;

import ru.team4.mismpm.messaging.Message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Chat {

    private final long id;
    private final ChatType type;
    private final Collection<Long> members;
    private final Collection<Long> messages;

    public Chat(long id, ChatType type) {
        this.id = id;
        this.type = type;
        members = new ArrayList<>();
        messages = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public ChatType getType() {
        return type;
    }

    public Collection<Long> getMembers() {
        return members;
    }

    public Collection<Long> getMessages() {
        return messages;
    }

}
