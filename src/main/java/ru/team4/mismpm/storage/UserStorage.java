package ru.team4.mismpm.storage;

import ru.team4.mismpm.messaging.Message;
import ru.team4.mismpm.user.User;

import java.util.HashMap;
import java.util.Map;

public class UserStorage implements IStorage<Long, User> {

    private final Map<Long, User> map = new HashMap<>();

    @Override
    public void save(Long id, User user) {
        map.put(id, user);
    }

    @Override
    public User get(Long id) {
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
