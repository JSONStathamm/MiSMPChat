package ru.team4.mismpm.storage;

import ru.team4.mismpm.messaging.chat.Chat;
import ru.team4.mismpm.user.User;

import java.util.HashMap;
import java.util.Map;

public class ChatStorage implements IStorage<Long, Chat> {

    private final Map<Long, Chat> map = new HashMap<>();

    @Override
    public void save(Long id, Chat chat) {
        map.put(id, chat);
    }

    @Override
    public Chat get(Long id) {
        return map.get(id);
    }

    @Override
    public void remove(Long id) {
        map.remove(id);
    }

    @Override
    public boolean contains(Long id) {
        return map.containsKey(id);
    }
}
