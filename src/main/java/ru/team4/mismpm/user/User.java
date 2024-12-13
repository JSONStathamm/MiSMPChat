package ru.team4.mismpm.user;

public class User {

    private final long id;
    private final UserData data;
    private final UserSettings settings;
    private final UserBlacklist blacklist;

    public User(long id, UserData data) {
        this.id = id;
        this.data = data;
        settings = new UserSettings();
        blacklist = new UserBlacklist();
    }

    public long getId() {
        return id;
    }

    public UserData getData() {
        return data;
    }

    public UserSettings getSettings() {
        return settings;
    }

    public UserBlacklist getBlacklist() {
        return blacklist;
    }
}
