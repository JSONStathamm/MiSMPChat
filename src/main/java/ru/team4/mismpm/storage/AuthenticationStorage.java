package ru.team4.mismpm.storage;

import ru.team4.mismpm.authentication.AuthenticationPair;

import java.util.HashMap;

public class AuthenticationStorage implements IStorage<AuthenticationPair, Long> {

    private final HashMap<AuthenticationPair, Long> map = new HashMap<>();

    @Override
    public void save(AuthenticationPair pair, Long userId) {
        map.put(pair, userId);
    }

    @Override
    public Long get(AuthenticationPair key) {
        return map.containsKey(key) ? map.get(key) : -1;
    }

    @Override
    public void remove(AuthenticationPair key) {
        map.remove(key);
    }

    @Override
    public boolean contains(AuthenticationPair pair) {
        return map.containsKey(pair);
    }
}
