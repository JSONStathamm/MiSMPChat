package ru.team4.mismpm.messaging.channel;

public enum ChannelRole {

    SUBSCRIBER("Читатель"),
    MODERATOR("Модератор"),
    ADMINISTRATOR("Администратор");

    private final String prefix;
    ChannelRole(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

}
