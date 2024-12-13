package ru.team4.mismpm.user;

import java.util.ArrayList;
import java.util.Collection;

public class UserBlacklist {

    private Collection<Long> blacklist;

    public UserBlacklist() {
        blacklist = new ArrayList<>();
    }

    public void add(Long id) {
        blacklist.add(id);
    }

    public void remove(Long id) {
        blacklist.remove(id);
    }

    public boolean isInBlacklist(Long id) {
        return blacklist.contains(id);
    }
}
