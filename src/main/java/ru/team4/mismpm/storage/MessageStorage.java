package ru.team4.mismpm.storage;

import ru.team4.mismpm.messaging.Message;
import ru.team4.mismpm.user.User;

import java.util.HashMap;
import java.util.Map;

public class MessageStorage implements IStorage<Long, Message> {

    private final Map<Long, Message> map = new HashMap<>();

    @Override
    public void save(Long id, Message message) {
        map.put(id, message);
    }

    @Override
    public Message get(Long id) {
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
