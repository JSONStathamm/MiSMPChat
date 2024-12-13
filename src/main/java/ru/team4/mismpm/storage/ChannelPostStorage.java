package ru.team4.mismpm.storage;

import ru.team4.mismpm.messaging.Message;
import ru.team4.mismpm.messaging.channel.ChannelPost;

import java.util.HashMap;
import java.util.Map;

public class ChannelPostStorage implements IStorage<Long, ChannelPost> {

    private final Map<Long, ChannelPost> map = new HashMap<>();

    @Override
    public void save(Long id, ChannelPost post) {
        map.put(id, post);
    }

    @Override
    public ChannelPost get(Long id) {
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
