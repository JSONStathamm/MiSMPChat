package ru.team4.mismpm.storage;

import ru.team4.mismpm.messaging.channel.Channel;

import java.util.HashMap;
import java.util.Map;

public class ChannelStorage implements IStorage<Long, Channel> {

    private final Map<Long, Channel> map = new HashMap<>();

    @Override
    public void save(Long id, Channel channel) {
        map.put(id, channel);
    }

    @Override
    public Channel get(Long id) {
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
