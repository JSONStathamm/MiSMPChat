package ru.team4.mismpm.messaging.channel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Channel {

    public static class Builder {

        private final long id;              // Идентификатор
        private final long ownerId;         // Идентификатор владельца
        private String name;                // Отображаемое имя
        private ChannelType type;           // Тип
        private ChannelCategory category;   // Категория
        private String avatarUrl;           // Изображение в списке каналов

        public Builder(long id, long ownerId) {
            this.id = id;
            this.ownerId = ownerId;
            name = "Новый канал";
            type = ChannelType.PUBLIC;
            category = ChannelCategory.UNDEFINED;
            avatarUrl = "https://group4.ru/assets/channel/" + id + "/avatar/default.png";
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder type(ChannelType type) {
            this.type = type;
            return this;
        }

        public Builder category(ChannelCategory category) {
            this.category = category;
            return this;
        }

        public Builder avatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        public Channel build() {
            return new Channel(id, ownerId, name, type, category, avatarUrl);
        }
    }

    private final long id;                          // Идентификатор
    private final long ownerId;                     // Идентификатор владельца
    private String name;                            // Отображаемое имя
    private ChannelType type;                       // Тип
    private ChannelCategory category;               // Категория
    private String avatarUrl;                       // Изображение в списке каналов
    private final Collection<Long> moderators;      // Список модераторов канала
    private final Collection<Long> administrators;  // Список администраторов канала
    private final Collection<Long> subscribers;     // Список подписчиков канала
    private final Collection<Long> posts;           // Список публикаций канала
    private final Collection<Long> blacklisted;     // Список заблокированных пользователей

    protected Channel(long id, long ownerId, String name, ChannelType type, ChannelCategory category, String avatarUrl) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.type = type;
        this.category = category;
        this.avatarUrl = avatarUrl;
        moderators = new ArrayList<>();
        administrators = new ArrayList<>();
        subscribers = new ArrayList<>();
        posts = new ArrayList<>();
        blacklisted = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public ChannelType getType() {
        return type;
    }

    public ChannelCategory getCategory() {
        return category;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Collection<Long> getModerators() {
        return moderators;
    }

    public Collection<Long> getAdministrators() {
        return administrators;
    }

    public Collection<Long> getSubscribers() {
        return subscribers;
    }

    public Collection<Long> getPosts() {
        return posts;
    }

    public Collection<Long> getBlacklisted() {
        return blacklisted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(ChannelType type) {
        this.type = type;
    }

    public void setCategory(ChannelCategory category) {
        this.category = category;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void assignRole(Long userId, ChannelRole role) {
        switch (role) {
            case SUBSCRIBER -> {
                administrators.remove(userId);
                moderators.remove(userId);
            }
            case MODERATOR -> {
                administrators.remove(userId);
                moderators.add(userId);
            }
            case ADMINISTRATOR -> {
                moderators.remove(userId);
                administrators.add(userId);
            }
        }
    }
}
