package ru.team4.mismpm.messaging.channel;

import java.util.ArrayList;
import java.util.Collection;

public class ChannelPost {

    private final long id;
    private final long publisherId;
    private final long date;
    private String text;
    private int views;
    private int likes;
    private Collection<Long> comments;

    public ChannelPost(long id, long publisherId, long date, String text) {
        this.id = id;
        this.publisherId = publisherId;
        this.date = date;
        this.text = text;
        comments = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public long getPublisherId() {
        return publisherId;
    }

    public long getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public int getViews() {
        return views;
    }

    public int getLikes() {
        return likes;
    }

    public Collection<Long> getComments() {
        return comments;
    }

    public void addView() {
        views++;
    }

    public void addLike() {
        likes++;
    }
}
